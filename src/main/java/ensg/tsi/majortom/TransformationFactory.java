package ensg.tsi.majortom;

public class TransformationFactory {
	
	public Transformation createTransfo(TypeTransfo type) {
		
		Transformation transfo = null;
		System.out.println(type);
		
		switch (type) {
			case LINEAIRE:
				transfo = new LinearTransfo();
				break;
			case HELMERT:
				transfo = new HelmertTransfo();
				break;
		}
		
		return transfo;
	}

}
