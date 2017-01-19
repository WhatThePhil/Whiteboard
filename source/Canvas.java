import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Canvas extends JPanel{

	private ArrayList<DShape> shapes;
	private int selected = -1;
	private static Canvas canvas;
	private boolean canMove = false;
	private int resize = -1;
	private boolean line = false;
    private Whiteboard wb;
	
	public static Canvas getInstance(){
		
		if(canvas == null)
			canvas = new Canvas();
		
		return canvas;
		
	}
	
	private Canvas(){
		
		shapes = new ArrayList<DShape>();
		setBounds(315, 0, 400, 400);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK, 1));
		
		addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent event) {
			
				boolean select = false;
				line = false;
				Point p = event.getPoint();
				for(int i = 0; i < shapes.size(); i++){
					
					if(shapes.get(i).getModel().getRectangle().contains(p)){
						selected = i;
						select = true;
					}
					
				}
				
				if(!select)
					selected = -1;
				else if(shapes.get(selected).getModel() instanceof DLineModel){
					line = true;
				}
				
				Canvas.this.repaint();
                wb = Whiteboard.getInstance();
                wb.startClient();

				
			}

			@Override
			public void mousePressed(MouseEvent event) {
				
				if(selected != -1){
					
					Point p = event.getPoint();
					if(line){
						
						DLineModel model = (DLineModel)shapes.get(selected).getModel();
						if(model.getKnob1().contains(p))
							resize = 1;
						else if(model.getKnob2().contains(p))
							resize = 2;
						else if(model.getRectangle().contains(p))
							canMove = true;
						
					}else{
						DShapeModel model = shapes.get(selected).getModel();
						if(model.getKnob1().contains(p))
							resize = 1;
						else if(model.getKnob2().contains(p))
							resize = 2;
						else if(model.getKnob3().contains(p))
							resize = 3;
						else if(model.getKnob4().contains(p))
							resize = 4;
						else if(model.getRectangle().contains(p))
							canMove = true;
					}
					
				}
                wb = Whiteboard.getInstance();
                wb.startClient();
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				canMove = false;
				resize = -1;
                wb = Whiteboard.getInstance();
                wb.startClient();
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter(){

			@Override
			public void mouseDragged(MouseEvent event) {
			
				if(selected != -1){
					
					if(canMove){
						
						if(line){
							
							DLineModel model = (DLineModel)shapes.get(selected).getModel();
							                                                                             
							int tempx = event.getX() + model.getWidthLine();
							int tempy = event.getY() + model.getHeightLine();
							
							if(tempx <= 395 && event.getX() >= 5 && tempy <= 395 && event.getY() >= 5){
							
								int x = event.getX();
								int y = event.getY();
								model.setP2(new Point(x+model.getWidthLine(),y+model.getHeightLine()));
								model.setP1(new Point(x, y));
							
							}
							
						}else{
							
							DShapeModel model = shapes.get(selected).getModel();
							int x = event.getX() + (model.getWidth());
							int y = event.getY() + model.getHeight();
							
							if(x <= 395 && event.getX() >= 5 && y <= 395 && event.getY() >= 5){
								
								model.setX(event.getX());
								model.setY(event.getY());
								
							}
							
						}
						
						UpdateTable();
						
					}else if(resize != -1){
						
						if(line){
							
							DLineModel model = (DLineModel)shapes.get(selected).getModel();
							int x = event.getX();
							int y = event.getY();
							
							if(resize == 1)	
								model.setP1(new Point(x,y));
							else	
								model.setP2(new Point(x,y));
							
							
						}else{
							
							DShapeModel model = shapes.get(selected).getModel();
							int x = event.getX();
							int y = event.getY();
							if(resize == 1){
								
								model.setWidth(model.getWidth() - (x - model.getX()));
								model.setHeight(model.getHeight() - (y - model.getY()));
								model.setX(x);
								model.setY(y);
								
							}else if(resize == 2){
								
								model.setWidth(model.getWidth() + (x - (model.getX() + model.getWidth())));
								model.setHeight(model.getHeight() + (model.getY() - y));
								model.setY(y);
								
							}else if(resize == 3){
								
								model.setWidth(model.getWidth() + (x - (model.getX() + model.getWidth())));
								model.setHeight(model.getHeight() + (y - (model.getY() + model.getHeight())));
								
							}else if(resize == 4){
								
								model.setWidth(model.getWidth() - (x - model.getX()));
								model.setHeight(model.getHeight() + (y - (model.getY() + model.getHeight())));
								model.setX(x);
								model.setY(y - model.getHeight());
								
							}
							
						}
						
						UpdateTable();
					}
					
				}
                wb = Whiteboard.getInstance();
                wb.startClient();

			}
			
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
	
		super.paintComponent(g);
		
		for(int i = 0; i < shapes.size(); i++)
			shapes.get(i).draw(g);
		
		if(selected != -1){
			
			if(line){
				DLineModel model = (DLineModel)shapes.get(selected).getModel();
				Rectangle rec1 = model.getKnob1();
				Rectangle rec2 = model.getKnob2();
				g.setColor(Color.BLACK);
				g.fillRect(rec1.x, rec1.y, rec1.width, rec1.height);
				g.fillRect(rec2.x, rec2.y, rec2.width, rec2.height);
			}else{
				DShapeModel model = shapes.get(selected).getModel();
				Rectangle rec1 = model.getKnob1();
				Rectangle rec2 = model.getKnob2();
				Rectangle rec3 = model.getKnob3();
				Rectangle rec4 = model.getKnob4();
				g.setColor(Color.BLACK);
				g.fillRect(rec1.x, rec1.y, rec1.width, rec1.height);
				g.fillRect(rec2.x, rec2.y, rec2.width, rec2.height);
				g.fillRect(rec3.x, rec3.y, rec3.width, rec3.height);
				g.fillRect(rec4.x, rec4.y, rec4.width, rec4.height);
			}
			
		}
		
	}
	
	public void addShape(DShapeModel model){
		
		if(model instanceof DRectModel)	
			shapes.add(new DRect());
		else if(model instanceof DOvalModel)	
			shapes.add(new DOval());
		else if(model instanceof DLineModel)	
			shapes.add(new DLine());
		else if(model instanceof DTextModel)
			shapes.add(new DText());
		
		shapes.get(shapes.size() - 1).getModel().setIndex(shapes.size() - 1);		
		UpdateTable();
		
		repaint();
		
	}
	
	public void setColorChange(Color color){
		
		if(selected != -1){
			
			shapes.get(selected).getModel().setColor(color);
			
		}else
			JOptionPane.showMessageDialog(null, "No any object is selected.");
		
	}
	
	public void RemoveSelected(){
		
		if(selected != -1){
			
			shapes.remove(selected);
			selected = -1;
			repaint();
			
		}else
			JOptionPane.showMessageDialog(null, "No any object is selected.");
		
	}
	
	public void moveToBack(){
		
		if(selected != -1){
			
			if(selected != 0){
				
				DShape shape = shapes.get(selected);
				shapes.set(selected, shapes.get(selected-1));
				shapes.set(selected-1, shape);
				selected--;
				repaint();
				
			}else
				JOptionPane.showMessageDialog(null, "Already at most back position.");
			
		}else
			JOptionPane.showMessageDialog(null, "No any object is selected.");
		
	}
	
	public void moveToFront(){
		
		if(selected != -1){
			
			if(selected != shapes.size() - 1){
				
				DShape shape = shapes.get(selected);
				shapes.set(selected, shapes.get(selected+1));
				shapes.set(selected+1, shape);
				selected++;
				repaint();
				
			}else
				JOptionPane.showMessageDialog(null, "Already at most front position.");
			
		}else
			JOptionPane.showMessageDialog(null, "No any object is selected.");
		
	}
	
	public void UpdateTable(int row, int[] data){
		
		if(shapes.get(row).getModel() instanceof DLineModel){
			
			DLineModel model = (DLineModel)shapes.get(row).getModel();
			if(data[0] > 5 && data[0] < 395)
				model.getP1().x = data[0];
			if(data[1] > 5 && data[1] < 395)
				model.getP1().y = data[1];
			if(data[2] > 5 && data[2] < 395)
				model.getP2().x = data[2];
			if(data[3] > 5 && data[3] < 395)
				model.getP2().y = data[3];
			
		}else{
		
			DShapeModel model = shapes.get(row).getModel();
			if(data[0] > 5 && (data[0]+data[2]) < 395)
				model.setX(data[0]);
			if(data[1] > 5 && (data[1]+data[3]) < 395)
				model.setY(data[1]);
			if((data[0]+data[2]) < 395)
				model.setWidth(data[2]);
			if((data[1]+data[3]) < 395)
				model.setHeight(data[3]);
			
		}
		
		UpdateTable();
		repaint();
		
	}
	
	private void UpdateTable(){
		
		String[][] data = new String[shapes.size()][4];
		for(int i = 0; i < shapes.size(); i++)
			if(shapes.get(i).getModel() instanceof DLineModel){
				DLineModel model = (DLineModel)shapes.get(i).getModel();
				data[i] = model.getDataArray();
			}else
				data[i] = shapes.get(i).getModel().getDataArray();
		
		Whiteboard.getInstance().setTableData(data);
		
	}

}
