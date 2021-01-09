package maps

import com.google.common.collect.ImmutableList

class MapObject(
        points: List<Float>,
        override val name: String,
        override val type: ObjectType
): IMapObject {
    override val points: ImmutableList<Float> = ImmutableList.copyOf(points)

}
