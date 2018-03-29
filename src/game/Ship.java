package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Thomas
 */

// AKA Graphic
public class Ship extends Shape {
    
    private boolean pressedW;
    private boolean pressedA;
    private boolean pressedS;
    private boolean pressedD;
    
    private boolean mousePressed;
    
    private ShotA shotA;
    
    private int vDiag;
    
    private int vmax;
    
    ArrayList<ShotA> al;
    
    /**
     * Constructor for objects of class Graphic
     * @param x
     * @param y
     * @param vmax
     * @param fileName
     */
    public Ship(int x, int y, int vmax, String fileName) {
        super(x, y);
        this.vmax = vmax;
        
        try {
            image = (BufferedImage) ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        calcVDiag();
    }
    
    private void calcVDiag(){
        double temp = Math.sqrt(Math.pow(vmax, 2) / 2);
        vDiag = (int) temp;
        System.out.println(temp);
        System.out.println("rounded for game: " + vDiag);
    }

    @Override
    public void paint(Graphics2D g2d) {
        calcAlpha();
        g2d.drawImage(rotateImage(image, alpha), x - image.getWidth() / 2, y - image.getHeight() / 2, null);
    }

    public void setPressedW(boolean pressedW) {
        this.pressedW = pressedW;
    }

    public void setPressedA(boolean pressedA) {
        this.pressedA = pressedA;
    }

    public void setPressedS(boolean pressedS) {
        this.pressedS = pressedS;
    }

    public void setPressedD(boolean pressedD) {
        this.pressedD = pressedD;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    public ShotA getShotA(){
        return shotA;
    }
    
    
    /** 
     * Fuer korrekte Diagonal-Bewegung mit Vorraussetzung vx = vy
     * vx_neu = sqr[(vx_alt^2) / 2]  -> s.o.
     * Diese Art der Steuerung hat den "Nachteil", dass eine Richtung pro Achse die andere dominiert.
     * D.h. wenn z.B. moveUp und moveDown angesprochen werden, bewegt das Schiff sich immer nach oben,
     * unabhaengig von der Reihenfolge in der die entsprechenden Tasten gedrueckt wurden
     */
    
    public void moveShip(){
        if(pressedW){
            if(pressedA){
                moveShipUL();
            } else if(pressedD){
                moveShipUR();
            } else {
                moveShipU();
            }
        } else if(pressedS){
            if(pressedA){
                moveShipDL();
            } else if(pressedD){
                moveShipDR();
            } else {
                moveShipD();
            } 
        } else if(pressedA){
            moveShipL();
        } else if(pressedD){
            moveShipR();
        } else {
            moveShipS();
        }
    }
    
    // move UpLeft
    public void moveShipUL(){
        vx = - vDiag;
        vy = - vDiag;
    }
    
    // move UpRight
    public void moveShipUR(){
        vx = vDiag;
        vy = - vDiag;
    }
    
    // move Up
    public void moveShipU(){
        vx = 0;
        vy = - vmax;
    }
    
    // move DownLeft
    public void moveShipDL(){
        vx = - vDiag;
        vy = vDiag;
    }
    
    // move DownRight
    public void moveShipDR(){
        vx = vDiag;
        vy = vDiag;
    }
    
    // move Down
    public void moveShipD(){
        vx = 0;
        vy = vmax;
    }
    
    // move Left
    public void moveShipL(){
        vx = - vmax;
        vy = 0;
    }
    
    // move Right
    public void moveShipR(){
        vx = vmax;
        vy = 0;
    }
    
    // Stop
    public void moveShipS(){
        vx = 0;
        vy = 0;
    }
    
    
    
    public boolean shoot(){
        if(mousePressed){
            shotA = new ShotA(x, y);
            shotA.setMouseX(mouseX);
            shotA.setMouseY(mouseY);
            shotA.velocity();
            return true;
        } else {
            return false;
        }
    }
    
}
