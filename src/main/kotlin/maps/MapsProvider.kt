package maps

import java.util.*
import kotlin.collections.ArrayList

class MapsProvider() : IMapsProvider {
    override val maps: ArrayList<Map>
        get() {
            return maps
        }

    override fun add(map: Map) {
        maps.add(map)
    }

    override fun delete(id: UUID) {
        maps.removeAt(index(id))
    }

    override fun delete(index: Int) {
        maps.removeAt(index)
    }

    override fun update(map: Map, id: UUID) {
        maps[index(id)] = map
    }

    override fun update(map: Map, index: Int) {
        maps[index] = map
    }

    override fun get(id: UUID): Map {
        return maps[index(id)]
    }

    override fun index(id: UUID): Int {
        return maps.indexOf(maps.find { x -> x.id == id })
    }

    override fun index(map: Map): Int{
        return maps.indexOf(maps.find{ x -> x.id == map.id })
    }

}