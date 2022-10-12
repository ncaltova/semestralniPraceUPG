import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Trida reprezentujici platno, na ktere se vykresluje vysledna simulace.
 * @author Nikol Caltova
 */
public class DrawingPanel extends JPanel {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /** Seznam vsech entit v simulaci */
    private ArrayList<Entity> entities;

    /** Gravitacni konstanta simulace */
    private double gravConst;

    /** Animacni krok simulace */
    private double aniStep;
    /** Simulacni cas */
    private double sTime;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Konstruktor, ktery vytvori instanci platna z hodnot predanych ze souboru.
     * @param args Cesta k soubotu.
     */
    public DrawingPanel(String args) {
        FileReader rd = new FileReader(args);

        this.setPreferredSize(new Dimension(800, 600));
        this.entities = rd.getEntity();
        this.gravConst = rd.getGravitationalConstant();
        this.aniStep = rd.getAnimationStep();
        this.sTime = 0.0;

    }

    /*____________________________________________METODY PRO KOMUNIKACI_______________________________________________*/

    /**
     * Metoda, ktera vrati zpet velikost zadane galaxie.
     * @return Velikost zadane galaxie.
     */
    private GalaxySize getGalaxySize() {
        double min_X = Double.MAX_VALUE;
        double min_Y = Double.MAX_VALUE;
        double max_X = Double.MIN_VALUE;
        double max_Y = Double.MIN_VALUE;

        for (Entity entity: this.entities) {
            max_X = Math.max(max_X, entity.getPositionX() + (entity.getRadius()*2.0));
            max_Y = Math.max(max_Y, entity.getPositionY() + (entity.getRadius()*2.0));

            min_X = Math.min(min_X, entity.getPositionX());
            min_Y = Math.min(min_Y, entity.getPositionY());
        }

        /*Zjistene rozmery galaxie*/
        return new GalaxySize(max_X, max_Y, min_X, min_Y);
    }

    /**
     * Metoda pro vypocteni skalovaci konstanty a offsetu pro zarovnani stredu galaxie na stred platna.
     * @return Skalovaci konstanta a offset pro zarovnani stredu galaxie na stred platna.
     */
    private ContextSpec getScaleOff(GalaxySize galaxySize) {

        double galaxyWidth = galaxySize.getMaxX()-galaxySize.getMinX();
        double galaxyHeight = galaxySize.getMaxY()-galaxySize.getMinY();

        /*Vypocet skalovaci konstanty*/
        double scaleX = this.getWidth()/galaxyWidth;
        double scaleY = this.getHeight()/galaxyHeight;

        /* Vybere se mensi skalovaci konstanta, aby se galaxie vzdy vesla na platno. */
        double scale = Math.min(scaleX,scaleY);

        /*Vypocet offsetu*/
        double offsetX = (scaleY<scaleX)?((this.getWidth() - (galaxyWidth*scale))/2.2):0.0;
        double offsetY = (scaleY>scaleX)?((this.getHeight() - (galaxyHeight*scale))/2.0):0.0;

        return new ContextSpec(scale, offsetX, offsetY);
    }

    /**
     * Metoda, ktera vykresli preskalovanou a posunutou predanou entitu na predane platno a ulozi do pole
     * vytvorenych objektu Ellipse2D reprezentujucich vsechny entity.
     * @param g2 Predane platno.
     * @param entity Predana entita.
     */
    public void drawEntity(Graphics2D g2, Entity entity) {

        GalaxySize worldSize = this.getGalaxySize();
        ContextSpec scaleInfo = this.getScaleOff(worldSize);

        Ellipse2D e = new Ellipse2D.Double(((entity.getPositionX()*scaleInfo.getScale())-
                (worldSize.getMinX()*scaleInfo.getScale()))+
                scaleInfo.getOffsetX(),
                ((entity.getPositionY()*scaleInfo.getScale())-
                        (worldSize.getMinY()*scaleInfo.getScale()))+
                        scaleInfo.getOffsetY(),
                entity.getRadius()*2.0*scaleInfo.getScale(),
                entity.getRadius()*2.0*scaleInfo.getScale());

        g2.fill(e);
    }

    /**
     * Metoda, ktera se stara o cely chod animace, tedy prepocitava zrychleni, rychlost a pozici entit v galaxii.
     * @param simulationTime Cas od posledni aktualizace systemu, po ktery se bude vykonavat while smycka.
     */
    public void updateSystem(double simulationTime) {
        double minimalStep = this.aniStep/1000.0;
        simulationTime=(simulationTime/1000.0)*this.aniStep;

        while (Math.abs(simulationTime)>0.001) {
            double simulationStep = Math.min(simulationTime, minimalStep);

            for (Entity entity : this.entities) {
                entity.calcAcceleration(this.entities, this.gravConst);
            }

            for (Entity entity : this.entities) {
                entity.calcVelocity(simulationStep);
            }

            simulationTime -= simulationStep;
            sTime += simulationStep;
        }
    }

    /**
     * Metoda, ktera vykresli cerny obdelnik o velikosti platna(okna) jako pozadianimace
     * @param g2 Objekt třídy Graphics2D, pomocí kterého se vykreslí na plátno.
     */
    private void drawBackground(Graphics2D g2) {
        g2.setColor(Color.black);
        Rectangle rect = new Rectangle(this.getWidth(), this.getHeight());
        g2.fill(rect);
    }

    /**
     * Metoda, ktera vykresli aktualni stav cele galaxie vcetne informacnich textu.
     * @param g Objekt třídy Graphics, pomocí kterého se vykreslí na plátno.
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);

        g2.setColor(Color.YELLOW);

        for (Entity entity: this.entities) {
            this.drawEntity(g2, entity);
        }

        g2.setColor(Color.green);
        String simTime = String.format("Simulation time =  %.08f s", sTime);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString(simTime, this.getWidth() - g2.getFontMetrics().stringWidth(simTime), 10);
    }


}
