
package src.movieReviewClassification;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUI_Interface
{
    public static void main(String[] args)
    {
        GUI_Interface gui = new GUI_Interface();
    }

/*---------------------------------------------Main Section-----------------------------------------------------------*/
    JFrame mainWindow = new JFrame("Move Review Database"); // main window for the GUI
    JPanel mainPanel = new JPanel(new BorderLayout()); // specific layout within the mainWindow
    String[] tableColumnNames = {         "ID",
                                                                        "Movie Review Preview",
                                                                        "Predicted Class",
                                                                        "Real Class"};
    Object[][] data = {};
    JLabel title = new JLabel("Movie Review Database", SwingConstants.CENTER); // Title for the GUI
    JPanel grid = new JPanel(new GridBagLayout()); // a flexible grid to go in the far left panel
    GridBagConstraints c = new GridBagConstraints(); // used to control the attributes of the GridBag
    JTable reviewTable = new JTable(); // table for holding all of the data from the database
    JScrollPane scrollPane = new JScrollPane(); // put the table inside this to give it headers and make it scrollable
    JPanel mainQuestionHolder = new JPanel(new GridLayout(4, 1)); // to hold the main Question and combo box
    JLabel mainQuestion; //Main prompt for user label
    JComboBox userOptions = new JComboBox(); // main options for users


/*---------------------------------------Choosing Review Type Section-------------------------------------------------*/
    JLabel posORneg = new JLabel("Choose the real class of the file", SwingConstants.CENTER);
    //    Radio Buttons
    JPanel radioHolder0 = new JPanel(new GridLayout(1, 4)); // holds the radio buttons side by side
        JRadioButton posButton = new JRadioButton("Positive");
        JRadioButton negButton = new JRadioButton("Negative"); // the text of one of the radio options
        JRadioButton unknoButton = new JRadioButton("Unknown");

    JButton submit = new JButton("Submit");
    ButtonGroup    posNegUnknBtnGroup = new ButtonGroup();// grouping the buttons


/*----------------------------------------------Option Setup Section--------------------------------------------------*/
    JPanel deleteByIdHolder = new JPanel(new GridLayout(3, 1)); // holds Label+TextField+SearchButton
    JLabel enterIdLabel = new JLabel(" Enter ID to delete "); // text of label for Delete by ID option
    JPanel searchReviewHolder = new JPanel(new GridBagLayout()); // radio buttons+TextBox+SearchButton
    JPanel radioHolder = new JPanel(new GridLayout(1, 2)); // holds the radio buttons side by side
    JRadioButton idButton = new JRadioButton("By Review ID");
    JRadioButton stringButton = new JRadioButton("By substring"); // the text of one of the radio options
    ButtonGroup group = new ButtonGroup();
    JTextArea stringInput = new JTextArea(4,30);
    JButton searchButton2 = new JButton("Search");
    JButton searchButton = new JButton("Search");
    JTextField inputText = new JTextField(10);
    JLabel blankLabel = new JLabel(""); // blank label added to the button to push all of the items up towards the top on teh West Side

    GUI_Interface()
    {
        // Review handler created upon initialization
        ReviewHandler reviewHandler = new ReviewHandler();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);

        title.setFont(new java.awt.Font("Serif", Font.BOLD, 40));
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setBackground(Color.BLACK);

        mainPanel.add(title, BorderLayout.PAGE_START);

        grid.setBackground(Color.BLACK);

        for (int i : reviewHandler.database.keySet())
        {
            data[i][0] = "test";
            data[i][1] = reviewHandler.database.get(i).getText50();
            data[i][2]= reviewHandler.database.get(i).getPredictedClass();
            data[i][3] = reviewHandler.database.get(i).getRealClass();
        }

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

        userOptions.addItem("");
        userOptions.addItem("1. Load new movie review collection");
        userOptions.addItem("2. Delete movie reviews from database (given its id)");
        userOptions.addItem("3. Search movie reviews in database");
        userOptions.addItem("4. Show database");

        posButton.setOpaque(true);
        posButton.setForeground(Color.WHITE);
        posButton.setBackground(Color.BLACK);

        negButton.setOpaque(true);
        negButton.setForeground(Color.WHITE);
        negButton.setBackground(Color.BLACK);

        unknoButton.setOpaque(true);
        unknoButton.setForeground(Color.WHITE);
        unknoButton.setBackground(Color.BLACK);

        radioHolder0.setBackground(Color.BLACK);

        radioHolder0.add(posButton);
        radioHolder0.add(negButton);
        radioHolder0.add(unknoButton);
        radioHolder0.add(submit);

           posNegUnknBtnGroup.add(posButton);
           posNegUnknBtnGroup.add(negButton);
           posNegUnknBtnGroup.add(unknoButton);

        posORneg.setFont(new Font("Serif", Font.PLAIN, 16));
        posORneg.setOpaque(true);
        posORneg.setForeground(Color.WHITE);
        posORneg.setBackground(Color.BLACK);

        mainQuestionHolder.add(mainQuestion); // add to holder
        mainQuestionHolder.add(userOptions); // add to holder
        mainQuestionHolder.add(posORneg);
        mainQuestionHolder.add(radioHolder0);

        posORneg.setVisible(false);
        radioHolder0.setVisible(false);


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

        searchButton.addActionListener
        (
            e -> inputText.setText("Done!")
        );

        userOptions.addItemListener
        (
        new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    if(e.getItem().equals("1. Load new movie review collection") && e.getStateChange() == ItemEvent.SELECTED)
                    {
                        deleteByIdHolder.setVisible(false);
                        searchReviewHolder.setVisible(false);
                        posORneg.setVisible(true);
                        radioHolder0.setVisible(true);


                        int check;
                        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
                        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        check = fileChooser.showOpenDialog(mainWindow);

                        // We only allow the Button to do something if we recieve a filepath from j file choser o
                        if(check == JFileChooser.APPROVE_OPTION)
                        {
                            String filepath = fileChooser.getSelectedFile().getPath();
                            submit.addActionListener
                            (
                                e1 ->
                                {
                                    int classif = -1;

                                    if (negButton.isSelected())
                                    {
                                        classif = 0;
                                    }
                                    else if (posButton.isSelected())
                                    {
                                        classif = 1;
                                    }
                                    else if (negButton.isSelected())
                                    {
                                        classif = 2;
                                    }
                                    System.out.println(filepath);

                                    reviewHandler.loadReviews(filepath, classif);
                                }

                            );
                        }
                    }
                    else if(e.getItem().equals("2. Delete movie reviews from database (given its id)"))
                    {
                            deleteByIdHolder.setVisible(true);
                            searchReviewHolder.setVisible(false);
                            posORneg.setVisible(false);
                            radioHolder0.setVisible(false);

                            searchButton.addActionListener
                            (
                                e2 ->
                                {
                                    try
                                    {
                                        reviewHandler.deleteReview(Integer.parseInt(inputText.getText()));
                                    }
                                    catch (NumberFormatException e1)
                                    {
                                        System.out.println("Input was not a number");
                                    }
                                }
                            );
                    }
                    else if(e.getItem().equals("3. Search movie reviews in database"))
                    {
                            deleteByIdHolder.setVisible(false);
                            searchReviewHolder.setVisible(true);
                            posORneg.setVisible(false);
                            radioHolder0.setVisible(false);


                    }
                    else if(e.getItem().equals("4. Show database"))
                    {
                        reviewTable.setVisible(true);
                        scrollPane.setVisible(true);

                        System.out.println("|Review ID|Review Preview                                         |Predicted Class |Real Class|"); // outputting in a table format // this might just be for our use because I dont think it is necessary to output them all
                        System.out.println("===============================================================================================");
                        for(Integer it : reviewHandler.database.keySet()) //iterating through using the keys to then access the methods within each object
                        {
                            System.out.format("| %8d", it); // setting the width of the output to 8
                            System.out.print( "| " + reviewHandler.database.get(it).getText50() + "...");
                            if(reviewHandler.database.get(it).getPredictedClass() == 0)
                            {
                                System.out.print( " | " + "Negative      " );
                            }
                            else
                            {
                                System.out.print( " | " + "Positive      " );
                            }

                            if(reviewHandler.database.get(it).getRealClass() == 0)
                            {
                                System.out.format( " | " + "Negative" + " |%n");
                            }
                            else if (reviewHandler.database.get(it).getRealClass() == 1)
                            {
                                System.out.format( " | " + "Positive" + " |%n");
                            }
                            else
                            {
                                System.out.format( " | " + "Unknown" + "  |%n");
                            }
                        }
                    }

                }
            }
        );
    }

}
