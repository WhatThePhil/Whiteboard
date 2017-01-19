
import java.awt.Graphics;

public class DShape{

	private DShapeModel model;
	
	public DShape(DShapeModel shapeModel){
		
		this.model = shapeModel;
		
	}
	
	public void draw(Graphics g){
		
	}

	public DShapeModel getModel() {
	
		return model;
	
	}

	public void setModel(DShapeModel model) {
	
		this.model = model;
	
	}

}
