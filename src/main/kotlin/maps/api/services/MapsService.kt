package maps.api.services

import database.IMapObject
import ioc.ServiceContainer
import login.LoginService
import maps.api.ITrackable
import maps.api.Map
import maps.api.State
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


class MapsService(
        private val mapsProvider: FilesystemMapsProvider
) : IMapsService {
    var tracked = ArrayList<ITrackable>()
    private val defaultMapSize = 50;

    var mapObjectManager: IMapObject? = ServiceContainer.getMapObjectManager()

    override fun emptyMap(): Map{
        return Map.empty(defaultMapSize);
    }

    override fun generateMap(): Map {
        return Map.fromNoise(defaultMapSize)
    }

    override fun saveMap(map: Map) {
        if(map.state == State.UNCHANGED) return
        setState(map, State.UNCHANGED)
        if(tracked.contains(map))
            mapsProvider.replace(map) // replacing

        else {
//            map.guid = UUID.randomUUID()
            mapsProvider.add(map) // adding new
            tracked.add(map)
        }
    }

    override fun fromTemplate(guid: UUID): Map? {
        val map = mapsProvider.get(guid)
        map?.guid = UUID.randomUUID()

        if (map != null) {
            // read obj from db
            if(mapObjectManager != null) {
                val objects = mapObjectManager?.getMapObjectsForUuids(map.objectsGuidList)
                for(metadata in map.objectsMetadata){
                    metadata.mapObject = objects?.find { x -> x.guid == metadata.mapObjectGuid }!!
                }
            }
            setState(map, State.CREATED)
            saveMap(map)
        }
        return map
    }

    override fun getMap(id: UUID): Map? {
        val map = mapsProvider.get(id) ?: return null
        tracked.add(map)
        setState(map, State.UNCHANGED)

        // read obj from db
        if(mapObjectManager != null) {
            val objects = mapObjectManager?.getMapObjectsForUuids(map.objectsGuidList)
            for(metadata in map.objectsMetadata){
                metadata.mapObject = objects?.find { x -> x.guid == metadata.mapObjectGuid }!!
            }
        }
        return map
    }

    override fun deleteMap(map: Map) {
        if(!tracked.contains(map)) return
        mapsProvider.delete(map.guid)
        setState(map, State.DELETED)
    }

    private fun setState(map: Map, state: State){
        val prop = map::class.memberProperties.find { it.name == "state" }!!
        prop.let {
            it.isAccessible = true
            it as KMutableProperty<*>
            it.setter.call(map, state)
        }
    }

    companion object{
        private var instance: MapsService? = null


        fun getInstance(): MapsService {
            if (instance == null) {
                instance = MapsService(FilesystemMapsProvider(".\\resources\\maps"))
            }
            return instance!!
        }
    }
}