package movieReviewClassification;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.awt.*;
import javax.swing.*;

/**
  Graphical User Interface for Assignment 3 
  @author Michael Paulson
  @author Francis Williams
 */
public class GUI 
{ /**
  * GUI controls I/O with user and method calls
  * @param args command line arguments
  */
    public static void main(String[] args) 
    {
        String[] tableColumnNames = {"ID",
                                     "Movie Review Preview",
                                     "Predicted Class", 
                                     "Real Class"};

        Object[][] data = { //just some testing object I stole from the internet to test out the JTable
      {"Kathy", "Smith",
       "Snowboarding", new Integer(5)},
      {"John", "Doe",
       "Rowing", new Integer(3)},
      {"Sue", "Black",
       "Knitting", new Integer(2)},
      {"Jane", "White",
       "Speed reading", new Integer(20)},
      {"Joe", "Brown",
       "Pool", new Integer(10)}
        };

        JFrame mainWindow = new JFrame("Move Review Database"); // main window for the GUI
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          JPanel mainPanel = new JPanel(new BorderLayout()); // specific layout within the mainWindow
              mainPanel.setOpaque(true);
              mainPanel.setBackground(Color.BLACK);


            JLabel title = new JLabel("Movie Review Database", SwingConstants.CENTER); // Title for the GUI
                title.setFont(new java.awt.Font("Serif", Font.BOLD, 40));
                title.setOpaque(true);
                title.setForeground(Color.WHITE);
                title.setBackground(Color.BLACK);
                mainPanel.add(title, BorderLayout.PAGE_START);

            JPanel grid = new JPanel(new GridBagLayout()); // a flexible grid to go in the far left panel
                grid.setBackground(Color.BLACK);

        GridBagConstraints c = new GridBagConstraints(); // used to control the attributes of the GridBag


            JTable reviewTable = new JTable(data, tableColumnNames);// table to hold data
                reviewTable.setFont(new Font("Serif", Font.PLAIN, 16));
                reviewTable.setOpaque(true);
                reviewTable.setForeground(Color.WHITE);
                reviewTable.setBackground(Color.BLACK);

            JScrollPane scrollPane = new JScrollPane(); // put the table inside this to give it headers and make it scrollable
            scrollPane.setViewportView(reviewTable);
                scrollPane.getViewport().setBackground(Color.BLACK);
                scrollPane.getViewport().setForeground(Color.WHITE);
                
        mainPanel.add(grid, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

              JPanel mainQuestionHolder = new JPanel(new GridLayout(2, 1)); // to hold the main Question and combo box
                  mainQuestionHolder.setOpaque(true);
                  mainQuestionHolder.setForeground(Color.WHITE);
                  mainQuestionHolder.setBackground(Color.BLACK);

                    JLabel mainQuestion = new JLabel(" Please choose from the options below ", SwingConstants.CENTER);
                        mainQuestion.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
                        mainQuestion.setForeground(Color.WHITE);
                    mainQuestionHolder.add(mainQuestion); // add to holder

                    JComboBox userOptions = new JComboBox();
                        userOptions.addItem("1. Load new movie review collection");
                        userOptions.addItem("2. Delete movie reviews from database (given its id).");
                        userOptions.addItem("3. Search movie reviews in database");
                        userOptions.addItem("4. Show database");
                    mainQuestionHolder.add(userOptions); // add to holder

                    c.insets = new Insets(20,10,20,10);
                    c.gridx = 0;
                    c.gridy = 0;
                    c.weighty = 0;

                    grid.add(mainQuestionHolder, c); // add holder to grid

        c.insets = new Insets(0,0,0,0); // reseting the padding value

              JPanel deleteByIdHolder = new JPanel(new GridLayout(3, 1)); // holds Label+TextField+SearchButton
                  deleteByIdHolder.setOpaque(true);
                  deleteByIdHolder.setForeground(Color.WHITE);
                  deleteByIdHolder.setBackground(Color.BLACK);

                JLabel alert = new JLabel("Enter Review ID");
                    alert.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
                    alert.setForeground(Color.WHITE);  
                deleteByIdHolder.add(alert); // add to holder
                
                JTextField inputText = new JTextField(10);
                deleteByIdHolder.add(inputText); // add to holder

                JButton searchButton = new JButton("Search"); 
                deleteByIdHolder.add(searchButton); // add to holder

                c.gridx = 0;
                c.gridy = 1;
                c.weighty = 0;

                deleteByIdHolder.setVisible(true);

                grid.add(deleteByIdHolder, c); // adding holder to the grid

              JPanel searchReviewHolder = new JPanel(new GridBagLayout()); // radio buttons+TextBox+SearchButton
                  searchReviewHolder.setOpaque(true);
                  searchReviewHolder.setForeground(Color.WHITE);
                  searchReviewHolder.setBackground(Color.BLACK);

                JPanel radioHolder = new JPanel(new GridLayout(1, 2));

                JRadioButton stringButton = new JRadioButton("By substring");
                    stringButton.setOpaque(true);
                    stringButton.setForeground(Color.WHITE);
                    stringButton.setBackground(Color.BLACK);
                radioHolder.add(stringButton);
                JRadioButton idButton = new JRadioButton("By Review ID");
                    idButton.setOpaque(true);
                    idButton.setForeground(Color.WHITE);
                    idButton.setBackground(Color.BLACK);
                radioHolder.add(idButton);

                ButtonGroup group = new ButtonGroup();
                    group.add(stringButton);
                    group.add(idButton);
                c.gridx = 0;
                c.gridy = 0;    
                searchReviewHolder.add(radioHolder, c);

                JTextArea stringInput = new JTextArea(4,30);
                c.gridx = 0;
                c.gridy = 1; 
                searchReviewHolder.add(stringInput, c);

                JButton searchButton2 = new JButton("Search");
                  searchButton2.setPreferredSize(new Dimension(130,30));
                c.gridx = 0;
                c.gridy = 2;
                c.insets = new Insets(0,10,0,10);
                searchReviewHolder.add(searchButton2, c);

                c.insets = new Insets(20,0,0,0);
                c.gridx = 0;
                c.gridy = 2;
                c.weighty = 0;
                grid.add(searchReviewHolder, c);

        JLabel blankLabel = new JLabel("");
          blankLabel.setOpaque(true);
          blankLabel.setBackground(Color.BLACK);
          c.gridx = 0;
          c.gridy = 3;
          c.weighty = .5;
          grid.add(blankLabel, c);


        mainWindow.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainWindow.pack(); //windowsize fits all contents tightly
        mainWindow.setVisible(true);
            
    }
}