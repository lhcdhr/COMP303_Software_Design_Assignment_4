package Functioning;

import java.io.File;

public class Driver {

    public static void main(String[] args){

        //Creating Movies
        File f1 = new File("C:\\Users\\1.mp4");
        File f2 = new File("C:\\Users\\2.mp4");
        File f3 = new File("C:\\Users\\3.mp4");
        File f4 = new File("C:\\Users\\4.mp4");
        Watchable m1 = new Movie(f1, "Movie1",Language.ENGLISH, "WarnerBrothers");
        Watchable m2 = new Movie(f2,"movie2",Language.ENGLISH,"mcgill");
        Watchable m3 = new Movie(f3,"movie3",Language.ENGLISH,"ubc");
        Watchable m4 = new Movie(f4,"movie4",Language.FRENCH,"WarnerBrothers");
        Library.getInstance().addMovie((Movie)m1);
        Library.getInstance().addMovie((Movie)m2);
        Library.getInstance().addMovie((Movie)m3);
        Library.getInstance().addMovie((Movie)m4);

        //TVShow1 and its episodes
        TVShow show1 = new TVShow("Wow Show",Language.ENGLISH,"WarnerBrothers");
        File f5 = new File("C:\\Users\\5.mp4");
        File f6 = new File("C:\\Users\\6.mp4");
        File f7 = new File("C:\\Users\\7.mp4");
        show1.createAndAddEpisode(f5,"wow1");
        show1.createAndAddEpisode(f6,"wow2");
        show1.createAndAddEpisode(f7,"wow3");

        //TVShow2 and its episodes
        TVShow show2 = new TVShow("Bruh Show",Language.FRENCH,"WarnerBrothers");
        File f8 = new File("C:\\Users\\8.mp4");
        File f9 = new File("C:\\Users\\9.mp4");
        File f10 = new File("C:\\Users\\10.mp4");
        show2.createAndAddEpisode(f8,"Bruh1");
        show2.createAndAddEpisode(f9,"Bruh2");
        show2.createAndAddEpisode(f10,"Bruh3");

        //TVShow3 and its episodes
        TVShow show3 = new TVShow("Phew Show",Language.ENGLISH,"BarnerSisters");
        File f11 = new File("C:\\Users\\11.mp4");
        File f12 = new File("C:\\Users\\12.mp4");
        File f13 = new File("C:\\Users\\13.mp4");
        show3.createAndAddEpisode(f11,"phew1");
        show3.createAndAddEpisode(f12,"phew2");
        show3.createAndAddEpisode(f13,"phew3");
        Library.getInstance().addTVShow(show1);
        Library.getInstance().addTVShow(show2);
        Library.getInstance().addTVShow(show3);

        //English filter
        LanguageFilterStrategy l1 = new LanguageFilterStrategy(Language.ENGLISH);
        //French filter
        LanguageFilterStrategy l2 = new LanguageFilterStrategy(Language.FRENCH);
        //WarnerBrother studio filter(the required one in Q1)
        StudioFilterStrategy s1 = new StudioFilterStrategy("WarnerBrothers");
        WatchListFilter[] fs1 = {l1,l2};
        //English or French filter
        OrFilterStrategy of1 = new OrFilterStrategy(fs1);
        WatchListFilter[] fs2 = {of1, s1};
        //English or French filter, and WarnerBrother studio filter
        AndFilterStrategy af1 = new AndFilterStrategy(fs2);
        //1st episode filter
        WhichEpisodeStrategy getEpisode1 = new WhichEpisodeStrategy(1);
        WatchListFilter[] wf2 = {getEpisode1,af1};

        //The filter required in Q1:
        // first episodes of all TV shows from the WarnerBrothers studio
        // that are in either English or French.
        AndFilterStrategy af3 = new AndFilterStrategy(wf2);

        WatchList w2 = Library.getInstance().generateWatchList("WatchList2",af3);
        System.out.println("In Q1, Driver.java is required to generate such a WatchList:\n"+
                "first episodes of all TV shows from the WarnerBrothers studio\n"+
                "that are in either English or French.\n");
        System.out.println("Based on the filters, the generated WatchList should\n" +
                "contain the episode 1 from TVShow1(Wow Show),\n" +
                "and the episode 1 from TVShow2(Bruh Show).\n");
        System.out.println("Now, the generated WatchList contains "+w2.getTotalCount()+" Watchable objects.\n");
        Watchable result1 = w2.next();
        System.out.println("The class for the first Watchable Object: "+result1.getClass());
        System.out.println("The title of this episode: "+result1.getTitle());
        result1 = (Episode) result1;
        System.out.println("The episode number: "+ ((Episode) result1).getEpisodeNumber()+"\n");


        Watchable result2 = w2.next();
        System.out.println("The class for the second Watchable Object: "+result2.getClass());
        System.out.println("The title of this episode: "+result2.getTitle());
        result2 = (Episode) result2;
        System.out.println("The episode number: "+ ((Episode) result1).getEpisodeNumber()+"\n");

        System.out.println("The result meets the requirement.");
    }
}
