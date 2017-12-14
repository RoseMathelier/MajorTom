package ensg.tsi.majortom;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vividsolutions.jts.geom.Coordinate;

@PrepareForTest(ShapefileUtils.class)
@RunWith(PowerMockRunner.class)
public class PointGeoreferencerTest {
	
	
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testApplyTransfo() throws IOException {
		
		//Mocking file
		File fileTest = Mockito.mock(File.class);
		
		//Mocking context
		Context c = Mockito.mock(Context.class);
		Mockito.when(c.getTargetFile()).thenReturn(fileTest);
		
		//Mocking static function getPointsCoordFromShp
		Coordinate c1 = new Coordinate(0,0,0);
		Coordinate c2 = new Coordinate(0,1,0);
		Coordinate c3 = new Coordinate(1,0,0);
		Coordinate c4 = new Coordinate(1,1,0);
		List<Coordinate> inputCoord = new ArrayList<Coordinate>();
		Collections.addAll(inputCoord, c1, c2, c3, c4);
		PowerMockito.mockStatic(ShapefileUtils.class);
		Mockito.when(ShapefileUtils.getPointsCoordsFromShp(fileTest)).thenReturn(inputCoord).thenCallRealMethod();
		
		//Mocking transformation factory
		TransformationFactory tf = Mockito.mock(TransformationFactory.class);
		Mockito.doNothing().when(tf.createTransfo(TypeTransfo.LINEAIRE));
		Mockito.doNothing().when(tf.createTransfo(TypeTransfo.HELBERT));
				
		//Mocking parameters
		Parameters p = Mockito.mock(Parameters.class);
		List<Double> tlTest = new ArrayList<Double>();
		List<Double> trTest = new ArrayList<Double>();
		List<Double> tsTest = new ArrayList<Double>();
		Collections.addAll(tlTest, 3.0, 4.0, 5.0);
		Collections.addAll(trTest, 2.0, 1.0, 8.0);
		tsTest.add(2.0);
		Mockito.when(p.getTranslationParams()).thenReturn(tlTest);
		Mockito.when(p.getRotationParams()).thenReturn(trTest);
		Mockito.when(p.getScaleParams()).thenReturn(tsTest);
		
		//Mocking transformation
		Transformation t = Mockito.mock(Transformation.class);
		Mockito.when(t.getNbMinGCP()).thenCallRealMethod();
		Mockito.when(t.getParam()).thenReturn(p);
		
		//Context set-up
		Georeferencer georef = new PointGeoreferencer();
		georef.setContext(c);
		
		//Expected output coordinates
		Coordinate c5 = new Coordinate(3,4,5);
		Coordinate c6 = new Coordinate(11,7,7);
		Coordinate c7 = new Coordinate(6,12,4);
		Coordinate c8 = new Coordinate(2,15,6);
		List<Coordinate> expectedCoord = new ArrayList<Coordinate>();
		Collections.addAll(expectedCoord, c5, c6, c7, c8);
		
		//Test
		georef.applyTransfo(TypeTransfo.HELBERT); //the type does not matter here because the transformation is not really created	
		List<Coordinate> outputCoord = ShapefileUtils.getPointsCoordsFromShp(c.getOutputFile());
		for(int i = 0; i < 4; i++){
			assertEquals(expectedCoord.get(i).getOrdinate(0), outputCoord.get(i).getOrdinate(0), 0.001);
			assertEquals(expectedCoord.get(i).getOrdinate(1), outputCoord.get(i).getOrdinate(1), 0.001);
			assertEquals(expectedCoord.get(i).getOrdinate(2), outputCoord.get(i).getOrdinate(2), 0.001);
		}
		
	}

}
