package ensg.tsi.majortom;

import java.util.List;

public abstract class Georeferencer {
	
	private Context context;
	private TransformationFactory transfoFactory;
	
	public Georeferencer() {
		this.transfoFactory = new TransformationFactory();
	}
	
	public Context getContext() {
		return this.context;
	}
	
	public TransformationFactory getTransfoFactory() {
		return this.transfoFactory;
	}
	
	public void setContext(String inputPath, String outputPath, String outputName){
		
		//TODO: handle exceptions (invalid path)
		Context c = new Context();
		c.setInputPath(inputPath);
		c.setOutputPath(outputPath);
		c.setOutputName(outputName);
		this.context = c;
	}
	
	//TODO: handle exception (not enough GCPs)
	
	public abstract void applyTransfo(List<ControlPoint> GCPs, List<CheckPoint> CPs, TypeTransfo type);

}
