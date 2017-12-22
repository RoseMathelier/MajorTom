package ensg.tsi.majortom;

import java.io.File;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Concrete class for lines georeferencer.
 * This class is where the transformation is really applied to the input point layer.
 * @author Rose Mathelier
 *
 */
public class LineGeoreferencer extends Georeferencer {

	/**
	 * Method to apply the transformation to the context.
	 * This does the real work.
	 * Calls the transformation factory, creates the transformation, set the parameters using GCPs, apply the parameters to the features, and generates a report with the results (parameters, residuals, accuracy).
	 * @param type The type of transformation to apply.
	 */
	@Override
	public void applyTransfo(TypeTransfo type) {
		
		//Get the transformation
		Context context = this.getContext();
		Transformation transfo = this.getTransfoFactory().createTransfo(type);
		transfo.setControlPoints(context.getControlPoints());
		transfo.setCheckPoints(context.getCheckPoints());
		transfo.setTransfoFromGCP();
		Parameters param = transfo.getParam();
						
		//Get the input and output path
		String inputPath = context.getInputPath();
		String outputPath = context.getOutputPath();
		String outputName = context.getOutputName();
								
		//Get the layer
		File layerFile = new File(inputPath);		
				
		//Get the coordinates
		List<Coordinate[]> coords = ShapefileReader.getCoordsFromShp(layerFile);
				
		//Compute new coordinates using transformation parameters
		List<Coordinate[]> newCoords = param.applyParam(coords);
				
		//Write output layer with new coordinates
		ShapefileWriter writer = new ShapefileLineWriter();
		writer.writeShp(newCoords, outputPath, outputName);
				
		//Compute accuracy
		transfo.computeAccuracy();
				
		//Generate report
		transfo.generateReport(outputPath);	
		
	}

}
