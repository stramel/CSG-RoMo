package edu.mst.cs206.sp2012;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map.Entry;
import java.util.Vector;
import javax.naming.InvalidNameException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**This Class builds the GUI console for the project.*/
public class GUI extends JFrame implements ActionListener {
	private static final boolean DEBUG = true;
	private static final long serialVersionUID = 1331420879L;
	private MainController controller;
	private JPanel inputPanel;
	private JTextField numberOfIterations;
	private JTextField numberOfFinalSolutions;
	private JTextField numberOfRulesPerSolution;
	private JTextField pathToSampleSummary;
	private JTextField pathToMetricsResults;
	private static NumberFormat nf = NumberFormat.getInstance();
	
	/**public static void main(String[] args)
	 * This function creates a new GUI frame.
	 * @param args of type String[]
	 */
	public static void main(String[] args) {
		GUI gui = new GUI();
	    gui.setVisible(true);
	}
	
	/**public class FileSystemNavigator extends AbstractAction
	 * This function creates the Frame and TextFields for the GUI
	 *
	 */
	public class FileSystemNavigator extends AbstractAction {
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
	        int result = chooser.showOpenDialog(frame);

	        // Get the selected file
	        if ( result == JFileChooser.APPROVE_OPTION){
	        	textField.setText(chooser.getSelectedFile().getAbsolutePath());
	        }
	    }
	};
	
	/**public GUI()
	 * This function builds all the panels for the GUI
	 */
	public GUI()
	{
		setSize(550,200);
		setTitle("cs206sp2012 Project");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputPanel = new JPanel (new FlowLayout());
		
		addNumberOfIterationsField();
		addNumberOfFinalSolutionsField();
	    addNumberOfRulesPerSolutionField();
		addSampleSummaryUrlField();
	    addMetrixFileUrlField();
	    addRunButton();
	    integrateComponents();
	}

	/**
	 * 
	 */
	private void integrateComponents() {
		add(inputPanel);
	}

	/**
	 * 
	 */
	private void addRunButton() {
		JButton runButton = new JButton ("Run");
	    runButton.addActionListener(this);
	    inputPanel.add(runButton);
	}

	/**
	 * 
	 */
	private void addMetrixFileUrlField() {
		JLabel labelForMetricsResult = new JLabel("Path to Metrics Results: ");
	    pathToMetricsResults = new JTextField(20);
	    JButton browseForMetricsResults = new JButton(new FileSystemNavigator(pathToMetricsResults));
	    browseForMetricsResults.setText("Browse...");
	    
		inputPanel.add(labelForMetricsResult);
	    inputPanel.add(pathToMetricsResults);
	    inputPanel.add(browseForMetricsResults);
	}

	/**
	 * 
	 */
	private void addSampleSummaryUrlField() {
		pathToSampleSummary = new JTextField(20);
		JButton browseForSampleSummary = new JButton(new FileSystemNavigator(pathToSampleSummary));
	    browseForSampleSummary.setText("Browse...");
	    
	    inputPanel.add(new JLabel("Path to Sample Summary: "));
	    inputPanel.add(pathToSampleSummary);
	    inputPanel.add(browseForSampleSummary);
	}

	/**
	 * 
	 */
	private void addNumberOfRulesPerSolutionField() {
		numberOfRulesPerSolution = new JTextField(20);
		inputPanel.add(new JLabel("Number of Rules per Solution: "));
	    inputPanel.add(numberOfRulesPerSolution);
	}

	/**
	 * 
	 */
	private void addNumberOfFinalSolutionsField() {
		numberOfFinalSolutions = new JTextField(20);
		inputPanel.add(new JLabel("Number of Final Solutions: "));
	    inputPanel.add(numberOfFinalSolutions);
	}

	/**
	 * 
	 */
	private void addNumberOfIterationsField() {
		numberOfIterations = new JTextField(20);
		inputPanel.add(new JLabel("Number of Iterations: "));
	    inputPanel.add(numberOfIterations);
	}

	/**public void actionPerformed(ActionEvent arg0)
	 * This function creates the controller for GUI
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		final int NUM_ITERATIONS = getNumberOfIterations();
		final int NUM_FINAL_SOLUTIONS = getNumberOfFinalSolutions();
		final int NUM_RULES_PER_SOLUTIONS = getNumberOfRulesPerSolution();
		final String SAMPLE_SUMMARY_PATH = getPathToSampleSummary();
		final String METRICS_RESULT_PATH = getPathToMetricsResults();
		
		this.controller = new MainController(NUM_ITERATIONS, NUM_FINAL_SOLUTIONS,
				NUM_RULES_PER_SOLUTIONS, SAMPLE_SUMMARY_PATH,
				METRICS_RESULT_PATH);
		try
		{
			nf.setMinimumFractionDigits(3);
		    nf.setGroupingUsed(false); 
			this.controller.run();
			JOptionPane.showMessageDialog(this, "Finished! The best solution has been found!", "Finished!", JOptionPane.INFORMATION_MESSAGE);
			
			String humanReadableRules = getRulesOfBestSolutionAsHumanReadableString();
			if (GUI.DEBUG) {
				JOptionPane.showMessageDialog(this, "The best solution has a Recall value of " + nf.format(this.controller.getBestSolution().getRecall()) + ", and a Precision value of " + nf.format(this.controller.getBestSolution().getPrecision()), "Fitness Values", JOptionPane.INFORMATION_MESSAGE);
			}
			
			BufferedWriter rulesToFile = new BufferedWriter(new FileWriter("rulesOfBestSolution.txt"));
			rulesToFile.write(humanReadableRules);
			rulesToFile.close();
			
			JOptionPane.showMessageDialog(this, "The best solution has been\n output to the file 'rulesOfBestSolution.txt'", "Best solution written to file", JOptionPane.INFORMATION_MESSAGE);
			
			
		} catch (InvalidNameException e) {
			JOptionPane.showMessageDialog(this, "There was a problem with the path to the "+ e.getMessage()+", please fix and try again.", "File Path Error", JOptionPane.INFORMATION_MESSAGE);
			
			if (e.getLocalizedMessage() == "Summary Table") {
				pathToMetricsResults.requestFocusInWindow(); 
			} else if (e.getLocalizedMessage() == "Metric Results") {
				pathToSampleSummary.requestFocusInWindow();
			}
			
			if (GUI.DEBUG) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "There was a problem!", "Error", JOptionPane.INFORMATION_MESSAGE);
			
			if (GUI.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	/**private String getPathToMetricsResults()
	 * Returns the Text of pathToMetricsResults
	 * @return pathToMetricsResults.getText()
	 */
	private String getPathToMetricsResults() {
		return pathToMetricsResults.getText();
	}

	/**private String getPathToSampleSummary()
	 * Return the Text of the pathToSampleSummary
	 * @return pathToSampleSummary.getText()
	 */
	private String getPathToSampleSummary() {
		return pathToSampleSummary.getText();
	}

	/**private int getNumberOfRulesPerSoultion() throws NumberFOrmatException
	 * This function will throw errors if things were inputed into GUI incorrectly.
	 * @return temp
	 * @throws NumberFormatException
	 */
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

	/**private int getNumberOfFinalSoultion() throws NumberFormatException
	 * 
	 * This function will throw errors if things were inputed into GUI incorrectly.
	 * @return temp
	 * @throws NumberFormatExceptionn
	 */
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

	/**private int getNumberofIterations() throws NumberFormatException
	 * 
	 * This function will throw errors if things were inputed into GUI incorrectly.
	 * @return temp
	 * @throws NumberFormatExceptionn
	 */
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
	
	/**private String getRulesofBestSoultionAsHumanReadableString()
	 * This function will output the human readable form of the rules from the machine code values.
	 * @return humanReadableRulesOfBestSoultion
	 */
	private String getRulesOfBestSolutionAsHumanReadableString() {
		// TODO change this to actually save the text in a file, and maybe in a cleaner format...
		Individual bestSolution = this.controller.getBestSolution();
		
		Vector<Rule> bestSolutionRules = bestSolution.getRules();

		String humanReadableRulesOfBestSolution = "Add element to summary if (";
		for (int i = 0; i < bestSolutionRules.size(); i++) {
			humanReadableRulesOfBestSolution += " ( "; 
			int j = 0;
			for (Entry<String, Integer> entry : bestSolutionRules.get(i).getThresholds().entrySet()) {
				
				humanReadableRulesOfBestSolution += entry.getKey() + " > " + entry.getValue();
				String logicalConnector = ( j < bestSolutionRules.get(i).getAndBetweens().size() ? (bestSolutionRules.get(i).getAndBetweens().get(j) ? " && " : " OR ") : (""));
				humanReadableRulesOfBestSolution += logicalConnector;
				j++;
			}
			humanReadableRulesOfBestSolution += " )";
			if ( bestSolutionRules.size() - 1 > i ) {
				humanReadableRulesOfBestSolution += " || "; 
			}
		}
		humanReadableRulesOfBestSolution += ")\n";
		
		return humanReadableRulesOfBestSolution;
	}
}
