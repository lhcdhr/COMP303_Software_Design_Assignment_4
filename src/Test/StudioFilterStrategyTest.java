package Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import Functioning.*;

/**
 * Unit test for StudioFilterStrategy.
 * Blackbox testing
 *
 * @author Haochen Liu
 * @version 1.0
 */
class StudioFilterStrategyTest {
    StudioFilterStrategy sFilter;
    Movie m1;
    Movie m2;
    TVShow show1;
    TVShow show2;

    @BeforeEach
    void setUp() {
        //set up Watchable objects
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
        show2 = new TVShow("Bruh Show",Language.FRENCH,"RenrawBrothers");
        show2.createAndAddEpisode(f5,"Bruh1");
        show2.createAndAddEpisode(f6,"Bruh2");
        Library.getInstance().addMovie(m1);
        Library.getInstance().addMovie(m2);
        Library.getInstance().addTVShow(show1);
        Library.getInstance().addTVShow(show2);
        // filter studio WarnerBrothers
        sFilter = new StudioFilterStrategy("WarnerBrothers");
    }

    @Test
    void testFilterMovie() {
        assertTrue(sFilter.filter(m1));
        assertFalse(sFilter.filter(m2));
    }

    @Test
    void testFilterTVShow() {
        assertTrue(sFilter.filter(show1));
        assertFalse(sFilter.filter(show2));
    }

    @Test
    void testFilterEpisode() {
        assertTrue(sFilter.filter(show1.getEpisode(1)));
        assertTrue(sFilter.filter(show1.getEpisode(2)));
        assertFalse(sFilter.filter(show2.getEpisode(1)));
        assertFalse(sFilter.filter(show2.getEpisode(2)));
    }
}