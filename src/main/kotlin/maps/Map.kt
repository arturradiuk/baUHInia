package maps

import java.util.*

class Map(
        override val id: UUID?,
        override val name: String
): IMap {

    lateinit var objectList: List<MapObject>

    override fun getOsm() {
        TODO("Not yet implemented")
    }

    override fun getObjects(): List<MapObject> {
        return Collections.unmodifiableList(objectList)
    }

    fun addObject(obj: MapObject){

    }

    fun removeObject(obj: MapObject){

    }

    fun validate(){
        
    }
}