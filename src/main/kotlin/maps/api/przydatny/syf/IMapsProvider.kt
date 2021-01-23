package maps.api.przydatny.syf

import java.util.*
import java.util.stream.Stream

interface IMapsProvider {
    fun add(map: Map)
    fun delete(id: UUID)
    fun update(map: Map, id: UUID)
    fun get(id: UUID): Map?
    fun index(id: UUID): Stream<MapInfo>?
}