package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Map;
import javax.swing.undo.UndoManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class testFrame extends JFrame {
    private JTextField textFieldName;
    private JTextArea textAreaComment;
    private JButton btnSubmit;
    private JSlider Comfort,Safety,Entertainment,Cleanliness;
    private JButton btnShowPieChart;
    JPanel jPanel3 = new JPanel(new BorderLayout()); // Initialize jPanel3
    public testFrame() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Hotel Review Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(883, 559);
        setLocationRelativeTo(null);
        
        getContentPane().add(jPanel3, BorderLayout.SOUTH); // Add jPanel3 to the frame

        JPanel pan1 = new JPanel(new GridLayout(7,2));
           
            
            
            pan1.add(new JLabel("Name:"));
            textFieldName = new JTextField();
            pan1.add(textFieldName);

            pan1.add(new JLabel("Comfort:"));
            Comfort = new JSlider(0, 5, 0);
            Comfort.setForeground(new Color(255, 255, 0));
            Comfort.setMajorTickSpacing(1);
            Comfort.setPaintTicks(true);
            pan1.add(Comfort);

            pan1.add(new JLabel("Safety:"));
            Safety = new JSlider(0, 5, 0);
            Safety.setForeground(new Color(255, 255, 0));
            Safety.setMajorTickSpacing(1);
            Safety.setPaintTicks(true);
            pan1.add(Safety);
            
            pan1.add(new JLabel("Entertainment:"));
            Entertainment = new JSlider(0, 5, 0);
            Entertainment.setForeground(new Color(255, 255, 0));
            Entertainment.setMajorTickSpacing(1);
            Entertainment.setPaintTicks(true);
            pan1.add(Entertainment);

            pan1.add(new JLabel("Cleanliness:"));
            Cleanliness = new JSlider(0, 5, 0);
            Cleanliness.setForeground(new Color(255, 255, 0));
            Cleanliness.setMajorTickSpacing(1);
            Cleanliness.setPaintTicks(true);
            pan1.add(Cleanliness);
            
            pan1.add(new JLabel("Comment:"));
            textAreaComment = new JTextArea();
            textAreaComment.setTabSize(9);
            JScrollPane scrollPane = new JScrollPane(textAreaComment);
            pan1.add(scrollPane);


            btnSubmit = new JButton("Submit");
            btnSubmit.setBackground(new Color(50, 205, 50));
            btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    submitReview();
                }
            });
            
           
            
            pan1.add(btnSubmit);
            pan1.setBackground(new Color(135, 206, 235));
            
            JPanel pan2 = new JPanel(new BorderLayout());
            JLabel lblNewLabel_6 = new JLabel();
    		
    		lblNewLabel_6.setBounds(10, 21, 89, 300);
    		pan2.add(lblNewLabel_6);
    		 btnShowPieChart = new JButton("Show Pie Chart");
             btnShowPieChart.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 showPieChart();
             }
         });
             pan1.add(btnShowPieChart);
    		//getContentPane().add(pan1, BorderLayout.EAST);
            
            getContentPane().add(pan1, BorderLayout.CENTER);
    		getContentPane().add(pan2, BorderLayout.WEST);}
    		
    		public DefaultPieDataset getDataFromDatabase() {
    	        DefaultPieDataset dataset = new DefaultPieDataset();

    	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reviews", "root", "")) {
    	            String query = "SELECT Comfort, Safety, Entertainment, Cleanliness FROM avis";
    	            try (PreparedStatement statement = connection.prepareStatement(query)) {
    	                try (ResultSet resultSet = statement.executeQuery()) {
    	                    if (resultSet.next()) {
    	                        double comfort = resultSet.getDouble("Comfort");
    	                        double safety = resultSet.getDouble("Safety");
    	                        double entertainment = resultSet.getDouble("Entertainment");
    	                        double cleanliness = resultSet.getDouble("Cleanliness");

    	                        // Adding data points to the dataset
    	                        dataset.setValue("Comfort", comfort);
    	                        dataset.setValue("Safety", safety);
    	                        dataset.setValue("Entertainment", entertainment);
    	                        dataset.setValue("Cleanliness", cleanliness);
    	                    }
    	                }
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace(); // Handle the exception appropriately
    	        }

    	        return dataset;
    	    }
    		public void showPieChart() {
    		    DefaultPieDataset dataset = getDataFromDatabase();

    		    // Create pie chart
    		    JFreeChart pieChart = ChartFactory.createPieChart("Avis Distribution", dataset, true, true, false);

    		    // Customize chart appearance if needed

    		    // Display the chart
    		    ChartPanel pieChartPanel = new ChartPanel(pieChart);
    		    
    			jPanel3.removeAll();
    		    jPanel3.add(pieChartPanel, BorderLayout.CENTER);
    		    jPanel3.validate();
    		
    		// Example: Call showPieChart when a button is clicked
    		btnShowPieChart.addActionListener(new ActionListener() {
    		    @Override
    		    public void actionPerformed(ActionEvent e) {
    		        showPieChart();
    		    }
    		});}    


   
    		private void submitReview() {
    	        String name = textFieldName.getText();
    	        int Comfort1 = Comfort.getValue();
    	        int Safety1 = Safety.getValue();
    	        int Entertainment1 = Entertainment.getValue();
    	        int Cleanliness1 = Cleanliness.getValue();
    	        String comment = textAreaComment.getText();

    	        // Add your logic to store the review data or display it as needed
    	        System.out.println("Name: " + name);
    	        System.out.println("Comfort: " + Comfort1);
    	        System.out.println("Safety: " + Safety1);
    	        System.out.println("Entertainment: " + Entertainment1);
    	        System.out.println("Cleanliness: " + Cleanliness1);
    	        System.out.println("Comment: " + comment);
    	      
    	        Traitement.saveRatingToDatabase(name, Comfort1,Safety1,Entertainment1,Cleanliness1 , comment);
    	    }
    public static void main(String[] args) {
    	 // Charger le pilote JDBC
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        SwingUtilities.invokeLater(() -> {
            testFrame frame = new testFrame();
            frame.setVisible(true);
        });
       

    }
}
