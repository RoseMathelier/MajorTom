package ensg.tsi.majortom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class ShapefileUtils {
	
	public static void readShp() throws IOException {
		
		File file = new File("outputs/result.shp");
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("url", file.toURI().toURL());

	    DataStore dataStore = DataStoreFinder.getDataStore(map);
	    String typeName = dataStore.getTypeNames()[0];

	    FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore
	            .getFeatureSource(typeName);
	    Filter filter = Filter.INCLUDE;

	    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
	    
	    try {
	    	FeatureIterator<SimpleFeature> features = collection.features();
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	        }
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    finally {
	    
	    }
	}
	
	public static FeatureCollection<SimpleFeatureType, SimpleFeature> getFeatureCollectionsFromShp(File file) throws IOException{
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("url", file.toURI().toURL());

	    DataStore dataStore = DataStoreFinder.getDataStore(map);
	    String typeName = dataStore.getTypeNames()[0];

	    FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore
	            .getFeatureSource(typeName);
	    Filter filter = Filter.INCLUDE;

	    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
	    
	    return collection;
	}
	
	
	public static void writeShp() throws SchemaException, IOException {
		
		//Type creation
		
		SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
		b.setName("MyFeatureType");
		b.setCRS(DefaultGeographicCRS.WGS84);
		b.add("the_geom", Point.class);
		b.add("X", Double.class);
		b.add("Y", Double.class);
		final SimpleFeatureType featureType = b.buildFeatureType();
		
		//Feature collection creation
		
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		List<SimpleFeature> features = new ArrayList<SimpleFeature>();
		
		Point point1 = geometryFactory.createPoint(new Coordinate(50, 50));
		featureBuilder.add(point1);
		featureBuilder.add(point1.getX());
		featureBuilder.add(point1.getY());
		SimpleFeature feature = featureBuilder.buildFeature( "fid.1" );
		features.add(feature);
		
		Point point2 = geometryFactory.createPoint(new Coordinate(55, 45));
		featureBuilder.add(point2);
		featureBuilder.add(point2.getX());
		featureBuilder.add(point2.getY());
		feature = featureBuilder.buildFeature( "fid.2" );
		features.add(feature);
		
		Point point3 = geometryFactory.createPoint(new Coordinate(45, 55));
		featureBuilder.add(point3);
		featureBuilder.add(point3.getX());
		featureBuilder.add(point3.getY());
		feature = featureBuilder.buildFeature( "fid.3" );
		features.add(feature);
		
		//Shapefile creation
		
		FileDataStoreFactorySpi factory = FileDataStoreFinder.getDataStoreFactory("shp");

		File file = new File("outputs/test.shp");
		Map map = Collections.singletonMap("url", file.toURI().toURL());

		DataStore dataStore = factory.createNewDataStore(map);

		dataStore.createSchema(featureType);
		
		//Transaction
		
		Transaction transaction = new DefaultTransaction("create");

        String typeName = dataStore.getTypeNames()[0];
        SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeName);

        if (featureSource instanceof SimpleFeatureStore) {
        	
            SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
            SimpleFeatureCollection collection = new ListFeatureCollection(featureType, features);
            featureStore.setTransaction(transaction);
            
            try {
                featureStore.addFeatures(collection);
                transaction.commit();

            } catch (Exception problem) {
                problem.printStackTrace();
                transaction.rollback();

            } finally {
                transaction.close();
            }

        } else {
            System.out.println(typeName + " does not support read/write access");
            System.exit(1);
        }
		
	}
		


}
