package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import common.enums.CellType
import common.enums.ObjectType
import java.util.*
import kotlin.collections.ArrayList

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "guid")
class MapObject (
        var name: String,
        var guid: UUID = UUID.randomUUID(),
        var width: Int, // x dim
        var length: Int, // y dim
        var height: Int,
        var type: ObjectType
    ){


    // dla logiki
    var allowedTerrainType: CellType = CellType.None
    var price: Int = 0
    var heatFactor: Double = 0.0

    // todo to remove
    var cells = ArrayList<Cell>()
    override fun toString(): String {
        return "MapObject(name='$name', type=$type, width=$width, height=$height, cells=$cells)"
    }
}