
import java.awt.Graphics;

public class DOval extends DShape implements ModelListener{

	public DOval() {
		
		super(new DOvalModel());
		super.getModel().addListener(this);
		
	}
	
	@Override
	public void draw(Graphics g){
		DShapeModel model = super.getModel();
        g.setColor(model.getColor());
        if(model.getWidth() >= 0 && model.getHeight() >= 0){
            g.drawOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
            g.fillOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        }
        else if(model.getWidth() >= 0 && model.getHeight() < 0){
            g.drawOval(model.getX(), model.getY() - Math.abs(model.getHeight()), model.getWidth(), Math.abs(model.getHeight()));
            g.fillOval(model.getX(), model.getY() - Math.abs(model.getHeight()), model.getWidth(), Math.abs(model.getHeight()));
        }
        else if(model.getWidth() < 0 && model.getHeight() >= 0){
            g.drawOval(model.getX() - Math.abs(model.getWidth()), model.getY(), Math.abs(model.getWidth()), model.getHeight());
            g.fillOval(model.getX() - Math.abs(model.getWidth()), model.getY(), Math.abs(model.getWidth()), model.getHeight());
        }
        else if(model.getWidth() < 0 && model.getHeight() < 0){
            g.drawOval(model.getX() - Math.abs(model.getWidth()), model.getY() - Math.abs(model.getHeight()), Math.abs(model.getWidth()), Math.abs(model.getHeight()));
            g.fillOval(model.getX() - Math.abs(model.getWidth()), model.getY() - Math.abs(model.getHeight()), Math.abs(model.getWidth()), Math.abs(model.getHeight()));
        }

		
	}
	
	@Override
	public void modelChanged(DShapeModel model) {
		
		Canvas canvas = Canvas.getInstance();
		canvas.repaint();
		
	}
	
}
