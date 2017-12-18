package ensg.tsi.majortom;

import java.util.List;

public abstract class Transformation {
	
	private Parameters param;
	private List<Double> residuals;
	private double accuracy;
	
	public abstract int getNbMinGCP();
	
	public Parameters getParam(){
		return this.param;
	}
	
	public List<Double> getResiduals(){
		return this.residuals;
	}
	
	public void setParam(Parameters param){
		this.param = param;
	}
	
	public void setResiduals(List<Double> residuals){
		this.residuals = residuals;
	}
	
	public abstract void setTransfoFromGCP(List<ControlPoint> GCPs);
	
	public void computeAccuracy(List<CheckPoint> CPs){
		this.accuracy = 0;
	}
	
	public void generateReport(String outputPath){
		
	}
	
	

}
