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
	


	public List<Double> getValues() {
		List<Double> list = new ArrayList<Double>();
		list.add(this.T1);
		list.add(this.T2);
		list.add(this.T3);
		list.add(this.R1);
		list.add(this.R2);
		list.add(this.R3);
		list.add(this.S);
		return list;
	}



	public List<Coordinate> applyParam(List<Coordinate> coords) {
		
		List<Coordinate> newCoords = new ArrayList<Coordinate>();
		
		double x, y, z, newX, newY, newZ;
		
		for(Coordinate coord: coords){
			x = coord.getOrdinate(0);
			y = coord.getOrdinate(1);
			z = coord.getOrdinate(2);
			newX = T1 + (1 + S)*x - R3*y + R2*z;
			newY = T1 + (1 + S)*y - R1*z + R3*x;
			newZ = T1 + (1 + S)*z - R2*x + R1*y;
			Coordinate newCoord = new Coordinate(newX, newY, newZ);
			newCoords.add(newCoord);
		}
		
		return newCoords;
	}

}
