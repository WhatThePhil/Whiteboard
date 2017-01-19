/*
PHILLIP ROGNERUD
CS151 SEC04
*/

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Whiteboard extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Canvas canvas;
    private Canvas canvas2;
	private JPanel panel;
	private JTextField input;
	private JTable table;
	private JScrollPane pane;
	private JButton rect;
	private JButton oval;
	private JButton line;
	private JButton text;
	private JButton color;
	private JComboBox<String> font;
	private JButton front;
	private JButton back;
	private JButton remove;
    private JButton save;
    private JButton open;
    private JButton server;
    private JButton client;
	public static Whiteboard board;
    
    private String textInput;
    private String saveFile;
    private FileChooserTest t;
    private Boolean c = false;
    
    private JFrame frame = new JFrame();
	
	public static Whiteboard getInstance(){
		
		if(board == null)
			board = new Whiteboard();
		return board;
		
	}
	
	private Whiteboard() {
		setTitle("Whiteboard");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(731, 440);
		setLocationRelativeTo(null);
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 731, 440);
		
		this.setContentPane(contentPane);
		
		canvas = Canvas.getInstance();
		contentPane.add(canvas);
		
		// Adding components..
		AddComponents();
		
	}
	
	/**
	 * Adding components to frame.
	 */
	private void AddComponents(){
		
		panel = new JPanel();
		panel.setBounds(0, 0, 316, 400);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel addTxt = new JLabel("Add");
		addTxt.setHorizontalAlignment(SwingConstants.CENTER);
		addTxt.setBounds(10, 29, 36, 14);
		panel.add(addTxt);
		
		rect = new JButton("Rect");
		rect.setBackground(Color.WHITE);
		rect.setBounds(56, 7, 110, 23);
		rect.addActionListener(this);
		panel.add(rect);
		
		oval = new JButton("Oval");
		oval.addActionListener(this);
		oval.setBackground(Color.WHITE);
		oval.setBounds(176, 7, 112, 23);
		panel.add(oval);
		
		line = new JButton("Line");
		line.setBackground(Color.WHITE);
		line.addActionListener(this);
		line.setBounds(56, 41, 110, 23);
		panel.add(line);
		
		text = new JButton("Text");
		text.setBackground(Color.WHITE);
		text.addActionListener(this);
		text.setBounds(176, 41, 112, 23);
		panel.add(text);
		
		color = new JButton("Set Color");
		color.setBackground(Color.WHITE);
		color.addActionListener(this);
		color.setBounds(10, 75, 89, 23);
		panel.add(color);
		
		input = new JTextField();
		input.setBounds(10, 109, 110, 20);
		panel.add(input);
		input.setColumns(10);
		
		font = new JComboBox<String>();
		font.setBounds(130, 109, 180, 20);
		font.setBackground(Color.WHITE);
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = environment.getAvailableFontFamilyNames();
		for(String f: fonts)
			font.addItem(f);
		panel.add(font);
		
		front = new JButton("Move to Front");
		front.setBounds(10, 140, 110, 23);
		front.setBackground(Color.WHITE);
		front.addActionListener(this);
		panel.add(front);
		
		back = new JButton("Move To Back");
		back.setBounds(130, 140, 120, 23);
		back.addActionListener(this);
		back.setBackground(Color.WHITE);
		panel.add(back);
		
		remove = new JButton("Remove");
		remove.addActionListener(this);
		remove.setBounds(10, 174, 110, 23);
		remove.setBackground(Color.WHITE);
		panel.add(remove);
        
        save = new JButton("Save/Open File");
		save.addActionListener(this);
		save.setBounds(130, 174, 120, 23);
		save.setBackground(Color.WHITE);
		panel.add(save);
        
        server = new JButton("Start Server");
		server.addActionListener(this);
		server.setBounds(130, 208, 120, 23);
		server.setBackground(Color.WHITE);
		panel.add(server);
        
        client = new JButton("Client");
		client.addActionListener(this);
		client.setBounds(10, 208, 110, 23);
		client.setBackground(Color.WHITE);
		panel.add(client);
        
		
		// adding table.
		setTableData(new String[][]{});
		
	}
    
    public String getInput(){
        return input.getText();
    }
    
    public String getF(){
        return String.valueOf(font.getSelectedItem());
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Object object = e.getSource();
		if(object == rect){
			canvas.addShape(new DRectModel());
            startClient();
        }
		else if(object == oval){
			canvas.addShape(new DOvalModel());
            startClient();
        }
		else if(object == line){
			canvas.addShape(new DLineModel());
            startClient();
        }
        else if(object == text){
            if(!input.getText().isEmpty()){
                canvas.addShape(new DTextModel());
                startClient();
            }
        }
		else if(object == color){
			
			Color color = JColorChooser.showDialog(null, "Choose Color", null);
			if(color != null){
				
				canvas.setColorChange(color);
                startClient();
			}
			
		}else if(object == remove){
			canvas.RemoveSelected();
            startClient();
        }
		else if(object == front){
			canvas.moveToFront();
            startClient();
        }
		else if(object == back){
			canvas.moveToBack();
            startClient();
        }
        else if(object == save)
			t.run(new FileChooserTest(), 250, 110);
        else if(object == server){
            JOptionPane.showInputDialog("Set a port number");
        }
        else if(object == client){
            JOptionPane.showInputDialog("Enter Port Number");
            c = true;
            startClient();
        }
            
	}
    
    public void startClient(){
        
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if(c == true){
                    frame.setTitle("Client");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBackground(Color.WHITE);
                    frame.setSize(731, 440);
                    frame.setLayout(null);
                    frame.setContentPane(contentPane);
                    frame.setVisible(true);
                    frame.setEnabled(true);
                    
                    board.setTitle("Whiteboard");
                    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    board.setBackground(Color.WHITE);
                    board.setSize(731, 440);
                    board.setLayout(null);
                    board.setContentPane(contentPane);
                    board.setVisible(true);
                    board.setEnabled(true);
                }
                
                return null;
            }

            @Override
            protected void done() {
                if(c==true){
                    board.setVisible(true);
                    board.setEnabled(true);
                }
                
            }
        };
        
        worker.execute();
    }
	
	public void setTableData(String[][] data){
		
		try{
			panel.remove(pane);
		}catch(Exception e){}
		
		table = new JTable(data, new String[]{"X","Y","Width","Height"});
		table.setBounds(0, 242, 314, 192);
		table.getModel().addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent event) {
				
				int row = event.getFirstRow();
				int[] data = new int[4];
				for(int i = 0; i < data.length; i++)
					data[i] = Integer.parseInt((String) table.getValueAt(row, i));
				
				canvas.UpdateTable(row, data);
			}
			
		});
		pane = new JScrollPane(table);
		pane.setBounds(0, 242, 314, 192);
		panel.add(pane);
		
	}
	

	
	public static void main(String[] args) {
		
		Whiteboard.getInstance().setVisible(true);
	
	}

}
