package ensg.tsi.majortom;

import java.util.List;

public class LinearTransfo extends Transformation {

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
		
	}

	@Override
	public int getNbMinGCP() {
		return 2;
	}

}
