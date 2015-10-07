/*
 * ClipboardMonitor.java
 *
 * Created on 11 februari 2008, 15:26
 *
 */

/**
 *
 * @author ORi
 */

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public final class ClipboardMonitor extends Observable implements ClipboardOwner {
    private static ClipboardMonitor monitor = null;

    private ClipboardMonitor() {
        gainOwnership();
    }

    private void gainOwnership() {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            Transferable content = clip.getContents(null);

            clip.setContents(content, this);

            setChanged();
            notifyObservers(content);
        } catch (IllegalArgumentException istateexception) {
            istateexception.printStackTrace();
        } catch (Exception ioexception) {
            ioexception.printStackTrace();
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Ownership lost ...");
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(200);
                    gainOwnership();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void flushClipboard() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), this);
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("There can be only one instance of this monitor!");
    }

    public static final ClipboardMonitor getMonitor() {
        if (monitor == null)
            monitor = new ClipboardMonitor();
        return (monitor);
    }

}
