public class DirectorsFilter implements Filter
{
	private String myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
		String directors[] = myDirectors.split(",");
		for(String d : directors)
		{
			if(MovieDatabase.getDirector(id).contains(d))
				return true;
		}
		return false;
	}
}