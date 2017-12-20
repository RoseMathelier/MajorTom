package ensg.tsi.majortom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

public class HelmertParametersTest {

	@Test
	public void testApplyParam() {
		
		//Parameters
		HelmertParameters helmertParam = new HelmertParameters(1, 2.5, -1, 0.2, -0.1, 0.3, 0.5);
		
		//Layer coordinates
		List<Coordinate[]> oldCoords = new ArrayList<Coordinate[]>();
		oldCoords.add(new Coordinate[]{new Coordinate(1,2,3), new Coordinate(4,5,6)});
		oldCoords.add(new Coordinate[]{new Coordinate(4,5,6), new Coordinate(7,8,9)});
		oldCoords.add(new Coordinate[]{new Coordinate(7,8,9), new Coordinate(1,2,3)});
		
		//Expected coordinates
		List<Coordinate[]> expCoords = new ArrayList<Coordinate[]>();
		expCoords.add(new Coordinate[]{new Coordinate(1.6, 5.2, 4), new Coordinate(4.9, 10, 9.4)});
		expCoords.add(new Coordinate[]{new Coordinate(4.9, 10, 9.4), new Coordinate(8.2, 14.8, 14.8)});
		expCoords.add(new Coordinate[]{new Coordinate(8.2, 14.8, 14.8), new Coordinate(1.6, 5.2, 4)});
		
		List<Coordinate[]> newCoords = helmertParam.applyParam(oldCoords);
		
		for(int i = 0; i < newCoords.size(); i++){
			assertEquals(newCoords.get(i)[0].getOrdinate(0), expCoords.get(i)[0].getOrdinate(0), 0.01);
			assertEquals(newCoords.get(i)[0].getOrdinate(1), expCoords.get(i)[0].getOrdinate(1), 0.01);
			assertEquals(newCoords.get(i)[0].getOrdinate(2), expCoords.get(i)[0].getOrdinate(2), 0.01);
			assertEquals(newCoords.get(i)[1].getOrdinate(0), expCoords.get(i)[1].getOrdinate(0), 0.01);
			assertEquals(newCoords.get(i)[1].getOrdinate(1), expCoords.get(i)[1].getOrdinate(1), 0.01);
			assertEquals(newCoords.get(i)[1].getOrdinate(2), expCoords.get(i)[1].getOrdinate(2), 0.01);
		}
		
	}
	
	@Test
	public void testGetValues(){
		
		HelmertParameters helmertParam = new HelmertParameters(1, 2.5, -1, 0.2, -0.1, 0.3, 0.5);
		List<Double> lParam = helmertParam.getValues();
		assertEquals(lParam.get(0), 1, 0.01);
		assertEquals(lParam.get(1), 2.5, 0.01);
		assertEquals(lParam.get(2), -1, 0.01);
		assertEquals(lParam.get(3), 0.2, 0.01);
		assertEquals(lParam.get(4), -0.1, 0.01);
		assertEquals(lParam.get(5), 0.3, 0.01);
		assertEquals(lParam.get(6), 0.5, 0.01);
	}

}
