package src.movieReviewClassification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUI_Interface implements ItemListener, ActionListener
{
/*---------------------------------------------Main Section-----------------------------------------------------------*/
    JFrame mainWindow = new JFrame("Move Review Database"); // main window for the GUI
    JPanel mainPanel = new JPanel(new BorderLayout()); // specific layout within the mainWindow
    String[] tableColumnNames = {         "ID",
                                                                        "Movie Review Preview",
                                                                        "Predicted Class",
                                                                        "Real Class"};
    JLabel title = new JLabel("Movie Review Database", SwingConstants.CENTER); // Title for the GUI
    JPanel grid = new JPanel(new GridBagLayout()); // a flexible grid to go in the far left panel
    GridBagConstraints c = new GridBagConstraints(); // used to control the attributes of the GridBag
    JTable reviewTable = new JTable(); // table for holding all of the data from the database
    JScrollPane scrollPane = new JScrollPane(); // put the table inside this to give it headers and make it scrollable
    JPanel mainQuestionHolder = new JPanel(new GridLayout(4, 1)); // to hold the main Question and combo box
    JLabel mainQuestion; //Main prompt for user label
    JComboBox userOptions = new JComboBox(); // main options for users
    JButton refreshButton = new JButton("Refresh");


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
    JPanel searchReviewHolder = new JPanel(new GridBagLayout()); // radio buttons+TextBox+deleteButton
    JPanel radioHolder = new JPanel(new GridLayout(1, 2)); // holds the radio buttons side by side
    JRadioButton idButton = new JRadioButton("By Review ID");
    JRadioButton stringButton = new JRadioButton("By substring"); // the text of one of the radio options
    ButtonGroup group = new ButtonGroup();
    JTextArea searchInput = new JTextArea(4,30);
    JButton searchButton = new JButton("Search");
    JButton deleteButton = new JButton("Delete");
    JTextField inputText = new JTextField(10);
    JLabel blankLabel = new JLabel(""); // blank label added to the button to push all of the items up towards the top on teh West Side
    ReviewHandler reviewHandler = new ReviewHandler();
    String filepath;
    JFileChooser fileChooser;

    GUI_Interface()
    {
        // Review handler created upon initialization
        String dataBasePath = System.getProperty("user.dir") + System.getProperty("file.separator") + reviewHandler.DATA_FILE_NAME;
        File checkFileExist = new File(dataBasePath); //load data path to database to check if is currently exists

          if(checkFileExist.exists())
          {
            reviewHandler.loadSerialDB();
          }
            setTable(reviewHandler, (HashMap<Integer, MovieReview>) reviewHandler.database);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLACK);

        title.setFont(new java.awt.Font("Serif", Font.BOLD, 40));
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setBackground(Color.BLACK);

        mainPanel.add(title, BorderLayout.PAGE_START);

        grid.setBackground(Color.BLACK);

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
        grid.add(refreshButton);
        refreshButton.addActionListener(this);

        c.insets = new Insets(0,0,0,0); // reseting the padding value

        deleteByIdHolder.setOpaque(true);
        deleteByIdHolder.setForeground(Color.WHITE);
        deleteByIdHolder.setBackground(Color.BLACK);

        enterIdLabel.setFont(new java.awt.Font("Serif", Font.PLAIN, 20));
        enterIdLabel.setForeground(Color.WHITE);
        deleteByIdHolder.add(enterIdLabel); // add to holder

        deleteByIdHolder.add(inputText); // add to holder

        deleteByIdHolder.add(deleteButton); // add to holder

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

        searchReviewHolder.add(searchInput, c);

        searchButton.setPreferredSize(new Dimension(130,35));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,10,0,10);

        searchReviewHolder.add(searchButton, c);

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

        userOptions.addItemListener(this);
        submit.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        idButton.addActionListener(this);
    }

