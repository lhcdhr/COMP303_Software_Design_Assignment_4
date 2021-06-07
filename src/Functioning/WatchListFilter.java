package Functioning;

/**
 * Interface for Strategy design pattern
 *
 * @author Haochen Liu
 * @version 1.0
 */
public interface WatchListFilter {

    /**
     * Indicates whether a Functioning.Watchable elements should be included in the watchlist.
     *
     * @param pMovie
     *            a Functioning.Watchable to potentially include in the Watchlist
     * @return true if the Functioning.Watchable must be included, false otherwise
     */
    public boolean filter(Movie pMovie);

    /**
     * Indicates whether a Functioning.Watchable elements should be included in the watchlist.
     *
     * @param pTVShow
     *            a Functioning.Watchable to potentially include in the Watchlist
     * @return true if the Functioning.Watchable must be included, false otherwise
     */
    public boolean filter(TVShow pTVShow);

    /**
     * Indicates whether a Functioning.Watchable elements should be included in the watchlist.
     *
     * @param pEpisode
     *            a Functioning.Watchable to potentially include in the Watchlist
     * @return true if the Functioning.Watchable must be included, false otherwise
     */
    public boolean filter(Episode pEpisode);
}
