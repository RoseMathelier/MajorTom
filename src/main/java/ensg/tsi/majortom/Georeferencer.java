package ensg.tsi.majortom;

public abstract class Georeferencer {
	
	private Context context;
	private TransformationFactory transfoFactory;
	
	public abstract void applyTransfo();

}
