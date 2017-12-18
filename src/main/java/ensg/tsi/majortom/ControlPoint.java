package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

public class ControlPoint extends PointConnu {

	public ControlPoint(CoordinateSequence basicCoord, CoordinateSequence groundCoord, GeometryFactory factory) {
		super(basicCoord, groundCoord, factory);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
