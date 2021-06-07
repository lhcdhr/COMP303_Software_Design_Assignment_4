package Functioning;

/**
 * Represents a list of elements to watch in sequence ("binge")
 *
 * @author Haochen Liu
 * @version 1.0
 */
interface Bingeable<T extends Watchable> extends Iterable<T> {

    /**
     * @return the total number of Functioning.Watchable elements in this object
     */
    public int getTotalCount();

    /**
     * @return the number of Functioning.Watchable elements that haven't been watched yet
     */
    public int getRemainingCount();

    /**
     * Resets the status of the elements to watch
     */
    public void reset();

    /**
     * Returns an element to watch and mark that element as watched
     *
     * @return the next element to watch
     * @pre getRemainingCount() > 0
     */
    public T next();
}
