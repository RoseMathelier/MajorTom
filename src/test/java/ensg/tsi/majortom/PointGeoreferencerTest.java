package ensg.tsi.majortom;

import org.geotools.feature.FeatureIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.opengis.feature.simple.SimpleFeature;


@RunWith(MockitoJUnitRunner.class)
public class PointGeoreferencerTest {
	
	
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testApplyTransfo() {
		
		//Set up
		
		//Test
		//FeatureIterator<SimpleFeature> features = ShapefileUtils.getFeatureCollectionsFromShp(file);
	}

}
