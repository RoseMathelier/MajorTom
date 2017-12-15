package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class HelbertParameters implements Parameters {
	
	private double T1;
	private double T2;
	private double T3;
	private double R1;
	private double R2;
	private double R3;
	private double S;

	public List<Coordinate> applyParam(List<Coordinate> coords) {
		
		List<Coordinate> newCoords = new ArrayList<Coordinate>();
		
		for(Coordinate coord: coords){
			double newX = 0;
			double newY = 0;
			double newZ = 0;
			Coordinate newCoord = new Coordinate(newX, newY, newZ);
			newCoords.add(newCoord);
		}
		
		return newCoords;
	}

}
