package maps.api.przydatny.syf

import java.util.*

abstract class AbstractMapsProvider : IMapsProvider {
    protected class MapInfo(
            val name: String,
            val id: UUID,
            val map: Map
    )
}