package Test;

import Functioning.Episode;
import Functioning.Language;
import Functioning.TVShow;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 * Unit test for TVShow, focusing on the method createAndAddEpisode.
 * Blackbox testing and Whitebox testing.
 * reflection is used to access some package-private/private fields/methods.
 *
 * @author Haochen Liu
 * @version 1.0
 */
class TVShowTest {

    @Test
    void testCreateAndAddEpisode() {
        try{
            TVShow show1 = new TVShow("show1", Language.ENGLISH,"WarnerBrothers");
            File p1 = new File("C:\\Users\\1.mp4");
            File p2 = new File("C:\\Users\\2.mp4");
            show1.createAndAddEpisode(p1,"ep1");
            show1.createAndAddEpisode(p2,"ep2");
            Episode e1 = show1.getEpisode(1);
            Episode e2 = show1.getEpisode(2);
            Field path = Episode.class.getDeclaredField("aPath");
            path.setAccessible(true);
            File path1 = (File) path.get(e1);
            File path2 = (File) path.get(e2);
            //check whether the two episodes are successfully
            //created and added
            assertEquals(show1.getTotalCount(),2);
            //check whether the information is successfully set
            assertEquals(path1,p1);
            assertEquals(path2,p2);
            assertEquals(1,e1.getEpisodeNumber());
            assertEquals(2,e2.getEpisodeNumber());
            assertEquals("ep1",e1.getTitle());
            assertEquals("ep2",e2.getTitle());
        }
        //exception handling(basically there should no exception should be thrown from above)
        catch(Exception e){
            fail();
        }
    }
}