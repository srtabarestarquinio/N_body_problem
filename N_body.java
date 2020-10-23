//Rose Tabares - CS245 - Assignment01 - N-Body-Problem
import java.util.*;
import java.awt.Color;

public class N_body{
	private static final double G = 6.673e-11; //gravitational constant
	private static final double solarmass = 1.98892e30;

	public double rx, ry;	//cartesian positions
	public double vx, vy;	//velocity components
	public double fx, fy;	//force components
	public double mass;		//mass
	public Color color;		//color

	public 

	public static void main(String[] args){
		//Read CSV file from command line
		


		Tutorial t = new Tutorial();
		JFrame jf = new JFrame();
		jf.setTitle( "Tutorial" );
		jf.setSize( t.maxX , t.maxY ); // Window size defined in the class
		jf.add(t); // This appears below "setVisible" in the video
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
