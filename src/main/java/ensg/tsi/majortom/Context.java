package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class Context {
	
	//Attributes
	private List<ControlPoint> controlPoints;
	private List<CheckPoint> checkPoints;
	private String inputPath;
	private String outputPath;
	private String outputName;
	
	//Constructor
	public Context(){
		this.controlPoints = new ArrayList<ControlPoint>();
		this.checkPoints = new ArrayList<CheckPoint>();
		this.inputPath = null;
		this.outputPath = null;
		this.outputName = null;
	}
	
	//Getters
	public List<ControlPoint> getControlPoints(){
		return this.controlPoints;
	}
	
	public PointConnu getControlPoint(int index){
		return this.controlPoints.get(index);
	}
	
	public List<CheckPoint> getCheckPoints(){
		return this.checkPoints;
	}
	
	public PointConnu getCheckPoint(int index){
		return this.checkPoints.get(index);
	}
	
	public String getInputPath(){
		return this.inputPath;
	}
	
	public String getOutputPath(){
		return this.outputPath;
	}
	
	public String getOutputName(){
		return this.outputName;
	}
	
	//File setters
	public void setInputPath(String path){
		this.inputPath = path;
	}
	
	public void setOutputPath(String path){ 
		this.outputPath = path;
	}
	
	public void setOutputName(String name){
		this.outputName = name;
	}
	
	
	//Add, move and delete ground control points
	public void addGCP(ControlPoint gcp) {
		this.controlPoints.add(gcp);
	}
	
	public void moveGCP(PointConnu pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			ControlPoint newPt = new ControlPoint(newBasicCoord, newGroundCoord, geometryFactory);
			this.controlPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void moveGCP(int index, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			ControlPoint newPt = new ControlPoint(newBasicCoord, newGroundCoord, geometryFactory);
			this.controlPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void deleteGCP(ControlPoint gcp) {
		this.controlPoints.remove(gcp);
	}
	
	public void deleteGCP(int index) {
		this.controlPoints.remove(index);
	}
	
	
	//Add, move and delete check points
	public void addCP(CheckPoint cp) {
		this.checkPoints.add(cp);
	}
	
	public void moveCP(PointConnu pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.checkPoints.indexOf(pt);
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			CheckPoint newPt = new CheckPoint(newBasicCoord, newGroundCoord, geometryFactory);
			this.checkPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void moveCP(int index, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			CheckPoint newPt = new CheckPoint(newBasicCoord, newGroundCoord, geometryFactory);
			this.checkPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void deleteCP(CheckPoint pt) {
		this.checkPoints.remove(pt);
	}
	
	public void deleteCP(int index) {
		this.checkPoints.remove(index);
	}
	

}
