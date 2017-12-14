package ensg.tsi.majortom;

import java.util.List;

public class Parameters {
	
	private List<Double> translation;
	private List<Double> rotation;
	private List<Double> scale;
	
	public List<Double> getTranslationParams(){
		return this.translation;
	}
	
	public List<Double> getRotationParams(){
		return this.rotation;
	}

	public List<Double> getScaleParams(){
		return this.scale;
	}
	
	public void setTranslationParams(List<Double> tl){
		this.translation = tl;
	}
	
	public void setRotationParams(List<Double> tr){
		this.rotation = tr;
	}
	
	public void setScaleParams(List<Double> ts){
		this.scale = ts;
	}


}
