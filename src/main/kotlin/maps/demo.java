package maps;

import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.OsmNode;
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class demo {
    public static void main(String[] args) {

        try {
            var fin = new FileInputStream("E:\\STUDIA\\5 SEM\\IO\\map.osm");
            OsmIterator iterator = new OsmXmlIterator(fin, true);
            for(var container : iterator){
                var node = (OsmNode)container.getEntity();
                System.out.println(node.getId());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
