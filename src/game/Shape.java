package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Write a description of class Shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Shape
{
     /**
     * x-Koordinate des Mittelpunkts
     */
    protected int x;

    /**
     * y-Koordinate des Mittelpunkts
     */
    protected int y;

    /**
     * "Geschwindigkeit" in x-Richtung
     */    
    protected int vx;

    /**
     * "Geschwindigkeit in y-Richtung
     */
    protected int vy;
    
    
    protected BufferedImage image;
    protected BufferedImage rotatedImage;
    
    /**
     * Constructor for objects of class Shape
     * @param x
     * @param y
     * @param vx
     * @param vy
     * @param fileName
     */
    public Shape(int x, int y, int vx, int vy, String fileName) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    
        /**
     * Diese Methode bewirkt das Verschieben des Balles in x- und y-Richtung.
     *
     */
    public void move() {
        x = x + vx;
        y = y + vy;
    }

    public abstract void paint(Graphics2D g2d);
}