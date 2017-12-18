package ensg.tsi.majortom;

import java.io.File;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class LineGeoreferencer extends Georeferencer {

	@Override
	public void applyTransfo(List<ControlPoint> GCPs, List<CheckPoint> CPs, TypeTransfo type) {
		
		// TODO: adapt to lines
		
		//Get the transformation
		Transformation transfo = this.getTransfoFactory().createTransfo(type);
		transfo.setTransfoFromGCP(GCPs);
		Parameters param = transfo.getParam();
						
		//Get the input and output path
		Context context = this.getContext();
		String inputPath = context.getInputPath();
		String outputPath = context.getOutputPath();
								
		//Get the layer
		File layerFile = new File(inputPath);
		
		/*
		 * For each line in the layer :
		 * Get the points
		 * Then get the coordinates (Geometry.getCoordinates)
		 * Then compute the new coordinates
		 * Then write the line in the output layer
		 */
				
		//Get the coordinates
		List<Coordinate> coords = ShapefileUtils.getPointsCoordsFromShp(layerFile);
				
		//Compute new coordinates using transformation parameters
		List<Coordinate> newCoords = param.applyParam(coords);
				
		//Write output layer with new coordinates
		ShapefileUtils.writePointsShp(newCoords, outputPath);
				
		//TODO: Generate report		
		
	}

}
