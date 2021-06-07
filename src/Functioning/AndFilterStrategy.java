package Functioning;
/**
 * And filter strategy.
 * Check whether the Watchable Object
 * meets all requirements of the filters
 * stored in the array aFilters.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class AndFilterStrategy implements WatchListFilter{

    private final WatchListFilter[] aFilters;


    /**
     * Constructor.
     *
     * @param pFilters the filters to check
     */
    public AndFilterStrategy(WatchListFilter[] pFilters){
        aFilters = pFilters;
    }

    @Override
    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        for(WatchListFilter toCheck: aFilters){
            if(!toCheck.filter(pMovie)){
                return false;
            }
        }
        //true if all filters passes
        return true;
    }

    //similar to above
    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        for(WatchListFilter toCheck: aFilters){
            if(!toCheck.filter(pTVShow)){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        for(WatchListFilter toCheck: aFilters){
            if(!toCheck.filter(pEpisode)){
                return false;
            }
        }
        return true;
    }
}

