package Test;

import Functioning.Episode;
import Functioning.Language;
import Functioning.TVShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 * Unit test for Episode.
 * Blackbox and whitebox texting.
 * reflection is used to access package-private/private constructor/methods/fields
 *
 * @author Haochen Liu
 * @version 1.0
 */
class EpisodeTest {
    Episode ep1;
    TVShow show1;

    @BeforeEach
    void setUp(){
        try{
            show1 = new TVShow("show1", Language.ENGLISH,"WarnerBrothers");
            //Access the package-private constructor.
            Constructor<Episode> con = Episode.class.getDeclaredConstructor(File.class,TVShow.class, String.class, int.class);
            con.setAccessible(true);
            File f1 = new File("C:\\Users\\1.mp4");
            //Use the package-private constructor to create a Episode object.
            ep1 = (Episode)con.newInstance(f1,show1,"ep1",1);
        }
        //exception handling(basically there should no exception should be thrown from above)
        catch(Exception e){
            fail();
        }
    }

    @Test
    void testClone() {
        Episode cloneEp1 = ep1.clone();
        //Check whether the overridden clone follows the three conditions seen in class.
        assertNotSame(ep1, cloneEp1);
        assertSame(ep1.getClass(), cloneEp1.getClass());
        assertEquals(ep1,cloneEp1);
    }

    @Test
    void cloneSet() {
        Episode cloneEp1 = ep1.clone();
        File newPath = new File("C:\\Users\\new.mp4");
        try{
            Method cloneSet = Episode.class.getDeclaredMethod("cloneSet",File.class,String.class,int.class);
            cloneSet.setAccessible(true);
            cloneSet.invoke(cloneEp1,newPath,"cloneEp1",2);
            Field path = Episode.class.getDeclaredField("aPath");
            path.setAccessible(true);
            File getPath = (File) path.get(cloneEp1);
            //Check whether the updated information is successfully set.
            assertEquals(newPath,getPath);
            assertEquals("cloneEp1",cloneEp1.getTitle());
            assertEquals(2,cloneEp1.getEpisodeNumber());
        }
        //exception handling(basically there should no exception should be thrown from above)
        catch(Exception e){
            fail();
        }
    }
}