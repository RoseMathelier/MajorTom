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
	

	public List<Double> getValues() {
		List<Double> list = new ArrayList<Double>();
		list.add(this.dx);
		list.add(this.dy);
		list.add(this.dz);
		return list;
	}

	public List<Coordinate> applyParam(List<Coordinate> coords) {
		
		List<Coordinate> newCoords = new ArrayList<Coordinate>();
		
		double x, y, z, newX, newY, newZ;
		
		for(Coordinate coord: coords){
			x = coord.getOrdinate(0);
			y = coord.getOrdinate(1);
			z = coord.getOrdinate(2);
			newX = x + this.dx;
			newY = y + this.dy;
			newZ = z + this.dz;
			Coordinate newCoord = new Coordinate(newX, newY, newZ);
			newCoords.add(newCoord);
		}
		
		return newCoords;
	}

}
