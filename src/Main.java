/*
 * Main.java
 *
 * Created on 2 februari 2007, 17:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;

/**
 *
 * @author ORi
 */
public class Main {

    /** Creates a new instance of Main */
    public Main() {
    }

    /**
     * @param args the command line arguments
     */
    private static Logger theLogger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Interface frame = new Interface();
            FileHandler fh = new FileHandler("TravCalcByORi.log", true); /* true means append */
            fh.setFormatter(new SimpleFormatter());
            theLogger.addHandler(fh);
            //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        } catch(Exception ex){
            theLogger.log(Level.SEVERE, "Can't start frame " + ex.toString());


        }

    }

}
