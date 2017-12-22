package ensg.tsi.majortom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Class containing static methods to read a shapefile.
 * @author Rose Mathelier
 *
 */
public class ShapefileReader {
	
	
	/**
	 * Static method to get a collection of the features in a shapefile.
	 * @param file The file from where the features will be extracted.
	 * @return The features collection.
	 */
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
	
	/**
	 * Static method to get the coordinates of the features in a shapefile.
	 * @param file The file from where the coordinates will be extracted.
	 * @return A list of coordinate arrays (one array = one feature).
	 */
	public static List<Coordinate[]> getCoordsFromShp(File file){
		
		List<Coordinate[]> lCoord = new ArrayList<Coordinate[]>();
		
		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = getFeatureCollectionsFromShp(file);
		try {
	    	FeatureIterator<SimpleFeature> features = collection.features();
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	            Geometry geom = (Geometry) feature.getDefaultGeometry();
	            Coordinate[] coords = geom.getCoordinates();	  
	            lCoord.add(coords);
	        }
	    }
		catch(Exception e){
			System.out.println(e);
		}
		
		return lCoord;
	}

}
