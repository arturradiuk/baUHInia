package maps

import java.util.*
import kotlin.collections.ArrayList

interface IMapsProvider {
    val maps: ArrayList<Map>

    fun add(map: Map)
    fun delete(id: UUID)
    fun delete(index: Int)
    fun update(map: Map, id: UUID)
    fun update(map: Map, index: Int)
    fun get(id: UUID): Map
    fun index(id: UUID): Int
    fun index(map: Map): Int
}