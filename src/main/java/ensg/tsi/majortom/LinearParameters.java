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
	
	public List<String> getNames(){
		List<String> list = new ArrayList<String>();
		list.add("dx");
		list.add("dy");
		list.add("dz");
		return list;
	}

	public List<Coordinate[]> applyParam(List<Coordinate[]> coords) {
		
		List<Coordinate[]> newCoords = new ArrayList<Coordinate[]>();
		
		double x, y, z, newX, newY, newZ;
		
		for(Coordinate[] coord: coords){
			Coordinate[] newCoord = new Coordinate[coord.length];
			for(int i = 0; i < coord.length; i++){
				x = coord[i].getOrdinate(0);
				y = coord[i].getOrdinate(1);
				z = coord[i].getOrdinate(2);
				newX = x + this.dx;
				newY = y + this.dy;
				newZ = z + this.dz;
				newCoord[i] = new Coordinate(newX, newY, newZ);
			}
			newCoords.add(newCoord);
		}
		
		return newCoords;
	}
	
	public String toString(){
		String paramText = "";
		List<Double> values = this.getValues();
		List<String> names = this.getNames();
		
		for(int i = 0; i < names.size(); i++){
			paramText += "- "+ names.get(i) + " = " + values.get(i) + ". \n";
		}
		
		return paramText;
	}
	

}
