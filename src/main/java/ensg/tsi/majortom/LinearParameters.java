package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class LinearParameters implements Parameters {
	
	/**
	 * x translation
	 */
	private double dx;
	/**
	 * y translation
	 */
	private double dy;
	/**
	 * z translation
	 */
	private double dz;
	
	/**
	 * Constructor for class LinearParameters
	 * @param dx x translation
	 * @param dy y translation
	 * @param dz z translation
	 */
	public LinearParameters(double dx, double dy, double dz){
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
	
	/**
	 * Parameters values getter
	 * @return the list of values of the parameters
	 */
	public List<Double> getValues() {
		List<Double> list = new ArrayList<Double>();
		list.add(this.dx);
		list.add(this.dy);
		list.add(this.dz);
		return list;
	}
	
	/**
	 * Parameters names getter
	 * @return the list of names of the parameters
	 */
	public List<String> getNames(){
		List<String> list = new ArrayList<String>();
		list.add("dx");
		list.add("dy");
		list.add("dz");
		return list;
	}
	
	/**
	 * Method to apply the linear parameters previously computed and actually georeference the layer
	 * @param coords : the list of original sets of coordinates of the features to georeference
	 * @return the list of new sets of coordinates of the georeferenced features
	 */
	public List<Coordinate[]> applyParam(List<Coordinate[]> coords) {
		
		List<Coordinate[]> newCoords = new ArrayList<Coordinate[]>();
		
		double x, y, z, newX, newY, newZ;
		
		for(Coordinate[] coord: coords) {
			System.out.println("POLYGONE");
			for(int i = 0; i < coord.length; i++) {
				System.out.println(coord[i]);
			}
		}
		
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
		
		for(Coordinate[] coord: newCoords) {
			System.out.println("POLYGONE");
			for(int i = 0; i < coord.length; i++) {
				System.out.println(coord[i]);
			}
		}
		return newCoords;
		
	}

}
