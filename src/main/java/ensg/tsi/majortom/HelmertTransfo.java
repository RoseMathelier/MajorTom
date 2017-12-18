package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

public class HelmertTransfo extends Transformation{

	@Override
	public void setTransfoFromGCP(List<ControlPoint> GCPs) {
		
		int n = GCPs.size();
		
		//Initializations of observation vector and model matrix
		double[][] observ = new double[1][3*n];
		double[][] model = new double [0][0]; 	
		
		//We fill the observation vector and the model matrix
		int i = 0;
		for(ControlPoint gcp: GCPs){
			
			observ[i][0] = gcp.getGroundCoord().getOrdinate(0);
			observ[i+1][0] = gcp.getGroundCoord().getOrdinate(1);
			observ[i+2][0] = gcp.getGroundCoord().getOrdinate(2);
			
			model[i][4] = gcp.getBasicCoord().getOrdinate(2);
			model[i][5] = - gcp.getBasicCoord().getOrdinate(1);
			model[i][6] = gcp.getBasicCoord().getOrdinate(0);
			model[i+1][3] = - gcp.getBasicCoord().getOrdinate(2);
			model[i+1][5] = gcp.getBasicCoord().getOrdinate(0);
			model[i+1][6] = gcp.getBasicCoord().getOrdinate(1);
			model[i+2][3] = gcp.getBasicCoord().getOrdinate(1);
			model[i+2][4] = - gcp.getBasicCoord().getOrdinate(0);
			model[i+2][6] = gcp.getBasicCoord().getOrdinate(2);
			
			i += 3;
		}
		
		SimpleMatrix B = new SimpleMatrix(observ);
		SimpleMatrix A = new SimpleMatrix(model);
		SimpleMatrix P = SimpleMatrix.identity(3*n);
		
		//Least square solution
		SimpleMatrix tA = A.transpose();
		SimpleMatrix N = (tA.mult(P)).mult(A);
		SimpleMatrix X = ((N.invert().mult(tA)).mult(P)).mult(B);
		SimpleMatrix V = B.minus(A.mult(X));
		
		//We extract and set the parameters
		double T1 = X.get(0,0);
		double T2 = X.get(0,1);
		double T3 = X.get(0,2);
		double R1 = X.get(0,3);
		double R2 = X.get(0,4);
		double R3 = X.get(0,5);
		double S = X.get(0,6);
		
		Parameters param = new HelmertParameters(T1, T2, T3, R1, R2, R3, S);
		this.setParam(param);
		
		//We extract and set the residuals
		double rT1 = V.get(0,0);
		double rT2 = V.get(0,1);
		double rT3 = V.get(0,2);
		double rR1 = V.get(0,3);
		double rR2 = V.get(0,4);
		double rR3 = V.get(0,5);
		double rS = V.get(0,6);
		
		List<Double> resid = new ArrayList<Double>();
		Collections.addAll(resid, rT1, rT2, rT3, rR1, rR2, rR3, rS);
		this.setResiduals(resid);		
	}

	@Override
	public int getNbMinGCP() {
		return 2;
	}

}
