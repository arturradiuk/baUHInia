package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class Cell(
        val x: Int,
        val y: Int,
        val terrainType: TerrainType?
){
    var objects: MapObject? = null
}