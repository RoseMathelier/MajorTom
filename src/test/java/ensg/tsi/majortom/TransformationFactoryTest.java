package ensg.tsi.majortom;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransformationFactoryTest {

	@Test
	public void testCreateTransfo() {
		
		TransformationFactory tf = new TransformationFactory();
		Transformation transfoLineaire = tf.createTransfo(TypeTransfo.LINEAIRE);
		assertTrue(transfoLineaire.getClass() == LinearTransfo.class);
		
		Transformation transfoHelmert = tf.createTransfo(TypeTransfo.HELMERT);
		assertTrue(transfoHelmert.getClass() == HelmertTransfo.class);
	}


}
