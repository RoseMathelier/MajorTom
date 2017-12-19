package ensg.tsi.majortom;

import java.io.File;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class LineGeoreferencer extends Georeferencer {

	@Override
	public void applyTransfo(List<ControlPoint> GCPs, List<CheckPoint> CPs, TypeTransfo type) {
		
		//Get the transformation
		Transformation transfo = this.getTransfoFactory().createTransfo(type);
		transfo.setTransfoFromGCP(GCPs);
		Parameters param = transfo.getParam();
						
		//Get the input and output path
		Context context = this.getContext();
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
		transfo.computeAccuracy(CPs);
				
		//Generate report
		transfo.generateReport(outputPath);	
		
	}

}
