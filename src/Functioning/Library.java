package Functioning;

import java.util.*;


/**
 * Represents a movie library, with individual movie titles and watch lists.
 * Singleton design pattern.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	private String name;
	private String EmailID;
	private static Library library = new Library();
	/**
	 * Constructor of Functioning.Library.
	 * Generate the only Functioning.Library object(singleton design pattern).
	 */
	private Library(){
		this.name = "unnamed";
	}

	/**
	 * Singleton.
	 *
	 * @return the only Functioning.Library instance.
	 */
	public static Library getInstance(){
		if(library==null){
			library = new Library();
		}
		return library;
	}

	/**
	 * Set the name of the Functioning.Library instance.
	 *
	 * @param nName the (new) name of the instance
	 */
	public void setName(String nName){
		assert nName != null;
		library.name = nName;
	}

	/**
	 * Get the name of the Functioning.Library instance.
	 *
	 * @return the current name of the Functioning.Library instance
	 */
	public String getName(){
		return library.name;
	}

	/**
	 * Set the email ID of the Functioning.Library instance.
	 *
	 * @param nEmailID the (new) email ID
	 * @pre nEmailID != null
	 */
	public void setEmailID(String nEmailID){
		assert nEmailID != null;
		library.EmailID = nEmailID;
	}

	/**
	 * Get the current email ID of the Functioning.Library instance.
	 *
	 * @return the current email ID
	 */
	public String getEmailID(){
		return library.EmailID;
	}

	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a Functioning.TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the Functioning.TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListFilter pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		WatchList watchlist = new WatchList(pName);
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						watchlist.addWatchable(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				watchlist.addWatchable(movie);
			}
		}
		return watchlist;
	}
}
