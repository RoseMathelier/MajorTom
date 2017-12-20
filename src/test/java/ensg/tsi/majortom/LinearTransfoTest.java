package ensg.tsi.majortom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.vividsolutions.jts.geom.Coordinate;

@RunWith(MockitoJUnitRunner.class)
public class LinearTransfoTest {
	
	@Test
	public void testSetTransfoFromGCP() {
		
		Transformation transfo = new LinearTransfo();
		
		ControlPoint pt1 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt1.getBasicCoord()).thenReturn(new Coordinate(1,1,0));
		Mockito.when(pt1.getGroundCoord()).thenReturn(new Coordinate(2,3,4));
		
		ControlPoint pt2 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt2.getBasicCoord()).thenReturn(new Coordinate(-1,-1,0));
		Mockito.when(pt2.getGroundCoord()).thenReturn(new Coordinate(1,0,3));
		
		ControlPoint pt3 = Mockito.mock(ControlPoint.class);
		Mockito.when(pt3.getBasicCoord()).thenReturn(new Coordinate(3,3,0));
		Mockito.when(pt3.getGroundCoord()).thenReturn(new Coordinate(3,3,2));
		
		List<ControlPoint> GCPs = new ArrayList<ControlPoint>();
		GCPs.add(pt1);
		GCPs.add(pt2);
		GCPs.add(pt3);
		transfo.setControlPoints(GCPs);
		
		transfo.setTransfoFromGCP();
		
		assertNotNull(transfo.getParam());
		assertEquals(transfo.getParam().getValues().get(0), 1.0, 0.001);
		assertEquals(transfo.getParam().getValues().get(1), 1.0, 0.001);
		assertEquals(transfo.getParam().getValues().get(2), 3.0, 0.001);
		
	}
	
	@Test
	public void testGetNbMinGCP(){
		Transformation transfo = new LinearTransfo();
		assertEquals(transfo.getNbMinGCP(), 2);
	}
	
	@Test
	public void testGetParam() {
		
		Parameters param = Mockito.mock(LinearParameters.class);
		List<Double> expValue = new ArrayList<Double>();
		expValue.add(1.0);
		expValue.add(2.0);
		expValue.add(-1.0);
		Mockito.when(param.getValues()).thenReturn(expValue);
		
		Transformation transfo = new LinearTransfo();
		transfo.setParam(param);
		
		assertEquals(transfo.getParam().getValues().get(0), 1.0, 0.01);
		assertEquals(transfo.getParam().getValues().get(1), 2.0, 0.01);
		assertEquals(transfo.getParam().getValues().get(2), -1.0, 0.01);
	}
	
	@Test
	public void testSetParam() {
		
		Parameters param = Mockito.mock(LinearParameters.class);
		List<Double> expValue = new ArrayList<Double>();
		expValue.add(1.0);
		expValue.add(2.0);
		expValue.add(-1.0);
		Mockito.when(param.getValues()).thenReturn(expValue);
		
		Transformation transfo = new LinearTransfo();
		transfo.setParam(param);
		
		assertEquals(transfo.getParam().getValues().get(0), 1.0, 0.01);
		assertEquals(transfo.getParam().getValues().get(1), 2.0, 0.01);
		assertEquals(transfo.getParam().getValues().get(2), -1.0, 0.01);
	}
	
	@Test
	public void testGetResiduals() {
		
		List<Double> expRes = new ArrayList<Double>();
		expRes.add(0.2);
		expRes.add(0.1);
		expRes.add(0.5);
		
		Transformation transfo = new LinearTransfo();
		transfo.setResiduals(expRes);
		
		assertEquals(transfo.getResiduals().get(0), 0.2, 0.01);
		assertEquals(transfo.getResiduals().get(1), 0.1, 0.01);
		assertEquals(transfo.getResiduals().get(2), 0.5, 0.01);
	}
	
	@Test
	public void testSetResiduals() {
		
		List<Double> expRes = new ArrayList<Double>();
		expRes.add(0.2);
		expRes.add(0.1);
		expRes.add(0.5);
		
		Transformation transfo = new LinearTransfo();
		transfo.setResiduals(expRes);
		
		assertEquals(transfo.getResiduals().get(0), 0.2, 0.01);
		assertEquals(transfo.getResiduals().get(1), 0.1, 0.01);
		assertEquals(transfo.getResiduals().get(2), 0.5, 0.01);
	}
	
	@Test
	public void testComputeAccuracy(){
		
		//Set up
		Transformation t = new LinearTransfo();
		
		CheckPoint pt1 = Mockito.mock(CheckPoint.class);
		Mockito.when(pt1.getBasicCoord()).thenReturn(new Coordinate(1,1,0));
		Mockito.when(pt1.getGroundCoord()).thenReturn(new Coordinate(2,3,4));
		
		CheckPoint pt2 = Mockito.mock(CheckPoint.class);
		Mockito.when(pt2.getBasicCoord()).thenReturn(new Coordinate(-1,-1,0));
		Mockito.when(pt2.getGroundCoord()).thenReturn(new Coordinate(1,0,3));
		
		CheckPoint pt3 = Mockito.mock(CheckPoint.class);
		Mockito.when(pt3.getBasicCoord()).thenReturn(new Coordinate(3,3,0));
		Mockito.when(pt3.getGroundCoord()).thenReturn(new Coordinate(3,3,2));
		
		List<CheckPoint> CPs = new ArrayList<CheckPoint>();
		Collections.addAll(CPs, pt1, pt2, pt3);
		
		Parameters p = Mockito.mock(LinearParameters.class);
		List<Double> fakeParam = new ArrayList<Double>();
		Collections.addAll(fakeParam, 1.0, 1.0, 3.0);
		Mockito.when(p.getValues()).thenReturn(fakeParam);
		Mockito.when(p.applyParam(Mockito.any(List.class))).thenCallRealMethod();
		
		t.setCheckPoints(CPs);
		t.setParam(p);
		
		//Test
		t.computeAccuracy();
		assertNotNull(t.getAccuracy());
		//assertEquals(t.getAccuracy().get(0), , 0.01);
		//assertEquals(t.getAccuracy().get(1), , 0.01);
		//assertEquals(t.getAccuracy().get(2), , 0.01);
		//assertEquals(t.getAccuracy().get(3), , 0.01);
		
	}

}
