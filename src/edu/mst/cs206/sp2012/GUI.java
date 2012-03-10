package edu.mst.cs206.sp2012;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	
	public class FileSystemNavigator extends AbstractAction {
	    JFrame frame;
	    JFileChooser chooser;
	    JTextField textField;

	    FileSystemNavigator(JTextField textField) {
	        this.chooser = new JFileChooser();
	        this.frame = new JFrame();
	        this.textField = textField;
	    }

	    public void actionPerformed(ActionEvent evt) {
	        // Show dialog; this method does not return until dialog is closed
	        chooser.showOpenDialog(frame);

	        // Get the selected file
	        textField.setText(chooser.getSelectedFile().getAbsolutePath());
	    }
	};
	
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
		JButton browseForSampleSummary = new JButton("Browse...");
	    browseForSampleSummary.setAction(new FileSystemNavigator(pathToSampleSummary));
	    
	    inputPanel.add(new JLabel("Path to Sample Summary: "));
	    inputPanel.add(pathToSampleSummary);
	    inputPanel.add(browseForSampleSummary);
	    
	    JLabel labelForMetricsResult = new JLabel("Path to Metrics Results: ");
	    pathToMetricsResults = new JTextField(20);
	    JButton browseForMetricsResults = new JButton("Browse...");
	    browseForMetricsResults.setAction(new FileSystemNavigator(pathToMetricsResults));
	    
		inputPanel.add(labelForMetricsResult);
	    inputPanel.add(pathToMetricsResults);
	    inputPanel.add(browseForMetricsResults);
	    
	    JButton runButton = new JButton ("Run");
	    runButton.addActionListener(this);
	    inputPanel.add(runButton);
	    
	    add(inputPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final int NUM_ITERATIONS = getNumberOfIterations();
		final int NUM_FINAL_SOLUTIONS = getNumberOfFinalSolutions();
		final int NUM_RULES_PER_SOLUTIONS = getNumberOfRulesPerSolution();
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

	private int getNumberOfRulesPerSolution() throws NumberFormatException {
		return Integer.parseInt(numberOfRulesPerSolution.getText());
	}

	private int getNumberOfFinalSolutions() throws NumberFormatException {
		return Integer.parseInt(numberOfFinalSolutions.getText());
	}

	private int getNumberOfIterations() throws NumberFormatException {
		return Integer.parseInt(numberOfIterations.getText());
	}
}
