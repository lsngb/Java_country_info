package project3;

import javax.swing.JFrame;

public class Viewer {
	public static void main(String[]args){
		JFrame frame = new GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Get Country Info");
		frame.setVisible(true);
	}
}