public void setTable(ReviewHandler reviewHandler, HashMap<Integer, MovieReview> db)
{
    try {
        reviewHandler.threadList.get("Load Database").join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    reviewTable.setVisible(true);
    scrollPane.setVisible(true);
    Object[][] data = new Object[reviewHandler.database.size()][4];
    int j = 0;
    for (int i : db.keySet())
    {
        data[j][0] = reviewHandler.database.get(i).getID();
        data[j][1] = reviewHandler.database.get(i).getText50();

        if(reviewHandler.database.get(i).getPredictedClass() == 0)
        {
            data[j][2] = "Negative";
        }
        else
        {
            data[j][2] = "Positive";  
        }
        if(reviewHandler.database.get(i).getRealClass() == 0)
        {
            data[j][3] = "Negative";
        }
        else if(reviewHandler.database.get(i).getRealClass() == 1)
        {
            data[j][3] = "Positive";  
        }
        else
        {
            data[j][3] = "Unknown";
        }
        j++;
    }

        reviewTable = new JTable(data, tableColumnNames);
        reviewTable.setDefaultEditor(Object.class, null);
        reviewTable.setRowHeight(25);
        reviewTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        reviewTable.setFont(new Font("Serif", Font.PLAIN, 18));
        reviewTable.setOpaque(true);
        reviewTable.setForeground(Color.WHITE);
        reviewTable.setBackground(Color.BLACK);
        scrollPane.setViewportView(reviewTable);
        scrollPane.createVerticalScrollBar();
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.getViewport().setForeground(Color.WHITE);
        mainPanel.add(grid, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

public void itemStateChanged(ItemEvent e)
{
    if (e.getItem().equals("") && e.getStateChange() == ItemEvent.SELECTED)
    {
        deleteByIdHolder.setVisible(false);
        searchReviewHolder.setVisible(false);
        posORneg.setVisible(false);
        radioHolder0.setVisible(false);
        refreshButton.setVisible(true);
    }
    if(e.getItem().equals("1. Load new movie review collection") && e.getStateChange() == ItemEvent.SELECTED)
    {
        deleteByIdHolder.setVisible(false);
        searchReviewHolder.setVisible(false);
        posORneg.setVisible(true);
        radioHolder0.setVisible(true);
        refreshButton.setVisible(false);


        int check;
        fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        check = fileChooser.showOpenDialog(mainWindow);

        /* We only allow the Button to do something if we recieve a filepath from j file choser o */
        if(check == JFileChooser.APPROVE_OPTION)
        {
            filepath = fileChooser.getSelectedFile().getPath();
            
        }
    }
    else if(e.getItem().equals("2. Delete movie reviews from database (given its id)"))
    {
            deleteByIdHolder.setVisible(true);
            searchReviewHolder.setVisible(false);
            posORneg.setVisible(false);
            radioHolder0.setVisible(false);
            refreshButton.setVisible(false);

    }
    else if(e.getItem().equals("3. Search movie reviews in database"))
    {
        deleteByIdHolder.setVisible(false);
        searchReviewHolder.setVisible(true);
        posORneg.setVisible(false);
        radioHolder0.setVisible(false);
        refreshButton.setVisible(false);
    }
}

public void actionPerformed(ActionEvent e)
{
    if(e.getSource() == submit)
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
        setTable(reviewHandler, (HashMap<Integer, MovieReview>) reviewHandler.database);
        reviewHandler.saveSerialDB();
    }
    else if(e.getSource() == deleteButton)
    {
        try
        {
            reviewHandler.deleteReview(Integer.parseInt(inputText.getText()));
            inputText.setText("Done!");
            setTable(reviewHandler, (HashMap<Integer, MovieReview>) reviewHandler.database);
            reviewHandler.saveSerialDB();
        }
        catch (NumberFormatException e1)
        {
            inputText.setText("Input was not a number");
        }
    }
    else if (e.getSource() == searchButton)
    {
        /*
        Create Hashtable
        Put the object that meets the criteria into the hashtable

        Call the settable method, which creates a table with the given hashmap
         */

        if (idButton.isSelected())
        {
            int id = Integer.parseInt(searchInput.getText());
            HashMap<Integer, MovieReview> temp = new HashMap<Integer, MovieReview>();

            if (reviewHandler.database.containsKey(id));
            {
                temp.put(id, reviewHandler.database.get(id));
            }
            setTable(reviewHandler, temp);
            System.out.println("test");
        }
        else if (stringButton.isSelected())
        {
            HashMap<Integer, MovieReview> temp = new HashMap<Integer, MovieReview>();
            for (int i : reviewHandler.database.keySet())
            {
                String term = searchInput.getText();
                term.replace("/n", "");
                term.replace("/0", "");

                if (reviewHandler.database.get(i).getText().contains(term))
                {
                    temp.put(i,reviewHandler.database.get(i));
                }
            }
            reviewTable.setVisible(false);

            setTable(reviewHandler, temp);
        }
    }
    else if (e.getSource() == refreshButton)
    {
        setTable(reviewHandler, (HashMap<Integer, MovieReview>) reviewHandler.database);
    }

}
}
          

                
                
            