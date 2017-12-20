package ensg.tsi.majortom;

import java.io.IOException;

import org.geotools.feature.SchemaException;

/**
 * Hello world!
 *
 */
public class App 
{	
    
    public static void main( String[] args ) throws IOException, SchemaException{
   
    	System.out.println( "Hello World!" );
    	
    	String inputPath = "";
    	String outputPath = "";
    	String outputName = "";
    	
    	Georeferencer g = new PointGeoreferencer();
    	g.setContext(inputPath, outputPath, outputName);
    	g.applyTransfo(TypeTransfo.LINEAIRE);
    	
    	System.out.println( "Done!" );

    }
}
