//package utility;
//
//import java.awt.*;
//import java.awt.image.*;
//import java.io.*;
//import java.util.*;
//
//import javax.imageio.*;
//import javax.swing.*;
//
//public class ViewRateCalculator extends JFrame{
//	private JScrollPane scrollPane;
//	private ImageIcon img;
//	public static int f_width;
//	public static int f_height;
//	public static ArrayList<Rect> rectArr = new ArrayList<Rect>();
//	public static JTextField tf = new JTextField(10);
//	
//	public ViewRateCalculator(){
//		super("calculator");
//		this.setResizable(false);
//		img = new ImageIcon("C:\\Users\\sunghoon\\Desktop\\map\\base_map.png");
//		f_width = img.getIconWidth();
//		f_height = img.getIconHeight();
//		
//		JPanel background = new JPanel() {
//			public void paintComponent(Graphics g) {
//				g.clearRect(0,0,this.getWidth(),this.getHeight());
//				g.drawImage(img.getImage(),0,0,this.getWidth(),this.getHeight(),this);
//				setOpaque(false);
//				for(int i = 0;i<rectArr.size();i++) {
//					rectArr.get(i).draw(g);
//				}
//				super.paintComponent(g);
//			}
//			
//			
//		};
//		
//		
//		
//		scrollPane = new JScrollPane(background);
//		scrollPane.addMouseListener(new Calc(background));
//		setContentPane(scrollPane);
//		add(tf);
//
//		setSize(f_width,f_height);
//		setVisible(true);
//	}
//	
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ViewRateCalculator();
//	}
//	
//
//
//}
