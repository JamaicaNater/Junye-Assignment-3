
package movieReviewClassification;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BuildGUI
{
  JFrame mainWindow = new JFrame("Move Review Database"); // main window for the GUI
  JPanel mainPanel = new JPanel(new BorderLayout()); // specific layout within the mainWindow
  String[] tableColumnNames = {"ID",
                                     "Movie Review Preview",
                                     "Predicted Class", 
                                     "Real Class"};
  Object[][] data = {};                                   
  JLabel title = new JLabel("Movie Review Database", SwingConstants.CENTER); // Title for the GUI
  JPanel grid = new JPanel(new GridBagLayout()); // a flexible grid to go in the far left panel
  GridBagConstraints c = new GridBagConstraints(); // used to control the attributes of the GridBag
  JTable reviewTable; // table for holding all of the data from the database
  JScrollPane scrollPane = new JScrollPane(); // put the table inside this to give it headers and make it scrollable
  JPanel mainQuestionHolder = new JPanel(new GridLayout(2, 1)); // to hold the main Question and combo box
  JLabel mainQuestion; //Main prompt for user label
  JComboBox userOptions = new JComboBox(); // main options for users
  JPanel deleteByIdHolder = new JPanel(new GridLayout(3, 1)); // holds Label+TextField+SearchButton
  JLabel enterIdLabel = new JLabel(" Enter ID to delete "); // text of label for Delete by ID option
  JPanel searchReviewHolder = new JPanel(new GridBagLayout()); // radio buttons+TextBox+SearchButton
  JPanel radioHolder = new JPanel(new GridLayout(1, 2)); // holds the radio buttons sidde by side
  JRadioButton idButton = new JRadioButton("By Review ID");
  ButtonGroup group = new ButtonGroup();
  JTextArea stringInput = new JTextArea(4,30);
  JRadioButton stringButton = new JRadioButton("By substring"); // the text of one of the radio options
  JButton searchButton2 = new JButton("Search");
  JButton searchButton = new JButton("Search");
  JTextField inputText = new JTextField(10); 
  JLabel blankLabel = new JLabel(""); // blank label added to the buttom to push all of the items up towards the top on teh West Side

    BuildGUI()
    {  
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);
              
        title.setFont(new java.awt.Font("Serif", Font.BOLD, 40));
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setBackground(Color.BLACK);

        mainPanel.add(title, BorderLayout.PAGE_START);
             
        grid.setBackground(Color.BLACK);

        reviewTable = new JTable(data, tableColumnNames);// table to hold data
            reviewTable.setFont(new Font("Serif", Font.PLAIN, 16));
            reviewTable.setOpaque(true);
            reviewTable.setForeground(Color.WHITE);
            reviewTable.setBackground(Color.BLACK);
              
        scrollPane.setViewportView(reviewTable);
            scrollPane.createVerticalScrollBar();
            scrollPane.getViewport().setBackground(Color.BLACK);
            scrollPane.getViewport().setForeground(Color.WHITE);
                  
        mainPanel.add(grid, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
               
        mainQuestionHolder.setOpaque(true);
        mainQuestionHolder.setForeground(Color.WHITE);
        mainQuestionHolder.setBackground(Color.BLACK);

        mainQuestion = new JLabel(" Please choose from the options below ", SwingConstants.CENTER);
            mainQuestion.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
            mainQuestion.setForeground(Color.WHITE);
            mainQuestionHolder.add(mainQuestion); // add to holder

        userOptions.addItem("1. Load new movie review collection");
        userOptions.addItem("2. Delete movie reviews from database (given its id)");
        userOptions.addItem("3. Search movie reviews in database");
        userOptions.addItem("4. Show database");
     
        mainQuestionHolder.add(userOptions); // add to holder

        c.insets = new Insets(20,10,20,10);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;

        grid.add(mainQuestionHolder, c); // add holder to grid

        c.insets = new Insets(0,0,0,0); // reseting the padding value
                
        deleteByIdHolder.setOpaque(true);
        deleteByIdHolder.setForeground(Color.WHITE);
        deleteByIdHolder.setBackground(Color.BLACK);
                  
        enterIdLabel.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
        enterIdLabel.setForeground(Color.WHITE);  
        deleteByIdHolder.add(enterIdLabel); // add to holder
                            
        deleteByIdHolder.add(inputText); // add to holder
                  

        deleteByIdHolder.add(searchButton); // add to holder

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0;

        deleteByIdHolder.setVisible(false);

        grid.add(deleteByIdHolder, c); // adding holder to the grid
                
        searchReviewHolder.setOpaque(true);
        searchReviewHolder.setForeground(Color.WHITE);
        searchReviewHolder.setBackground(Color.BLACK);

        stringButton.setOpaque(true);
        stringButton.setForeground(Color.WHITE);
        stringButton.setBackground(Color.BLACK);
        radioHolder.add(stringButton);

        idButton.setOpaque(true);
        idButton.setForeground(Color.WHITE);
        idButton.setBackground(Color.BLACK);
         radioHolder.add(idButton);

        group.add(stringButton);
        group.add(idButton);
        c.gridx = 0;
        c.gridy = 0;

        searchReviewHolder.add(radioHolder, c);

        
        c.gridx = 0;
        c.gridy = 1;

        searchReviewHolder.add(stringInput, c);

        
        searchButton2.setPreferredSize(new Dimension(130,35));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,10,0,10);

        searchReviewHolder.add(searchButton2, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0;

        grid.add(searchReviewHolder, c);

        searchReviewHolder.setVisible(false);

        blankLabel.setOpaque(true);
        blankLabel.setBackground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = .5;

        grid.add(blankLabel, c);

        mainWindow.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainWindow.pack(); //windowsize fits all contents tightly
        mainWindow.setVisible(true);
       
       searchButton.addActionListener(
          new ActionListener() 
          {
              public void actionPerformed(ActionEvent e)
              {
                inputText.setText("Done!");
              }
          }
        );
        
        userOptions.addItemListener(
            new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    if(e.getItem().equals("1. Load new movie review collection"))
                    {
                        
                    }
                    else if(e.getItem().equals("2. Delete movie reviews from database (given its id)"))
                    {
                        deleteByIdHolder.setVisible(true);
                        searchReviewHolder.setVisible(false);
                    }
                    else if(e.getItem().equals("3. Search movie reviews in database"))
                    {
                        deleteByIdHolder.setVisible(false);
                        searchReviewHolder.setVisible(true);
                    }
                }
            }

        );
  }

}
