package Functioning;

import java.io.File;
import java.util.*;

/**
 * Prototype design pattern.
 * Plays the role of Manager.
 *
 * Represents a single TV show, with at least a title, language, and publishing studio. Each Functioning.TVShow aggregates episodes.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class TVShow implements Watchable, Bingeable<Episode> {
	
	private final String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;
	private Episode protoEpisode;
	
	/**
	 * Prototype design pattern.
	 * Works as a Functioning.Episode Manager that stores a prototype Functioning.Episode
	 * for this Functioning.TVShow.
	 * Creates a Functioning.TVShow with required metadata about the show.
	 * Also,
	 *
	 * @param pTitle
	 *            official title of the Functioning.TVShow
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	public TVShow(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aNextToWatch = 0;
		aInfo = new HashMap<>();
		// create the prototype episode and store it
		File protoPath = new File("");
		protoEpisode = new Episode(protoPath, this, pTitle,0);
	}

	/**
	 *
	 * Creates a new Functioning.Episode for this TV show, and adds it the end of the episode list.
	 * This episode is created based on the prototype.
	 *
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null && pNum != 0;
	 */
	public void createAndAddEpisode(File pPath, String pTitle) {
		assert pPath != null && pTitle != null;
		int nb = aEpisodes.size()+1;
		//cloning from the prototype
		Episode eEpisode = protoEpisode.clone();
		//setting new information
		eEpisode.cloneSet(pPath,pTitle,nb);
		aEpisodes.add(eEpisode);
	}



	@Override
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
	}
	@Override
	public String getTitle() {
		return aTitle;
	}
	@Override
	public Language getLanguage() {
		return aLanguage;
	}
	@Override
	public String getStudio() {
		return aStudio;
	}
	
	public String getInfo(String pKey) {
		return aInfo.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aInfo.containsKey(pKey);
	}
	
	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	@Override
	public boolean isValid() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Returns the Functioning.Episode of a given number. Functioning.Episode numbers are 1-based: the first episode is 1, not 0.
	 *
	 * @param pNumber
	 *            the number whose Functioning.Episode is to be returned
	 * @return the Functioning.Episode of the given number
	 * @pre there is an episode for the given number
	 */
	public Episode getEpisode(int pNumber) {
		assert aEpisodes.size() >= pNumber;
		return aEpisodes.get(pNumber - 1);
	}
	
	@Override
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aEpisodes.size() - aNextToWatch;
	}
	
	@Override
	public Episode next() {
		assert getRemainingCount() > 0;
		Episode nextEpisode = aEpisodes.get(aNextToWatch);
		aNextToWatch++;
		if (aNextToWatch >= aEpisodes.size()) {
			aNextToWatch = 0;
		}
		return nextEpisode;
	}
	
	@Override
	public void reset() {
		aNextToWatch = 0;
	}
	
	@Override
	public Iterator<Episode> iterator() {
		return aEpisodes.iterator();
	}
}