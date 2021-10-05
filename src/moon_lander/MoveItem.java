package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class MoveItem {
    
    private Random RandomItem;

    public int x;
    public int y;

    public boolean eat;
    
    private int speedx;
    
    public int speedy;

    private BufferedImage MoveItemImg;

        public int MoveItemImgWidth;
    
    public int MoveItemImgHeight;

    private boolean ItemD;

    private final int STARTING_POSITION = -100;

    private final int MAX_RANDOM_SPEED = 5;

    private final int SMALLEST_X_SPEED = 3;
    private final int SMALLEST_Y_SPEED = 4;

    MoveItem() {
        Initialize();
        LoadContent();
    }

    private void Initialize() {
       RandomItem = new Random();
        ResetMoveItem();


    }

    private void LoadContent() {
         try{
            java.net.URL MoveItemImgUrl = this.getClass().getResource("/resources/images/MoveItem.png");
            MoveItemImg = ImageIO.read(MoveItemImgUrl);
            MoveItemImgWidth = MoveItemImg.getWidth();
            MoveItemImgHeight = MoveItemImg.getHeight();
         }
            
        	
        catch (IOException ex) {
            Logger.getLogger(MoveItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetMoveItem() {
        eat = false;
        
		x = RandomItem.nextInt(Framework.frameWidth - MoveItemImgWidth);
        y = STARTING_POSITION;
        ItemD = RandomItem.nextBoolean();
        if (ItemD) {
            speedx = RandomItem.nextInt(MAX_RANDOM_SPEED) + SMALLEST_X_SPEED;
            speedy = RandomItem.nextInt(MAX_RANDOM_SPEED) + SMALLEST_Y_SPEED;
        } else {
            speedx = -RandomItem.nextInt(MAX_RANDOM_SPEED) - SMALLEST_X_SPEED;
            speedy = RandomItem.nextInt(MAX_RANDOM_SPEED) + SMALLEST_Y_SPEED;
        }
    }

    /**
     * Here we move the rocket.
     */
    public void Update() {
        if (eat) {
            speedx = 0;
            speedy = 0;
            ResetMoveItem();
        }
        x += speedx;
        y += speedy;
    }

    public void Draw(Graphics2D g2d) {
        g2d.drawImage(MoveItemImg, x, y, null);
    }

   
}