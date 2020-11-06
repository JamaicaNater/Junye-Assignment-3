package src.movieReviewClassification;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
  Main Application for Assignment 2 
  @author Michael Paulson
  @author Francis Williams
 */
public class MainApp 
{ /**
  * Main controls I/O with user and method calls
  * @param args command line arguments
  */
    public static void main(String[] args) 
    {
      Scanner in = new Scanner(System.in); //to get user input from commandline
      int userIntInput; //to store user input that should only be an int
      String userStringInput; // string input from user

      ReviewHandler reviewHandler = new ReviewHandler(); 

      String dataBasePath = System.getProperty("user.dir") + System.getProperty("file.separator") + reviewHandler.DATA_FILE_NAME;
      File checkFileExist = new File(dataBasePath); //load data path to database to check if is currently exists

      if(checkFileExist.exists())
      {
        reviewHandler.loadSerialDB();
      }
        while(true)
        {

            System.out.println("Please choose one of the following options... (Enter 0, 1, 2, 3, or 4)");
            System.out.println("0. Exit program.");
            System.out.println("1. Load new movie review collection (given a folder or a file path).");
            System.out.println("2. Delete movie reviews from database (given its id).");
            System.out.println("3. Search movie reviews in database by id or by matching a substring.");
            System.out.println("4. Show database ");

            if(in.hasNextInt()) //only accept user input if int
            {
                userIntInput = in.nextInt();
            }
            else
            {
                System.out.print("Please enter a valid int... : "); 
                in.nextLine();
                continue; // trap in loop until valid input
            }

            in.nextLine(); // eats null char

            switch(userIntInput)
            {

                case 0:
                    System.out.println("Exiting program...");
                    reviewHandler.saveSerialDB();
                    return;
        
                case 1:
                    System.out.println("Please input folder or a file path: ");
                    userStringInput = in.nextLine();

                    while(true)
                    {
                        System.out.println("What type of review is this?");
                        System.out.println("0. Negative ");
                        System.out.println("1. Positive ");
                        System.out.println("2. Unknown ");

                        if(in.hasNextInt())
                        {
                            userIntInput = in.nextInt();

                            if(userIntInput < 3 && userIntInput >= 0)
                            {
                                break;
                            }
                            else
                            {
                                System.out.print("Please enter a valid int... : "); 
                                in.nextLine();
                                continue;
                            }
                        }
                        else
                        {
                            System.out.print("Please enter a valid int... : "); 
                            in.nextLine();
                            continue;
                        }
                    }

                    reviewHandler.loadReviews(userStringInput, userIntInput); //accept file/folder path and load reviews
                    reviewHandler.saveSerialDB(); //load and serialize data into database 

                    break;

                case 2:
                    System.out.print("Please enter the id number of the review to delete: ");

                    while(true)
                    {
                        if(in.hasNextInt())
                            {
                                userIntInput = in.nextInt();
                                break;
                            }
                        else
                            {
                                System.out.print("Please enter a valid int... : "); 
                                in.nextLine();
                                continue;
                            }                  
                    }
                        reviewHandler.deleteReview(userIntInput); // delete review based on ID
                        
                        break;

                case 3: 
                    System.out.println("0. Search by id: ");
                    System.out.println("1. Search by substring: ");

                    while(true)
                    {
                        if(in.hasNextInt())
                            {
                                userIntInput = in.nextInt();

                                if(userIntInput < 2 && userIntInput >= 0)
                                {
                                    break;
                                }
                                else
                                {
                                    System.out.print("Please enter a valid int... : "); 
                                    in.nextLine();
                                    continue;
                                }
                            }
                        else
                            {
                                System.out.print("Please enter a valid int... : "); 
                                in.nextLine();
                                continue;
                            } 
                    }

                    if(userIntInput == 0)
                    {

                        System.out.print("Enter ID here: ");
                        MovieReview it;

                        while(true)
                        {
                            if(in.hasNextInt())
                                {
                                    userIntInput = in.nextInt();

                                    if(userIntInput <= reviewHandler.database.size() && userIntInput > 0)
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        System.out.print("Please enter a valid ID number... : ");
                                        in.nextLine();
                                        continue;
                                    }
                                }
                            else
                                {
                                    System.out.print("Please enter a valid ID number... : ");
                                    in.nextLine();
                                    continue;
                                } 
                        }

                        it = reviewHandler.searchById(userIntInput);

                        if(it != null)
                        {
                            System.out.println("|Review ID|Review Preview                                         |Predicted Class|Real Class|"); 
                            System.out.println("==============================================================================================");
                            System.out.format("| %8d", userIntInput);
                            System.out.print( "|" + it.getText50() + "... " );

                            if(it.getPredictedClass() == 0)
                            {
                                System.out.print( " | " + "Negative     " );
                            }
                            else
                            {
                                System.out.print( " | " + "Positive     " );
                            }

                            if(it.getRealClass() == 0)
                            {   
                                System.out.format( " | " + "Negative" + " |%n");
                            }
                            else if (it.getRealClass() == 1)
                            {
                                System.out.format( " | " + "Positive" + " |%n");
                            }
                            else
                            {
                                System.out.format( " | " + "Unknown" + " |%n");
                            }

                           // System.out.print( "|      " + it.getPredictedClass());
                           // System.out.format( "        |    " + it.getRealClass() + "     |%n%n");
                        }
                        else
                        {
                          System.out.format("%nNo matching ID found in database...%n");
                        }
                    }
                    else
                    {
                        System.out.print("Enter substring here: ");

                        List<MovieReview> options; // holds list of matching movie reviews retured from method

                        in.nextLine();
                        userStringInput = in.nextLine();

                        options = reviewHandler.searchBySubstring(userStringInput);

                        if(options != null)
                        {
                            System.out.println("|Review ID|Review Preview                                         |Predicted Class |Real Class|"); 
                            System.out.println("===============================================================================================");

                            for(MovieReview it : options)
                            {
                                System.out.format("| %8d", it.getID());
                                System.out.print( "| " + it.getText50() + "...");

                                if(it.getPredictedClass() == 0)
                                {
                                    System.out.print( " | " + "Negative      " );
                                }
                                else
                                {
                                    System.out.print( " | " + "Positive      " );
                                }

                                if(it.getRealClass() == 0)
                                {   
                                    System.out.format( " | " + "Negative" + " |%n");
                                }
                                else if (it.getRealClass() == 1)
                                {
                                    System.out.format( " | " + "Positive" + " |%n");
                                }
                                else
                                {
                                    System.out.format( " | " + "Unknown" + "  |%n");
                                }
                            }
                        }
                        else
                        {
                          System.out.format("%nNo matching substring found...%n");
                        }
                    }
                    break;

                case 4:
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

                default: 
                    System.out.format("%nInvalid input... Please try again.%n");
                    continue;  
            }
        }
    }
}
