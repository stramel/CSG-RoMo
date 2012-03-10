package edu.mst.cs206.sp2012;

import javax.swing.JFrame;

public class GUI extends JFrame {
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		//MainController ctrl = new MainController(0, 0, 0, "./", "./");
		
	}
	
	public GUI()
	{
		setSize(400,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
