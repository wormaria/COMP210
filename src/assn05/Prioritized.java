package assn05;

public interface Prioritized<V,P extends Comparable<P>> {
    V getValue();
    P getPriority();

    /**
     * returns int 0 if priorities are equal, or <0 if (this.getPriority < other.getPriority), or >0 otherwise.
     */
    int compareTo(Prioritized<V, P> other);
}