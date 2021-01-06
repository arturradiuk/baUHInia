package maps.api

class Map {
    val cells = ArrayList<ArrayList<Cell>>()
    val objects = ArrayList<MapObject>()

    fun remove(obj: MapObject){
        iterate(obj) { i,j ->
            cells[i][j].objects = null
            cells[i][j].type = CellType.None
            obj.cells.remove(cells[i][j])
        }
    }

    fun place(obj: MapObject){
        // check for bounds
        iterate(obj) { i, j ->
            cells[i][j].objects = obj
            cells[i][j].type = obj.type
            obj.cells.add(cells[i][j])
        }
        objects.add(obj)
    }

    fun iterate(obj: MapObject, action: (i: Int, j: Int) -> Unit){
        for (i in 0 until obj.width)
            for(j in 0 until obj.height)
                action(i,j)
    }

    companion object{
        @JvmStatic
        fun init(size: Int): Map {
            val map = Map()
            for (i in 0 until size){
                map.cells.add(ArrayList())
                for(j in 0 until size){
                    map.cells[i].add(Cell(CellType.None, i, j))
                }
            }
            return map
        }
    }
}