package movieReviewClassification;

import sentiment.Sentiment;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CS3354 Spring 2019 Review Handler Abstract Class specification
    @author metsis
    @author tesic
    @author wen
 */
public abstract class AbstractReviewHandler {

    public AbstractReviewHandler() {
        database = new HashMap<>();
        sentimentModel = new Sentiment(new File(TRAINED_MODEL_NAME));
    }
    

    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded.
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     * @return A list of reviews as objects.
     */
    public abstract void loadReviews(String filePath, int realClass);
    
    /**
     * Reads a single review file and returns it as a MovieReview object. 
     * This method also calls the method classifyReview to predict the polarity
     * of the review.
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be openned.
     */
    public abstract MovieReview readReview(String reviewFilePath, int realClass)
            throws IOException;


    /**
     * Classifies a review as negative, or positive by using the text of the review.
     * It updates the predictedPolarity value of the review object and it also
     * returns the predicted polarity.
     * Note: to achieve the classification, this method depends on the external
     * library sentiment.jar. 
     * @param review A review object.
     * @return 0 = negative, 1 = positive.
     */
    public int classifyReview(MovieReview review) {
        int polarity = sentimentModel.classifyText(review.getText());
        review.setPredictedPolarity(polarity);
        
        return polarity;
    }
    

    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
    public abstract void deleteReview(int id);
    
    
    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     *
     * @param c The file to be closed
     */
    public void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        }
    }
    

    /**
     * Saves the database in the working directory as a serialized object.
     */
    public void saveSerialDB() {
        System.out.print("Saving database...");
        //serialize the database
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = new FileOutputStream(DATA_FILE_NAME);
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);

            output.writeObject(database);

            output.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        } finally {
            close(file);
        }
        System.out.println("Done.");
    }
    

    /**
     * Loads review database.
     */
    @SuppressWarnings("unchecked")
    public abstract void loadSerialDB();
    

    /**
     * Searches the review database by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not 
     * exist in the database.
     */
    public abstract MovieReview searchById(int id);
    

    /**
     * Searches the review database for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    public abstract List<MovieReview> searchBySubstring(String substring);


    
    /**
     * A map of <id, review> pairs.
     */
    protected Map<Integer, MovieReview> database;
    
    /**
     * The file name of where the database is going to be saved.
     */
    protected static final String DATA_FILE_NAME = "database.ser";
    
    private static final String TRAINED_MODEL_NAME = "model.ser";
    
    private Sentiment sentimentModel;
}
