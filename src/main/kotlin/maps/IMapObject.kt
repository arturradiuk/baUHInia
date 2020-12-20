package maps

enum class ObjectType {
    HIGHWAY, TREE, CROSSING
}

interface IMapObject {
    val points: List<Float>
    val name:String
    val type: ObjectType
}