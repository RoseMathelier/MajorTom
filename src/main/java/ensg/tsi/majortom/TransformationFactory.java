package ensg.tsi.majortom;

enum TypeTransfo{
	LINEAIRE, 
	HELBERT
}

public class TransformationFactory {
	
	public Transformation createTransfo(TypeTransfo type) {
		
		Transformation transfo;
		
		switch (type) {
			case LINEAIRE:
				transfo = new LinearTransfo();
			case HELBERT:
				transfo = new HelbertTransfo();
			default:
				//gérer exception
				transfo = new HelbertTransfo();
		}
		
		return transfo;
	}

}
