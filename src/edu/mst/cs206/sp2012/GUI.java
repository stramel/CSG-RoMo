package edu.mst.cs206.sp2012;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
	
	private JPanel inputPanel;
	private JLabel labelForNumberOfIterations;
	private JTextField numberOfIterations;
	
	private JButton runButton;
	
	private MainController controller;
	private JLabel labelForNumberOfFinalSolutions;
	private JTextField numberOfFinalSolutions;
	private JLabel labelForNumberOfRulesPerSolution;
	private JTextField numberOfRulesPerSolution;
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		
//		
		
	    gui.setVisible(true);
	    
		//MainController ctrl = new MainController(0, 0, 0, "./", "./");
	}
	
	public GUI()
	{
		setSize(500,150);
		setTitle("cs206sp2012 Project");
		//setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputPanel = new JPanel (new FlowLayout());
		
		labelForNumberOfIterations = new JLabel("Number of Iterations: ");
		numberOfIterations = new JTextField(20);
		inputPanel.add(labelForNumberOfIterations);
	    inputPanel.add(numberOfIterations);
	    
	    labelForNumberOfFinalSolutions = new JLabel("Number of Final Solutions: ");
		numberOfFinalSolutions = new JTextField(20);
		inputPanel.add(labelForNumberOfFinalSolutions);
	    inputPanel.add(numberOfFinalSolutions);
	    
	    labelForNumberOfRulesPerSolution = new JLabel("Number of Rules per Solution: ");
		numberOfRulesPerSolution = new JTextField(20);
		inputPanel.add(labelForNumberOfRulesPerSolution);
	    inputPanel.add(numberOfRulesPerSolution);
	    
	    runButton = new JButton ("Run");
	    runButton.addActionListener(this);
	    inputPanel.add(runButton);
	    
	    add(inputPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final int NUM_ITERATIONS = Integer.parseInt(numberOfIterations.getText());
		final int NUM_FINAL_SOLUTIONS = Integer.parseInt(numberOfFinalSolutions.getText());
		JOptionPane.showMessageDialog(this, String.valueOf(NUM_FINAL_SOLUTIONS), "Number of Final Solutions", JOptionPane.INFORMATION_MESSAGE);
		
		final int NUM_RULES_PER_SOLUTIONS = Integer.parseInt(numberOfRulesPerSolution.getText());
		JOptionPane.showMessageDialog(this, String.valueOf(NUM_RULES_PER_SOLUTIONS), "Number of Rules per Solution", JOptionPane.INFORMATION_MESSAGE);
		
//		controller = new MainController(NUM_ITERATIONS, numberOfFinalSolutions,
//				maxNumberOfRulesPerSolution, urlToSampleSummaries,
//				urlToMetricsResults);
	}

	private void getInput() {
		// TODO Auto-generated method stub
		
	}
}
