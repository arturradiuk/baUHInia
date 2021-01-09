package maps.prototype

import de.topobyte.osm4j.core.model.iface.EntityContainer
import de.topobyte.osm4j.core.model.iface.OsmBounds

private class OsmMap(
        val bounds:OsmBounds,
        val containers: List<EntityContainer>
)