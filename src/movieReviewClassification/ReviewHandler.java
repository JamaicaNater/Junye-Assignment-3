package src.movieReviewClassification;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;

/**
  @author Michael Paulson
  @author Francis Williams
 */

//a class for processing movie review files and maintaining a database of those files
public class ReviewHandler extends AbstractReviewHandler
{
    public ReviewHandler() 
    {
        super();
    }

    /**
     * Allows user to get a key that does not clash with any preexisting key that we have
     * @return key
     */
    public int keyNext()
    {
        int key = database.size();

        while (database.containsKey(key))
        {
            key = key + 1;
        }

        return key;
    }


   /**
   *@param filepath input by the user on cmd line
   *@param realClass input by user on cmd line
   */
    public void loadReviews(String filePath, int realClass) 
    {
        // Setup creates the object ad sets our file up for use
        File reviewFile = new File(filePath);
        MovieReview object;
        int predicted;
        int count = 0; //using this to keep track of total correct classifications
        int totalCorrectClassified = 0; //using this to keep track of total correct classifications

        int key = database.size();

        System.out.println("Loading reviews...");

        if (reviewFile.isDirectory())
        {
            /*
            For every string in the list we initialize it to the value returned by readReview
            Then we add it to our data base
             */
            for (String s : reviewFile.list())
            {
            	count++;

                try 
                {
                    object = readReview(filePath + System.getProperty("file.separator") + s, realClass);
                } catch (IOException e) {
                    System.out.println("Failed to load Reviews (plural)");
                    e.printStackTrace();
                    return;
                }

                predicted = classifyReview(object); // added this to assign predicted class to each review before pushing in DB

       			if (predicted == object.getRealClass() && object.getRealClass() != 2)
       			{
       				totalCorrectClassified++;
       			}

                database.put( object.setID(keyNext()) , object );
            }

        }
        else
        {
        	count++;

            try 
            {
                object = readReview(filePath, realClass); //chaned reviewFile.getPath() to filePath // I think it was just converting it back
            } catch (IOException e) 
            {
                System.out.println("Failed to load Review (singular)");
                e.printStackTrace();
                return;
            }

            predicted = classifyReview(object); // added this to assign predicted class to each review before pushing in DB

            if (predicted == object.getRealClass() && object.getRealClass() != 2)
       			{
       				totalCorrectClassified++;
       			}

            database.put( object.setID(keyNext()) , object );
        }

        System.out.println(totalCorrectClassified + " out of " + count + " correctly classified.");


    }

    /**
   *@param reviewFilePath a file path to the review(s) to be read into the database
   *@param realClass the true classification of the items input by the user
   *@throws IOException
   */
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException // do we need to put a finally block with a .close()?
    {
        File file = new File(reviewFilePath);
        Scanner fin = new Scanner(file);
        String reviewContents = fin.useDelimiter("\\A").next(); //Attempting to get all the text in the review into this string

        MovieReview toReturn = new MovieReview( reviewContents, realClass ); //// ***** Only gets one line //Changed

        return toReturn;
    }

    /**
   *@param review takes a in a MovieReview object and classifies it 
   *
   */
    public int classifyReview(MovieReview review) 
    {
        return super.classifyReview(review);
    }

   /**
   *@param id takes a in a movie review id (key) and deletes it from the database
   *
   */
    public void deleteReview(int id) 
    {

        if (database.containsKey(id))
        {
            database.remove(id);
        }
    }

    public void close(Closeable c) 
    {
        super.close(c);
    }

    public void saveSerialDB() 
    {
        super.saveSerialDB();
    }

    /**
     * Creates a ObjectInputStream to read in information to our database then closes the database
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public void loadSerialDB() 
    {
        System.out.println("Loading Database");
        try{
            FileInputStream fis = new FileInputStream(DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            database = (HashMap<Integer,MovieReview>)ois.readObject();

            fis.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Failed to Load Database");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Failed to Load Database");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Failed to Load Database");
            e.printStackTrace();
        }
    }

    /**
     * First we use the contains key method of the database to check if the database even has what we are looking for
     * then we simply return the movie review whose key corresponds to what is asked
     *
     * Creates a ObjectInputStream to read in information to our database then closes the database
     * @returns MovieReview object
     */
    public MovieReview searchById(int id) 
    {
        if (database.containsKey(id))
        {
            return database.get(id);
        }
        else
        {
            return null;
        }
    }

    /**
     * The idea here is that we turn use the keySet method of the hashmap to give us a list of all the keys that we use
     * in the database. We can then iterate through all the keys and discover which ones contains the substring that
     * we want.
     *
     * @param substring
     * @returns List of Movie reviews that meets the criteria
     */
    public List<MovieReview> searchBySubstring(String substring)
    {
        List<MovieReview> toReturn = new ArrayList<>();

        for (int key : database.keySet())
        {
            if (database.get(key).getText().contains(substring))
            {
                toReturn.add(database.get(key));
            }
        }

        // Return null if empty, return the list otherwise
        if (toReturn.isEmpty())
        {
            return null;
        }
        else
        {
            return toReturn;
        }

    }
}