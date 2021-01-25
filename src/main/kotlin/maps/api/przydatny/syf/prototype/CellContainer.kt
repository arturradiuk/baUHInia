package maps.api.przydatny.syf.prototype

class CellContainer(
        val cellSize: Int,
        val cells: List<OsmCell>,
        val top: Int,
        val left: Int,
        val bottom: Int,
        val right: Int
) {
    val factorW = right - left
    val factorH = top - bottom

    fun getCell(x: Double, y: Double) : OsmCell{
        val xM = (x * Program.multiplier).toInt()
        val yM = (y * Program.multiplier).toInt()
        val diffX = xM - left
        val indexW = diffX / cellSize
        val diffY = top - yM
        val indexH = diffY / cellSize
        val lenW = factorH / cellSize
        return cells[indexW * lenW + indexH]
    }
}