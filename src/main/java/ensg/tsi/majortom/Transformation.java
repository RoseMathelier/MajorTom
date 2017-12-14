package ensg.tsi.majortom;

public abstract class Transformation {
	
	private int nbMinGCP;
	private Parameters param;
	
	public abstract int getNbMinGCP();
	
	public Parameters getParam(){
		return this.param;
	}
	
	public abstract void setTransfoFromGCP();

}
