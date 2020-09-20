import java.util.*;

public class MovieRunnerAverage
{
	public void printAverageRatings() throws Exception
	{
		SecondRatings sr = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
		/*System.out.println(sr.getMovieSize());
		System.out.println(sr.getRaterSize());
		*/
		ArrayList<Rating> averageRatings = sr.getAverageRatings(3);
		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +sr.getTitle(r.getItem()));
		}
	}


	public void getAverageRatingOneMovie() throws Exception
	{
		SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
		//System.out.println(sr.getID("The Godfather"));
		System.out.println(sr.getAverageById(sr.getID("Vacation"),0));
	}


	public static void main(String argS[]) throws Exception
	{
		//new MovieRunnerAverage().getAverageRatingOneMovie();
		//System.out.println(new SecondRatings("ratedmovies_short.csv","ratings_short.csv").getAverageById("0068646",1));
		SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
		try
		{
			sr.count();
		}
		catch(Exception e)
		{}
	}
}