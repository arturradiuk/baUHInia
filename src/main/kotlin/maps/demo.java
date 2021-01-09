package maps;

import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator;

import java.io.FileInputStream;
import java.util.UUID;

public class demo {

    public static final String PATH = "F:\\root";

    public static void main(String[] args) {
        ExampleClass a = new ExampleClass();
        a.elo();

        var provider = new FilesystemMapsProvider(PATH);
        var map = new Map("ELO", null, null);
        var id = map.getId();
        provider.add(map);
        provider.index(UUID.randomUUID()).forEach(System.out::println);
        provider.delete(id);
        provider.index(UUID.randomUUID()).forEach(System.out::println);

//        try {
//            var fin = new FileInputStream("E:\\STUDIA\\5 SEM\\IO\\map.osm");
//            OsmIterator iterator = new OsmXmlIterator(fin, true);
//            iterator.
//            for(var container : iterator){
//                var node = (OsmNode)container.getEntity();
//                System.out.println(node.getId());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
