package maps

import java.nio.file.Path
import java.util.*

data class MapInfo(
        val name: String,
        val id: UUID,
        val path: Path?,
)

