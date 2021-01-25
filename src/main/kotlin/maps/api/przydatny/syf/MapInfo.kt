package maps.api.przydatny.syf

import java.nio.file.Path
import java.util.*

data class MapInfo(
        val name: String,
        val id: UUID,
        val path: Path?,
)

