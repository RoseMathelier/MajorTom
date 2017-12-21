package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class HelmertParameters implements Parameters {
	
	/**
	 * x translation
	 */
	private double T1;
	/**
	 * y translation
	 */
	private double T2;
	/**
	 * z translation
	 */
	private double T3;
	/**
	 * Rotation around axis x
	 */
	private double R1;
	/**
	 * Rotation around axis y
	 */
	private double R2;
	/**
	 * Rotation around axis z
	 */
	private double R3;
	/**
	 * Scale factor
	 */
	private double S;
	
	/**
	 * Constructor for class HelmertParameters
	 * @param T1 x translation 
	 * @param T2 y translation
	 * @param T3 z translation
	 * @param R1 Rotation around axis x
	 * @param R2 Rotation around axis y
	 * @param R3 Rotation around axis z
	 * @param S Scale factor
	 */
	public HelmertParameters(double T1, double T2, double T3, double R1, double R2, double R3, double S){
		this.T1 = T1;
		this.T2 = T2;
		this.T3 = T3;
		this.R1 = R1;
		this.R2 = R2;
		this.R3 = R3;
		this.S = S;
	}
	
	/**
	 * Parameters values getter
	 * @return the list of values of the parameters
	 */
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
	
	/**
	 * Parameters names getter
	 * @return the list of names of the parameters
	 */
	public List<String> getNames(){
		List<String> list = new ArrayList<String>();
		list.add("T1");
		list.add("T2");
		list.add("T3");
		list.add("R1");
		list.add("R2");
		list.add("R3");
		list.add("S");
		return list;
	}
	
	/**
	 * Method to apply the Helmert parameters previously computed and actually georeference the layer
	 * @param coords : the list of original sets of coordinates of the features to georeference
	 * @return the list of new sets of coordinates of the georeferenced features
	 */
	public List<Coordinate[]> applyParam(List<Coordinate[]> coords) {
		
		List<Coordinate[]> newCoords = new ArrayList<Coordinate[]>();
		
		double x, y, z, newX, newY, newZ;
		
		for(Coordinate[] coord: coords){
			Coordinate[] newCoord = new Coordinate[coord.length];
			for(int i = 0; i < coord.length; i++){
				x = coord[i].getOrdinate(0);
				y = coord[i].getOrdinate(1);
				z = coord[i].getOrdinate(2);
				newX = T1 + (1 + S)*x - R3*y + R2*z;
				newY = T2 + (1 + S)*y - R1*z + R3*x;
				newZ = T3 + (1 + S)*z - R2*x + R1*y;
				newCoord[i] = new Coordinate(newX, newY, newZ);
				
			}
			newCoords.add(newCoord);
		}
		
		return newCoords;
	}

}
