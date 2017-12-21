package ensg.tsi.majortom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Abstract class for writing shapefiles : implemented by points, lines and polygons writers.
 * @author Rose Mathelier
 *
 */
public abstract class ShapefileWriter {
	
	/**
	 * Method to create the SimpleFeatureType.
	 * @return The SimpleFeatureType
	 * @see org.opengis.feature.simple.SimpleFeatureType
	 */
	public abstract SimpleFeatureType createFeatureType();
	
	/**
	 * Method to create a list of SimpleFeature with their coordinates and a FeatureSimpleType previously created.
	 * @param coords The coordinates (in a list of array, one array = one feature) of the features we want to create.
	 * @param featureType The FeatureType
	 * @return The list of SimpleFeature.
	 * @see org.opengis.feature.simple.SimpleFeatureType
	 * @see org.opengis.feature.simple.SimpleFeature;
	 */
	public abstract List<SimpleFeature> createFeatureList(List<Coordinate[]> coords, SimpleFeatureType featureType);
	
	/**
	 * Method to create a shapefile from a list of arrays of coordinates (one array = one feature).
	 * @param coords The coordinates (in a list of array, one array = one feature) of the features we want to create.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 */
	public void writeShp(List<Coordinate[]> coords, String outputPath, String outputName) {
		
		//Type creation
		SimpleFeatureType featureType = createFeatureType();
		
		//Feature collection creation
		List<SimpleFeature> features = createFeatureList(coords, featureType);
		
		//Layer creation
		createLayer(outputPath, outputName, featureType, features);
		  
	}
	
	/**
	 * Method to create a layer from a list of features.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 * @param featureType The FeatureType.
	 * @param features The list of SimpleFeature.
	 * @see org.opengis.feature.simple.SimpleFeatureType
	 * @see org.opengis.feature.simple.SimpleFeature
	 */
	public void createLayer(String outputPath, String outputName, SimpleFeatureType featureType, List<SimpleFeature> features){
		
		FileDataStoreFactorySpi factory = FileDataStoreFinder.getDataStoreFactory("shp");
		File file = new File(outputPath + "/" + outputName + ".shp");
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

		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	}
}
