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

        JFrame mainWindow = new JFrame("Move Review Database");
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        JPanel testPanel = new JPanel(new BorderLayout());
            testPanel.setOpaque(true);
            testPanel.setBackground(Color.BLACK);
       
        JLabel testLabel0 = new JLabel("Movie Review Database", SwingConstants.CENTER);
            testLabel0.setFont(new java.awt.Font("Serif", Font.PLAIN, 30));
            testLabel0.setOpaque(true);
            testLabel0.setForeground(Color.WHITE);
            testLabel0.setBackground(Color.BLACK);
            testPanel.add(testLabel0, BorderLayout.PAGE_START);
        

        JPanel grid = new JPanel(new GridLayout(20, 1));
            grid.setBackground(Color.BLACK);

        testPanel.add(grid, BorderLayout.LINE_START);

        JLabel testLabel2 = new JLabel(" Please choose from the dropdown below ");
            testLabel2.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
            testLabel2.setForeground(Color.WHITE);

        grid.add(testLabel2);

        JComboBox userOptions = new JComboBox();
            userOptions.addItem("1. Load new movie review collection");
            userOptions.addItem("2. Delete movie reviews from database (given its id).");
            userOptions.addItem("3. Search movie reviews in database");
            userOptions.addItem("4. Show database");

        grid.add(userOptions);

        JLabel alert = new JLabel("test");
            alert.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
            alert.setForeground(Color.WHITE);

        JTextField inputText = new JTextField(10);

        grid.add(alert);
        grid.add(inputText);

        JPanel radioHolder = new JPanel(new GridLayout(1, 2));

        JRadioButton stringButton = new JRadioButton("Search by Review substring");
            radioHolder.add(stringButton);
        JRadioButton idButton = new JRadioButton("Search by Review ID");
            radioHolder.add(idButton);

        ButtonGroup group = new ButtonGroup();
            group.add(stringButton);
            group.add(idButton);

        grid.add(radioHolder);

        JTextArea stringInput = new JTextArea(10,20);

        grid.add(stringInput);

        JButton searchButton = new JButton("Search");

        grid.add(searchButton);




        mainWindow.getContentPane().add(testPanel, BorderLayout.CENTER);
        mainWindow.setSize(1000,1000);
        mainWindow.setVisible(true);
            
    }
}