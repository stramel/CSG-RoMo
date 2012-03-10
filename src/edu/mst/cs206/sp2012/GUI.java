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
	
	private static final long serialVersionUID = 1331420879L;
	private JPanel inputPanel;
	private JTextField numberOfIterations;
	private JTextField numberOfFinalSolutions;
	private JTextField numberOfRulesPerSolution;
	private JTextField pathToSampleSummary;
	private JTextField pathToMetricsResults;
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	    gui.setVisible(true);
	}
	
	public GUI()
	{
		setSize(500,150);
		setTitle("cs206sp2012 Project");
		//setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputPanel = new JPanel (new FlowLayout());
		
		numberOfIterations = new JTextField(20);
		inputPanel.add(new JLabel("Number of Iterations: "));
	    inputPanel.add(numberOfIterations);
	    
		numberOfFinalSolutions = new JTextField(20);
		inputPanel.add(new JLabel("Number of Final Solutions: "));
	    inputPanel.add(numberOfFinalSolutions);
	    
	    numberOfRulesPerSolution = new JTextField(20);
		inputPanel.add(new JLabel("Number of Rules per Solution: "));
	    inputPanel.add(numberOfRulesPerSolution);
	    
		pathToSampleSummary = new JTextField(20);
		inputPanel.add(new JLabel("Path to Sample Summary: "));
	    inputPanel.add(pathToSampleSummary);
	    
	    pathToMetricsResults = new JTextField(20);
		inputPanel.add(new JLabel("Path to Metrics Results: "));
	    inputPanel.add(pathToMetricsResults);
	    
	    JButton runButton = new JButton ("Run");
	    runButton.addActionListener(this);
	    inputPanel.add(runButton);
	    
	    add(inputPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final int NUM_ITERATIONS = Integer.parseInt(numberOfIterations.getText());
		final int NUM_FINAL_SOLUTIONS = Integer.parseInt(numberOfFinalSolutions.getText());
		final int NUM_RULES_PER_SOLUTIONS = Integer.parseInt(numberOfRulesPerSolution.getText());
		final String SAMPLE_SUMMARY_PATH = pathToSampleSummary.getText();
		JOptionPane.showMessageDialog(this, SAMPLE_SUMMARY_PATH,
				"Path to Sample Summary", JOptionPane.INFORMATION_MESSAGE);

		final String METRICS_RESULT_PATH = pathToMetricsResults.getText();
		JOptionPane.showMessageDialog(this, METRICS_RESULT_PATH,
				"Path to Metrics Results", JOptionPane.INFORMATION_MESSAGE);
		
		new MainController(NUM_ITERATIONS, NUM_FINAL_SOLUTIONS,
				NUM_RULES_PER_SOLUTIONS, SAMPLE_SUMMARY_PATH,
				METRICS_RESULT_PATH).run();
	}
}
