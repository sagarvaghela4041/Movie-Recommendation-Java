import java.util.*;

public class FourthRatings
{
	ArrayList<Rater> myRaters = new ArrayList<Rater>();
	public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }

    public FourthRatings(String ratingsfile)
    {
    	try
    	{
	    	
	    	//myMovies = new ArrayList<Movie>(fr.loadMovies(moviefile));
	    	RaterDatabase.initialize(ratingsfile);
	    	myRaters = RaterDatabase.getRaters();
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
    	
    	//myRaters = RaterDatabase.getRaters();
    	/*Cheking for given id the number of ratings are greter than or equal to minimalRaters or not*/
		int cnt = 0;
		for(Rater r:myRaters)
		{
			for(String s : r.getItemsRated())
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
				for(String s : r.getItemsRated())
				{
					if(s.equals(id) )
					{
						//System.out.println(l.getItem()+"\t"+l.getValue());
						sum+=r.getRating(s);
					

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


 	private double dotProduct(Rater me,Rater r)
 	{
 		/*me = new EfficientRater(me.getID());
 		r = new EfficientRater(r.getID());*/
 		/*ArrayList<Rating> meRating = new ArrayList<Rating>((me.getmyRatings()).values());
 		HashMap<String,Double> me_hm = new HashMap<String,Double>();
 		ArrayList<Rating> rRating = new ArrayList<Rating>((r.getmyRatings()).values());
 		HashMap<String,Double> r_hm = new HashMap<String,Double>();
 		for(Rating d : meRating)
		{
			me_hm.put(d.getItem(),(d.getValue()-5.0));
		}
		for(Rating d : rRating)
		{
			r_hm.put(d.getItem(),(d.getValue()-5.0));
		}
		double sum=0.0;
		for(String m : me_hm.keySet())
		{
			if(r_hm.containsKey(m))
			{
				sum+=(me_hm.get(m)*r_hm.get(m));
			}	
			else
			{
				sum+=0.0;
			}
		}
		return sum;*/
		double similarValue=0;
    	ArrayList<String> itemsRatedByMe = me.getItemsRated();

    	for(String movieID:itemsRatedByMe){
    		if(r.hasRating(movieID)){
    			double rRating = r.getRating(movieID)-5;
    			double myRating = me.getRating(movieID)-5;

    			similarValue = similarValue + (rRating*myRating);
    		}
    	}
    	return similarValue;
 	}

 	private ArrayList<Rating> getSimilarities(String id)
 	{
 		ArrayList<Rating> similarRaters = new ArrayList<Rating>();
 		RaterDatabase.initialize("ratings.csv");
 		Rater me = RaterDatabase.getRater(id);
 		ArrayList<Rater> raters = RaterDatabase.getRaters();
 		System.out.println(RaterDatabase.size());
 		for(Rater r : raters)
 		{
 			if(!(r.equals(me)))
 			{
 				double sum = dotProduct(me,r);
 				if(sum>0)
 					similarRaters.add(new Rating(r.getID(),sum));
 			}
 		}
 		Collections.sort(similarRaters,Collections.reverseOrder());
 		return similarRaters;
 	}

 	//id = rater_id
 	public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters)
 	{
 		ArrayList<Rating> mySimilarRaters = new ArrayList<Rating>();
 		ArrayList<Rating> similarRaters = getSimilarities(id);
 		similarRaters = new ArrayList<Rating>(similarRaters.subList(0,numSimilarRaters));

 		ArrayList<Rater> raters = new ArrayList<Rater>();
 		HashSet<String> movies = new HashSet<String>();
 		for(Rating rating : similarRaters)
 		{
 			Rater r = RaterDatabase.getRater(rating.getItem());

 			ArrayList<String> myrating = r.getItemsRated();
 			for(String s : myrating)
 			{
 				r.addRating(s,r.getRating(s)*rating.getValue());
 				movies.add(s);
 			}
 			raters.add(r);
 		}
 		
 		for(String m : movies)
 		{
 			int cnt = 0;
			for(Rater r:raters)
			{
				for(String s : r.getItemsRated())
				{
					if(s.equals(m) )
					{
						cnt++;
					}
				}
			}  
			double sum = 0.0;
			if(cnt>=minimalRaters)
			{
				/*Movie has ratings greater than minimalRaters*/
				for(Rater r:raters)
				{
					for(String s : r.getItemsRated())
					{
						if(s.equals(m) )
						{
							//System.out.println(l.getItem()+"\t"+l.getValue());
							sum+=r.getRating(s);

						}
					}
				}
				sum = (sum/cnt); 
			}
			
			if(sum>0.0)
			{
				mySimilarRaters.add(new Rating(m,sum));
				//System.out.println(m + "\t"+sum);
			}
 		}
 		Collections.sort(mySimilarRaters,Collections.reverseOrder());
 		return mySimilarRaters;

 	}
 	

 	public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria)
 	{
 		ArrayList<Rating> mySimilarRaters = new ArrayList<Rating>();
 		ArrayList<Rating> similarRaters = getSimilarities(id);

 		similarRaters = new ArrayList<Rating>(similarRaters.subList(0,numSimilarRaters));
 		ArrayList<Rater> raters = new ArrayList<Rater>();
 		HashSet<String> movies = new HashSet<String>();
 		for(Rating rating : similarRaters)
 		{
 			Rater r = RaterDatabase.getRater(rating.getItem());
 			ArrayList<String> myrating = r.getItemsRated();
 			for(String s : myrating)
 			{
 				r.addRating(s,r.getRating(s)*rating.getValue());
 				movies.add(s);
 			}
 			raters.add(r);
 		}
 		for(String m : movies)
 		{
 			int cnt = 0;
			for(Rater r:raters)
			{
				for(String s : r.getItemsRated())
				{
					if(s.equals(m) )
					{
						cnt++;
					}
				}
			}  
			double sum = 0.0;
			if(cnt>=minimalRaters)
			{
				/*Movie has ratings greater than minimalRaters*/
				for(Rater r:raters)
				{
					for(String s : r.getItemsRated())
					{
						if(s.equals(m) )
						{
							//System.out.println(l.getItem()+"\t"+l.getValue());
							sum+=r.getRating(s);

						}
					}
				}
				sum = (sum/cnt); 
			}
			if(filterCriteria.satisfies(m))
			{
				mySimilarRaters.add(new Rating(m,sum));
			}
 		}
 		Collections.sort(mySimilarRaters,Collections.reverseOrder());
 		return mySimilarRaters;
 	}

}