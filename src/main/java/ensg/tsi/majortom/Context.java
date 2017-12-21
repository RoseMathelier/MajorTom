package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

/**
 * Class to define the context of the georeferencer
 * @author Rose Mathelier
 *
 */
public class Context {
	
	/**
	 * The list of control points (points whose field coordinates are known, used to determine the parameters of the transformation).
	 */
	private List<ControlPoint> controlPoints;
	
	/**
	 * The list of check points (points whose field coordinates are known, used to verify the accuracy after the transformation).
	 */
	
	private List<CheckPoint> checkPoints;
	
	/**
	 * The path of the shapefile to georeference.
	 */
	private String inputPath;
	
	/**
	 * The directory where the output layer should be written.
	 */
	private String outputPath;
	
	/**
	 * The name of the georeferenced shapefile.
	 */
	private String outputName;
	
	/**
	 * Empty constructor for the class Context.
	 */
	public Context(){
		this.controlPoints = new ArrayList<ControlPoint>();
		this.checkPoints = new ArrayList<CheckPoint>();
		this.inputPath = null;
		this.outputPath = null;
		this.outputName = null;
	}
	
	/**
	 * Constructor with input and output paths.
	 * @param inputPath The path of the shapefile to georeference.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 */
	public Context(String inputPath, String outputPath, String outputName){
		this.controlPoints = new ArrayList<ControlPoint>();
		this.checkPoints = new ArrayList<CheckPoint>();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.outputName = outputName;
	}
	
	/**
	 * Constructor with input and output paths and control points
	 * @param inputPath The path of the shapefile to georeference.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 * @param GCPs The list of control points (points whose field coordinates are known, used to determine the parameters of the transformation).
	 */
	public Context(String inputPath, String outputPath, String outputName, List<ControlPoint> GCPs){
		this.controlPoints = GCPs;
		this.checkPoints = new ArrayList<CheckPoint>();
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.outputName = outputName;
	}
	
	/**
	 * Constructor with input and output paths, control points and checkpoints.
	 * @param inputPath The path of the shapefile to georeference.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 * @param GCPs The list of control points (points whose field coordinates are known, used to determine the parameters of the transformation).
	 * @param CPs The list of check points (points whose field coordinates are known, used to verify the accuracy after the transformation).
	 */
	public Context(String inputPath, String outputPath, String outputName, List<ControlPoint> GCPs, List<CheckPoint> CPs){
		this.controlPoints = GCPs;
		this.checkPoints = CPs;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.outputName = outputName;
	}
	
	/**
	 * Control points getter.
	 * @return The list of control points.
	 */
	public List<ControlPoint> getControlPoints(){
		return this.controlPoints;
	}
	
	/**
	 * Control point getter using an index.
	 * @param index The index of the wanted control point.
	 * @return The control point.
	 */
	public ControlPoint getControlPoint(int index){
		return this.controlPoints.get(index);
	}
	
	/**
	 * Check points getter.
	 * @return The list of check points.
	 */
	public List<CheckPoint> getCheckPoints(){
		return this.checkPoints;
	}
	
	/**
	 * Check point getter using an index.
	 * @param index The index of the wanted check point.
	 * @return The check point.
	 */
	public CheckPoint getCheckPoint(int index){
		return this.checkPoints.get(index);
	}
	
	/**
	 * Input path getter.
	 * @return The path of the shapefile to georeference.
	 */
	public String getInputPath(){
		return this.inputPath;
	}
	
	/**
	 * Output path getter.
	 * @return The directory where the output layer should be written.
	 */
	public String getOutputPath(){
		return this.outputPath;
	}
	
	/**
	 * Output name getter.
	 * @return The name of the georeferenced shapefile.
	 */
	public String getOutputName(){
		return this.outputName;
	}
	
	/**
	 * Input path setter.
	 * @param path The path of the shapefile to georeference.
	 */
	public void setInputPath(String path){
		this.inputPath = path;
	}
	
	/**
	 * Output path setter.
	 * @param path The directory where the output layer should be written.
	 */
	public void setOutputPath(String path){ 
		this.outputPath = path;
	}
	
	/**
	 * Output name setter.
	 * @param name The name of the georeferenced shapefile.
	 */
	public void setOutputName(String name){
		this.outputName = name;
	}
	
	
	/**
	 * Method to add a control point to the list
	 * @param gcp The control point to add.
	 */
	public void addGCP(ControlPoint gcp) {
		this.controlPoints.add(gcp);
	}
	
