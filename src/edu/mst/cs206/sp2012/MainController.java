package edu.mst.cs206.sp2012;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainController extends JFrame implements ActionListener{
	
	private final int numberOfIterators;
	private final int maxNumberOfSolutions;
	private final int maxNumberOfRulesPerSolution;
	private final String urlToSampleSummary;
	private final String urlToMetricsResults;
	private Population bestSolutions;
	private String[] availableMetrics;
	private SpringLayout layout;
	private JTextField NIText;
	private JTextField NFSText;
	private JTextField NRSText;
	private JTextField URLSSText;
	private JTextField URLMRText;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {public void run() { MainController main = new MainController(); main.setVisible(true);}});
	
		Population currentPopulation = new Population(maxNumberOfSolutions, maxNumberOfRulesPerSolution);
		currentPopulation.setSampleProject(new OpenSourceProject(urlToMetricsResults, urlToSampleSummary, availableMetrics));
		currentPopulation.setAvailableMetrics(availableMetrics);
		
		currentPopulation.initialize();
		for(int generation=0; generation < numberOfIterators; generation++)
		{
			currentPopulation.evaluateSolutions();
			bestSolutions.push(currentPopulation.getBestSolution());
			currentPopulation.purgeAndRenew();
		}
		
		// Output the best solution that is stored in ctrl.bestSolutions;
	}

	public MainController()
	{
		JPanel frame = new JPanel();
		frame.super();
		layout = new SpringLayout();
		frame.setLayout(layout);
		create();
		add(frame);
		setTitle("CSG - RoMo");
		setSize(250,300);
		setLocationRelativeTo(null);
		setResizeable(false);
		setDefaultCloseOpteration(JFrame.EXIT_ON_CLOSE);
	}

	private void create()
	{
		boxWidth = 100;

		JLabel NILabel = new JLabel("Number of Iterations: ");
		layout.putConstraint(SpringLayout.NORTH, NILabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, NILabel, 10, SpringLayout.WEST, this);
		add(NILabel);
		NIText = new JTextField("", boxWidth);
		layout.putConstraint(SpringLayout.NORTH, NIText, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, NIText, 10, SpringLayout.EAST, NILabel);
		add(NIText);

		JLabel NFSLabel = new JLabel("Number of Final Solutions: ");
		layout.putConstraint(SpringLayout.NORTH, NFSLabel, 10, SpringLayout.SOUTH, NILabel);
                layout.putConstraint(SpringLayout.WEST, NFSLabel, 10, SpringLayout.WEST, this);
		add(NFSLabel);
		NFSText = new JTextField("", boxWidth);
		layout.putConstraint(SpringLayout.NORTH, NFSText, 10, SpringLayout.SOUTH, NIText);
                layout.putConstraint(SpringLayout.WEST, NFSText, 10, SpringLayout.EAST, NFSLabel);
		add(NFSText);

		JLabel NRSLabel = new JLabel("Number of Rules per Solution");
		layout.putConstraint(SpringLayout.NORTH, NRSLabel, 10, SpringLayout.SOUTH, NFSLabel);
                layout.putConstraint(SpringLayout.WEST, NRSLabel, 10, SpringLayout.WEST, this);
		add(NRSLabel);
		NRSText = new JTextField("", boxWidth);
		layout.putConstraint(SpringLayout.NORTH, NRSText, 10, SpringLayout.SOUTH, NFSText);
                layout.putConstraint(SpringLayout.WEST, NRSText, 10, SpringLayout.EAST, NRSLabel);
		add(NRSText);

		JLabel URLSSLabel = new JLabel("Path to Sample Summaries: ");
		layout.putConstraint(SpringLayout.NORTH, URLSSLabel, 10, SpringLayout.SOUTH, NRSLabel);
                layout.putConstraint(SpringLayout.WEST, URLSSLabel, 10, SpringLayout.WEST, this);
		add(URLSSLabel);
		URLSSText = new JTextField("", boxWidth);
		layout.putConstraint(SpringLayout.NORTH, URLSSText, 10, SpringLayout.SOUTH, NRSText);
                layout.putConstraint(SpringLayout.WEST, URLSSText, 10, SpringLayout.EAST, URLSSLabel);
		add(URLSSText);

		JLabel URLMRLabel = new JLabel("Path to Metrics Results: ");
		layout.putConstraint(SpringLayout.NORTH, URLMRLabel, 10, SpringLayout.SOUTH, URLSSLabel);
                layout.putConstraint(SpringLayout.WEST, URLMRLabel, 10, SpringLayout.WEST, this);
		add(URLMRLabel);
		URLMRText = new JTextField("", boxWidth);
		layout.putConstraint(SpringLayout.NORTH, URLMRText, 10, SpringLayout.SOUTH, URLSSText);
                layout.putConstraint(SpringLayout.WEST, URLMRText, 10, SpringLayout.EAST, URLMRLabel);
		add(URLMRText);

		JButton RunButton = new JButton("Run");
		layout.putContraint(SpringLayout.NORTH, RunButton, 10, SpringLayout.SOUTH, URLSSLabel);
		layout.putContraint(SpringLayout.WEST, RunButton, 10, SpringLayout.WEST, this);
		RunButton.addActionListner(this);
		add(RunButton);
	}

	public void actionPerformed(ActionEvent e)
	{
		RunAction(NIText.getText(),NFSText.getText(),NRSText.getText(),URLSSText.getText(),URLMRText.getText());
	}
	
	public RunAction(int numberOfIterations, int numberOfFinalSolutions,
			int maxNumberOfRulesPerSolution, String urlToSampleSummaries,
			String urlToMetricsResults)
	{
		this.numberOfIterators = numberOfIterations;
		this.maxNumberOfSolutions = numberOfFinalSolutions;
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.urlToSampleSummary = urlToSampleSummaries;
		this.urlToMetricsResults = urlToMetricsResults;
		this.bestSolutions = new Population(numberOfFinalSolutions);
		
		// Hard-coded metrics!
		this.availableMetrics = new String[6];
		this.availableMetrics[0] = "MLOC";	// Method Lines of Code
		this.availableMetrics[1] = "PAR";	// Number of Parameters
		this.availableMetrics[2] = "VG";	// McCabe Cyclomatic Complexity
		this.availableMetrics[3] = "NBD";	// Nested Block Depth
		this.availableMetrics[4] = "NSC";	// Number of Children
		this.availableMetrics[5] = "NOM";	// Number of Methods
	}
}
