package maps.prototype;

import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.EntityContainer;
import de.topobyte.osm4j.core.model.iface.OsmTag;
import de.topobyte.osm4j.core.model.impl.Way;
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Program {
    private static final String OSM = "F:\\map.osm";
    public static final int multiplier = 1_000_00;
    private static final int cellSize = 20;

    public static void main(String[] args) {
        var map = getAll();
        var cells = getCells(map);
        var cell = cells.getCell( 19.43730, 51.80520);
        var ways = map.getContainers().stream().filter(x -> x.getEntity() instanceof Way).collect(Collectors.toList());
        for (int i = 0; i < ways.size(); i++) {
            var way = ways.get(i);
            var tags = new ArrayList<OsmTag>();
            for (int j = 0; j < way.getEntity().getNumberOfTags(); j++) {
                tags.add(way.getEntity().getTag(j));
            }
            if(tags.stream().anyMatch(x -> x.getKey().equals("highway"))){

            }
        }
//        resolveTypes(map, cells);
    }

    private static void resolveTypes(OsmMap map, CellContainer cells) {

    }

    public static CellContainer getCells(OsmMap map){
        var cells = new ArrayList<OsmCell>();
        int left = (int)(map.getBounds().getLeft() * multiplier);
        int right = (int)(map.getBounds().getRight() * multiplier);
        int top = (int)(map.getBounds().getTop() * multiplier);
        int bottom = (int)(map.getBounds().getBottom() * multiplier);

        for (int aLeft = left; aLeft < right; aLeft+=cellSize) {
            for (int aTop = top; aTop > bottom; aTop-=cellSize) {
                int cRight = aLeft + cellSize;
                int cBottom = aTop - cellSize;
                var cell = new OsmCell(aTop, aLeft, cBottom, cRight);
                cells.add(cell);
            }
        }

        return new CellContainer(cellSize, cells, top, left, bottom, right);
    }

    public static OsmMap getAll(){
        try {
            var fin = new FileInputStream(OSM);
            OsmIterator iterator = new OsmXmlIterator(fin, true);
            var list = new ArrayList<EntityContainer>();
            for(var container : iterator){
                list.add(container);
            }
            return new OsmMap(iterator.getBounds(), list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
