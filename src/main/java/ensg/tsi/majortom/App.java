package ensg.tsi.majortom;

import java.io.IOException;

import org.geotools.feature.SchemaException;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Hello world!
 *
 */
public class App 
{	
    
    public static void main( String[] args ) throws IOException, SchemaException{
   
    	System.out.println( "Hello World!" );
    	TestGeoTools.writeShp();
    	//TestGeoTools.readShp();
    	System.out.println( "Done!" );

    }
}
