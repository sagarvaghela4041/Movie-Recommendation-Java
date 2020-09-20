
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Sagar Vaghela 
 * @version 15-09-2020
 */

import java.util.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile)
    {
    	try
    	{
	    	FirstRatings fr = new FirstRatings();
	    	//myMovies = new ArrayList<Movie>(fr.loadMovies(moviefile));
	    	myRaters = new ArrayList<Rater>(fr.loadRaters(ratingsfile));
    	}
    	catch(Exception e)
    	{}
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
 		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
 		for(String m : movies)
 		{
 			if(getAverageById(m,minimalRaters)!=0.0)
 			averageRatings.add( new Rating(m,getAverageById(m,minimalRaters))); 			
 		}
 		return averageRatings;
 	}   

 	
 	public void count() throws Exception
 	{
 		FirstRatings fr = new FirstRatings("ratedmoviesfull.csv","ratings.csv");
 		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
 		int cnt = 0;
 		for(String m : movies)
 		{
 			if(fr.numberOfRatingMovies(m)>=12)
 			{
 				cnt++;
 				System.out.println(MovieDatabase.getTitle(m)+"\t"+fr.numberOfRatingMovies(m));
 			}
 			//break;

 		}
 		System.out.println(cnt);
 	}

 	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
 	{
 		ArrayList<Rating> averageRatingsByFilter = new ArrayList<Rating>();
 		/*ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());*/
 		ArrayList<Rating> averageRatings = getAverageRatings(minimalRaters);
 		for(Rating r : averageRatings)
 		{
 			if(filterCriteria.satisfies(r.getItem()))
 				averageRatingsByFilter.add(r);
 		}
 		return averageRatingsByFilter;
 	}
}