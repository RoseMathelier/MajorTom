package ensg.tsi.majortom;

import java.io.IOException;

/**
 * Abstract class for all types of georeferencer (points, lines, polygons).
 * This class is where the transformation is really applied to the input layer.
 * @author formation
 *
 */
public abstract class Georeferencer {
	
	/**
	 * The context of the transformation : input layer, output directory and name, GCPs and check points.
	 */
	private Context context;
	
	/**
	 * The transformation factory, that will create the transformation according to the chosen type.
	 */
	private TransformationFactory transfoFactory;
	
	/**
	 * Empty constructor for the class georeferencer.
	 */
	public Georeferencer() {
		this.transfoFactory = new TransformationFactory();
	}
	
	/**Context getter (input layer, output directory and name, GCPs and check points).
	 * 
	 * @return The context of transformation.
	 */
	public Context getContext() {
		return this.context;
	}
	
	/**
	 * Transformation factory getter.
	 * @return the transformation factory.
	 */
	public TransformationFactory getTransfoFactory() {
		return this.transfoFactory;
	}
	
	/**
	 * Method to set the context of transformation (input layer, output directory and name).
	 * @param inputPath The path of the shapefile to georeference.
	 * @param outputPath The directory where the output layer should be written.
	 * @param outputName The name of the georeferenced shapefile.
	 * @throws IOException
	 */
	public void setContext(String inputPath, String outputPath, String outputName) throws IOException{
		
		//TODO: handle exceptions (invalid path)
		Context c = new Context();
		c.setInputPath(inputPath);
		c.setOutputPath(outputPath);
		c.setOutputName(outputName);
		this.context = c;
	}
	
	//TODO: handle exception (not enough GCPs).
	
	/**
	 * Method to apply the transformation to the context.
	 * This does the real work.
	 * Calls the transformation factory, creates the transformation, set the parameters using GCPs, apply the parameters to the features, and generates a report with the results (parameters, residuals, accuracy).
	 * @param type The type of transformation to apply.
	 */
	public abstract void applyTransfo(TypeTransfo type);

}
