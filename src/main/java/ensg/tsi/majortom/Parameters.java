package ensg.tsi.majortom;

import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Interface to define all common methods for different types of parameters
 * @author Rose Mathelier
 *
 */
public interface Parameters {
	
	/**
	 * Parameters values getter
	 * @return the list of values of the parameters
	 */
	public List<Double> getValues();
	
	/**
	 * Parameters names getter
	 * @return the list of names of the parameters
	 */
	public List<String> getNames();
	
	/**
	 * Method to apply the parameters previously computed and actually georeference the layer
	 * @param coords : the list of original sets of coordinates of the features to georeference
	 * @return the list of new sets of coordinates of the georeferenced features
	 */
	public List<Coordinate[]> applyParam(List<Coordinate[]> coords);

}
