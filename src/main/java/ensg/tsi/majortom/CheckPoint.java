package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Class for check points, points whose field coordinates are known, used to verify the accuracy after the transformation.
 * @author Rose Mathelier
 *
 */
public class CheckPoint extends PointConnu {

	/**
	 * Constructor for class CheckPoint
	 * Calls the super-constructor from class PointConnu.
	 * @param basicCoord The basic coordinate (not georeferenced).
	 * @param groundCoord The known ground coordinate (georeferenced).
	 * @param factory The geometry factory from JTS.
	 * @see com.vividsolutions.jts.get.GeometryFactory
	 * @see PointConnu
	 * @see com.vividsolutions.jts.geom.Point
	 */
	public CheckPoint(CoordinateSequence basicCoord, CoordinateSequence groundCoord, GeometryFactory factory) {
		super(basicCoord, groundCoord, factory);
		// TODO Auto-generated constructor stub
	}

}
