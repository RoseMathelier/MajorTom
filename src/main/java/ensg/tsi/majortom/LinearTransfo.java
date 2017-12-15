package ensg.tsi.majortom;

import java.util.List;

public class LinearTransfo extends Transformation {

	@Override
	public void setTransfoFromGCP(List<PointConnu> GCPs) {
		
		
		double dx = 0;
		double dy = 0;
		double dz = 0;
		
		Parameters param = new LinearParameters(dx, dy, dz);
		this.setParam(param);
		
	}

	@Override
	public int getNbMinGCP() {
		return 2;
	}

}
