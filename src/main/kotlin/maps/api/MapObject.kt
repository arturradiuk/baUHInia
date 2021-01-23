package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import common.enums.CellType
import common.enums.ObjectType
import java.util.*
import kotlin.collections.ArrayList

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "guid")
class MapObject (
        val name: String,
        val guid: UUID = UUID.randomUUID(),
        val width: Int, // x dim
        val length: Int, // y dim
        val height: Int,
        val type: ObjectType,
    ){


    // dla logiki
    var allowedTerrainType: CellType = CellType.None
    var price: Int = 0
    var heatFactor: Double = 0.0

    // todo to remove
    var cells = ArrayList<Cell>()
}