package ensg.tsi.majortom;

import java.util.ArrayList;
import java.util.List;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * Class for writing lines shapefiles.
 * @author Rose Mathelier
 *
 */
public class ShapefileLineWriter extends ShapefileWriter {

	/**
	 * Method to create the SimpleFeatureType for lines.
	 * @return The SimpleFeatureType
	 * @see org.opengis.feature.simple.SimpleFeatureType
	 */
	@Override
	public SimpleFeatureType createFeatureType() {
		
		SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
		b.setName("PointFeatureType");
		b.setCRS(DefaultGeographicCRS.WGS84);
		b.add("the_geom", LineString.class);
		b.add("control", Integer.class);
		final SimpleFeatureType featureType = b.buildFeatureType();
		
		return featureType;
	}

	/**
	 * Method to create a list of line SimpleFeature with their coordinates and a FeatureSimpleType previously created.
	 * @param coords The coordinates (in a list of array, one array = one line) of the features we want to create.
	 * @param featureType The FeatureType
	 * @return The list of SimpleFeature.
	 * @see org.opengis.feature.simple.SimpleFeatureType
	 * @see org.opengis.feature.simple.SimpleFeature;
	 */
	@Override
	public List<SimpleFeature> createFeatureList(List<Coordinate[]> coords, SimpleFeatureType featureType) {
		
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		List<SimpleFeature> features = new ArrayList<SimpleFeature>();
				
		for(Coordinate[] coord: coords){
			LineString line = geometryFactory.createLineString(coord);
			featureBuilder.add(line);
			featureBuilder.add(0);
			SimpleFeature feature = featureBuilder.buildFeature( "fid.1" );
			features.add(feature);
		}
		return features;
	}

}
