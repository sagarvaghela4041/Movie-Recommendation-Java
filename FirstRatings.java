import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirstRatings
{
	public FirstRatings()
	{
		
	}
	public FirstRatings(String moviefile, String ratingsfile)
	{
		try
    	{
	    	movie = new ArrayList<Movie>(loadMovies(moviefile));
	    	rater = new ArrayList<Rater>(loadRaters(ratingsfile));
    	}
    	catch(Exception e)
    	{}
	}

	List<Movie> movie = new ArrayList<Movie>();
	public List loadMovies(String filename) throws Exception
	{
		Reader reader = Files.newBufferedReader(Paths.get("data/"+filename));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
        	.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
        for (CSVRecord csvRecord : csvParser) 
        {
        	
			movie.add(new Movie(csvRecord.get("id"),csvRecord.get("title"),
				csvRecord.get("year"),csvRecord.get("country"),
				csvRecord.get("genre"),csvRecord.get("director"),
				Integer.parseInt(csvRecord.get("minutes")),csvRecord.get("poster")
				));
		}
		
		return movie;
	 
	}

	public void testLoadMovies() throws Exception
	{
		List<Movie> movie = new ArrayList<Movie>();
		movie = loadMovies("ratedmoviesfull.csv");
		System.out.println(movie.size());
		for(Movie m:movie)
		{
			System.out.println(m.getTitle());
		}
	}

	public void comedyGenre() throws Exception
	{
		List<Movie> movie = new ArrayList<Movie>();
		movie = loadMovies("ratedmoviesfull.csv");
		int cnt = 0;
		for(Movie m:movie)
		{
			if(m.getGenres().contains("Comedy"))
			{
				//System.out.println(m.getGenres());
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	public void checkMinutes() throws Exception
	{
		List<Movie> movie = new ArrayList<Movie>();
		movie = loadMovies("ratedmoviesfull.csv");
		int cnt = 0;
		for(Movie m:movie)
		{
			if(m.getMinutes()>150)
			{
				//System.out.println(m.getMinutes());
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	public void directorNumbers() throws Exception
	{
		List<Movie> movie = new ArrayList<Movie>();
		movie = loadMovies("ratedmovies_short.csv");
		for(Movie m:movie)
		{
			
		}
	}

	List<Rater> rater = new ArrayList<Rater>();
	public List loadRaters(String filename) throws Exception
	{
		Reader reader = Files.newBufferedReader(Paths.get("data/"+filename));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
        	.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
        List<Rating> rating = new ArrayList<Rating>();
        for (CSVRecord csvRecord : csvParser) 
        {
        	/*rating.add(csvRecord.get("movie_id"));
        	rating.add(csvRecord.get("rating"));*/
			Rater r = new EfficientRater(csvRecord.get("rater_id"));
			r.addRating(csvRecord.get("movie_id"),Double.parseDouble(csvRecord.get("rating")));
			rater.add(r);
			r = null;
        	rating.clear();
		}
		
		return rater;
	 
	}


	public void testLoadRaters() throws Exception
	{
		List<Rater> rater = new ArrayList<Rater>();
		rater = loadRaters("ratings.csv");
		Set<String> set = new HashSet<String>();
		for(Rater r:rater)
		{
			set.add(r.getID());
		}
		System.out.println(set.size());
		for(String s:set)
		{
			int cnt = 0;
			for(Rater r:rater)
			{
				if(r.getID().equals(s))
				{
					cnt++;
				}
			}
			System.out.println(s +"\t"+ cnt);
			for(Rater r:rater)
			{
				if(r.getID().equals(s))
				{
					//System.out.println(r.getmyRatings());
				}
			}
			
		}
	}

	public int numberOfRatings(String id) throws Exception
	{
		List<Rater> rater = new ArrayList<Rater>();
		rater = loadRaters("ratings.csv");
		int cnt = 0;
		for(Rater r:rater)
		{
			if(r.getID().equals(id))
			{
				cnt++;
			}
		}
		//System.out.println(cnt);
		return cnt;
	}


	public void maxRating() throws Exception
	{
		List<Rater> rater = new ArrayList<Rater>();
		rater = loadRaters("ratings.csv");
		Set<String> set = new HashSet<String>();
		Map<String,String> map = new HashMap<String,String>();
		for(Rater r:rater)
		{
			set.add(r.getID());
		}
		int max = 0;
		for(String s:set)
		{
			int cnt = 0;
			for(Rater r:rater)
			{
				if(r.getID().equals(s))
				{
					cnt++;
				}
			}
			if(max>=cnt)
				max = max;
			else
				max = cnt;
			//max = max>cnt?max:cnt;
			map.put(s,cnt+"");
		}
		for(String s:map.keySet())
		{
			if(map.get(s).equals(max+""))
			System.out.println(s + "\t" + map.get(s));
		}

	}

	public int numberOfRatingMovies(String movie_id) throws Exception
	{
		int cnt = 0;
		//Set<String> set = new HashSet<String>();
		for(Rater r:rater)
		{
			for(String s : r.getItemsRated())
			{
				if(r.getmyRatings().get(s).getItem().equals(movie_id) )
				{
					cnt++;
				}
			}
		}
		//System.out.println(cnt);
		return cnt;
	}

	public int numberOfMovies() throws Exception
	{
		List<Rater> rater = new ArrayList<Rater>();
		rater = loadRaters("ratings_short.csv");
		Set<String> set = new HashSet<String>();
		for(Rater r:rater)
		{
			for(String s : r.getmyRatings().keySet())
			{
				set.add(s);
			}
		}
		return (set.size());
	}



	public static void main(String argS[]) throws Exception
	{
		new FirstRatings().numberOfMovies();
	}
}