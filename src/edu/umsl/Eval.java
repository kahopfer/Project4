/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

/**
 *
 * @author Kyle
 */



import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.*;
//import org.apache.derby.jdbc.*;
//import sun.jdbc.odbc.JdbcOdbcDriver;

public class Eval extends JFrame implements ActionListener
{
    //DECLARE THE ELEMENTS OR OBJECTS THAT YOU WILL PUT IN YOUR FRAME
    //NOTICE HOW A PANEL IS CREATED FOR EACH ONE THIS WILL MAKE IT EASIER BUILD
  
    public JLabel teamLabel;
    private JComboBox teamComboBox;
    public JComboBox imagesComboBox;
    private JPanel teamPanel;
    private JPanel sliderPanel;
    public JLabel q1Label;
    public JLabel q2Label;
    public JLabel q3Label;
    public JLabel q4Label;
    static final int MIN = 1;
    static final int MAX = 8;
    static final int INIT = 1;
    public JSlider q1Slider;
    public JSlider q2Slider;
    public JSlider q3Slider;
    public JSlider q4Slider;
    private JPanel commentsPanel;
    private JLabel commentLabel;
    private JTextField commentBox;
    private JPanel avgPanel;
    private JLabel avgLabel;
    private JButton avgButton;
    private JTextField avgBox;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JButton clearButton;
  
   //these are fields that will be used to hold the values pulled from the interface
    String teamname;
    int q1;
    int q2;
    int q3;
    int q4;
    String comments;
    double teamavg;
   
   
   
    // instance variables used to manipulate database
    private Connection myConnection;//highway
    private Statement myStatement;//delivery truck on highway
    private ResultSet myResultSet;//load that the truck will bring back



    //MAIN METHOD: NOTICE WE TAKE IN THE ARGUMENTS THAT ARE
    //PASSED IN AND INSTANTIATE OUR CLASS WITH THEM
    public static void main(String args[])
    {
        // check command-line arguments
//        if ( args.length == 2 )
//        {
         // get command-line arguments
        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        //String databaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
        String databaseURL = "jdbc:derby://localhost:1527/Eval;create=true";

        // create new Eval
         Eval eval = new Eval( databaseDriver, databaseURL );
         eval.createUserInterface();
         eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
//        }
//        else // invalid command-line arguments
//        {
//           System.out.println( "Usage: java EVAL needs databaseDriver databaseURL" );
//        }
    }
  
    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
    //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval(String databaseDriver, String databaseURL)
    {
        // establish connection to database
        try
        {
          // load Sun driver
          //Class.forName( databaseDriver );
          DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
          // connect to database
          myConnection = DriverManager.getConnection( databaseURL );

          // create Statement for executing SQL
          myStatement = myConnection.createStatement();
        }
        catch ( SQLException exception )
        {
           exception.printStackTrace();
        }
//        catch ( ClassNotFoundException exception )
//        {
//           exception.printStackTrace();
//        }
        // set up accountNumberJComboBox
    
      
     // createUserInterface(); // set up GUI    
    }
  
