package maps.api.services

import maps.api.Map
import java.util.*

interface IMapsService {
    fun saveMap(map: Map)
    fun getMap(id: UUID): Map?
    fun deleteMap(map: Map)
    fun generateMap(): Map
    fun emptyMap(): Map
}