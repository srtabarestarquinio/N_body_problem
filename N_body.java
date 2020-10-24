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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;


public class N_body extends JPanel implements ActionListener{
	private String name;
	private int rx, ry;	//cartesian positions
	private int vx, vy;	//velocity components
	private int fx, fy;	//force components
	private int mass;		//mass
	private int bodySize;
	private List<String[]> content;
	private List<CelestBody> arrList;
	private Color color;		//color

	private static final double G = 6.673e-11; //gravitational constant
	private static final double solarmass = 1.98892e30;

	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E value){
			data=value;
			next=null;
		}
	}
	public class LinkedList<E>{
		private class Node<E>{
			E data;
			Node<E> next;
			public Node(E value){
				data=value;
				next=null;
			}
		}
		Node<E> head;
		int size;
		public LinkedList(){
			head=null;
			size=0;
		}
		public void add(E item){
			if(head==null){
				head = new Node<E>(item);
			}
			else{
				Node<E> prev = head;
			}
		}
	}
	private static class CelestBody{
		private String name;
		private int rx, ry;	//cartesian positions
		private int vx, vy;	//velocity components
		private int mass;		//mass
		private int bodySize;

		public CelestBody(String name, int mass, int rx, int ry, int vx, int vy, int size){
			this.name=name;
			this.rx=rx;
			this.ry=ry;
			this.vx=vx;
			this.vy=vy;
			this.mass=mass;	
			this.bodySize=bodySize;
		}
		public String giveName(){
			return this.name;
		}

	}
	//Timer object: GUI version of the sleep method, good for animation
	//sleep for 5 seconds
	Timer tm = new Timer(5, this);
	int x=0, velx=2;

	//From Tutorial: Making Shapes on JPanel:
	public void paintComponent(Graphics g){
		//super b/c from extended JPanel
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawOval(x, 100, 50, 50);
		//bigger coordinate to not over lap the circle
		g.fillOval(x, 200, 50, 50);
		//other methods:
			// g.drawRect(100, 10, 30, 40);
			// g.fillRect(10, 10, 20, 10);
			// g.drawLine(10, 10, 30, 70);
		//start timer, start action listener:
		tm.start();
	}
	public void actionPerformed(ActionEvent e){
		if(x<0 || x>718){
			velx = -velx;
		}
		x += velx;
		//built in method, repaints the figure every 5 seconds
		repaint();
	}
	public void NbodyCreator(String filename) throws IOException{
		String fileInput = filename;
		content = new ArrayList<>();
		try(BufferedReader read = new BufferedReader(new FileReader(fileInput))){
			String line = "";
			while(line=read.readLine()){
				content.add(line.split(","));
			}
		}
		catch(Exception e){
			System.out.println("Error, no file");
		}
	}
	public static void main(String[] args){
		//Read CSV file from command line
		String fileName = "nbody_input.text";
		CelestBody body1 = new CelestBody("Earth", 20000, 20, 5, 16, 3000, 20000);
		System.out.println(body1.giveName());

		//t to reprent JPanel:
		N_body t = new N_body();
		//JFrame object
		JFrame jf = new JFrame();
		jf.setTitle( "N_body");
		int t.maxX = t.maxY = 768;
		jf.setSize( t.maxX , t.maxY ); // Window size defined in the class
		//adding JPanel onto the frame
		jf.add(t); // This appears below "setVisible" in the video
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	

	//initialize new body
	// public N_body(double rx, double ry, double vx, double vy, double mass, Color color){
	// 	this.rx = rx;
	// 	this.ry = ry;
	// 	this.vx = vx;
	// 	this.vy = vy;
	// 	this.mass = mass;
	// 	this.color = color;
	// }
	// //update velocity and position
	// public void update(double dt){
	// 	vx += dt*fx/mass;
	// 	vy += dt*fy/mass;
	// 	rx += dt*vx;
	// 	ry += dt*vy;
	// }
	// //return distance between two bodies
	// public double distanceTo(Body b){
	// 	double dx = rx-b.rx;
	// 	double dy = ry-b.ry;
	// 	return Math.sqrt(dx**2 + dy**2)
	// }
	// //set the force to 0 for the next iteration
	// public void resetForce(){
	// 	fx = 0.0;
	// 	fy = 0.0;
	// }
	// //calculate net force acting between the bodies
	// public void addForce(Body b){
	// 	Body a = this;
	// 	double EPS = 3E4;	//softening parameter (just to avoid infinities)
	// 	double dx = b.rx - a.rx;
	// 	double dy = b.ry - a.ry;
	// 	double distance = Math.sqrt(dx**2 + dy**2);
	// 	double force = (G*a.mass*b.mass)/(distance**2 + EPS**2);
	// 	a.fx += force*dx/distance;
	// 	a.fy += force*dy/distance;
	// } 
	// public String toString(){
	// 	return " "+rx+", "+ry+", "+vx+", "+vy+", "+mass
	// }
	// public void readFile(String[] args){
	// 	double dataStruc = Double.parseDouble(args[0]);
	// 	double scale = Double.parseDouble(args[1]);
	// 	//The remainder of the file is in CSV
	// 	//each new record will appear on a new line and each field in a record will be separated by commas. Each record defines a celestial body.
	// 	for (int i = 0; i<args.lenght(); i++){
			
	// 		name = 
	// 		mass = 
	// 		rx = 
	// 		ry = 
	// 		vx = 
	// 		vy = 
	// 		size = //Size (in pixels) for rendering
	// 	}
	// 	String filename = args[2];
		
	// }
}
