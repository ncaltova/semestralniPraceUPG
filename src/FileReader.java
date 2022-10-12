import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Trida reprezentujucic parser vstupnich dat
 * @author Nikol Caltova
 */
public class FileReader {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /*Jmena entit prectenych ze souboru*/
    private ArrayList<String> names;

    /*Typy entit prectenych ze souboru*/
    private ArrayList<String> types;

    /*X pozice entit prectenych ze souboru*/
    private ArrayList<Double> positionsX;

    /*Y pozice entit prectenych ze souboru*/
    private ArrayList<Double> positionsY;

    /*X slozky vektoru rychlosti entit prectenych ze souboru*/
    private ArrayList<Double> velocitiesX;

    /*Y slozky vektoru rychlosti entit prectenych ze souboru*/
    private ArrayList<Double> velocitiesY;

    /*Hmotnosti entit prectenych ze souboru*/
    private ArrayList<Double> masses;

    /*Gravitacni konstanta entit*/
    private double gravitationalConstant;

    /*Animacni krok entit*/
    private double animationStep;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Konstruktor, precte soubor a prectena data nahraje do atributů.
     */
    public FileReader(String args) {

        this.names = new ArrayList<String>();
        this.types = new ArrayList<String>();
        this.positionsX = new ArrayList<Double>();
        this.positionsY = new ArrayList<Double>();
        this.velocitiesX = new ArrayList<Double>();
        this.velocitiesY = new ArrayList<Double>();
        this.masses = new ArrayList<Double>();

        try {
            /*Vytvoreni noveho scanneru, pro cteni ze zadaneho souboru*/
            Scanner sc = new Scanner(Paths.get(args));
            /*Nacteni simulacniho kroku a gravitacni konstanty*/
            String currLine = sc.nextLine();
            String[] sepData = new String[7];

            sepData = currLine.split(",");
            this.gravitationalConstant = Double.parseDouble(sepData[0]);
            this.animationStep = Double.parseDouble(sepData[1]);

            /*Postupne nacitani informaci o entitach a prirazovani do prislusnych listu*/
            while (sc.hasNextLine()){

                currLine = sc.nextLine();
                sepData = currLine.split(",");
                this.names.add(sepData[0]);
                this.types.add(sepData[1]);
                this.positionsX.add((Double.parseDouble(sepData[2])));
                this.positionsY.add(Double.parseDouble(sepData[3]));
                this.velocitiesX.add(Double.parseDouble(sepData[4]));
                this.velocitiesY.add(Double.parseDouble(sepData[5]));
                this.masses.add(Double.parseDouble(sepData[6]));
            }
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong...\n");
        }
    }

    /*____________________________________________GETTERY A SETTRY____________________________________________________*/

    /**
     * Getter, ktery vraci seznam entit vytvorenych z predanych dat.
     * @return seznam entit vytvorenych z predanych dat.
     */
    public ArrayList<Entity> getEntity(){
        ArrayList<Entity> entities = new ArrayList<Entity>(this.names.size());

        for (int i = 0; i < this.names.size(); i++) {
            entities.add(new Entity(this.names.get(i), this.types.get(i), this.positionsX.get(i),
                    this.positionsY.get(i), this.velocitiesX.get(i), this.velocitiesY.get(i),
                    this.masses.get(i)));
        }

        return entities;
    }

    /**
     * Getter, ktery vraci gravitacni konstantu z predanych dat.
     * @return gravitacni konstanta z predanych dat.
     */
    public double getGravitationalConstant() {
        return gravitationalConstant;
    }

    /**
     * Getter, ktery vraci animacni krok z predanych dat.
     * @return animacni krok z predanych dat.
     */
    public double getAnimationStep() {
        return animationStep;
    }
}
