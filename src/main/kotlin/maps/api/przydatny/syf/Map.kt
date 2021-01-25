package maps.api.przydatny.syf

import de.topobyte.osm4j.core.model.iface.EntityContainer
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class Map(
        override val name: String,
        override val id: UUID,
        stream: InputStream
) : IMap {

    private val entities = ArrayList<EntityContainer>()

    init{
        val iterator = OsmXmlIterator(stream, true)
        iterator.forEach { entities += it }
    }

    var state: MapState = MapState.UNCHANGED
    private set

    lateinit var objectList: List<MapObject>


    override fun getOsm() {
        TODO("Not yet implemented")
    }

    override fun getObjects(): List<MapObject> {
        return Collections.unmodifiableList(objectList)
    }

    fun addObject(obj: MapObject){

    }

    fun removeObject(obj: MapObject){

    }

    fun validate(){

    }
}