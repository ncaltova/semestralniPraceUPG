/**
 * Trida reprezentujici prepravku velikosti galaxie.
 * @author Nikol Caltova
 */
public class GalaxySize {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /** Maximalni souradnice na ose x. */
    private final double maxX;
    /** Maximalni souradnice na ose y. */
    private final double maxY;
    /** Minimalni souradnice na ose x. */
    private final double minX;
    /** Minimalni souradnice na ose y. */
    private final double minY;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Konstruktor, ktery vytvori instanci prepravky velikosti galaxie z predanych hodnot.
     * @param maxX Maximalni souradnice na ose x.
     * @param maxY Maximalni souradnice na ose y.
     * @param minX Minimalni souradnice na ose x.
     * @param minY Minimalni souradnice na ose y.
     */
    public GalaxySize(double maxX, double maxY, double minX, double minY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }

    /*____________________________________________GETTERY A SETTRY____________________________________________________*/

    /**
     * Getter, ktery vraci maximalni souradnici na ose x.
     * @return maximalni souradnice na ose x.
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * Getter, ktery vraci maximalni souradnici na ose y.
     * @return maximalni souradnice na ose y.
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * Getter, ktery vraci minimalni souradnici na ose x.
     * @return minimalni souradnice na ose x.
     */
    public double getMinX() {
        return minX;
    }

    /**
     * Getter, ktery vraci minimalni souradnici na ose y.
     * @return minimalni souradnice na ose y.
     */
    public double getMinY() {
        return minY;
    }
}
