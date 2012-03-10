package edu.mst.cs206.sp2012;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {

	public static void main(String[] args) {
		GUI gui = new GUI();
	
		JPanel inputPanel = new JPanel (new FlowLayout());
		
		JLabel labelForNumberOfIterations = new JLabel("Number of Iterations: ");
		JTextField numberOfIterations = new JTextField(20);
		inputPanel.add(labelForNumberOfIterations);
	    inputPanel.add(numberOfIterations);
		
//		JLabel labelForNumberOfFinalSolutions = new JLabel("Number of Final Solutions: ");
//		JTextField numberOfFinalSolutions = new JTextField(20);
//		inputPanel.add(labelForNumberOfFinalSolutions);
//	    inputPanel.add(numberOfFinalSolutions);
	    
	    gui.add(inputPanel);
	    gui.setVisible(true);
	    
//		JPanel j2 = new JPanel();
//		JButton b1 = new JButton ("Click me");

//	    j2.add(b1);
	    
//	    gui.add(j2);
		//MainController ctrl = new MainController(0, 0, 0, "./", "./");
	}
	
	public GUI()
	{
		setSize(400,150);
		setTitle("cs206sp2012 Project");
		//setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
