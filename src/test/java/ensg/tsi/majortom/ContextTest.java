package ensg.tsi.majortom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class ContextTest {
	

	@Test
	public void testAddGCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		ControlPoint pt = new ControlPoint(basicCoord, groundCoord, geometryFactory);
		
		//Test
		context.addGCP(pt);
		assertNotNull(context.getControlPoints());
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(0), 2, 0.001);
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(1), 3, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(0), 4, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(1), 5, 0.001);

	}
	
	@Test
	public void testMoveGCP() {

		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		ControlPoint pt = new ControlPoint(basicCoord, groundCoord, geometryFactory);
		context.addGCP(pt);
		
		//Test
		context.moveGCP(pt, new Coordinate(6,7), new Coordinate(8,9));
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(0), 6, 0.001);
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(1), 7, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(0), 8, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(1), 9, 0.001);
	}
	
	@Test
	public void testDeleteGCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		ControlPoint pt = new ControlPoint(basicCoord, groundCoord, geometryFactory);
		context.addGCP(pt);
		
		//Test
		context.deleteGCP(pt);
		assertTrue(context.getControlPoints().isEmpty());
	}
	
	@Test
	public void testAddCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		CheckPoint pt = new CheckPoint(basicCoord, groundCoord, geometryFactory);
		
		//Test
		context.addCP(pt);
		assertNotNull(context.getCheckPoints());
		assertEquals(context.getCheckPoint(0).getBasicCoord().getOrdinate(0), 2, 0.001);
		assertEquals(context.getCheckPoint(0).getBasicCoord().getOrdinate(1), 3, 0.001);
		assertEquals(context.getCheckPoint(0).getGroundCoord().getOrdinate(0), 4, 0.001);
		assertEquals(context.getCheckPoint(0).getGroundCoord().getOrdinate(1), 5, 0.001);
	}
	
	@Test
	public void testMoveCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		CheckPoint pt = new CheckPoint(basicCoord, groundCoord, geometryFactory);
		context.addCP(pt);
		
		//Test
		context.moveCP(pt, new Coordinate(6,7), new Coordinate(8,9));
		assertEquals(context.getCheckPoint(0).getBasicCoord().getOrdinate(0), 6, 0.001);
		assertEquals(context.getCheckPoint(0).getBasicCoord().getOrdinate(1), 7, 0.001);
		assertEquals(context.getCheckPoint(0).getGroundCoord().getOrdinate(0), 8, 0.001);
		assertEquals(context.getCheckPoint(0).getGroundCoord().getOrdinate(1), 9, 0.001);
	}

	@Test
	public void testDeleteCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		CheckPoint pt = new CheckPoint(basicCoord, groundCoord, geometryFactory);
		context.addCP(pt);
		
		//Test
		context.deleteCP(pt);
		assertTrue(context.getCheckPoints().isEmpty());
	}
	
	@Test
	public void testSetInputPath(){
		Context context = new Context();
		String inputPath = "input/input.shp";
		context.setInputPath(inputPath);
		assertEquals(context.getInputPath(), inputPath);
	}
	
	@Test
	public void testGetInputPath(){
		Context context = new Context();
		String inputPath = "input/input.shp";
		context.setInputPath(inputPath);
		assertEquals(context.getInputPath(), inputPath);
	}
	
	@Test
	public void testSetOutputPath(){
		Context context = new Context();
		String outputPath = "output";
		context.setOutputPath(outputPath);
		assertEquals(context.getOutputPath(), outputPath);
	}
	
	@Test
	public void testGetOutputPath(){
		Context context = new Context();
		String outputPath = "output";
		context.setOutputPath(outputPath);
		assertEquals(context.getOutputPath(), outputPath);
	}
	
	@Test
	public void testSetOutputName(){
		Context context = new Context();
		String outputName = "outputName";
		context.setOutputName(outputName);
		assertEquals(context.getOutputName(), outputName);
	}
	
	@Test
	public void testGetOutputName(){
		Context context = new Context();
		String outputName = "outputName";
		context.setOutputName(outputName);
		assertEquals(context.getOutputName(), outputName);
	}

}
