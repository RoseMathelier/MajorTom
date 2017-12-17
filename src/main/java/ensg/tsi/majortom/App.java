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
    	
    	Georeferencer g = new PointGeoreferencer();
    	g.setContext(inputPath, outputPath);
    	g.applyTransfo(g.getContext().getControlPoints(), TypeTransfo.LINEAIRE);
    	
    	System.out.println( "Done!" );

    }
}
