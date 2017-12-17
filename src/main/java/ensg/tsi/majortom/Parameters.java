package ensg.tsi.majortom;

import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public interface Parameters {
	
	public List<Double> getValues();
	
	public List<Coordinate> applyParam(List<Coordinate> coords);

}
