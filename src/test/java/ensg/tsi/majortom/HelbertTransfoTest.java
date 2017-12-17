package ensg.tsi.majortom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.vividsolutions.jts.geom.Coordinate;

public class HelbertTransfoTest {

	@Test
	public void testSetTransfoFromGCP() {
		
		Transformation transfo = new LinearTransfo();
		
		PointConnu pt1 = Mockito.mock(PointConnu.class);
		Mockito.when(pt1.getBasicCoord()).thenReturn(new Coordinate(1,1,0));
		Mockito.when(pt1.getGroundCoord()).thenReturn(new Coordinate(2,3,4));
		
		PointConnu pt2 = Mockito.mock(PointConnu.class);
		Mockito.when(pt2.getBasicCoord()).thenReturn(new Coordinate(-1,-1,0));
		Mockito.when(pt2.getGroundCoord()).thenReturn(new Coordinate(1,0,3));
		
		PointConnu pt3 = Mockito.mock(PointConnu.class);
		Mockito.when(pt3.getBasicCoord()).thenReturn(new Coordinate(3,3,0));
		Mockito.when(pt3.getGroundCoord()).thenReturn(new Coordinate(3,3,2));
		
		List<PointConnu> GCPs = new ArrayList<PointConnu>();
		GCPs.add(pt1);
		GCPs.add(pt2);
		GCPs.add(pt3);
		
		transfo.setTransfoFromGCP(GCPs);
		
		assertNotNull(transfo.getParam());
		assertEquals(transfo.getParam().getValues().get(0), 1.0, 0.001);
		assertEquals(transfo.getParam().getValues().get(1), 1.0, 0.001);
		assertEquals(transfo.getParam().getValues().get(2), 3.0, 0.001);
	}

}
