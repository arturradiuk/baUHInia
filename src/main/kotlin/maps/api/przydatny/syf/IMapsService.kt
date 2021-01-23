package maps.api.przydatny.syf

import java.util.*

interface IMapsService {
    fun saveMap(map: Map)
    fun getMap(id: UUID): Map?
    fun deleteMap(id: UUID)

}