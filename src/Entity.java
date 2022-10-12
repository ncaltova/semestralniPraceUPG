import java.util.ArrayList;

/**
 * Trida reprezentujici obecnou entitu.
 * @author Nikol Caltova
 */
public class Entity {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /** Typ entity. */
    private final String type;

    /** Nazev entity. */
    private final String name;

    /** Pozice X entitiy. */
    private double positionX;

    /** Pozice Y entitiy. */
    private double positionY;

    /** Slozka x rychlosti entity. */
    private double velocityX;

    /** Slozka y rychlosti entity. */
    private double velocityY;

    /** Slozka x aktualniho zrychleni entity. */
    private double accelerationX;

    /** Slozka y aktualniho zrychleni entity. */
    private double accelerationY;

    /** Hmotnost entity. */
    private final double mass;

    /** Prumer entity. */
    private final double radius;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Konstruktor, ktery na zaklade predanych hodnot vytvori novou entitu.
     * @param type Typ dane entity.
     * @param name Nazev dane entity.
     * @param positionX Pozice x dane entity.
     * @param positionY Pozice y dane entity.
     * @param velocityX Rychlost na ose x dane entity.
     * @param velocityY Rychlost na ose y dane entity.
     * @param mass  Hmotnost dane entity.
     */
    public Entity(String type, String name, double positionX, double positionY, double velocityX,
                  double velocityY, double mass) {
        this.type = type;
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.radius = (Math.cbrt((3*mass)/(4*Math.PI)));
        this.accelerationY = 0.0;
        this.accelerationX = 0.0;
    }

    /*____________________________________________GETTERY A SETTRY____________________________________________________*/

    /**
     * Getter, ktery vraci pozicy x dane entity.
     * @return Pozice x dane entity.
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * Setter, ktery nastavi aktualni pozicy x.
     * @param positionX Aktualni pozice x.
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    /**
     * Getter, ktery vraci pozicy y dane entity.
     * @return Pozice y dane entity.
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * Setter, ktery nastavi aktualni pozicy x.
     * @param positionY Aktualni pozice x.
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    /**
     * Getter, ktery vraci rychlost na ose x dane entity.
     * @return Rychlost na ose x dane entity.
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Setter, ktery nastavi aktualni hodnotu rychlosti v ose x.
     * @param velocityX Aktualni hodnota rychlosti v ose x.
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Getter, ktery vraci rychlost na ose y dane entity.
     * @return Rychlost na ose y dane entity.
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Setter, ktery nastavi aktualni hodnotu rychlosti v ose y.
     * @param velocityY Aktualni hodnota rychlosti v ose y.
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Getter, ktery vraci polomer dane entity.
     * @return Polomer dane entity.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Getter, ktery vraci aktualni zrychleni na ose x.
     * @return Aktualni zrychleni na ose x.
     */
    public double getAccelerationX() {
        return accelerationX;
    }

    /**
     * Getter, ktery vraci aktualni zrychleni na ose y.
     * @return Aktualni zrychleni na ose y.
     */
    public double getAccelerationY() {
        return accelerationY;
    }

    /*____________________________________________METODY PRO KOMUNIKACI_______________________________________________*/

    public void calcAcceleration(ArrayList<Entity> entities, double gravConst){
        double forceX = 0.0;
        double forceY = 0.0;

        for (Entity entity: entities) {
            if (entity.equals(this)) continue;

            double distanceX = entity.getPositionX() - this.positionX;
            double distanceY = entity.getPositionY() - this.positionY;
            double vectorSize = (Math.sqrt((distanceX*distanceX)+(distanceY*distanceY)));

            if ( !((Math.abs(this.mass)<0.001) || (Math.abs(entity.mass)<0.001)) ){
                forceX += (gravConst*this.mass*entity.mass*distanceX)/(vectorSize*vectorSize*vectorSize);
                forceY += (gravConst*this.mass*entity.mass*distanceY)/(vectorSize*vectorSize*vectorSize);
            }
        }

        this.accelerationX = forceX/this.mass;
        this.accelerationY = forceY/this.mass;
    }

    public void calcVelocity(double simulationStep){

        this.velocityX += 0.5*simulationStep*this.accelerationX;
        this.velocityY += 0.5*simulationStep*this.accelerationY;

        this.positionX += simulationStep*this.velocityX;
        this.positionY += simulationStep*this.velocityY;

        this.velocityX += 0.5*simulationStep*this.accelerationX;
        this.velocityY += 0.5*simulationStep*this.accelerationY;

    }
}
