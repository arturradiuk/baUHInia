package maps.api

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnore
import common.enums.CellType
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Map constructor() : ITrackable() {
    // by aradiuk
    var created: DateTime? = null;
    var modified: DateTime? = null;
    var userId: Int? = null;
    //

    constructor(size: Int, cellInitializer: (i: Int, j: Int) -> CellType) : this() {
        this.size = size
        this.cells = Array(size) { i ->
            Array(size) { j ->
                Cell(i, j, cellInitializer(i, j))
            }
        }
    }

    @JsonIgnore
    val objects = ArrayList<MapObject>()
    val objectsGuidList = ArrayList<UUID>()
    var size: Int = 0
        private set
    var guid: UUID
    var name: String? = null
    private lateinit var cells: Array<Array<Cell>> // is lateinit safe here ?
    var objectsMetadata = ArrayList<MapObjectMetadata>()

    init {
//        guid = UUID.fromString("00000000-0000-0000-0000-000000000000")
        guid = UUID.randomUUID()
    }

    operator fun get(i: Int, j: Int): Cell {
        return cells[i][j]
    }

    fun remove(obj: MapObject) {
        iterate(obj) { i, j ->
            cells[i][j].placedObjectMetadata = null
        }
        val metadata = objectsMetadata.find { x -> x.mapObjectGuid == obj.guid }
        objectsMetadata.remove(metadata)
        objects.remove(obj)
        objectsGuidList.remove(obj.guid)
        state = State.MODIFIED
    }

    fun place(x: Int, y: Int, obj: MapObject) {
        if (!validate(x, y, obj)) throw Exception("Validation failed") // todo more detailed msg
        // todo check for bounds
        val metadata = MapObjectMetadata(obj.guid,x,y, obj)
        iterate(obj) { i, j ->
            cells[x+i][y+j].placedObjectMetadata = metadata
            metadata.cells.add(cells[x+i][y+j])
        }
        objects.add(obj)
        objectsMetadata.add(metadata)
        objectsGuidList.add(obj.guid)
        state = State.MODIFIED
    }

    fun validate(x: Int, y: Int, obj: MapObject): Boolean {
        for (i in 0 until obj.width)
            for (j in 0 until obj.length) {
                val cell = cells[x + i][y + j]
                when {
                    cell.lockedByAdmin -> return false
                    cell.placedObjectMetadata != null -> return false
                    cell.type != obj.allowedTerrainType -> return false
                }
            }
        return true
    }

    private fun iterate(obj: MapObject, action: (i: Int, j: Int) -> Unit) {
        for (i in 0 until obj.width)
            for (j in 0 until obj.length)
                action(i, j)
    }

    fun iterate(action: (cell: Cell, i: Int, j: Int) -> Unit) {
        for (i in 0 until size)
            for (j in 0 until size)
                action(this[i, j], i, j)
    }


    companion object {
        private const val GRID_SIZE = 20

        @JvmStatic
        fun empty(size: Int): Map {
            val map = Map(size) { _, _ ->
                CellType.None
            }
            return map
        }

        @JvmStatic
        fun fromNoise(size: Int): Map {
            val noise = Noise()
            val map = Map(size){
                i, j ->
                val factor = floor((noise.turbulence(i.toDouble() / GRID_SIZE, j.toDouble() / GRID_SIZE, 0.0, size.toDouble()) + 1) * 127).toInt()
                when{
                    factor < 85 -> CellType.Road
                    factor < 125 -> CellType.Concrete
                    else -> CellType.Green
                }
            }

            return map
        }
    }
}