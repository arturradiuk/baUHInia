package maps.api

import java.util.*
import kotlin.collections.ArrayList

class MapObject (
    var name: String,
    var type: CellType,
    var width: Int,
    var height: Int,
    val ID: UUID
    ){
    var cells = ArrayList<Cell>()
    override fun toString(): String {
        return "MapObject(name='$name', type=$type, width=$width, height=$height, cells=$cells)"
    }
}