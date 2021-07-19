/*
Esto es el splash configurado en segundos
 */
package principal;

import com.sun.awt.AWTUtilities;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Alex
 */
public class Splash extends JFrame{
    
    ImageIcon Arranque = new ImageIcon(getClass().getResource("/imagenes/load.gif"));
    JLabel Cargando = new JLabel(Arranque);
    
     public Splash() {
        configInicial();
        agregarEtiqueta();
        agregarOyentes();
        this.setVisible(true);
        // matar al gif de carga
        matarSplash();
    } 

    private void configInicial() {
        this.setSize(Arranque.getIconWidth(), Arranque.getIconHeight());
        this.setLocationRelativeTo(this);
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        this.setAlwaysOnTop(true);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/Java.png")).getImage());//añadir icono

    }

    private void agregarEtiqueta() {
        this.add(Cargando);
    }

    private void agregarOyentes() {
        
    }
    private void matarSplash() {
      try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose(); // liberando memoria (cierra el Splash o mata al splash pero no a la app
        //inicio s = new inicio();
    }
}