package Functioning;

/**
 * The filter that focus on TVShow and Episode.
 * Filter out the required Episode based on episode number.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class WhichEpisodeStrategy implements WatchListFilter{
    private final int nEpisode;

    /**
     * Constructor.
     *
     * @param pNum the required Episode number
     */
    public WhichEpisodeStrategy(int pNum){
        // Let user think as usual, getting the first episode
        nEpisode=pNum;
    }

    //Does not serve Movie.
    @Override
    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        return false;
    }

    //To accommodate the generateWatchList method in class Library,
    //The filter for all TVShow object needs to be true
    //in order to enter the iteration of the episodes stored in it.
    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        return true;
    }

    //If a episode has the required Episode number,
    //then it is the one to get.
    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        if(pEpisode.getEpisodeNumber()==nEpisode){
            return true;
        }
        return false;
    }

}

