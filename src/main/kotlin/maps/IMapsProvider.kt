package maps

import java.nio.file.Path
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList

interface IMapsProvider {
    fun add(map: Map)
    fun delete(id: UUID)
    fun update(map: Map, id: UUID)
    fun get(id: UUID): Map?
    fun index(id: UUID): Stream<MapInfo>?
}