package ensg.tsi.majortom;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public abstract class PointConnu extends Point {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CoordinateSequence groundCoord;
	
	public PointConnu(CoordinateSequence basicCoord, CoordinateSequence groundCoord, GeometryFactory factory) {
		super(basicCoord, factory);
		this.groundCoord = groundCoord;
		
	}
	
	public Coordinate getBasicCoord(){
		return this.getCoordinate();
	}
	
	public Coordinate getGroundCoord(){
		return this.groundCoord.getCoordinate(0);
	}

}
