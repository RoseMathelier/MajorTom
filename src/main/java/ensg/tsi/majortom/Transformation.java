package ensg.tsi.majortom;

import java.util.List;

public abstract class Transformation {
	
	private int nbMinGCP;
	private Parameters param;
	
	public abstract int getNbMinGCP();
	
	public Parameters getParam(){
		return this.param;
	}
	
	public void setParam(Parameters param){
		this.param = param;
	}
	
	public abstract void setTransfoFromGCP(List<PointConnu> GCPs);

}
