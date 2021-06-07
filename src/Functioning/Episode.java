package Functioning;

import java.io.File;
import java.util.*;

/**
 * Prototype design pattern.
 *
 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path on
 * the file system.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class Episode implements Sequenceable<Episode>, Cloneable {

    private File aPath;
    private final TVShow aTVShow;
    private String aTitle;
    private int aEpisodeNumber;
    private Map<String, String> aCast = new HashMap<>();
    private Map<String, String> aTags = new HashMap<>();

    /**
     * Creates an episode from the file path. This method should not be called by a client. Use
     * Functioning.TVShow.createAndAddEpisode instead.
     *
     * @param pPath
     *            location of the episode on the file system.
     * @param pTVShow
     *            Functioning.TVShow that this episode is a part of
     * @param pTitle
     *            official title of the episode
     * @param pEpisodeNumber
     *            the episode number that identifies the episode
     * @pre pPath!=null && pTVShow != null && pTitle!=null
     * @throws IllegalArgumentException
     *             if the path doesn't point to a file (e.g., it denotes a directory)
     */
    Episode(File pPath, TVShow pTVShow, String pTitle, int pEpisodeNumber) {
        // Package-private constructor AND notice in the Javadoc to prevent clients from using this constructor.
        // Still, a client could create an Functioning.Episode directly, and it would only affect the episode object, not the TV
        // show.
        // Alternatively, the Functioning.Episode class could be nested inside Functioning.TVShow, with a private constructor.
        assert (pPath != null) && (pTVShow != null) && (pTitle != null);
        if (pPath.exists() && !pPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        aPath = pPath; // ok because File is immutable.
        aTVShow = pTVShow;
        aTitle = pTitle;
        aEpisodeNumber = pEpisodeNumber;
    }


    @Override
    public void watch() {
        System.out.println("Now watching " + aTVShow.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
    }

    @Override
    public boolean isValid() {
        return aPath.isFile() && aPath.canRead();
    }
    @Override
    public String getTitle() {
        return aTitle;
    }
    @Override
    public String getStudio() {
        return aTVShow.getStudio();
    }
    @Override
    public Language getLanguage() {
        return aTVShow.getLanguage();
    }

    /**
     * @return the episode number of the episode
     */
    public int getEpisodeNumber() {
        return aEpisodeNumber;
    }

    public String setCast(String pCharacter, String pActor) {
        if (pActor == null) {
            return aCast.remove(pCharacter);
        }
        else {
            return aCast.put(pCharacter, pActor);
        }
    }

    public String getCast(String pCharacter) {
        return aCast.get(pCharacter);
    }

    public Set<String> getAllCharacters() {
        return Collections.unmodifiableSet(aCast.keySet());
    }

    public String setInfo(String pKey, String pValue) {
        if (pValue == null) {
            return aTags.remove(pKey);
        }
        else {
            return aTags.put(pKey, pValue);
        }
    }

    public String getInfo(String pKey) {
        return aTags.get(pKey);
    }

    public boolean hasInfo(String pKey) {
        return aTags.containsKey(pKey);
    }

    @Override
    public boolean hasPrevious() {
        return aEpisodeNumber > 1;
    }

    @Override
    public boolean hasNext() {
        return aEpisodeNumber < aTVShow.getTotalCount();
    }

    @Override
    public Episode getPrevious() {
        return aTVShow.getEpisode(aEpisodeNumber - 1);
    }

    @Override
    public Episode getNext() {
        return aTVShow.getEpisode(aEpisodeNumber + 1);
    }

    //overridden clone()
    //Client should not call it directly.
    @Override
    public Episode clone(){
        try{
            Episode clone = (Episode) super.clone();
            //deep-copying object-related field except aTVShow
            String cPath = this.aPath.getAbsolutePath();
            File cFile = new File(cPath);
            clone.aPath = cFile;
            //deep-copying hashmaps
            clone.aCast = new HashMap<String,String>(this.aCast);
            clone.aTags = new HashMap<String,String>(this.aTags);
            return clone;
        }
        catch(CloneNotSupportedException e){
            assert false;
            return null;
        }
    }


    /**
     * Reset some necessary fields after cloning from the prototype.
     * Package-private.
     * Client should not call it directly.
     *
     * @param sFile the path of file for this Functioning.Episode
     * @param sTitle the title of this Functioning.Episode
     * @param sNum the sequential number of this Functioning.Episode
     */
    void cloneSet(File sFile, String sTitle, int sNum){
        this.aPath = sFile;
        this.aTitle = sTitle;
        this.aEpisodeNumber = sNum;
    }

    //overridden equals() and hashcode()
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Episode episode = (Episode) o;
        return aPath.getAbsolutePath() == episode.aPath.getAbsolutePath()
                && aTVShow == episode.aTVShow
                && aTitle == episode.aTitle
                && aTags.equals(episode.aTags)
                && aCast.equals(episode.aCast);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31*hash + aPath.hashCode();
        hash = 31*hash + aTVShow.hashCode();
        hash = 31*hash + aTitle.hashCode();
        hash = 31*hash + aEpisodeNumber;
        hash = 31*hash + aCast.hashCode();
        hash = 31*hash + aTags.hashCode();
        return hash;
    }
}
