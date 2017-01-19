import java.awt.Graphics;
import java.awt.Point;

public class DLine extends DShape implements ModelListener{

	public DLine() {
		
		super(new DLineModel());
		super.getModel().addListener(this);
		
	}
	
	@Override
	public void draw(Graphics g){
		
		DLineModel model = (DLineModel)super.getModel();
		Point first = model.getP1();
		Point second = model.getP2();
		g.setColor(model.getColor());
		g.drawLine((int)first.getX(), (int)first.getY(),
				(int)second.getX(), (int)second.getY());
		
	}
	
	@Override
	public void modelChanged(DShapeModel model) {
		
		Canvas canvas = Canvas.getInstance();
		canvas.repaint();
		
	}
	
}
