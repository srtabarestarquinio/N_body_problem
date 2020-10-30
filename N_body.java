//Rose Tabares - CS245 - Assignment01 - N-Body-Problem
import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class N_body extends JPanel implements ActionListener{
	private String name;	//string to store name of the body
	private double mass;	//double to store mass of body in kg
	private int rx, ry;		//int to store cartesian positions, Initial x and y coordinate on the JFrame
	private double vx, vy;	//double to store velocity components, Initial x and y direction velocity
	private int size;		//int to store Size (in pixels) for rendering
	private Color color;	//color
	private double fx, fy; //double to store forces
	private static final double G = 6.673e-11; //gravitational constant

	//Class constructor:
	public N_body(String name, String mass, String rx, String ry, String vx, String vy, String size){
		this.name=name;
		this.mass=Double.parseDouble(mass);	
		this.rx=Integer.parseInt(rx);
		this.ry=Integer.parseInt(ry);
		this.vx=Double.parseDouble(vx);
		this.vy=Double.parseDouble(vy);
		this.size=Integer.parseInt(size.substring(1));//store size as the 1st value of the given size
		//random variable to generate a random int:
		Random rand = new Random();
		color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));//create a new random color for each boundary

	}
	private Lists<N_body> list;//iniate a list called bodies
	private double scale;
	public N_body(Lists<N_body> newList, double newScale){
		list = newList;
		scale = newScale;
	}
	//Force function:
	//calculate net force acting between the bodies
	public void force(N_body b, double scale){
		N_body a = this;
		double dx = b.rx - a.rx;
		double dy = b.ry - a.ry;
		//store distance (square root of sum of each diference in distance in each coordinate squared) in double variable "distance"
		//Pythagoran theorem: c^2 = a^2 + b^2, distance^2 = dx^2 + dy^2
		double distance = Math.sqrt((dx * dx) + (dy * dy));
		//Force = G(m1xm2)/r^2
		double force = (G*a.mass*b.mass)/((distance * distance)/scale);
		//set forces in each direction:
		a.fx += force*dx/distance;
		a.fy += force*dy/distance;
	}
	//set the force to 0 for the next iteration
	public void resetForce(){
		fx = 0.0;
		fy = 0.0;
	}
	//update velocity and position
	public void updatePosition(){
		//update velocities
		vx += fx/mass;
		vy += fy/mass;
		//update positions
		rx += vx;
		ry += vy;
	}
	//Get functions:
	public double getMass(){
		return mass;
	}
	public int getXcoordinate(){
		return rx;
	}
	public int getYcoordinate(){
		return ry;
	}
	public double getXvelocity(){
		return vx;
	}
	public double getYvelocity(){
		return vy;
	}
	public int get_size(){
		return size;
	} 
	public Color getColor(){
		return color;
	}

	//From Tutorial: Making Shapes on JPanel:
	public void paintComponent(Graphics g){
		//super b/c from extended JPanel
		super.paintComponent(g);
		//Timer object: GUI version of the sleep method, good for animation
		Timer tm = new Timer(200, this); 
		//Loop over list to create the different bodies
		for (int i=0; i<list.getSize(); i++){
			//set color to random color
			g.setColor(list.get(i).getColor());
			// g.drawOval(x, 100, 50, 50);
			//bigger coordinate to not over lap the circle
			//Make circle using given x and y coordinates and size
			g.fillOval(list.get(i).getXcoordinate(), list.get(i).getYcoordinate(), list.get(i).get_size(), list.get(i).get_size());
		}
		tm.start();//start timer
	}
	// Requirement 4: Change the position of the bodies
	public void updateShapes(){
		int i;
		for (i=0; i<list.getSize()-1; i++){
			list.get(i).force(list.get(i+1), scale);//calculate the force 
			list.get(i).updatePosition();//update velocity with the new force and then update position
			list.get(i).resetForce();//reset the forces values
		}
		if(list.getSize()>1){
			list.get(i).force(list.get(i-1), scale);
			list.get(i).updatePosition();
			list.get(i).resetForce();
		}
	}
	public void actionPerformed(ActionEvent a){
		updateShapes();
		//built in method, repaints the figure every 5 seconds
		repaint();
	}
 
	public static void main(String[] args){
		
		Lists<N_body> temp = null;//temporary null list
		double tempS = 0; //temp scale set to 0
		//Read CSV file from command line
		File file = new File(args[0]);//takes inputted file at the command line
		//Requirement 1: Read the data file
		try{
			Scanner sc = new Scanner(file);//make object of scanner class
			String listType = sc.nextLine();
			//if first line in file is ArrayList, store values in ArrayList
			if(listType.equals("ArrayList")){
				// ArrayList<String> temp = new ArrayList<String>();
				temp = new ArrayList<>();
			}
			//if first line is LinkedList, store values in LinkedList
			else if(listType.equals("LinkedList")){
				// LinkedList<String> temp = new LinkedList<String>();
				temp = new LinkedList<>();
			}
			//excpetion handeling
			else{
				System.out.println("Invalid type of list");
			}
			tempS = Double.parseDouble(sc.nextLine());//set the temporary scale variable to the information in next line after data structure
			//remainder of file is in CSV comma separated value format
			sc.useDelimiter(",");//split the values at the commas
			while(sc.hasNext()){
				assert temp!=null;
				temp.add(new N_body(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextLine()));//add each value of each body
			}
			sc.close();//close the file
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		//Requirement 2: Create, instantiate and maintain a List of celestial bodies
		N_body nBody = new N_body(temp, tempS);//object of Nbodies class

		// Requirement 3: Render each celestial body
		//JFrame object
		JFrame jf = new JFrame();
		jf.setTitle( "N_body");
		//int t.maxX = t.maxY = 768;
		jf.setSize( 768 , 768 ); // Window size defined in the class
		//adding JPanel onto the frame
		jf.add(nBody); // This appears below "setVisible" in the video
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
