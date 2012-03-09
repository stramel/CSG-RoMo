package edu.mst.cs206.sp2012;

import java.awt.event.*;
import javax.swing.*;

public class MainController extends JFrame implements ActionListener{
	

	private static int numberOfIterators;
	private static int maxNumberOfSolutions;
	private static int maxNumberOfRulesPerSolution;
	private static String urlToSampleSummary;
	private static String urlToMetricsResults;
	private static Population bestSolutions;
	private static String[] availableMetrics;
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
		layout = new SpringLayout();
		frame.setLayout(layout);
		create();
		add(frame);
		setTitle("CSG - RoMo");
		setSize(250,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void create()
	{
		int boxWidth = 100;

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
		layout.putConstraint(SpringLayout.NORTH, RunButton, 10, SpringLayout.SOUTH, URLSSLabel);
		layout.putConstraint(SpringLayout.WEST, RunButton, 10, SpringLayout.WEST, this);
		RunButton.addActionListener(this);
		add(RunButton);
	}

	public void actionPerformed(ActionEvent e)
	{
		RunAction(NIText.getText(),NFSText.getText(),NRSText.getText(),URLSSText.getText(),URLMRText.getText());
	}
	
	public void RunAction(String Ni, String NFS,
			String NRS, String urlToSampleSummaries,
			String urlToMetricsResult)
	{
		numberOfIterators = Integer.parseInt(Ni);
		maxNumberOfSolutions = Integer.parseInt(NFS);
		maxNumberOfRulesPerSolution = Integer.parseInt(NRS);
		urlToSampleSummary = urlToSampleSummaries;
		urlToMetricsResults = urlToMetricsResult;
		bestSolutions = new Population(Integer.parseInt(NRS), Integer.parseInt(NFS));
		
		// Hard-coded metrics!
		availableMetrics = new String[6];
		availableMetrics[0] = "MLOC";	// Method Lines of Code
		availableMetrics[1] = "PAR";	// Number of Parameters
		availableMetrics[2] = "VG";	// McCabe Cyclomatic Complexity
		availableMetrics[3] = "NBD";	// Nested Block Depth
		availableMetrics[4] = "NSC";	// Number of Children
		availableMetrics[5] = "NOM";	// Number of Methods
	}
}
