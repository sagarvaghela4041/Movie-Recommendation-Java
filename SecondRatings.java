
/**
 * Write a description of SecondRatings here.
 * 
 * @author Sagar Vaghela 
 * @version 15-09-2020
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile)
    {
    	try
    	{
	    	FirstRatings fr = new FirstRatings();
	    	myMovies = new ArrayList<Movie>(fr.loadMovies(moviefile));
	    	myRaters = new ArrayList<Rater>(fr.loadRaters(ratingsfile));
    	}
    	catch(Exception e)
    	{}
    }

    public int getMovieSize() throws Exception
    {
    	Set<String> set = new HashSet<String>();
		for(Movie m:myMovies)
		{
			set.add(m.getID());
		}
    	return (set.size());
    }

    public int getRaterSize()
    {
		Set<String> set = new HashSet<String>();
		for(Rater r:myRaters)
		{
			set.add(r.getID());
		}
    	return (set.size());
    }

    public double getAverageById(String id, int minimalRaters)
    {
    	/*Cheking for given id the number of ratings are greter than or equal to minimalRaters or not*/
		int cnt = 0;
		for(Rater r:myRaters)
		{
			for(String s : r.getmyRatings().keySet())
			{
				if(s.equals(id) )
				{
					cnt++;
				}
			}
		}  
		double sum = 0.0;
		if(cnt>=minimalRaters)
		{
			/*Movie has ratings greater than minimalRaters*/
			for(Rater r:myRaters)
			{
				for(String s : r.getmyRatings().keySet())
				{
					if(s.equals(id) )
					{
						//System.out.println(l.getItem()+"\t"+l.getValue());
						sum+=r.getmyRatings().get(s).getValue();

					}
				}
			}
			return (sum/cnt); 
		}	
		else
		{
			/*Movie has not ratings greater than minimalRaters*/
			return 0.0;
		}
    }
 	

 	public ArrayList<Rating> getAverageRatings(int minimalRaters)
 	{
 		ArrayList<Rating> averageRatings = new ArrayList<Rating>();
 		for(Movie m : myMovies)
 		{
 			if(getAverageById(m.getID(),minimalRaters)!=0.0)
 			averageRatings.add( new Rating(m.getID(),getAverageById(m.getID(),minimalRaters))); 			
 		}
 		return averageRatings;
 	}   

 	public String getTitle(String movie_id)
 	{
 		String output = "";
 		for(Movie m : myMovies)
 		{
 			if(m.getID().equals(movie_id))
 			{
 				output = m.getTitle();
 				break;
 			}
 			else
 			{
 				output = "The ID was not found";
 			}
 		}
 		return output;
 	}

 	public String getID(String title)
 	{
 		String output = "";
 		for(Movie m : myMovies)
 		{
 			if(m.getTitle().equals(title))
 			{
 				output = m.getID();
 				break;
 			}
 			else
 			{
 				output = "NO SUCH TITLE";
 			}
 		}
 		return output;
 	}


 	public void count() throws Exception
 	{
 		FirstRatings fr = new FirstRatings("ratedmoviesfull.csv","ratings.csv");
 		int cnt = 0;
 		for(Movie m : myMovies)
 		{
 			if(fr.numberOfRatingMovies(m.getID())>=12)
 			{
 				cnt++;
 				System.out.println(getTitle(m.getID())+"\t"+fr.numberOfRatingMovies(m.getID()));
 			}
 			//break;

 		}
 		System.out.println(cnt);
 	}
}