package movieReviewClassification;

import java.io.Serializable;

/**
 * @author Michael Paulson
   @author Francis Williams
 */

public class MovieReview implements Serializable {
    private String review;
    private int predictedClass;
    private int realClass;
    private int id;

    /**
     * Constructs the MovieReview object.
     * The review must be initialized to a string
     * The reviews identification is closely related to the number or objects
     * @param rev A path to a .txt file containing a review.
     * @param rev A data member denoting the movies tru classification.
     * @return none
     */
    public MovieReview(String rev, int rClass)
    {
        review = rev;
        realClass = rClass;
    }

    /**
     * Allows the user to set a MovieReviews predicted polarity
     * @param pol polarity
     * @return none
     */
    public void setPredictedPolarity(int pol)
    {
        predictedClass = pol;
    }

    /**
     * Allows user to get the ID of the MovieReview
     * @return id
     */
    public int getID()
    {
    	return id;
    }

    public int setID(int i)
    {
        id = i;
        
        return id;
    }

    /**
     * Allows user to get the Predicted class of the MovieReview
     * @return predictedClass
     */
    public int getPredictedClass()
    {
    	return predictedClass;
    }

    /**
     * Allows user to get the Predicted class of the MovieReview
     * @return predictedClass
     */
    public int getRealClass()
    {
    	return realClass;
    }

    /**
     * Gets the review of a MovieReview
     * @return review content
     */
    public String getText()
    {
        return review;
    }

    /**
     * Gets the first 50 chars of a review of a MovieReview
     * @return review content
     */
    public String getText50()
    {
        return review.substring(0,50);
    }
}