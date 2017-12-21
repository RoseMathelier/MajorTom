package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Class for control points, points whose field coordinates are known, used to determine the parameters of the transformation.
 * @author Rose Mathelier
 *
 */
public class ControlPoint extends PointConnu {

	/**
	 * Constructor for class ConctrolPoint
	 * Calls the super-constructor from class PointConnu.
	 * @param basicCoord The basic coordinate (not georeferenced).
	 * @param groundCoord The known ground coordinate (georeferenced).
	 * @param factory The geometry factory from JTS.
	 * @see com.vividsolutions.jts.get.GeometryFactory
	 * @see PointConnu
	 * @see com.vividsolutions.jts.geom.Point
	 */
	public ControlPoint(CoordinateSequence basicCoord, CoordinateSequence groundCoord, GeometryFactory factory) {
		super(basicCoord, groundCoord, factory);
	}

}
