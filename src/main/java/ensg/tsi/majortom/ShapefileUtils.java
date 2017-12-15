package ensg.tsi.majortom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
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

	    FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
	    Filter filter = Filter.INCLUDE;

	    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
	    
	    try {
	    	FeatureIterator<SimpleFeature> features = collection.features();
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	            System.out.println(feature.toString());
	        }
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    finally {
	    
	    }
	}
	
	public static FeatureCollection<SimpleFeatureType, SimpleFeature> getFeatureCollectionsFromShp(File file) {
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    try {
			map.put("url", file.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	    DataStore dataStore;
		try {
			dataStore = DataStoreFinder.getDataStore(map);
			String typeName = dataStore.getTypeNames()[0];
			FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
			Filter filter = Filter.INCLUDE;
		    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
		    return collection;
		} 
		catch (IOException e) {
			e.printStackTrace();
			FeatureCollection<SimpleFeatureType, SimpleFeature> collection = new DefaultFeatureCollection();
			return collection;
		}
	}
	
	public static List<Coordinate> getPointsCoordsFromShp(File file){
		
		List<Coordinate> lCoord = new ArrayList<Coordinate>();
		
		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = getFeatureCollectionsFromShp(file);
		try {
	    	FeatureIterator<SimpleFeature> features = collection.features();
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	            Point pt = (Point) feature.getDefaultGeometryProperty().getValue();
	            lCoord.add(pt.getCoordinate());
	        }
	    }
		catch(Exception e){
			System.out.println(e);
		}
		
		return lCoord;
		
	}
	
	
	public static void writePointsShp(List<Coordinate> coords, String outPath) {
		
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
				
		for(Coordinate coord: coords){
			Point point = geometryFactory.createPoint(coord);
			featureBuilder.add(point);
			featureBuilder.add(point.getX());
			featureBuilder.add(point.getY());
			SimpleFeature feature = featureBuilder.buildFeature( "fid.1" );
			features.add(feature);
		}
		
		//Layer creation
		
		FileDataStoreFactorySpi factory = FileDataStoreFinder.getDataStoreFactory("shp");
		File file = new File(outPath);
		Map map;
		
		try {
			
			map = Collections.singletonMap("url", file.toURI().toURL());
			DataStore dataStore;
			
			try {
				
				dataStore = factory.createNewDataStore(map);
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

		            } 
		            catch (Exception problem) {
		                problem.printStackTrace();
		                transaction.rollback();
		            } 
		            finally {
		                transaction.close();
		            }

		        } 
		        else {
		        	System.out.println(typeName + " does not support read/write access");
		            System.exit(1);
		        }
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}   
	}
}