	/**
	 * Method to change the basic coordinates of a control point.
	 * @param pt The point you want to move.
	 * @param basicCoord The new basic coordinate.
	 */
	public void moveGCP(ControlPoint pt, Coordinate basicCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			moveGCP(index, basicCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic coordinates of a control point.
	 * @param index The index (in the list) of the point you want to move.
	 * @param basicCoord The new basic coordinate.
	 */
	public void moveGCP(int index, Coordinate basicCoord){
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate GCoord[] = {this.controlPoints.get(index).getGroundCoord()};
			CoordinateSequence GroundCoord = new CoordinateArraySequence(GCoord);
			ControlPoint newPt = new ControlPoint(newBasicCoord, GroundCoord, geometryFactory);
			this.controlPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic and ground coordinates of a control point.
	 * @param pt The point you want to move.
	 * @param basicCoord The new basic coordinate.
	 * @param groundCoord The new ground coordinate.
	 */
	public void moveGCP(ControlPoint pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			moveGCP(index, basicCoord, groundCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic and ground coordinates of a control point.
	 * @param index The index (in the list) of the point you want to move.
	 * @param basicCoord The new basic coordinate.
	 * @param groundCoord The new ground coordinate.
	 */
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
	
	/**
	 * Method to change the ground coordinates of a control point.
	 * @param pt The point whose coordinate we want to change.
	 * @param groundCoord The new ground coordinate.
	 */
	public void modifyGCP(ControlPoint pt, Coordinate groundCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			modifyGCP(index, groundCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the ground coordinates of a control point.
	 * @param pt The point whose coordinate we want to change.
	 * @param groundCoord The new ground coordinate.
	 */
	public void modifyGCP(int index, Coordinate groundCoord) {
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate BCoord[] = {this.controlPoints.get(index).getBasicCoord()};
			CoordinateSequence BasicCoord = new CoordinateArraySequence(BCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			ControlPoint newPt = new ControlPoint(BasicCoord, newGroundCoord, geometryFactory);
			this.controlPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to delete a control point from the list.
	 * @param gcp The control point to delete.
	 */
	public void deleteGCP(ControlPoint gcp) {
		this.controlPoints.remove(gcp);
	}
	
	/**
	 * Method to delete a control point from the list.
	 * @param index The index of the control point to delete.
	 */
	public void deleteGCP(int index) {
		this.controlPoints.remove(index);
	}
	
	
	/**
	 * Method to add a check point to the list.
	 * @param cp The check point to add.
	 */
	public void addCP(CheckPoint cp) {
		this.checkPoints.add(cp);
	}
	
	/**
	 * Method to change the basic coordinates of a control point.
	 * @param pt The point you want to move.
	 * @param basicCoord The new basic coordinate.
	 */
	public void moveCP(ControlPoint pt, Coordinate basicCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			moveGCP(index, basicCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic coordinates of a control point.
	 * @param index The index (in the list) of the point you want to move.
	 * @param basicCoord The new basic coordinate.
	 */
	public void moveCP(int index, Coordinate basicCoord){
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate GCoord[] = {this.checkPoints.get(index).getGroundCoord()};
			CoordinateSequence GroundCoord = new CoordinateArraySequence(GCoord);
			CheckPoint newPt = new CheckPoint(newBasicCoord, GroundCoord, geometryFactory);
			this.checkPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic and ground coordinates of a control point.
	 * @param pt The point you want to move.
	 * @param basicCoord The new basic coordinate.
	 * @param groundCoord The new ground coordinate.
	 */
	public void moveCP(CheckPoint pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.checkPoints.indexOf(pt);
			moveCP(index, basicCoord, groundCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the basic and ground coordinates of a control point.
	 * @param index The index (in the list) of the point you want to move.
	 * @param basicCoord The new basic coordinate.
	 * @param groundCoord The new ground coordinate.
	 */
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
	
	/**
	 * Method to change the ground coordinates of a control point.
	 * @param pt The point whose coordinate we want to change.
	 * @param groundCoord The new ground coordinate.
	 */
	public void modifyCP(CheckPoint pt, Coordinate groundCoord) {
		
		try{
			int index = this.checkPoints.indexOf(pt);
			modifyCP(index, groundCoord);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to change the ground coordinates of a control point.
	 * @param pt The point whose coordinate we want to change.
	 * @param groundCoord The new ground coordinate.
	 */
	public void modifyCP(int index, Coordinate groundCoord) {
		
		try{
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate BCoord[] = {this.checkPoints.get(index).getBasicCoord()};
			CoordinateSequence BasicCoord = new CoordinateArraySequence(BCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			CheckPoint newPt = new CheckPoint(BasicCoord, newGroundCoord, geometryFactory);
			this.checkPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	/**
	 * Method to delete a control point from the list.
	 * @param pt The check point to delete.
	 */
	public void deleteCP(CheckPoint pt) {
		this.checkPoints.remove(pt);
	}
	
	/**
	 * Method to delete a control point from the list.
	 * @param index The index of the check point to delete.
	 */
	public void deleteCP(int index) {
		this.checkPoints.remove(index);
	}
	

}
