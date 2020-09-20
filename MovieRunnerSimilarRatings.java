
import java.util.*;
public class MovieRunnerSimilarRatings
{
	public void printAverageRatings() throws Exception
	{
		FourthRatings fr = new FourthRatings("ratings_short.csv");
		RaterDatabase.initialize("ratings_short.csv");
		System.out.println("Read data for "+RaterDatabase.size()+" raters");
		MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		ArrayList<Rating> averageRatings = fr.getAverageRatings(1);
		System.out.println("Found "+averageRatings.size()+" movies");
		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByYearAfterAndGenre() throws Exception
	{
		FourthRatings fr = new FourthRatings("ratings_short.csv");
		System.out.println("Read data for "+fr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmovies_short.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		AllFilters filterCriteria = new AllFilters();
		YearAfterFilter filterCriteriaYear =  new YearAfterFilter(1980);
		GenreFilter filterCriteriaGenre =  new GenreFilter("Romance");
		filterCriteria.addFilter(filterCriteriaYear);
		filterCriteria.addFilter(filterCriteriaGenre);
		ArrayList<Rating> averageRatings = fr.getAverageRatingsByFilter(1,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getYear(r.getItem())+ "\t" +MovieDatabase.getTitle(r.getItem()) + "\n\t"+MovieDatabase.getGenres(r.getItem()));
		}		
	}


	public void printSimilarRatings()
	{
		MovieDatabase.initialize("ratedmoviesfull.csv");
		FourthRatings fr = new FourthRatings("ratings.csv");
		ArrayList<Rating> similarMovie = fr.getSimilarRatings("71",20,5);
		for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem())+"\t" +r.getValue());
		}
	}

	public void printSimilarRatingsByGenre()
	{
		MovieDatabase.initialize("ratedmoviesfull.csv");
		FourthRatings fr = new FourthRatings("ratings.csv");
		GenreFilter filterCriteriaGenre =  new GenreFilter("Mystery");
		ArrayList<Rating> similarMovie = fr.getSimilarRatingsByFilter("964",20,5,filterCriteriaGenre);
		for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem())+"\t" +r.getValue()+"\n\t"+MovieDatabase.getGenres(r.getItem()));
		}
	}
	public void printSimilarRatingsByDirector()
	{
		MovieDatabase.initialize("ratedmoviesfull.csv");
		FourthRatings fr = new FourthRatings("ratings.csv");
		DirectorsFilter filterCriteriaDirector =  new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		ArrayList<Rating> similarMovie = fr.getSimilarRatingsByFilter("120",10,2,filterCriteriaDirector);
		for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem())+"\t" +r.getValue()+"\n\t"+MovieDatabase.getDirector(r.getItem()));
		}
	}

	public void printSimilarRatingsByGenreAndMinutes()
	{
		MovieDatabase.initialize("ratedmoviesfull.csv");
		FourthRatings fr = new FourthRatings("ratings.csv");
		AllFilters filterCriteria = new AllFilters();
		GenreFilter filterCriteriaGenre =  new GenreFilter("Drama");
		MinutesFilter filterCriteriaMinutes = new MinutesFilter(80,160);
		filterCriteria.addFilter(filterCriteriaGenre);
		filterCriteria.addFilter(filterCriteriaMinutes);
		ArrayList<Rating> similarMovie = fr.getSimilarRatingsByFilter("168",10,3,filterCriteria);
		for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem())+"\t" +MovieDatabase.getMinutes(r.getItem())+"\t" +r.getValue()+"\n\t"+MovieDatabase.getGenres(r.getItem()));
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes()
	{
		MovieDatabase.initialize("ratedmoviesfull.csv");
		FourthRatings fr = new FourthRatings("ratings.csv");
		AllFilters filterCriteria = new AllFilters();
		YearAfterFilter filterCriteriaYear =  new YearAfterFilter(1975);
		MinutesFilter filterCriteriaMinutes = new MinutesFilter(70,200);
		filterCriteria.addFilter(filterCriteriaYear);
		filterCriteria.addFilter(filterCriteriaMinutes);
		ArrayList<Rating> similarMovie = fr.getSimilarRatingsByFilter("314",10,5,filterCriteria);
		for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem())+"\t" +MovieDatabase.getYear(r.getItem())+"\t" +MovieDatabase.getMinutes(r.getItem())+"\t" +r.getValue());
		}
	}

	public static void main(String argS[])
	{
		try
		{
			new MovieRunnerSimilarRatings().printSimilarRatings();
		}
		catch(Exception e)
		{}
		/*Rater me = new EfficientRater("1");
		me.addRating("10",9.8);
		me.addRating("20",7.8);
		ArrayList<Rating> rating = new ArrayList<Rating>((me.getmyRatings()).values());
		for(Rating d : rating)
		{
			System.out.println(d.getValue());
		}*/
	}
}