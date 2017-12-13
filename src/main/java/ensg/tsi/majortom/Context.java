package ensg.tsi.majortom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Context {
	
	private List<PointConnu> controlPoints;
	private List<PointConnu> checkPoints;
	private File targetFile;
	
	
	public Context(){
		this.controlPoints = new ArrayList<PointConnu>();
		this.checkPoints = new ArrayList<PointConnu>();
		this.targetFile = null;
	}
	
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
	
	
	public void addGCP(PointConnu pt) {
		this.controlPoints.add(pt);
	}
	
	public void moveGCP() {
		
	}
	
	public void deleteGCP(PointConnu pt) {
		this.controlPoints.remove(pt);
	}
	
	public void addCP(PointConnu pt) {
		this.checkPoints.add(pt);
	}
	
	public void moveCP() {
		
	}
	
	public void deleteCP(PointConnu pt) {
		this.checkPoints.remove(pt);
	}
	
	public void setTargetFile(File file){
		this.targetFile = file;
	}

}
