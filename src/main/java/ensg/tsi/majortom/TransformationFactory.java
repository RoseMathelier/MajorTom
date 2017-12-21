package ensg.tsi.majortom;

/**
 * A factory of transformation, to initialize concrete transformations according to the chosen type.
 * @author Rose Mathelier
 * @see TypeTransfo
 *
 */
public class TransformationFactory {
	
	/**
	 * Method to create a transformation according to the type
	 * @param type : the type of the transformation
	 * @see TypeTransfo
	 * @return the instance of transformation
	 */
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
