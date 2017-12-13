package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class PointConnu extends Point {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CoordinateSequence groundCoord;
	
	public PointConnu(CoordinateSequence coords, GeometryFactory factory, CoordinateSequence groundCoord) {
		super(coords, factory);
		this.groundCoord = groundCoord;
		
	}

}
