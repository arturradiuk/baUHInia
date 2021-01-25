package maps.api.utils

import java.util.*


fun isEmptyGuid(guid: UUID): Boolean{
    return guid == UUID.fromString("00000000-0000-0000-0000-000000000000")
}