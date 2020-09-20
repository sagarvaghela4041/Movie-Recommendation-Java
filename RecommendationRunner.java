
import java.util.*;
public class RecommendationRunner implements Recommender
{
	public ArrayList<String> getItemsToRate ()
	{
		ArrayList<String> movies = new ArrayList<String>();
		movies.add("1300845");
		movies.add("0371746");
		movies.add("1228705");
		movies.add("1375666");
		movies.add("1454468");
		movies.add("0816692");
		movies.add("3659388");
		movies.add("1872181");
		movies.add("0948470");
		movies.add("0458339");
		movies.add("1843866");
		movies.add("0800080");
		movies.add("0800369");
		movies.add("1981115");
		movies.add("2395427");
		movies.add("0848228");
		movies.add("1798709");
		movies.add("2556308");
		movies.add("2461132");
		movies.add("1230168");
        Collections.shuffle(movies); 
		return movies;
	}

	public void printRecommendationsFor(String webRaterID)
	{
		try{
		//MovieDatabase.initialize("ratedmoviesfull.csv");
		//RaterDatabase.initialize("ratings.csv");
		FourthRatings fr = new FourthRatings("");
		AllFilters filterCriteria = new AllFilters();
		GenreFilter filterCriteriaGenre =  new GenreFilter("Sci-Fi");
		YearAfterFilter filterCriteriaYear = new YearAfterFilter(2008);
		filterCriteria.addFilter(filterCriteriaGenre);
		filterCriteria.addFilter(filterCriteriaYear);
		ArrayList<Rating> similarMovie = fr.getSimilarRatingsByFilter(webRaterID,10,5,filterCriteria);
		ArrayList<String> list = RaterDatabase.getRater(webRaterID).getItemsRated();
		for(String s : list)
		{
			for(Rating r : similarMovie)
			{
				if(r.getItem().equals(s))
				{
					similarMovie.remove(r);
					break;
				}
			}
		}
		if(similarMovie.size()==0)
		{
			System.out.println("Please select some of Movies from previous page !");
		}
		else
		{
			System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Recommended Movies:</h2></div><table class=\"outside_table\"><tr  class=\"table-header\"><th>&nbsp</th><th class=\"movie_title\">Title</th></tr>");
			int i=0;
             for(Rating r: similarMovie){
                 i++;
                 if((i+1)%2 == 0){
                 	System.out.println("<tr class=\"even_rows\"><td>" + i + "</td>");
                 }
                 else{
                 	System.out.println("<tr class=\"odd_rows\"><td>" + i + "</td>");
                 }

                 String URL = MovieDatabase.getPoster(r.getItem());
                 https://www.dukelearntoprogram.com//capstone/data/
                 URL = URL.substring(7,URL.length());
                 URL = "https://www.dukelearntoprogram.com//capstone/data/"+URL;
                 String title = MovieDatabase.getTitle(r.getItem());
                 String director = MovieDatabase.getDirector(r.getItem());
                 String country = MovieDatabase.getCountry(r.getItem());
                 int year = MovieDatabase.getYear(r.getItem());
                 String genre = MovieDatabase.getGenres(r.getItem());
                 int minutes = MovieDatabase.getMinutes(r.getItem());

                 System.out.println("<td><table><tr><td class = \"pic\">");

                 if(URL.length()>3){
                 	System.out.println("<img src = \""+URL+"\" target=_blank></td>");
                 }
                  
                 System.out.println("<td><h3>"+ title+"</h3>");
                 System.out.println("<b>by "+ genre+"</b><br>");
                 System.out.println(year+"<br>");
                 System.out.println(country+"<br>");
                 System.out.println(minutes+" minutes</td></tr></table></td></tr>");
                 if(i>12) break;
             }
              System.out.println("</table>");
              System.out.println("<h3>Developed By Sagar Vaghela.</h3>");

		}

		/*for(Rating r : similarMovie)
		{
			System.out.println(MovieDatabase.getTitle(r.getItem()));
		}*/
	}
	catch(Exception e)
	{
		System.out.println("Not Enough Data,\n Please provide some details about the movies you have watched in previous page !");
	}
		
	}

	

	/*public static void main(String argS[])
	{
		new RecommendationRunner().printRecommendationsFor("1034");



		
	}*/
}