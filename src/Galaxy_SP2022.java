import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hlavni spousteci trida.
 * @author ncalt
 */
public class Galaxy_SP2022 {

    /*___________________________________________________ATRIBUTY_____________________________________________________*/

    /** Atribut urcujici zda je animace pozastavena ci ne. */
    private static boolean pause = false;

    /*_________________________________________________KONSTRUKTOR____________________________________________________*/

    /**
     * Spousteci metoda.
     * @param args Nazev souboru predany pri spusteni.
     */
    public static void main(String[] args) {

        JFrame okno = new JFrame();
        okno.setTitle("Semestralni prace - Simulace galaxie		Nikol Caltova, A21B0091P");
        okno.setSize(800, 600);

        DrawingPanel panel = new DrawingPanel(args[0]);
        okno.add(panel);
        okno.pack();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();

        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            //Pozastaveni animace, ci zruseni jejiho pozastaveni.
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                    pause = !pause;
                }
            }
        });

        java.util.Timer tm = new Timer();
        tm.schedule(new TimerTask() {
                        double  time = System.currentTimeMillis();
                        double currTime = 0;
                        @Override
                        public void run() {
                            currTime = System.currentTimeMillis() - time;
                            if (!pause) {
                                panel.updateSystem(currTime);
                            }
                            panel.repaint();
                            time = System.currentTimeMillis();
                        }
                    }, 0,
                30
        );
    }
}
