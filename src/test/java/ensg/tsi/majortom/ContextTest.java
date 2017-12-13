package ensg.tsi.majortom;

import static org.junit.Assert.*;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class ContextTest {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAddGCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		PointConnu pt = new PointConnu(basicCoord, groundCoord, geometryFactory);
		
		//Test
		context.addGCP(pt);
		assertNotNull(context.getControlPoints());
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(0), 2, 0.001);
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(1), 3, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(0), 4, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(1), 5, 0.001);

	}
	
//	@Test
//	public void testMoveGCP() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testDeleteGCP() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testAddCP() {
		
		//Set up
		Context context = new Context();
		Coordinate bCoord[] = {new Coordinate(2,3)};
		CoordinateSequence basicCoord = new CoordinateArraySequence(bCoord);
		Coordinate gCoord[] = {new Coordinate(4,5)};
		CoordinateSequence groundCoord = new CoordinateArraySequence(gCoord);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		PointConnu pt = new PointConnu(basicCoord, groundCoord, geometryFactory);
		
		//Test
		context.addGCP(pt);
		assertNotNull(context.getControlPoints());
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(0), 2, 0.001);
		assertEquals(context.getControlPoint(0).getBasicCoord().getOrdinate(1), 3, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(0), 4, 0.001);
		assertEquals(context.getControlPoint(0).getGroundCoord().getOrdinate(1), 5, 0.001);
	}
	
//	@Test
//	public void testMoveCP() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testDeleteCP() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testSetLayer(){
//		fail("Not yet implemented");
//	}

}
