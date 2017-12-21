package ensg.tsi.majortom;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Abstract class for any type of transformation.
 * 
 * @author Rose Mathelier
 *
 */
public abstract class Transformation implements Serializable {
	
	/**
	 * The list of control points to be used to define the parameters of the transformation 
	 */
	private List<ControlPoint> GCPs;
	
	/**
	 * The list of check points to be used in the accuracy computation after the transformation
	 */
	private List<CheckPoint> CPs;
	
	/**
	 * The set of parameters used in the transformation, computed using the GCPs.
	 * @see Transformation#setTransfoFromGCP()
	 * @see Parameters
	 */
	private Parameters param;
	
	/**
	 * The set of residuals for each coordinate after least square resolution
	 * @see Transformation#setTransfoFromGCP()
	 */
	private List<Double> residuals;
	
	/**
	 * The accuracy of the transformation, computed using the check points.
	 * @see Transformation#computeAccuracy()
	 */
	private List<Double> accuracy;
	
	/**
	 * Minimum number of GCPs getter.
	 * @return The minimum number of control points necessary to perform the transformation.
	 */
	public abstract int getNbMinGCP();
	
	/**
	 * Control points getter.
	 * @return The list of control points.
	 */
	public List<ControlPoint> getControlPoints(){
		return this.GCPs;
	}
	
	/**
	 * Check points getter.
	 * @return The list of check points.
	 */
	public List<CheckPoint> getCheckPoints(){
		return this.CPs;
	}
	
	/**
	 * Control points setter.
	 * @param GCPs The list of control points to add.
	 */
	public void setControlPoints(List<ControlPoint> GCPs){
		this.GCPs = GCPs;
	}
	
	/**
	 * Check points setter.
	 * @param CPs The list of check points to add.
	 */
	public void setCheckPoints(List<CheckPoint> CPs){
		this.CPs = CPs;
	}
	
	/**
	 * Parameters getter.
	 * @return The set of parameters.
	 * @see Parameters
	 */
	public Parameters getParam(){
		return this.param;
	}
	
	/**
	 * Residuals getter.
	 * @return The set of residuals for each coordinate in the same order as the GCPs list and in the order XYZ.
	 */
	public List<Double> getResiduals(){
		return this.residuals;
	}
	
	/**
	 * Parameters setter.
	 * @param param The set of parameters to add.
	 */
	public void setParam(Parameters param){
		this.param = param;
	}
	
	/**
	 * Residuals setter.
	 * @param residuals The set of residuals to add.
	 */
	public void setResiduals(List<Double> residuals){
		this.residuals = residuals;
	}
	
	/**
	 * Accuracy getter.
	 * @return A list of double corresponding the the general RMS, and the RMS for x, y and z.
	 */
	public List<Double> getAccuracy(){
		return this.accuracy;
	}
	
	/**
	 * Abstract method to compute the parameters of the transformation using the ground control points.
	 * The methods of computation differ according to the implementation.
	 * Sets the parameters and residuals attributes of class transformation according to the results of this computation.
	 */
	public abstract void setTransfoFromGCP();
	
	/**
	 * Method to compute the accuracy of the transformation using the control points.
	 * 
	 */
	public void computeAccuracy(){
		
		List<Coordinate[]> basicCoord = new ArrayList<Coordinate[]>();
		List<Coordinate[]> groundCoord = new ArrayList<Coordinate[]>();
		
		for(PointConnu pt: this.CPs) {
			
			//Basic coordinates
			Coordinate[] bCoord = new Coordinate[]{pt.getBasicCoord()};
			basicCoord.add(bCoord);
			
			//Ground coordinates
			Coordinate[] gCoord = new Coordinate[]{pt.getGroundCoord()};
			groundCoord.add(gCoord);
		}
		
		//We apply the transformation to the basic coordinates of the checkpoints
		List<Coordinate[]> transfoCoord = this.getParam().applyParam(basicCoord);
		
		//Then we compare the results to the real known coordinates
		double RMS = 0, RMSX = 0, RMSY = 0, RMSZ = 0, xGi, yGi, zGi, xTi, yTi, zTi, coeffXi, coeffYi, coeffZi, distance;
		double N = groundCoord.size();
		
		for(int i = 0; i < N; i++){
			
			xGi = groundCoord.get(i)[0].getOrdinate(0);
			yGi = groundCoord.get(i)[0].getOrdinate(1);
			zGi = groundCoord.get(i)[0].getOrdinate(2);
			
			xTi = transfoCoord.get(i)[0].getOrdinate(0);
			yTi = transfoCoord.get(i)[0].getOrdinate(1);
			zTi = transfoCoord.get(i)[0].getOrdinate(2);
			
			coeffXi = Math.pow(xGi - xTi, 2);
			coeffYi = Math.pow(yGi - yTi, 2);
			coeffZi = Math.pow(zGi - zTi, 2);
			
			RMS += coeffXi + coeffYi + coeffZi;
			RMSX += coeffXi;
			RMSY += coeffYi;
			RMSZ += coeffZi;
			
		}
		
		RMS = Math.sqrt(1/N * RMS);
		RMSX = Math.sqrt(1/N * RMSX);
		RMSY = Math.sqrt(1/N * RMSY);
		RMSZ = Math.sqrt(1/N * RMSZ);
		
		List<Double> transfoAccuracy = new ArrayList<Double>();
		Collections.addAll(transfoAccuracy, RMS, RMSX, RMSY, RMSZ);
		
		this.accuracy = transfoAccuracy;
	}
	
	/**
	 * Method to write the report of the transformation, including  : the type of transformation, the parameters, the residuals, the accuracy (RMS).
	 * @return A string containing the report of the transformation.
	 */
	public String writeReport(){
		
		List<String> paramNames = this.getParam().getNames();
		List<Double> paramValues = this.getParam().getValues();
		List<Double> residuals = this.getResiduals();
		List<Double> accuracy = this.getAccuracy();
		List<ControlPoint> GCPs = this.getControlPoints();
		
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
		
		for(int i = 0; i < GCPs.size(); i++){
			int numGCP = i+1;
			transfoText += "GCP n° " + numGCP + " : \n";
			transfoText += "- X = " + GCPs.get(i).getCoordinate().getOrdinate(0) + " => Rx = " + residuals.get(3*(i-1) + 1);
			transfoText += "- Y = " + GCPs.get(i).getCoordinate().getOrdinate(1) + " => Ry = " + residuals.get(3*(i-1) + 2);
			transfoText += "- Z = " + GCPs.get(i).getCoordinate().getOrdinate(2) + " => Rz = " + residuals.get(3*i);
		}
		
		transfoText += "Accuracy : \n"
				+ "- RMS = " + accuracy.get(0) + "\n"
				+ "- RMSx = " + accuracy.get(1) + "\n"
				+ "- RMSy = " + accuracy.get(2) + "\n"
				+ "- RMSz = " + accuracy.get(3) + "\n";
				
		return transfoText;
	}
	
	/**
	 * Method to generate a .txt report of the transformation.
	 * @param outputPath The directory where the report should be written.
	 * @throws IOException
	 */
	public void generateReport(String outputPath){
		
		Path logFile = Paths.get(outputPath + "/Report.txt");
		try {
			Files.createFile(logFile);
			
			BufferedWriter writer = Files.newBufferedWriter(logFile,
					StandardCharsets.UTF_8, StandardOpenOption.WRITE);
			
			writer.write(this.writeReport());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
