package Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import Functioning.*;


/**
 * Unit test for WhichEpisodeStrategy.
 * Blackbox testing.
 *
 * @author Haochen Liu
 * @version 1.0
 */
class WhichEpisodeStrategyTest {
    WhichEpisodeStrategy wFilter1;
    WhichEpisodeStrategy wFilter2;
    Movie m1;
    TVShow show1;


    @BeforeEach
    void setUp() {
        //set up the TVShows and add Episodes for them
        File f1 = new File("C:\\Users\\1.mp4");
        File f3 = new File("C:\\Users\\3.mp4");
        File f4 = new File("C:\\Users\\4.mp4");
        m1 = new Movie(f1, "Movie1",Language.ENGLISH, "WarnerBrothers");
        show1 = new TVShow("Wow Show",Language.ENGLISH,"WarnerBrothers");
        show1.createAndAddEpisode(f3,"wow1");
        show1.createAndAddEpisode(f4,"wow2");
        Library.getInstance().addMovie(m1);
        Library.getInstance().addTVShow(show1);
        //first episode filter
        wFilter1 = new WhichEpisodeStrategy(1);
        //second episode filter
        wFilter2 = new WhichEpisodeStrategy(2);
    }

    @Test
    void testFilterMovie() {
        assertFalse(wFilter1.filter(m1));
    }

    @Test
    void testFilterTVShow() {
        assertTrue(wFilter2.filter(show1));
    }

    @Test
    void testFilterEpisode() {
        assertTrue(wFilter1.filter(show1.getEpisode(1)));
        assertFalse(wFilter2.filter(show1.getEpisode(1)));
    }
}