    private void createUserInterface()
    {
        // get content pane for attaching GUI components
        Container contentPane = getContentPane();

        contentPane.setLayout( null );

        // INSTRUCTOR COMBO BOX SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // set up Instructor Panel

        // enable explicit positioning of GUI components

        teamPanel = new JPanel();
        teamPanel.setBounds(40, 20, 500, 46 );
        teamPanel.setLayout( null );
        contentPane.add( teamPanel );

        // set up Instructor Label
        teamLabel = new JLabel();
        teamLabel.setBounds( 25, 13, 100, 20 );
        teamLabel.setText( "Teams:" );
        teamPanel.add( teamLabel );

        // set up accountNumberJComboBox
        teamComboBox = new JComboBox();
        teamComboBox.setBounds( 150, 13, 300, 20 );
        teamComboBox.addItem( "" );
        teamComboBox.setSelectedIndex( 0 );
        teamPanel.add( teamComboBox );
        
        sliderPanel = new JPanel();
        sliderPanel.setBounds(40, 66, 500, 300);
        sliderPanel.setLayout(null);
        contentPane.add(sliderPanel);
        
        Hashtable labelTable = new Hashtable();
        labelTable.put(1, new JLabel("C-") );
        labelTable.put(2, new JLabel("C") );
        labelTable.put(3, new JLabel("C+") );
        labelTable.put(4, new JLabel("B-"));
        labelTable.put(5, new JLabel("B"));
        labelTable.put(6, new JLabel("B+"));
        labelTable.put(7, new JLabel("A-"));
        labelTable.put(8, new JLabel("A"));        
        
        q1Label = new JLabel();
        q1Label.setBounds(25, 40, 100, 20);
        q1Label.setText("Q1: Technical?");
        sliderPanel.add(q1Label);
        
        q1Slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        q1Slider.setMajorTickSpacing(1);
        q1Slider.setPaintTicks(true);
        q1Slider.setPaintLabels(true);
        q1Slider.setLabelTable( labelTable );
        q1Slider.setPaintLabels(true);
        q1Slider.setBounds(150, 40, 300, 50 );
        sliderPanel.add(q1Slider);
        
        q2Label = new JLabel();
        q2Label.setBounds(25, 100, 100, 20);
        q2Label.setText("Q2: Useful?");
        sliderPanel.add(q2Label);
        
        q2Slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        q2Slider.setMajorTickSpacing(1);
        q2Slider.setPaintTicks(true);
        q2Slider.setPaintLabels(true);
        q2Slider.setLabelTable( labelTable );
        q2Slider.setPaintLabels(true);
        q2Slider.setBounds(150, 100, 300, 50 );
        sliderPanel.add(q2Slider);
        
        q3Label = new JLabel();
        q3Label.setBounds(25, 160, 100, 20);
        q3Label.setText("Q3: Clarity?");
        sliderPanel.add(q3Label);
        
        q3Slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        q3Slider.setMajorTickSpacing(1);
        q3Slider.setPaintTicks(true);
        q3Slider.setPaintLabels(true);
        q3Slider.setLabelTable( labelTable );
        q3Slider.setPaintLabels(true);
        q3Slider.setBounds(150, 160, 300, 50 );
        sliderPanel.add(q3Slider);
        
        q4Label = new JLabel();
        q4Label.setBounds(25, 220, 100, 20);
        q4Label.setText("Q4: Overall?");
        sliderPanel.add(q4Label);
        
        q4Slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        q4Slider.setMajorTickSpacing(1);
        q4Slider.setPaintTicks(true);
        q4Slider.setPaintLabels(true);
        q4Slider.setLabelTable( labelTable );
        q4Slider.setPaintLabels(true);
        q4Slider.setBounds(150, 220, 300, 50 );
        sliderPanel.add(q4Slider);
               
        commentsPanel = new JPanel();
        commentsPanel.setBounds(40, 366, 500, 100);
        commentsPanel.setLayout(null);
        contentPane.add(commentsPanel);
        
        commentLabel = new JLabel();
        commentLabel.setBounds(25, 40, 100, 20);
        commentLabel.setText("Comments:");
        commentsPanel.add(commentLabel);
        
        commentBox = new JTextField( "Add Group Member Names Here", 20 );
        commentBox.setBounds(150, 13, 300, 75);
        commentBox.setFont( new Font( "Serif", Font.PLAIN, 12 ) );
        commentsPanel.add( commentBox );
   
        avgPanel = new JPanel();
        avgPanel.setBounds(40, 466, 500, 66);
        avgPanel.setLayout(null);
        contentPane.add(avgPanel);
        
        avgLabel = new JLabel();
        avgLabel.setBounds(25, 7, 240, 20);
        avgLabel.setText("Computed average from questions above:");
        avgPanel.add(avgLabel);
        
        avgButton = new JButton("CalcAvg");
        avgButton.setBounds(210, 35, 80, 22);
        avgPanel.add(avgButton);
        
        avgBox = new JTextField();
        avgBox.setBounds(275, 7, 30, 25);
        avgBox.setFont( new Font( "Serif", Font.PLAIN, 12 ) );
        avgPanel.add(avgBox);
  
        buttonPanel = new JPanel();
        buttonPanel.setBounds(40, 532, 500, 66);
        buttonPanel.setLayout(null);
        contentPane.add(buttonPanel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(80, 22, 75, 22);
        submitButton.setEnabled(false);
        buttonPanel.add(submitButton);
                
        clearButton = new JButton("Clear");
        clearButton.setBounds(345, 22, 75, 22);
        buttonPanel.add(clearButton);

        avgButton.addActionListener(this);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        loadTeams();

        setTitle( "EVAL" );   // set title bar string
        setSize( 600, 656 ); // set window size
        setVisible( true );  // display window
    }

  
    private void loadTeams()
    {
        // get all account numbers from database
        try
        {
            myResultSet = myStatement.executeQuery( "SELECT DISTINCT TEAMNAME FROM APP.TEAMS order by TEAMNAME asc");

            while ( myResultSet.next() )
            {
                teamComboBox.addItem(myResultSet.getString( "TEAMNAME" ) );
            }

            myResultSet.close(); // close myResultSet
        } // end try

        catch ( SQLException exception )
        {
           exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == avgButton)
        {
            teamavg = ((q1Slider.getValue() + q2Slider.getValue() + q3Slider.getValue() + q4Slider.getValue()) / 4.0);
            avgBox.setText(String.valueOf(teamavg));
            submitButton.setEnabled(true);
        }
        else if(event.getSource() == submitButton)
        {
            boolean somethingIsMissing = false;
            if(teamComboBox.getSelectedItem().toString().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please select a team before submitting.");
                somethingIsMissing = true;
            }
            if(commentBox.getText().equals("Add Group Member Names Here"))
            {
                JOptionPane.showMessageDialog(null, "Please type the group member names before submitting.");
                somethingIsMissing = true;
            }
            if(somethingIsMissing == false)
            {
                teamname = teamComboBox.getSelectedItem().toString();
                q1 = q1Slider.getValue();
                q2 = q2Slider.getValue();
                q3 = q3Slider.getValue();
                q4 = q4Slider.getValue();
                comments = commentBox.getText();
                updateTeams();
            }            
        }
        else if(event.getSource() == clearButton)
        {
            teamComboBox.setSelectedIndex( 0 );
            q1Slider.setValue(1);
            q2Slider.setValue(1);
            q3Slider.setValue(1);
            q4Slider.setValue(1);
            commentBox.setText("Add Group Member Names Here");
            avgBox.setText("");
            submitButton.setEnabled(false);
        }
    }

    private void updateTeams()
    {
        // update balance in database
        try
        {
            PreparedStatement update = myConnection.prepareStatement("UPDATE APP.TEAMS SET Q1 = ?, Q2 = ?, Q3 = ?, Q4 = ?, AVGSCORE = ?, COMMENTS = ? WHERE TEAMNAME = ?");
            update.setInt(1, q1);
            update.setInt(2, q2);
            update.setInt(3, q3);
            update.setInt(4, q4);
            update.setDouble(5, teamavg);
            update.setString(6, comments);
            update.setString(7, teamname);
            update.executeUpdate();
        }
        catch ( SQLException exception )
        {
            exception.printStackTrace();
        }
    }
}