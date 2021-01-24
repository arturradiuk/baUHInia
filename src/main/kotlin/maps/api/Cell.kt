package maps.api

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import common.enums.CellType

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
// mandatory properties
class Cell(
        val x: Int,
        val y: Int,
        var type: CellType
){

    var placedObject: MapObject? = null

    // dla logiki
    var lockedByAdmin: Boolean = false

    // dla symulacji
    var heat: Double? = null
}