package ensg.tsi.majortom;

public abstract class Georeferencer {
	
	private Context context;
	private TransformationFactory transfoFactory;
	
	public Context getContext() {
		return this.getContext();
	}
	
	public void setContext(Context c){
		this.context = c;
	}
	
	public TransformationFactory getTransfoFactory() {
		return this.getTransfoFactory();
	}
	
	public abstract void applyTransfo(TypeTransfo type);

}
