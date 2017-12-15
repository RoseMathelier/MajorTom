package ensg.tsi.majortom;

public abstract class Georeferencer {
	
	private Context context;
	private TransformationFactory transfoFactory;
	
	public Context getContext() {
		return this.context;
	}
	
	public TransformationFactory getTransfoFactory() {
		return this.transfoFactory;
	}
	
	public void setContext(String inputPath, String outputPath){
		//TODO: handle exceptions !!
		Context c = new Context();
		c.setInputPath(inputPath);
		c.setOutputPath(outputPath);
		this.context = c;
	}
	
	public abstract void applyTransfo(TypeTransfo type);

}
