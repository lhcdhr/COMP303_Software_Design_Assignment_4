package Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import Functioning.*;

/**
 * Unit test for OrFilterStrategy.
 * Blackbox testing.
 *
 * @author Haochen Liu
 * @version 1.0
 */
class OrFilterStrategyTest {
    OrFilterStrategy oFilter1;
    OrFilterStrategy oFilter2;
    Movie m1;
    Movie m2;
    Movie m3;
    TVShow show1;
    TVShow show2;

    @BeforeEach
    void setUp() {
        //set up the Watchable Objects
        File f1 = new File("C:\\Users\\1.mp4");
        File f2 = new File("C:\\Users\\2.mp4");
        File f3 = new File("C:\\Users\\3.mp4");
        File f4 = new File("C:\\Users\\4.mp4");
        File f5 = new File("C:\\Users\\5.mp4");
        File f6 = new File("C:\\Users\\6.mp4");
        File f7 = new File("C:\\Users\\7.mp4");
        m1 = new Movie(f1, "Movie1", Language.ENGLISH, "WarnerBrothers");
        m2 = new Movie(f2,"movie2",Language.FRENCH,"mcgill");
        m3 = new Movie(f7,"movie3",Language.LATIN,"BarnerSisters");
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
        WatchListFilter[] combination2 = {lFilter1,sFilter};
        //filter for English or French
        oFilter1 = new OrFilterStrategy(combination1);
        //filter for French or studio as WarnerBrothers
        oFilter2 = new OrFilterStrategy(combination2);
    }

    @Test
    void testFilterMovie() {
        assertTrue(oFilter1.filter(m1));
        assertTrue(oFilter1.filter(m2));
        assertFalse(oFilter1.filter(m3));
    }

    @Test
    void testFilterTVShow() {
        assertTrue(oFilter1.filter(show1));
        assertTrue(oFilter2.filter(show1));
        assertFalse(oFilter1.filter(show2));
        assertFalse(oFilter2.filter(show2));
    }

    @Test
    void testFilterEpisode() {
        assertTrue(oFilter1.filter(show1.getEpisode(1)));
        assertTrue(oFilter2.filter(show1.getEpisode(1)));
        assertFalse(oFilter1.filter(show2.getEpisode(1)));
        assertFalse(oFilter2.filter(show2.getEpisode(1)));
    }
}