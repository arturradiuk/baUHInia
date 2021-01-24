package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.util.*

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class MapObjectMetadata (
        val mapObjectGuid: UUID,
        val x: Int,
        val y: Int,
        mapObject: MapObject
        ){
    @JsonIgnore
    var mapObject: MapObject = mapObject

    // todo to remove
    var cells = ArrayList<Cell>()
}