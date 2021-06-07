package Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import Functioning.*;

/**
 * Unit test for AndFilterStrategy.
 * Blackbox testing.
 *
 * @author Haochen Liu
 * @version 1.0
 */
class AndFilterStrategyTest {
    AndFilterStrategy aFilter1;
    AndFilterStrategy aFilter2;
    AndFilterStrategy aFilter3;
    Movie m1;
    Movie m2;
    TVShow show1;
    TVShow show2;

    @BeforeEach
    void setUp() {
        //set up the Watchable objects to check
        File f1 = new File("C:\\Users\\1.mp4");
        File f2 = new File("C:\\Users\\2.mp4");
        File f3 = new File("C:\\Users\\3.mp4");
        File f4 = new File("C:\\Users\\4.mp4");
        File f5 = new File("C:\\Users\\5.mp4");
        File f6 = new File("C:\\Users\\6.mp4");
        m1 = new Movie(f1, "Movie1", Language.ENGLISH, "WarnerBrothers");
        m2 = new Movie(f2,"movie2",Language.ENGLISH,"mcgill");
        show1 = new TVShow("Wow Show",Language.ENGLISH,"WarnerBrothers");
        show1.createAndAddEpisode(f3,"wow1");
        show1.createAndAddEpisode(f4,"wow2");
        show2 = new TVShow("Bruh Show",Language.LATIN,"BarnerSisters");
        show2.createAndAddEpisode(f5,"Bruh1");
        show2.createAndAddEpisode(f6,"Bruh2");
        LanguageFilterStrategy lFilter1 = new LanguageFilterStrategy(Language.FRENCH);
        LanguageFilterStrategy lFilter2 = new LanguageFilterStrategy(Language.ENGLISH);
        StudioFilterStrategy sFilter = new StudioFilterStrategy("WarnerBrothers");
        WatchListFilter[] combination1 = {lFilter1,lFilter2};
        WatchListFilter[] combination2 = {lFilter2,sFilter};
        //and filter for English and French.
        //This filter should literally get nothing since every Watchable object
        //can only have one language.
        aFilter1 = new AndFilterStrategy(combination1);
        //and filter for English and WarnerBrothers
        aFilter2 = new AndFilterStrategy(combination2);
        WhichEpisodeStrategy wFilter1 = new WhichEpisodeStrategy(2);
        //and filter for English and WarnerBrothers, and get the second episodes.
        WatchListFilter[] combination3 = {lFilter2,sFilter,wFilter1};
        aFilter3 = new AndFilterStrategy(combination3);
    }

    @Test
    void testFilterMovie() {
        assertFalse(aFilter1.filter(m1));
        assertFalse(aFilter1.filter(m2));
        assertTrue(aFilter2.filter(m1));
        assertFalse(aFilter3.filter(m1));
    }

    @Test
    void testFilterTVShow() {
        assertFalse(aFilter1.filter(show1));
        assertTrue(aFilter2.filter(show1));
        assertFalse(aFilter2.filter(show2));
        assertTrue(aFilter3.filter(show1));
    }

    @Test
    void testFilterEpisode() {
        assertFalse(aFilter1.filter(show1.getEpisode(1)));
        assertTrue(aFilter2.filter(show1.getEpisode(1)));
        assertFalse(aFilter2.filter(show2.getEpisode(1)));
        assertTrue(aFilter3.filter(show1.getEpisode(2)));
        assertFalse(aFilter3.filter(show1.getEpisode(1)));
    }
}