/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import klase.Instruktor;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Goca
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Instruktor ulogovani;

    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Instruktor ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Instruktor getUlogovani() {
        return ulogovani;
    }

}
