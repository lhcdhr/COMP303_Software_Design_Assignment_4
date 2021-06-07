package Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import Functioning.*;

/**
 * Unit test for LanguageFilterStrategy.
 * Blacbox testing.
 *
 * @author Haochen Liu
 * @version 1.0
 */
class LanguageFilterStrategyTest {
    LanguageFilterStrategy lFilter;
    Movie m1;
    Movie m2;
    TVShow show1;
    TVShow show2;
    @BeforeEach
    void setUp() {
        //set up the Watchable objects
        File f1 = new File("C:\\Users\\1.mp4");
        File f2 = new File("C:\\Users\\2.mp4");
        File f3 = new File("C:\\Users\\3.mp4");
        File f4 = new File("C:\\Users\\4.mp4");
        File f5 = new File("C:\\Users\\5.mp4");
        File f6 = new File("C:\\Users\\6.mp4");
        m1 = new Movie(f1, "Movie1",Language.ENGLISH, "WarnerBrothers");
        m2 = new Movie(f2,"movie2",Language.FRENCH,"mcgill");
        show1 = new TVShow("Wow Show",Language.ENGLISH,"WarnerBrothers");
        show1.createAndAddEpisode(f3,"wow1");
        show1.createAndAddEpisode(f4,"wow2");
        show2 = new TVShow("Bruh Show",Language.FRENCH,"WarnerBrothers");
        show2.createAndAddEpisode(f5,"Bruh1");
        show2.createAndAddEpisode(f6,"Bruh2");
        //French filter
        lFilter = new LanguageFilterStrategy(Language.FRENCH);
    }

    @Test
    void testFilterMovie() {
        assertFalse(lFilter.filter(m1));
        assertTrue(lFilter.filter(m2));

    }

    @Test
    void testFilterTVShow() {
        assertFalse(lFilter.filter(show1));
        assertTrue(lFilter.filter(show2));
    }

    @Test
    void testFilterEpisode() {
        assertFalse(lFilter.filter(show1.getEpisode(1)));
        assertFalse(lFilter.filter(show1.getEpisode(2)));
        assertTrue(lFilter.filter(show2.getEpisode(1)));
        assertTrue(lFilter.filter(show2.getEpisode(2)));
    }
}