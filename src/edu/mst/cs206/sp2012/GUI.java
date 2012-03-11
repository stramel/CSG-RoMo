package edu.mst.cs206.sp2012;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
		setSize(550,200);
		setTitle("cs206sp2012 Project");
		setResizable(false);
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
		JButton browseForSampleSummary = new JButton(new FileSystemNavigator(pathToSampleSummary));
	    browseForSampleSummary.setText("Browse...");
	    
	    inputPanel.add(new JLabel("Path to Sample Summary: "));
	    inputPanel.add(pathToSampleSummary);
	    inputPanel.add(browseForSampleSummary);
	    
	    JLabel labelForMetricsResult = new JLabel("Path to Metrics Results: ");
	    pathToMetricsResults = new JTextField(20);
	    JButton browseForMetricsResults = new JButton(new FileSystemNavigator(pathToMetricsResults));
	    browseForMetricsResults.setText("Browse...");
	    
	    
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
		final String SAMPLE_SUMMARY_PATH = getPathToSampleSummary();

		final String METRICS_RESULT_PATH = getPathToMetricsResults();
		
		MainController controller = new MainController(NUM_ITERATIONS, NUM_FINAL_SOLUTIONS,
				NUM_RULES_PER_SOLUTIONS, SAMPLE_SUMMARY_PATH,
				METRICS_RESULT_PATH);
		try
		{
			controller.run();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "There was a problem with the path to the "+ e.getMessage()+" , please fix and try again.",
					"File Path Error", JOptionPane.INFORMATION_MESSAGE);
		}
		
		JOptionPane.showMessageDialog(this, "Finished! " + Integer.toString(controller.getBestSolutions().size()) + " best solutions have been found!",
				"Finished!", JOptionPane.INFORMATION_MESSAGE);
	}

	private String getPathToMetricsResults() {
		return pathToMetricsResults.getText();
	}

	private String getPathToSampleSummary() {
		return pathToSampleSummary.getText();
	}

	private int getNumberOfRulesPerSolution() throws NumberFormatException {
		final String USER_INPUT = numberOfRulesPerSolution.getText();
		int temp = 0;
		try {
			temp = Integer.parseInt(USER_INPUT);
		} catch (NumberFormatException e) {
			String errorMessage = "Number of Rules per Solution is expected to be an Integer value, but you input '";
			errorMessage += USER_INPUT;
			errorMessage += "'. Please fix this before moving on.";
			JOptionPane.showMessageDialog(this, errorMessage,
				"Number of Rules per Solution must be a positive Interger!",
				JOptionPane.INFORMATION_MESSAGE);
			
			throw new NumberFormatException(errorMessage);
		}
		return temp; 
	}

	private int getNumberOfFinalSolutions() throws NumberFormatException {
		final String USER_INPUT = numberOfFinalSolutions.getText();
		int temp = 0;
		try {
			temp = Integer.parseInt(USER_INPUT);
		} catch (NumberFormatException e) {
			String errorMessage = "Number of Final Solutions is expected to be an Integer value, but you input '";
			errorMessage += USER_INPUT;
			errorMessage += "'. Please fix this before moving on.";
			JOptionPane.showMessageDialog(this, errorMessage,
				"Number of Final Solutions must be a positive Interger!",
				JOptionPane.INFORMATION_MESSAGE);
			
			throw new NumberFormatException(errorMessage);
		}
		return temp;
	}

	private int getNumberOfIterations() throws NumberFormatException {
		final String USER_INPUT = numberOfIterations.getText();
		int temp = 0;
		try {
			temp = Integer.parseInt(USER_INPUT);
		} catch (NumberFormatException e) {
			String errorMessage = "Number of Iterations is expected to be an Integer value, but you input '";
			errorMessage += USER_INPUT;
			errorMessage += "'. Please fix this before moving on.";
			JOptionPane.showMessageDialog(this, errorMessage,
				"Number of Iterations must be a positive Interger!",
				JOptionPane.INFORMATION_MESSAGE);
			
			throw new NumberFormatException(errorMessage);
		}
		return temp;
	}
}
