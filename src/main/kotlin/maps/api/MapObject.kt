package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.util.*
import kotlin.collections.ArrayList

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "guid")
class MapObject (
        val name: String,
        val guid: UUID = UUID.randomUUID(),
        val width: Int,
        val height: Int,
        val allowedTerrains: Array<TerrainType>
    ){
    var cells = ArrayList<Cell>()
}