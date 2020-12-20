package maps

import java.util.*

class MapsService(
        private val mapsProvider: IMapsProvider
): IMapsService {
    override fun saveMap(map: Map) {
        if (map.id == null){
            mapsProvider.add(map)
        }
        else{
            mapsProvider.update(map, map.id)
        }
    }

    override fun getMap(id: UUID): Map {
        return mapsProvider.get(id)
    }

    override fun deleteMap(id: UUID) {
        mapsProvider.delete(id)
    }

}