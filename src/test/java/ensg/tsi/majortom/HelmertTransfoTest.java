package ensg.tsi.majortom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.vividsolutions.jts.geom.Coordinate;

public class HelmertTransfoTest {

	@Test
	public void testSetTransfoFromGCP() {
		
		Transformation transfo = new HelmertTransfo();
		
		ControlPoint pt1 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt1.getBasicCoord()).thenReturn(new Coordinate(1,1,0));
		Mockito.when(pt1.getGroundCoord()).thenReturn(new Coordinate(7,-9,0));
		
		ControlPoint pt2 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt2.getBasicCoord()).thenReturn(new Coordinate(-1,-1,0));
		Mockito.when(pt2.getGroundCoord()).thenReturn(new Coordinate(-4,-3,0));
		
		ControlPoint pt3 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt3.getBasicCoord()).thenReturn(new Coordinate(3,3,0));
		Mockito.when(pt3.getGroundCoord()).thenReturn(new Coordinate(6,10,0));
		
		ControlPoint pt4 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt4.getBasicCoord()).thenReturn(new Coordinate(1,-4,0));
		Mockito.when(pt4.getGroundCoord()).thenReturn(new Coordinate(2,7,0));
		
		List<ControlPoint> GCPs = new ArrayList<ControlPoint>();
		GCPs.add(pt1);
		GCPs.add(pt2);
		GCPs.add(pt3);
		GCPs.add(pt4);
		transfo.setControlPoints(GCPs);
		
		transfo.setTransfoFromGCP();
		
		List<Double> paramValues = transfo.getParam().getValues();
				
		assertNotNull(transfo.getParam());
		assertEquals(paramValues.get(0), 2.24, 0.01);
		assertEquals(paramValues.get(1), 1.31, 0.01);
		assertEquals(paramValues.get(2), 0.0, 0.01);
		assertEquals(paramValues.get(3), 0.0, 0.01);
		assertEquals(paramValues.get(4), 0.0, 0.01);
		assertEquals(paramValues.get(5), -0.06, 0.01);
		assertEquals(paramValues.get(6), -0.5, 0.01);
	}
	
	@Test
	public void testGetNbMinGCP(){
		Transformation transfo = new HelmertTransfo();
		assertEquals(transfo.getNbMinGCP(), 3);
	}

}
