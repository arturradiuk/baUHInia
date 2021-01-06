package maps.api

class Cell(
        var type: CellType,
        val x: Int,
        val y: Int
){
    var objects: MapObject? = null
}