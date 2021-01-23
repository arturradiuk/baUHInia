package maps.api.przydatny.syf

import java.util.*
import maps.api.Map;

// todo err in java.logic.service.MapManager.addMap() (wrong type provided)
//Required type:
//maps.Map
//Provided:
//maps.api.Map

interface IMapsService {
    fun saveMap(map: Map)
    fun getMap(id: UUID): Map?
    fun deleteMap(id: UUID)
    fun updateMap(id:UUID, map: Map);
    // todo getAll(): List<Map>

}