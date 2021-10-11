package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

	private final static int NUMBER_OF_OBSTACLE = 20;
	
	private final static int MoveitemSpon = 5;

    private Createlife clife;
	private MoveItem moveitem[] = new MoveItem[MoveitemSpon];

	private Item item;
	
	private life life;

    private  int totallife = 3;

    private  int score;

    private static int total;
    /**
     * The space rocket with which player will have to land.
     */
    private PlayerRocket playerRocket;
    
    /**
     * Landing area on which rocket will have to land.
     */
    private LandingArea landingArea;
    
    /**
     * Game background image.
     */
    private BufferedImage backgroundImg;
    
    /**
     * Red border of the frame. It is used when player crash the rocket.
     */
    private BufferedImage redBorderImg;
    

    
    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {

        playerRocket = new PlayerRocket();
        landingArea  = new LandingArea();
        item = new Item();
        clife = new Createlife();
        life = new life();
        for(int i=0; i< MoveitemSpon; i++ )
        {
        	moveitem[i] = new MoveItem();
        }

    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
        try
        {
            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            URL redBorderImgUrl = this.getClass().getResource("/resources/images/red_border.png");
            redBorderImg = ImageIO.read(redBorderImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
    	
        playerRocket.ResetPlayer();
        item.Create();
        life.Create();
        clife.Create();
        for(int i=0; i< MoveitemSpon; i++ )
        {
        	moveitem[i] = new MoveItem();
        }
        playerRocket.ResetPlayer();
        item.Resetitem(this.item);
        clife.Resetlife(this.clife);
        item.Create();
        totallife= 3;

    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
        playerRocket.Update();
        
        Music Music1 = new Music("Fail.mp3", false);
        Music Music2 = new Music("success.MP3", false);
        // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
        // First we check bottom y coordinate of the rocket if is it near the landing area.
        if(playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y)
        {
            // Here we check if the rocket is over landing area.
            if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth))
            {
                // Here we check if the rocket speed isn't too high.
                if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                    playerRocket.landed = true;
                
                Music1.close();
                Music2.start();
                }
                else
                {    playerRocket.crashed = true;
                Music1.start();
                Music2.close();
                }
                }
            else
                playerRocket.crashed = true;
                
            Framework.gameState = Framework.GameState.GAMEOVER;
        }
        if(GetItem(playerRocket, item)) {
            item.fixedItem = true;

            score=10;

            total = score;
        }

        if(GetClife(playerRocket, clife)) {

            totallife++;
            clife.fixedlife = false;

           clife.eraser();
        }

      }
    public boolean GetItem(PlayerRocket rocket, Item item){
        boolean check = false;
        if (Math.abs((playerRocket.x + playerRocket.rocketImgWidth / 2) - (item.ix + item.ItemImgx / 2)) < (item.ItemImgx / 2 + playerRocket.rocketImgWidth / 2) &&
                Math.abs((playerRocket.y + rocket.rocketImgHeight / 2) - (item.iy + item.ItemImgy / 2)) < (item.ItemImgy / 2 + rocket.rocketImgHeight / 2))
            check = true;

        return check;
    }
    public boolean GetClife(PlayerRocket rocket, Createlife clife){
        boolean check = false;
        if (Math.abs((playerRocket.x + playerRocket.rocketImgWidth / 2) - (clife.lx + clife.lifeImgx / 2)) < (clife.lifeImgx / 2 + playerRocket.rocketImgWidth / 2) &&
                Math.abs((playerRocket.y + rocket.rocketImgHeight / 2) - (clife.ly + clife.lifeImgy / 2)) < (clife.ly / 2 + rocket.rocketImgHeight / 2))
            check = true;

        return check;
    }



    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    { 
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        landingArea.Draw(g2d);
        
        playerRocket.Draw(g2d);
        item.Draw(g2d);
        
        life.Draw(g2d);
        clife.Draw(g2d);
        g2d.drawString("x "+totallife , 460, 17);

    }
    
    
    /**
     * Draw the game over screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition Current mouse position.
     * @param gameTime Game time in nanoseconds.
     */
    public void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        Draw(g2d, mousePosition);
        
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        
        if(playerRocket.landed)
        {
            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in " + gameTime / Framework.secInNanosec + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
        }
        else
        {
            g2d.setColor(Color.red);
            g2d.drawString("You have crashed the rocket!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        }
    }
    

}
