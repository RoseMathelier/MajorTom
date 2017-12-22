package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ejml.factory.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

/**
 * Concrete class for Helmert transformation.
 * This transformation is defined by 3 parameters : T1, T2, T3 (translation), R1, R2, R3 (rotation), S (scale factor).
 * @author Rose Mathelier
 *
 */
public class HelmertTransfo extends Transformation{
	
	/**
	 * Method to compute the Helmert parameters of the transformation using the ground control points.
	 * Implementation method of the abstract function from class Transformation.
	 * Sets the parameters and residuals attributes of class transformation according to the results of this computation.
	 * The parameters and residuals are computed using a least square method.
	 * @see HelmertParam
	 * @throws SingularMatrixEception
	 */
	@Override
	public void setTransfoFromGCP() {
		
		int n = this.getControlPoints().size();
		
		//Initializations of observation vector and model matrix
		double[][] observ = new double[3*n][1];
		double[][] model = new double [3*n][7]; 	
		
		//We fill the observation vector and the model matrix
		int i = 0;
		for(ControlPoint gcp: this.getControlPoints()){
			
			observ[i][0] = gcp.getGroundCoord().getOrdinate(0);
			observ[i+1][0] = gcp.getGroundCoord().getOrdinate(1);
			observ[i+2][0] = gcp.getGroundCoord().getOrdinate(2);
			
			model[i][0] = 1;
			model[i][1] = 0;
			model[i][2] = 0;
			model[i][3] = 0;
			model[i][4] = - gcp.getBasicCoord().getOrdinate(2);
			model[i][5] = gcp.getBasicCoord().getOrdinate(1);
			model[i][6] = gcp.getBasicCoord().getOrdinate(0);
			model[i+1][0] = 0;
			model[i+1][1] = 1;
			model[i+1][2] = 0;
			model[i+1][3] = gcp.getBasicCoord().getOrdinate(2);
			model[i+1][4] = 0;
			model[i+1][5] = - gcp.getBasicCoord().getOrdinate(0);
			model[i+1][6] = gcp.getBasicCoord().getOrdinate(1);
			model[i+2][0] = 0;
			model[i+2][1] = 0;
			model[i+2][2] = 1;
			model[i+2][3] = - gcp.getBasicCoord().getOrdinate(1);
			model[i+2][4] = gcp.getBasicCoord().getOrdinate(0);
			model[i+2][5] = 0;
			model[i+2][6] = gcp.getBasicCoord().getOrdinate(2);
			
			i += 3;
		}
		
		SimpleMatrix B = new SimpleMatrix(observ);
		SimpleMatrix A = new SimpleMatrix(model);
		
		//Least square solution
		SimpleMatrix tA = A.transpose();
		SimpleMatrix N = tA.mult(A);
		
		try{
			
			SimpleMatrix Ninv = N.invert();
			SimpleMatrix X = (Ninv.mult(tA)).mult(B);
			SimpleMatrix V = B.minus(A.mult(X));
			
			//We extract and set the parameters
			double T1 = X.get(0,0);
			double T2 = X.get(1,0);
			double T3 = X.get(2,0);
			double R1 = X.get(3,0);
			double R2 = X.get(4,0);
			double R3 = X.get(5,0);
			double S = X.get(6,0) - 1; //delta = 1 - S
			
			Parameters param = new HelmertParameters(T1, T2, T3, R1, R2, R3, S);
			this.setParam(param);
			
			//We extract and set the residuals
			List<Double> resid = new ArrayList<Double>();
			for(int j = 0; j < V.numRows(); j++){
				resid.add(V.get(j,0));
			}
			this.setResiduals(resid);
			
		}
		catch(SingularMatrixException e){
			System.out.println("The system has no solution, add more control points : " + e);
		}
				
	}

	/**
	 * Method to get the minimum number of control points necessary to perform the transformation.
	 * Implementation method of the abstract function from class Transformation.
	 * Always returns 3 for a linear transformation
	 * @see Transformation
	 */
	@Override
	public int getNbMinGCP() {
		return 3;
	}

}
