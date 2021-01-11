package maps.api.services

import maps.api.Map
import maps.api.MapInfo
import java.util.*
import java.util.stream.Stream

interface IFilesystemMapsProvider {
    fun add(map: Map)
    fun replace(map: Map)
    fun delete(id: UUID)
    fun get(id: UUID): Map?
    fun index(id: UUID): Stream<MapInfo>?
}