package ensg.tsi.majortom;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public abstract class Transformation implements Serializable {
	
	private Parameters param;
	private List<Double> residuals;
	private List<Double> accuracy;
	
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
	
	public List<Double> getAccuracy(){
		return this.accuracy;
	}
	
	public abstract void setTransfoFromGCP(List<ControlPoint> GCPs);
	
	public void computeAccuracy(List<CheckPoint> CPs){
		
		List<Coordinate[]> basicCoord = new ArrayList<Coordinate[]>();
		List<Coordinate[]> groundCoord = new ArrayList<Coordinate[]>();
		
		for(PointConnu pt: CPs) {
			
			//Basic coordinates
			Coordinate[] bCoord = new Coordinate[]{pt.getBasicCoord()};
			basicCoord.add(bCoord);
			
			//Ground coordinates
			Coordinate[] gCoord = new Coordinate[]{pt.getGroundCoord()};
			groundCoord.add(gCoord);
		}
		
		//We apply the transformation to the basic coordinates of the checkpoints
		List<Coordinate[]> transfoCoord = this.param.applyParam(basicCoord);
		
		//Then we compare the results to the real known coordinates
		double RMS = 0, RMSX = 0, RMSY = 0, RMSZ = 0, xGi, yGi, zGi, xTi, yTi, zTi, distance;
		double N = groundCoord.size();
		
		for(int i = 0; i < N; i++){
			
			xGi = groundCoord.get(i)[0].getOrdinate(0);
			yGi = groundCoord.get(i)[0].getOrdinate(1);
			zGi = groundCoord.get(i)[0].getOrdinate(2);
			
			xTi = transfoCoord.get(i)[0].getOrdinate(0);
			yTi = transfoCoord.get(i)[0].getOrdinate(1);
			zTi = transfoCoord.get(i)[0].getOrdinate(2);
			
			distance = Math.sqrt(Math.pow(xGi - xTi, 2) + Math.pow(yGi - yTi, 2) + Math.pow(zGi - zTi, 2));
			
			RMS += Math.pow(distance, 2);
			RMSX += Math.pow(xGi - xTi, 2);
			RMSY += Math.pow(yGi - yTi, 2);
			RMSZ += Math.pow(zGi - zTi, 2);
		}
		
		RMS = Math.sqrt(1/N * RMS);
		RMSX = Math.sqrt(1/N * RMSX);
		RMSY = Math.sqrt(1/N * RMSY);
		RMSZ = Math.sqrt(1/N * RMSZ);
		
		List<Double> transfoAccuracy = new ArrayList<Double>();
		Collections.addAll(transfoAccuracy, RMS, RMSX, RMSY, RMSZ);
		
		this.accuracy = transfoAccuracy;
	}
	
	public String toString(){
		
		List<String> paramNames = this.getParam().getNames();
		List<Double> paramValues = this.getParam().getValues();
		List<Double> residuals = this.getResiduals();
		List<Double> accuracy = this.getAccuracy();
		
		String transfoText = " GROUND CONTROL TO MAJOR TOM... \n"
				+ "***** \n"
				+ "\n"
				+ "Transformation : " + this.getClass() + ". \n"
				+ "\n"
				+ "Parameters : \n";
		
		for(int i = 0; i < paramNames.size(); i++){
			transfoText += "- " + paramNames.get(i) + " = " + paramValues.get(i) + ". \n";
		}
		
		transfoText += "Residuals : \n";
		
		for(int i = 0; i < paramNames.size(); i++){
			transfoText += "- Residual for " + paramNames.get(i) + " : " + residuals.get(i) + ". \n";
		}
		
		transfoText += "Accuracy : \n"
				+ "- RMS = " + accuracy.get(0) + "\n"
				+ "- RMSx = " + accuracy.get(1) + "\n"
				+ "- RMSy = " + accuracy.get(2) + "\n"
				+ "- RMSz = " + accuracy.get(3) + "\n";
				
		return transfoText;
	}
	
	public void generateReport(String outputPath){
		
		ObjectOutputStream oos;

	    try {
	      oos = new ObjectOutputStream(
	              new BufferedOutputStream(
	                new FileOutputStream(
	                  new File(outputPath + "/Report.txt"))));

	      oos.writeObject(this);

	      oos.close();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	

}
