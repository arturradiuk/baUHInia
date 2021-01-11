package maps.api

import java.util.*
import kotlin.collections.ArrayList

// todo: ograniczenia na budynkach
// todo: walidacja komórek
// todo: generowanie
class Map constructor(
       val name: String,
       val guid: UUID
) : ITrackable() {

    val cells = ArrayList<ArrayList<Cell>>()
    val objects = ArrayList<MapObject>()
    var terrainTypes: TerrainTypesRegistry? = null


    operator fun get(i: Int, j: Int): Cell {
        return cells[i][j]
    }

    fun remove(obj: MapObject){
        iterate(obj) { i,j ->
            cells[i][j].objects = null
            obj.cells.remove(cells[i][j])
        }
        state = State.MODIFIED
    }

    fun place(obj: MapObject){
        // check for bounds
        iterate(obj) { i, j ->
            cells[i][j].objects = obj
            obj.cells.add(cells[i][j])
        }
        objects.add(obj)
        state = State.MODIFIED
    }

    fun iterate(obj: MapObject, action: (i: Int, j: Int) -> Unit){
        for (i in 0 until obj.width)
            for(j in 0 until obj.height)
                action(i,j)
    }

    companion object{
        @JvmStatic
        fun init(name: String, size: Int, registry: TerrainTypesRegistry): Map {
            val map = Map(name, UUID.randomUUID())

            for (i in 0 until size){
                map.cells.add(ArrayList())
                for(j in 0 until size){
                    val ran = ClassicNoise.trubulence((i / size).toDouble(),
                            (j / size).toDouble(),
                            0.0,
                            size.toDouble())
                    val type: TerrainType =
                        if(ran > 0) registry.types["Beton"]!!;
                        else registry.types["Trawa"]!!;
                    map.cells[i].add(Cell(i, j, type))
                }
            }
            map.terrainTypes = registry
            return map
        }
    }
}