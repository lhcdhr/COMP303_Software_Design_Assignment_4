package Functioning;

/**
 * Or filter strategy.
 * Check whether the Watchable Object
 * meets at least one of the required
 * conditions.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class OrFilterStrategy implements WatchListFilter{

    private final WatchListFilter[] aFilters;

    /**
     * Constructor.
     *
     * @param pFilters the filters (conditions) to check
     */
    public OrFilterStrategy(WatchListFilter[] pFilters){
        aFilters = pFilters;
    }

    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        //If one of the condition is met,
        //then return true.
        for(WatchListFilter toCheck: aFilters){
            if(toCheck.filter(pMovie)){
                return true;
            }
        }
        //None of the conditions are met.
        return false;
    }

    // Similar to above.
    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        for(WatchListFilter toCheck: aFilters){
            if(toCheck.filter(pTVShow)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        for(WatchListFilter toCheck: aFilters){
            if(toCheck.filter(pEpisode)){
                return true;
            }
        }
        return false;
    }
}
