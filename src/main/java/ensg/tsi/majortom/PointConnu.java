package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * Abstract class for known points (control points and check points).
 * @author Rose Mathelier
 *
 */
public abstract class PointConnu extends Point {
	
	/**
	 * The ground coordinate of the control point.
	 */
	private CoordinateSequence groundCoord;
	
	/**
	 * Constructor for class PointConnu.
	 * Calls the super-constructor from class Point of JTS.
	 * @param basicCoord The basic coordinate (not georeferenced).
	 * @param groundCoord The known ground coordinate (georeferenced).
	 * @param factory The geometry factory from JTS.
	 * @see com.vividsolutions.jts.get.GeometryFactory
	 * @see com.vividsolutions.jts.geom.Point
	 */
	public PointConnu(CoordinateSequence basicCoord, CoordinateSequence groundCoord, GeometryFactory factory) {
		super(basicCoord, factory);
		this.groundCoord = groundCoord;
		
	}
	
	/**
	 * Basic coordinate getter.
	 * Calls the getCoordinate() method of class Point in JTS.
	 * @return the basic coordinate of the point.
	 * @see com.vividsolutions.jts.get.Point
	 */
	public Coordinate getBasicCoord(){
		return this.getCoordinate();
	}
	
	/**
	 * Ground coordinate getter.
	 * @return the ground coordinate of the point.
	 */
	public Coordinate getGroundCoord(){
		return this.groundCoord.getCoordinate(0);
	}

}
