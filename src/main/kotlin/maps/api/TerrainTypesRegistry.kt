package maps.api


class TerrainTypesRegistry {
    var types = HashMap<String, TerrainType>()

    fun register(terrain: TerrainType){
        types[terrain.name] = terrain
    }

    operator fun get(name: String): TerrainType? {
        return types[name]
    }
}