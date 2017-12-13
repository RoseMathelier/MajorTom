package ensg.tsi.majortom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class Context {
	
	//Attributes
	private List<PointConnu> controlPoints;
	private List<PointConnu> checkPoints;
	private File targetFile;
	
	//Constructor
	public Context(){
		this.controlPoints = new ArrayList<PointConnu>();
		this.checkPoints = new ArrayList<PointConnu>();
		this.targetFile = null;
	}
	
	//Getters
	public List<PointConnu> getControlPoints(){
		return this.controlPoints;
	}
	
	public PointConnu getControlPoint(int index){
		return this.controlPoints.get(index);
	}
	
	public List<PointConnu> getCheckPoints(){
		return this.checkPoints;
	}
	
	public PointConnu getCheckPoint(int index){
		return this.checkPoints.get(index);
	}
	
	public File getTargetFile(){
		return this.targetFile;
	}
	
	
	//Add, move and delete ground control points
	public void addGCP(PointConnu pt) {
		this.controlPoints.add(pt);
	}
	
	public void moveGCP(PointConnu pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.controlPoints.indexOf(pt);
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			PointConnu newPt = new PointConnu(newBasicCoord, newGroundCoord, geometryFactory);
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
			PointConnu newPt = new PointConnu(newBasicCoord, newGroundCoord, geometryFactory);
			this.controlPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void deleteGCP(PointConnu pt) {
		this.controlPoints.remove(pt);
	}
	
	public void deleteGCP(int index) {
		this.controlPoints.remove(index);
	}
	
	//Add, move and delete check points
	public void addCP(PointConnu pt) {
		this.checkPoints.add(pt);
	}
	
	public void moveCP(PointConnu pt, Coordinate basicCoord, Coordinate groundCoord) {
		
		try{
			int index = this.checkPoints.indexOf(pt);
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate newBCoord[] = {basicCoord};
			CoordinateSequence newBasicCoord = new CoordinateArraySequence(newBCoord);
			Coordinate newGCoord[] = {groundCoord};
			CoordinateSequence newGroundCoord = new CoordinateArraySequence(newGCoord);
			PointConnu newPt = new PointConnu(newBasicCoord, newGroundCoord, geometryFactory);
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
			PointConnu newPt = new PointConnu(newBasicCoord, newGroundCoord, geometryFactory);
			this.checkPoints.set(index, newPt);
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void deleteCP(PointConnu pt) {
		this.checkPoints.remove(pt);
	}
	
	public void deleteCP(int index) {
		this.checkPoints.remove(index);
	}
	
	//Target file setter
	public void setTargetFile(File file){
		this.targetFile = file;
	}

}
