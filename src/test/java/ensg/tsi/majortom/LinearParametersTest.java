package ensg.tsi.majortom;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

public class LinearParametersTest {

	@Test
	public void testApplyParam() {
		
		//Parameters
		LinearParameters linearParam = new LinearParameters(1,2,-1);
		
		//Layer coordinates
		List<Coordinate> oldCoords = new ArrayList<Coordinate>();
		oldCoords.add(new Coordinate(1,2,3));
		oldCoords.add(new Coordinate(4,5,6));
		oldCoords.add(new Coordinate(7,8,9));
		
		//Expected coordinates
		List<Coordinate> expCoords = new ArrayList<Coordinate>();
		expCoords.add(new Coordinate(2,4,2));
		expCoords.add(new Coordinate(5,7,5));
		expCoords.add(new Coordinate(8,10,8));
		
		List<Coordinate> newCoords = linearParam.applyParam(oldCoords);
		
		for(int i = 0; i < newCoords.size(); i++){
			assertEquals(newCoords.get(i).getOrdinate(0), expCoords.get(i).getOrdinate(0), 0.01);
			assertEquals(newCoords.get(i).getOrdinate(1), expCoords.get(i).getOrdinate(1), 0.01);
			assertEquals(newCoords.get(i).getOrdinate(2), expCoords.get(i).getOrdinate(2), 0.01);
		}
		
	}

}
