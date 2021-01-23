package maps.api.przydatny.syf

import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Stream

class FilesystemMapsProvider(path: String) : AbstractMapsProvider() {

    private val root: Path = Paths.get(path)
    private val regex = Regex("^.+\\\\(.+)-([a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})\\.osm")
    private val tracked = ArrayList<MapInfo>();

    override fun add(map: Map) {
        val info = MapInfo(map.name, UUID.randomUUID(), map)
        val filename = "${map.name}-${info.id}.osm"
        val file = File(root.toString(), filename)
        file.createNewFile()
        tracked.add(info)
    }

    override fun delete(id: UUID) {
        val mapInfo = find(id)
        val file = File(mapInfo.path.toString())
        file.delete()
    }

    private fun find(id: UUID): maps.api.przydatny.syf.MapInfo {
        return Files.walk(root)
                .filter { x ->
                    val result = regex.matchEntire(x.toString()) ?: return@filter false
                    val uuid = UUID.fromString(result.groupValues[2])
                    uuid == id
                }.map(this::mapPathToInfo)
                .findFirst()
                .orElse(null);
    }

    override fun update(map: Map, id: UUID) {
        TODO()
    }

    override fun get(id: UUID): Map? {
        val info = find(id)
        val fin = FileInputStream(info.path!!.toFile())
        val stream = ByteArrayInputStream(fin.readAllBytes());
        var map = Map(info.name, info.id, stream);
        val record = MapInfo(info.name, info.id, map)
        tracked.add(record);
        return map
    }

    private fun mapPathToInfo(path: Path): maps.api.przydatny.syf.MapInfo {
        val result = regex.matchEntire(path.toString()) ?: throw Exception()
        return MapInfo(
                result.groupValues[1],
                UUID.fromString(result.groupValues[2]),
                path,
        )
    }

    override fun index(id: UUID): Stream<maps.api.przydatny.syf.MapInfo>? {
        val regex = Regex("^.+\\\\(.+)-([a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})\\.osm")
        return Files.walk(root)
                .filter { x -> regex.matches(x.toString()) }
                .map(this::mapPathToInfo)
    }
}