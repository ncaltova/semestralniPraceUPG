/**
 * Trida reprezentujici prepravku specifikaci pro vykresleni galaxie na platno
 * @author Nikol Caltova
 */
public class ContextSpec {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /** Skalovaci konstanta pro zobrazeni na platne */
    private final double  scale;
    /** Offset vykreslene galaxie na ose x pro vycentrovani*/
    private final double  offsetX;
    /** Offset vykreslene galaxie na ose y pro vycentrovani*/
    private final double  offsetY;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Konstruktor, ktery vytvori instanci prepravky z predanych parametru.
     * @param scale Skalovaci konstatnta pro vykresleni.
     * @param offsetX Offset vykreslene galaxie na ose x.
     * @param offsetY Offset vykreslene galaxie na ose y.
     */
    public ContextSpec(double scale, double offsetX, double offsetY) {
        this.scale = scale;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /*____________________________________________GETTERY A SETTRY____________________________________________________*/

    /**
     * Getter, ktery vraci ulozenou skalovaci konstantu.
     * @return ulozena skalovaci konstanta.
     */
    public double getScale() {
        return scale;
    }

    /**
     * Getter, ktery vraci ulozeny offset vykreslene galaxie na ose x.
     * @return offset vykreslene galaxie na ose x.
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * Getter, ktery vraci ulozeny offset vykreslene galaxie na ose y.
     * @return offset vykreslene galaxie na ose y.
     */
    public double getOffsetY() {
        return offsetY;
    }
}
