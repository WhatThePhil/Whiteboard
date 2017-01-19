
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class DShapeModel {

	private int x = 30, y = 30, 
			width = 75, height = 50;
	private Color color = Color.gray;
	private int index;
	private List<ModelListener> listeners;
	
	public DShapeModel() {
	
		listeners = new ArrayList<ModelListener>();
		
	}
	
	public void addListener(ModelListener listener){
		
		listeners.add(listener);
		
	}
	
	public void removeListener(ModelListener listener){
		
		listeners.remove(listener);
		
	}
	
	public void notifyListeners(){
		
		for(ModelListener listener: listeners)
			listener.modelChanged(this);
		
	}
	
	public Rectangle getRectangle(){
		
		return new Rectangle(x,y,width,height);
		
	}
	
	public int getX() {
		
		return x;
	
	}

	public void setX(int x) {
	
		this.x = x;
		notifyListeners();
	
	}

	public int getY() {
	
		return y;
	
	}

	public void setY(int y) {
	
		this.y = y;
		notifyListeners();
	
	}

	public int getWidth() {
	
		return width;

	}

	public void setWidth(int width) {
	
		this.width = width;
		notifyListeners();
		
	}

	public int getHeight() {
	
		return height;
	
	}

	public void setHeight(int height) {
	
		this.height = height;
		notifyListeners();
		
	}

	public Color getColor() {
	
		return color;
	
	}

	public void setColor(Color color) {
	
		this.color = color;
		notifyListeners();
	
	}

	public String[] getDataArray(){
		
		return new String[]{x+"",y+"",width+"",height+""};
		
	}

	public int getIndex() {

		return index;
	
	}
	
	public void setIndex(int index) {
	
		this.index = index;
	
	}
	
	public Rectangle getKnob1(){
		
		return new Rectangle(x-5, y-5, 10, 10);
		
	}
	
	public Rectangle getKnob2(){
		
		return new Rectangle(x+width-5, y-5, 10, 10);
		
	}
	
	public Rectangle getKnob3(){
		
		return new Rectangle(x+width-5, y+height-5, 10, 10);
		
	}
	
	public Rectangle getKnob4(){
		
		return new Rectangle(x-5, y+height-5, 10, 10);
		
	}
	
}
