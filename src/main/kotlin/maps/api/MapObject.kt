package maps.api

class MapObject (
    val name: String,
    val type: CellType,
    val width: Int,
    val height: Int
    ){
    var cells = ArrayList<Cell>()
}