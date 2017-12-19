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
import com.vividsolutions.jts.geom.Point;

public class ShapefilePointWriter extends ShapefileWriter {

	@Override
	public SimpleFeatureType createFeatureType() {
		
		SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
		b.setName("PointFeatureType");
		b.setCRS(DefaultGeographicCRS.WGS84);
		b.add("the_geom", Point.class);
		b.add("control", Integer.class);
		final SimpleFeatureType featureType = b.buildFeatureType();
		
		return featureType;
	}

	@Override
	public List<SimpleFeature> createFeatureList(List<Coordinate[]> coords, SimpleFeatureType featureType) {
		
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		List<SimpleFeature> features = new ArrayList<SimpleFeature>();
				
		for(Coordinate[] coord: coords){
			Point point = geometryFactory.createPoint(coord[0]);
			featureBuilder.add(point);
			featureBuilder.add(0);
			SimpleFeature feature = featureBuilder.buildFeature( "fid.1" );
			features.add(feature);
		}
		return features;
	}

}
