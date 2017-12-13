package ensg.tsi.majortom;

import java.io.File;
import java.util.List;

public class Context {
	
	private List<PointConnu> GCPs;
	private List<PointConnu> checkPoints;
	private File targetLayer;
	
	public void addGCP(PointConnu pt) {
		this.GCPs.add(pt);
	}
	
	public void moveGCP() {
		
	}
	
	public void deleteGCP(PointConnu pt) {
		this.GCPs.remove(pt);
	}
	
	public void addCP(PointConnu pt) {
		this.checkPoints.add(pt);
	}
	
	public void moveCP() {
		
	}
	
	public void deleteCP(PointConnu pt) {
		this.checkPoints.remove(pt);
	}

}
