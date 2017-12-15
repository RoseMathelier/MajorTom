package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class LinearParameters implements Parameters {
	
	private double dx;
	private double dy;
	private double dz;
	
	public LinearParameters(double dx, double dy, double dz){
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	public List<Coordinate> applyParam(List<Coordinate> coords) {
		
		List<Coordinate> newCoords = new ArrayList<Coordinate>();
		
		for(Coordinate coord: coords){
			double newX = coord.getOrdinate(0) + this.dx;
			double newY = coord.getOrdinate(1) + this.dy;
			double newZ = coord.getOrdinate(2) + this.dz;
			Coordinate newCoord = new Coordinate(newX, newY, newZ);
			newCoords.add(newCoord);
		}
		
		return newCoords;
		
	}

}
