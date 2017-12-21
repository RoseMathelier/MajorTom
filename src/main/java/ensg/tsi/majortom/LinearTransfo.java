package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class for linear transformation.
 * This transformation is defined by 3 parameters : dx, dy and dz.
 * @author Rose Mathelier
 *
 */
public class LinearTransfo extends Transformation {

	/**
	 * Method to compute the linear parameters of the transformation using the ground control points.
	 * Implementation method of the abstract function from class Transformation.
	 * Sets the parameters and residuals attributes of class transformation according to the results of this computation.
	 * The parameters are set according to the average difference in x, y and z of the control points.
	 * Always set the residuals to a list of 0 in the case of a linear transformation
	 * @see LinearParam
	 */
	@Override
	public void setTransfoFromGCP() {
		
		double xBi, yBi, zBi, xGi, yGi, zGi;
		double sumX = 0, sumY = 0, sumZ = 0;
		int n = this.getControlPoints().size();
		
		for(PointConnu pt: this.getControlPoints()) {
			
			//Basic coordinates
			xBi = pt.getBasicCoord().getOrdinate(0);
			yBi = pt.getBasicCoord().getOrdinate(1);
			zBi = pt.getBasicCoord().getOrdinate(2);
			
			//Ground coordinates
			xGi = pt.getGroundCoord().getOrdinate(0);
			yGi = pt.getGroundCoord().getOrdinate(1);
			zGi = pt.getGroundCoord().getOrdinate(2);
			
			sumX += (xGi - xBi);
			sumY += (yGi - yBi);
			sumZ += (zGi - zBi);
			
		}
		
		//Average translation
		double dx = sumX / n;
		double dy = sumY / n;
		double dz = sumZ / n;
		
		Parameters param = new LinearParameters(dx, dy, dz);
		this.setParam(param);
		
		List<Double> residuals = new ArrayList<Double>();
		for(int i = 0; i < 3*this.getControlPoints().size(); i++){
			residuals.add(0.0);
		}
		this.setResiduals(residuals);
		
	}

	/**
	 * Method to get the minimum number of control points necessary to perform the transformation.
	 * Implementation method of the abstract function from class Transformation.
	 * Always returns 1 for a linear transformation
	 * @see Transformation
	 */
	@Override
	public int getNbMinGCP() {
		return 1;
	}

}
