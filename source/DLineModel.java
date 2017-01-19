import java.awt.Point;
import java.awt.Rectangle;

public class DLineModel extends DShapeModel{

	private Point p1, p2;
	private int width, height;
	
	public DLineModel(){
		
		p1 = new Point(10, 10);
		p2 = new Point(50, 50);
		setSize();
		
	}
	
	private void setSize(){
		
		if(p2.getX() > p1.getX())
			width = (int)(p2.getX() - p1.getX());
		else
			width = (int)(p1.getX() - p2.getX());
		if(p2.getY() > p1.getY())
			height = (int)(p2.getY() - p1.getY());
		else
			height = (int)(p1.getY() - p2.getY());
		
		
	}
	
	@Override
	public String[] getDataArray(){
		
		return new String[]{(int)p1.getX()+"", (int)p1.getY()+"", 
				(int)p2.getX()+"", (int)p2.getY()+""};
		
	}
	
	@Override
	public Rectangle getRectangle(){
		
		return new Rectangle((int)p1.getX(),(int)p1.getY(),
				width,height);
		
	}
	
	public Rectangle getKnob1(){
		
		return new Rectangle((int)p1.getX()-5, (int)p1.getY()-5, 10, 10);
		
	}
	
	public Rectangle getKnob2(){

		return new Rectangle((int)p2.getX()-5, (int)p2.getY()-5, 10, 10);
		
	}
	
	/**
	 * @return the width
	 */
	public int getWidthLine() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeightLine() {
		return height;
	}

	/**
	 * @return the p1
	 */
	public Point getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
		setSize();
		super.notifyListeners();
	}

	/**
	 * @return the p2
	 */
	public Point getP2() {
		return p2;
	}

	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
		setSize();
		super.notifyListeners();
	}
	
	
	
}
