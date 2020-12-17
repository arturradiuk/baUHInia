package maps

import com.google.common.collect.ImmutableList

class MapObject(
        points: List<Float>,
        val name: String
) {
    val points: ImmutableList<Float> = ImmutableList.copyOf(points)

}
