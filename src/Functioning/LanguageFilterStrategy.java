package Functioning;

/**
 * Strategy to filter Movies, TVShows or Episodes whose language is equal to pLanguage
 * All three cases work similarly.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class LanguageFilterStrategy implements WatchListFilter {

    private final Language aLanguage;

    /**
     * Constructor.
     *
     * @param pLanguage the Language to check
     */
    public LanguageFilterStrategy(Language pLanguage) {
        aLanguage = pLanguage;
    }
    // All three works similar, just check whether languages are the equal.
    @Override
    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        return pMovie.getLanguage().equals(aLanguage);
    }

    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        return pTVShow.getLanguage().equals(aLanguage);
    }

    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        return pEpisode.getLanguage().equals(aLanguage);
    }

}
