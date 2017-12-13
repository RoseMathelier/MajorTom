package ensg.tsi.majortom;

import java.io.File;

import org.geotools.feature.FeatureIterator;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.simple.SimpleFeature;

public class PointGeoreferencer extends Georeferencer{

	@Override
	public void applyTransfo(TypeTransfo type) {
		
		//Récupérer la transformation
		Transformation transfo = this.getTransfoFactory().createTransfo(type);
		
		//Lire le shapefile
		File layerFile = this.getContext().getTargetFile();
		
		//Récupérer les entités
		try {
	    	FeatureIterator<SimpleFeature> features = ShapefileUtils.getFeatureCollectionsFromShp(layerFile).features();
	        while (features.hasNext()) {
	            SimpleFeature feature = features.next();
	            GeometryAttribute geomAttr = feature.getDefaultGeometryProperty();
	        }
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
		
		//Récupérer les géométries
		
		//Récupérer les coordonnées
		
		//Modifier les coordonnées
		
		//Commit
		
		
	}

}
