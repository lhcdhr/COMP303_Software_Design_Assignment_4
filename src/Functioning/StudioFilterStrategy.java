package Functioning;

/**
 * Strategy to filter Movies, TVShows or Episodes whose studio is equal to pStudio
 * All three work similarly.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class StudioFilterStrategy implements WatchListFilter {

    private final String aStudio;

    /**
     * Constructor.
     *
     * @param pStudio the studio to check
     */
    public StudioFilterStrategy(String pStudio) {
        aStudio = pStudio;
    }

    @Override
    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        return pMovie.getStudio().equals(aStudio);
    }

    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        return pTVShow.getStudio().equals(aStudio);
    }

    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        return pEpisode.getStudio().equals(aStudio);
    }
}