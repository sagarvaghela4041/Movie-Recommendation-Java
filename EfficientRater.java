
/**
 * Write a description of class EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        this.myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) 
    {
        if(myRatings.containsKey(item))
        {
            return (myRatings.get(item).getValue());
        }
        else
        {
            return -1;
        }
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        Set s = myRatings.keySet();
        ArrayList<String> list = new ArrayList<String>(s);

        
        return list;
    }

    /*public HashMap<String,Rating> getmyRatings()
    {
        return myRatings;
    }*/
}
