import java.util.*;

public class MovieRunnerWithFilters
{
	public void printAverageRatings() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		
		System.out.println("Read data for "+tr.getRaterSize()+" raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		ArrayList<Rating> averageRatings = tr.getAverageRatings(35);
		System.out.println("Found "+averageRatings.size()+" movies");
		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getTitle(r.getItem()));
		}
	}

	public void printAverageRatingsByYear() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");

		Filter filterCriteria =  new YearAfterFilter(2000);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(20,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getTitle(r.getItem()));
		}	
	}

	public void printAverageRatingsByGenre() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");

		Filter filterCriteria =  new GenreFilter("Comedy");
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(20,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getTitle(r.getItem()) + "\n\t"+MovieDatabase.getGenres(r.getItem()));
		}
	}

	public void printAverageRatingsByMinutes() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");

		Filter filterCriteria =  new MinutesFilter(105,135);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(5,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t Time: "+MovieDatabase.getMinutes(r.getItem())+"\t" +MovieDatabase.getTitle(r.getItem()));
		}
	}	

	public void printAverageRatingsByDirectors() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");

		Filter filterCriteria =  new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(4,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getTitle(r.getItem()) + "\n\t"+MovieDatabase.getDirector(r.getItem()));
		}
	}

	public void printAverageRatingsByYearAfterAndGenre() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");
		AllFilters filterCriteria = new AllFilters();
		YearAfterFilter filterCriteriaYear =  new YearAfterFilter(1990);
		GenreFilter filterCriteriaGenre =  new GenreFilter("Drama");
		filterCriteria.addFilter(filterCriteriaYear);
		filterCriteria.addFilter(filterCriteriaGenre);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(8,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t" +MovieDatabase.getYear(r.getItem())+ "\t" +MovieDatabase.getTitle(r.getItem()) + "\n\t"+MovieDatabase.getGenres(r.getItem()));
		}		
	}

	public void printAverageRatingsByDirectorsAndMinutes() throws Exception
	{
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		System.out.println("Read data for "+tr.getRaterSize()+" raters");

		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for "+MovieDatabase.size()+" movies");

		AllFilters filterCriteria = new AllFilters();
		Filter filterCriteriaMinutes =  new MinutesFilter(90,180);
		Filter filterCriteriaDirector =  new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
		filterCriteria.addFilter(filterCriteriaMinutes);
		filterCriteria.addFilter(filterCriteriaDirector);
		ArrayList<Rating> averageRatings = tr.getAverageRatingsByFilter(3,filterCriteria);
		System.out.println("Found "+averageRatings.size()+" movies");

		Collections.sort(averageRatings);
		for(Rating r : averageRatings)
		{
			System.out.println(r.getValue()+ "\t Time: "+MovieDatabase.getMinutes(r.getItem())+"\t" +MovieDatabase.getTitle(r.getItem()) +"\n\t"+MovieDatabase.getDirector(r.getItem()));
		}
	}
	public static void main(String argS[])
	{
		try
		{
			new MovieRunnerWithFilters().printAverageRatingsByDirectorsAndMinutes();
		}
		catch(Exception e)
		{}
	}
}