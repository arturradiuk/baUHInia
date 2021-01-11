package maps.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import maps.api.Map
import maps.api.MapInfo
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Stream

class FilesystemMapsProvider(rootPath: String) : IFilesystemMapsProvider {

    private val root: Path = Paths.get(rootPath)
//    private val regex = Regex("^.+\\\\(.+)-([a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})\\.osm")
    private val regex = Regex("^.+\\\\([a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})\\.json")
    private val serializer: ObjectMapper = ObjectMapper().apply {
        registerModule(KotlinModule())
    }

    override fun add(map: Map) {
        val filename = "${map.guid}.json"
        val file = File(root.toString(), filename)
        file.createNewFile()
        serializer.writeValue(file, map)
    }

    override fun replace(map: Map){
        val oldMap = find(map.guid)
        Files.delete(oldMap.path!!)
        add(map)
    }

    override fun delete(id: UUID) {
        val mapInfo = find(id)
        val file = File(mapInfo.path.toString())
        file.delete()
    }

    private fun find(id: UUID): MapInfo {
        return Files.walk(root)
                .filter { x ->
                    val result = regex.matchEntire(x.toString()) ?: return@filter false
                    val guid = UUID.fromString(result.groupValues[1])
                    guid == id
                }.map(this::mapPathToInfo)
                .findFirst()
                .orElse(null);
    }

    override fun get(id: UUID): Map? {
        val info = find(id)
        val file = File(info.path.toString())
        return serializer.readValue(file, Map::class.java)
//        val fin = FileInputStream(info.path!!.toFile())
//        val stream = ByteArrayInputStream(fin.readAllBytes());
//        var map = Map(info.name, info.id, stream);
//        val record = MapInfo(info.name, info.id, map)
//        tracked.add(record);
//        return map
    }

    private fun mapPathToInfo(path: Path): MapInfo {
        val result = regex.matchEntire(path.toString()) ?: throw Exception()
        return MapInfo(
                result.groupValues[1],
                UUID.fromString(result.groupValues[2]),
                path,
        )
    }

    override fun index(id: UUID): Stream<MapInfo>? {
//        val regex = Regex("^.+\\\\(.+)-([a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})\\.osm")
        return Files.walk(root)
                .filter { x -> regex.matches(x.toString()) }
                .map(this::mapPathToInfo)
    }
}