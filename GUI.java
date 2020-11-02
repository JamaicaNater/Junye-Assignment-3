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

        JFrame mainWindow = new JFrame("Move Review Database");
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setOpaque(true);
            mainPanel.setBackground(Color.BLACK);

        JLabel title = new JLabel("Movie Review Database", SwingConstants.CENTER);
            title.setFont(new java.awt.Font("Serif", Font.BOLD, 40));
            title.setOpaque(true);
            title.setForeground(Color.WHITE);
            title.setBackground(Color.BLACK);
            mainPanel.add(title, BorderLayout.PAGE_START);

        JPanel grid = new JPanel(new GridBagLayout());
            grid.setBackground(Color.BLACK);

        GridBagConstraints c = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane();

        JTable reviewTable = new JTable(data, tableColumnNames);
            reviewTable.setFont(new Font("Serif", Font.PLAIN, 16));
            reviewTable.setOpaque(true);
            reviewTable.setForeground(Color.WHITE);
            reviewTable.setBackground(Color.BLACK);

        scrollPane.setViewportView(reviewTable);
            scrollPane.getViewport().setBackground(Color.BLACK);
            scrollPane.getViewport().setForeground(Color.WHITE);
                
        mainPanel.add(grid, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JLabel testLabel2 = new JLabel(" Please choose from the dropdown below ", SwingConstants.CENTER);
            testLabel2.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
            testLabel2.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        grid.add(testLabel2, c);

        JComboBox userOptions = new JComboBox();
            userOptions.addItem("1. Load new movie review collection");
            userOptions.addItem("2. Delete movie reviews from database (given its id).");
            userOptions.addItem("3. Search movie reviews in database");
            userOptions.addItem("4. Show database");
        c.gridx = 0;
        c.gridy = 1;
        grid.add(userOptions, c);

        JLabel alert = new JLabel("Enter Review ID");
            alert.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
            alert.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 4;    
        grid.add(alert, c);
        
        JTextField inputText = new JTextField(10);
        c.gridx = 0;
        c.gridy = 6;  
        grid.add(inputText, c);

        JButton searchButton = new JButton("Search");
        c.gridx = 0;
        c.gridy = 7;  
        grid.add(searchButton, c);

        JPanel radioHolder = new JPanel(new GridLayout(1, 2));

        JRadioButton stringButton = new JRadioButton("Search by Review substring");
            stringButton.setOpaque(true);
            stringButton.setForeground(Color.WHITE);
            stringButton.setBackground(Color.BLACK);
            radioHolder.add(stringButton);
        JRadioButton idButton = new JRadioButton("Search by Review ID");
            idButton.setOpaque(true);
            idButton.setForeground(Color.WHITE);
            idButton.setBackground(Color.BLACK);
            radioHolder.add(idButton);

        ButtonGroup group = new ButtonGroup();
            group.add(stringButton);
            group.add(idButton);

        c.gridx = 0;
        c.gridy = 8;  
        grid.add(radioHolder, c);

        JTextArea stringInput = new JTextArea(10,20);
        c.gridx = 0;
        c.gridy = 10;  
        grid.add(stringInput, c);

        JButton searchButton2 = new JButton("Search");
        c.gridx = 0;
        c.gridy = 12;  
        grid.add(searchButton2, c);

        mainWindow.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainWindow.pack();
        mainWindow.setVisible(true);
            
    }
}