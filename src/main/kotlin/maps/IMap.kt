package maps

import java.util.*

interface IMap {
    val id: UUID?
    val name: String

    fun getOsm()
    fun getObjects(): List<MapObject>
}