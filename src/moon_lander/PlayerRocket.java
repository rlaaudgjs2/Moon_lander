package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * The space rocket with which player will have to land.
 * 
 * @author www.gametutorial.net
 */

public class PlayerRocket {
    public int x;
    public int y;
    public boolean landed;
    public boolean crashed;
    public boolean wentout;
    private int speedAccelerating;
    private int speedStopping;
    public int topLandingSpeed;
    private int speedX;
    public int speedY;
    private BufferedImage rocketImg;
    private BufferedImage rocketLandedImg;
    private BufferedImage rocketCrashedImg;
    private BufferedImage rocketFireImg;
    public int rocketImgWidth;
    public int rocketImgHeight;



    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        // Now that we have rocketImgWidth we set starting x coordinate.
        this.x = (int)((double)Framework.frameWidth * 0.46D);
    }
    
    
    private void Initialize()
    {
        this.ResetPlayer();
        this.speedAccelerating = 2;
        this.speedStopping = 1;
        this.topLandingSpeed = 5;
    }
    
    private void LoadContent()
    {
        try {
            URL rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
            this.rocketImg = ImageIO.read(rocketImgUrl);
            this.rocketImgWidth = this.rocketImg.getWidth();
            this.rocketImgHeight = this.rocketImg.getHeight();
            URL rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
            this.rocketLandedImg = ImageIO.read(rocketLandedImgUrl);
            URL rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
            this.rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);
            URL rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
            this.rocketFireImg = ImageIO.read(rocketFireImgUrl);
        } catch (IOException var5) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, (String)null, var5);
        }
    }
    
    /**
     * Here we set up the rocket when we starting a new game.
     */
    public void ResetPlayer()
    {
        this.landed = false;
        this.crashed = false;
        this.x = (int)((double)Framework.frameWidth * 0.46D);
        this.y = 200;
        this.speedX = 0;
        this.speedY = 0;
    }
    
    
    /**
     * Here we move the rocket.
     */

    public void Update() {
        Music Music = new Music("Fly.mp3", false);

        if (Canvas.keyboardKeyState(87)) {
            Music.start();
            this.speedY -= this.speedAccelerating;
        } else {
            this.speedY += this.speedStopping;
            Music.close();
        }

        if (Canvas.keyboardKeyState(65)) {
            this.speedX -= this.speedAccelerating;
        } else if (this.speedX < 0) {
            this.speedX += this.speedStopping;
        }

        if (Canvas.keyboardKeyState(68)) {
            this.speedX += this.speedAccelerating;
        } else if (this.speedX > 0) {
            this.speedX -= this.speedStopping;
        }

        this.x += this.speedX;
        this.y += this.speedY;
    }

    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);

        // If the rocket is landed.
        if(landed)
        {

            g2d.drawImage(rocketLandedImg, x, y, null);
        }
        // If the rocket is crashed.
        else if(crashed)
        {

            g2d.drawImage(rocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
        }
        // If the rocket is still in the space.
        else
        {
            // If player hold down a W key we draw rocket fire.
            if(Canvas.keyboardKeyState(KeyEvent.VK_W))
            g2d.drawImage(rocketFireImg, x + 12, y + 66, null);
            g2d.drawImage(rocketImg, x, y, null);

        }
    }
    
}
