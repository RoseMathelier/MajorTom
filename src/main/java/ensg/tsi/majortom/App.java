package ensg.tsi.majortom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geotools.feature.SchemaException;
import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

/**
 * An easy, complete example to test the application without any data !
 *
 */
public class App 
{	
    
    public static void main( String[] args ) throws IOException, SchemaException{
   
    	System.out.println( "Hello World!" );
    	
    	GeometryFactory geomFactory = JTSFactoryFinder.getGeometryFactory();
    	
    	//Our basic coordinates
    	Coordinate c1 = new Coordinate(0,0,0);
    	Coordinate c2 = new Coordinate(0,1,0);
    	Coordinate c3 = new Coordinate(1,0,0);
    	Coordinate c4 = new Coordinate(1,1,0);
    	
    	//Set the context of transformation
    	Context c = new Context("inputs", "outputs", "testPointGeoref");
    	Coordinate[] GCPBCoord1 = new Coordinate[] {new Coordinate(0,0,0)};
    	Coordinate[] GCPGCoord1 = new Coordinate[] {new Coordinate(2,1,0)};
    	Coordinate[] GCPBCoord2 = new Coordinate[] {new Coordinate(1,1,0)};
    	Coordinate[] GCPGCoord2 = new Coordinate[] {new Coordinate(3.5,2.5,0)};
    	CoordinateSequence GCPBasicCoord1 = new CoordinateArraySequence(GCPBCoord1);
    	CoordinateSequence GCPGroundCoord1 = new CoordinateArraySequence(GCPGCoord1);
    	CoordinateSequence GCPBasicCoord2 = new CoordinateArraySequence(GCPBCoord2);
    	CoordinateSequence GCPGroundCoord2 = new CoordinateArraySequence(GCPGCoord2);
    	ControlPoint GCP1 = new ControlPoint(GCPBasicCoord1,GCPGroundCoord1, geomFactory);
    	ControlPoint GCP2 = new ControlPoint(GCPBasicCoord2,GCPGroundCoord2, geomFactory);
    	c.addGCP(GCP1);
    	c.addGCP(GCP2);
    	Coordinate[] CPBCoord1 = new Coordinate[] {new Coordinate(1,0,0)};
    	Coordinate[] CPGCoord1 = new Coordinate[] {new Coordinate(3,1,0)};
    	Coordinate[] CPBCoord2 = new Coordinate[] {new Coordinate(1,1,0)};
    	Coordinate[] CPGCoord2 = new Coordinate[] {new Coordinate(3.5,3.5,0)};
    	CoordinateSequence CPBasicCoord1 = new CoordinateArraySequence(CPBCoord1);
    	CoordinateSequence CPGroundCoord1 = new CoordinateArraySequence(CPGCoord1);
    	CoordinateSequence CPBasicCoord2 = new CoordinateArraySequence(CPBCoord2);
    	CoordinateSequence CPGroundCoord2 = new CoordinateArraySequence(CPGCoord2);
    	CheckPoint CP1 = new CheckPoint(CPBasicCoord1,CPGroundCoord1, geomFactory);
    	CheckPoint CP2 = new CheckPoint(CPBasicCoord2,CPGroundCoord2, geomFactory);
    	c.addCP(CP1);
    	c.addCP(CP2);
    	
    	//Write the test point shapefile in the input directory.
    	Coordinate[] pc1 = new Coordinate[] {c1};
    	Coordinate[] pc2 = new Coordinate[] {c2};
    	Coordinate[] pc3 = new Coordinate[] {c3};
    	Coordinate[] pc4 = new Coordinate[] {c4};
    	List<Coordinate[]> pcoords = new ArrayList<Coordinate[]>();
    	Collections.addAll(pcoords, pc1, pc2, pc3, pc4);
    	ShapefileWriter pointWriter = new ShapefilePointWriter();
    	pointWriter.writeShp(pcoords, "inputs", "testPoint");
    	
    	//Write the test line shapefile in the input directory.
    	Coordinate[] lc1 = new Coordinate[] {c1, c2};
    	Coordinate[] lc2 = new Coordinate[] {c3, c4};
    	List<Coordinate[]> lcoords = new ArrayList<Coordinate[]>();
    	Collections.addAll(lcoords,  lc1, lc2);
    	ShapefileWriter lineWriter = new ShapefileLineWriter();
    	lineWriter.writeShp(lcoords, "inputs", "testLine");
    	
    	//Georeferencer g = new PointGeoreferencer();
    	Georeferencer g = new LineGeoreferencer();
    	//Georeferencer g = new PolygonGeoreferencer();
    	g.setContext(c);
    	g.applyTransfo(TypeTransfo.LINEAIRE);
    	//g.applyTransfo(TypeTransfo.HELMERT);
    	
    	System.out.println( "Done!" );

    }
}
