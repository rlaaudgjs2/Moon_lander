package moon_lander;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Create a JPanel on which we will draw and listen for keyboard and mouse events.
 * 
 * @author www.gametutorial.net
 */

public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
    
    // Keyboard states - Here are stored states for keyboard keys - is it down or not.
    private static boolean[] keyboardState = new boolean[525];
    
    // Mouse states - Here are stored states for mouse keys - is it down or not.
    private static boolean[] mouseState = new boolean[3];

    private JButton Stage1 = new JButton("Stage1");
    private JButton Stage2 = new JButton("Stage2");
    private JButton Stage3 = new JButton("Stage3");
    private JButton Stage4 = new JButton("Stage4");
    private JButton Stage5 = new JButton("Stage5");
    private JButton information = new JButton("information");
    private JButton Exit = new JButton("Exit");
    public void addButtonListener() {
        Framework.mainMenuButtonArrayList.add(Stage1);
        Framework.mainMenuButtonArrayList.add(Stage2);
        Framework.mainMenuButtonArrayList.add(Stage3);
        Framework.mainMenuButtonArrayList.add(Stage4);
        Framework.mainMenuButtonArrayList.add(Stage5);
        Framework.mainMenuButtonArrayList.add(information);
        Framework.mainMenuButtonArrayList.add(Exit);
    }
    public void showMainButton(boolean show) {
        if (show) {
            this.add(Stage1);
            this.add(Stage2);
            this.add(Stage3);
            this.add(Stage4);
            this.add(Stage5);
            this.add(information);
            this.add(Exit);

            Stage1.setBounds(250, 400, 100, 50);
            Stage2.setBounds(250, 450,100, 50);
            Stage3.setBounds(250, 500, 100, 50);
            Stage4.setBounds(250, 550, 100, 50);
            Stage5.setBounds(250, 600, 100, 50);
            information.setBounds(250, 650, 100, 50);
            Exit.setBounds(250, 700, 100, 50);
        } else {
            this.remove(Stage1);
            this.remove(Stage2);
            this.remove(Stage3);
            this.remove(Stage4);
            this.remove(Stage5);
            this.remove(information);
            this.remove(Exit);
        }



    }

    public Canvas()
    {
        // We use double buffer to draw on the screen.
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.black);

        // If you will draw your own mouse cursor or if you just want that mouse cursor disapear,
        // insert "true" into if condition and mouse cursor will be removed.
        if(false)
        {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }

        // Adds the keyboard listener to JPanel to receive key events from this component.
        this.addKeyListener(this);
        // Adds the mouse listener to JPanel to receive mouse events from this component.
        this.addMouseListener(this);
    }


    // This method is overridden in Framework.java and is used for drawing to the screen.
    public abstract void Draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;        
        super.paintComponent(g2d);        
        Draw(g2d);
    }
       
    
    // Keyboard
    /**
     * Is keyboard key "key" down?
     * 
     * @param key Number of key for which you want to check the state.
     * @return true if the key is down, false if the key is not down.
     */
    public static boolean keyboardKeyState(int key)
    {
        return keyboardState[key];
    }
    
    // Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) 
    {keyboardState[e.getKeyCode()] = true;

    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    public abstract void keyReleasedFramework(KeyEvent e);
    
    
    // Mouse
    /**
     * Is mouse button "button" down?
     * Parameter "button" can be "MouseEvent.BUTTON1" - Indicates mouse button #1
     * or "MouseEvent.BUTTON2" - Indicates mouse button #2 ...
     * 
     * @param button Number of mouse button for which you want to check the state.
     * @return true if the button is down, false if the button is not down.
     */
    public static boolean mouseButtonState(int button)
    {
        return mouseState[button - 1];
    }
    
    // Sets mouse key status.
    private void mouseKeyStatus(MouseEvent e, boolean status)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
            mouseState[0] = status;
        else if(e.getButton() == MouseEvent.BUTTON2)
            mouseState[1] = status;
        else if(e.getButton() == MouseEvent.BUTTON3)
            mouseState[2] = status;
    }
    
    // Methods of the mouse listener.
    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseKeyStatus(e, true);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseKeyStatus(e, false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }
    
    @Override
    public void mouseEntered(MouseEvent e) { }
    
    @Override
    public void mouseExited(MouseEvent e) { }
    
}
