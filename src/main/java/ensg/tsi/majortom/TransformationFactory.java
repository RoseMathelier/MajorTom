package ensg.tsi.majortom;

public class TransformationFactory {
	
	public Transformation createTransfo(TypeTransfo type) {
		
		Transformation transfo;
		
		switch (type) {
			case LINEAIRE:
				transfo = new LinearTransfo();
			case HELBERT:
				transfo = new HelmertTransfo();
			default:
				//TODO : manage exception
				transfo = new HelmertTransfo();
		}
		
		return transfo;
	}

}
