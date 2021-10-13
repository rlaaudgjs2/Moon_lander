package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.cloud.firestore.Firestore;

import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

    Firestore firebaseFirestore;
    private obstracle obstacle1;
    private static final int NUMBER_OF_OBSTACLE = 10;
    private obstacles obstacle[]=new obstacles[10];

	private final static int MoveitemSpon = 5;

    private Createlife clife;
	private MoveItem moveitem[] = new MoveItem[MoveitemSpon];

	private Item item;

	private life life;

    private  int totallife = 3;

    private  int score;

    private int sum =0;
    private  int total =0;
    /**
     * The space rocket with which player will have to land.
     */
    private PlayerRocket playerRocket;
    private  int stagelevel;
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



    public Game(int level)
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        stagelevel = level;
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
        long gameTime = 0L;
        this.playerRocket = new PlayerRocket();
        this.landingArea = new LandingArea();

        switch (stagelevel) {
            case 1:

                if (gameTime / 1000000000L < 5L) {
                    this.obstacle1 = new obstracle();
                }
                stagelevel = 5;
                for(int i = 0; i < stagelevel; ++i) {
                    this.obstacle[i] = new obstacles();
                }
                for(int i = 0; i < stagelevel; ++i) {
                    item = new Item();
                }
                for(int i = 0; i < stagelevel; ++i) {
                    clife = new Createlife();
                }
                break;
            case 2:
                if (gameTime / 1000000000L < 5L) {
                    this.obstacle1 = new obstracle();
                }
                stagelevel = 7;
                for(int i = 0; i < stagelevel ; ++i) {
                    this.obstacle[i] = new obstacles();
                }
                for(int i = 0; i <stagelevel ;++i) {
                    item = new Item();
                }
                for(int i = 0; i <stagelevel; ++i) {
                    clife = new Createlife();
                }
                break;
            case 3:
                if (gameTime / 1000000000L < 5L) {
                    this.obstacle1 = new obstracle();
                }
                stagelevel =9;
                for(int i = 0; i < stagelevel; ++i) {
                    this.obstacle[i] = new obstacles();
                }
                for(int i = 0; i < stagelevel ;++i) {
                    item = new Item();
                }
                for(int i = 0; i < stagelevel-2; ++i) {
                    clife = new Createlife();
                }
                break;
            case 4:
                stagelevel =11;
                if (gameTime / 1000000000L < 5L) {
                    for (int i = 0; i < stagelevel; ++i) {
                        this.obstacle[i] = new obstacles();
                    }
                    for (int i = 0; i < stagelevel; ++i) {
                        item = new Item();
                    }
                    for (int i = 0; i < stagelevel - 3; ++i) {
                        clife = new Createlife();
                    }
                }
                break;
            case 5:
                if (gameTime / 1000000000L < 5L) {
                    stagelevel = 13;
                    for (int i = 0; i < stagelevel; ++i) {
                        this.obstacle[i] = new obstacles();
                    }
                    for (int i = 0; i < stagelevel; ++i) {
                        item = new Item();
                    }
                    for (int i = 0; i < stagelevel - 4; ++i) {
                        clife = new Createlife();
                    }
                }
                break;
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
        for(int i = 0; i < 10; ++i) {
            this.obstacle[i].resetObstacles();
        }
        for(int i=0; i< MoveitemSpon; i++ )
        {
        	moveitem[i] = new MoveItem();
        }
        playerRocket.ResetPlayer();
        item.Resetitem(this.item);
        clife.Resetlife(this.clife);
        item.Create();
        totallife= 3;
        //add obstales
        for (int i = 0; i < NUMBER_OF_OBSTACLE; i++)
            obstacle[i].resetObstacles();
    }


    /**
     * Update game logic.
     *
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition) {
        // Move the rocket
        this.playerRocket.Update();

        if(!(stagelevel==0)){
        for(int i = 0; i < 10; ++i) {
            this.obstacle[i].Update();
            if (this.playerRocket.x < this.obstacle[i].x + this.obstacle[i].obstacleImgWidth && this.playerRocket.x + this.playerRocket.rocketImgWidth > this.obstacle[i].x && this.playerRocket.y < this.obstacle[i].y + this.obstacle[i].obstacleImgHeight && this.playerRocket.rocketImgHeight + this.playerRocket.y > this.obstacle[i].y) {

                this.obstacle[i].resetObstacles();
                if (totallife > 1) {
                    totallife--;
                    this.obstacle[i].crashed = true;
                } else if (totallife == 1) {
                    totallife--;
                    this.playerRocket.crashed = true;
                    Framework.gameState = Framework.GameState.GAMEOVER;
                }
            }

            if (this.obstacle[i].y + this.obstacle[i].obstacleImgHeight - 10 > this.landingArea.y) {
                this.obstacle[i].crashed = true;
            }

            if (this.playerRocket.y + this.playerRocket.rocketImgHeight < 0 || this.playerRocket.x + this.playerRocket.rocketImgWidth < Framework.frameWidth * 0 || this.playerRocket.x > Framework.frameWidth * 1) {
                this.playerRocket.wentout = true;
                Framework.gameState = Framework.GameState.GAMEOVER;
            }

            if (gameTime / 1000000000L < 5L && this.playerRocket.x < this.obstacle1.x + this.obstacle1.obstacle1ImgWidth && this.playerRocket.x + this.playerRocket.rocketImgWidth > this.obstacle1.x && this.playerRocket.y < this.obstacle1.y + this.obstacle1.obstacle1ImgHeight && this.playerRocket.rocketImgHeight + this.playerRocket.y > this.obstacle1.y) {
                this.playerRocket.crashed = true;
                Framework.gameState = Framework.GameState.GAMEOVER;
            }


            Music Music1 = new Music("Fail.mp3", false);
            Music Music2 = new Music("success.MP3", false);
            // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
            // First we check bottom y coordinate of the rocket if is it near the landing area.
            if (playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y) {
                // Here we check if the rocket is over landing area.
                if ((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth)) {
                    // Here we check if the rocket speed isn't too high.
                    if (playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;

                        Music1.close();
                        Music2.start();
                    } else {
                        playerRocket.crashed = true;
                        Music1.start();
                        Music2.close();
                    }
                } else
                    playerRocket.crashed = true;

                Framework.gameState = Framework.GameState.GAMEOVER;
            }
        }
    }
        if(GetItem(playerRocket, item)) {
            item.fixedItem = true;

//       total = (100/(int)(gameTime / Framework.secInNanosec));

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
        //add obstacle
        obstacle1.Draw(g2d);
        //add moving obstacle
        for (int i = 0; i < NUMBER_OF_OBSTACLE; i++) {
            obstacle[i].Draw(g2d);
            if (obstacle[i].crashed) {
                obstacle[i].DrawobstacleCrash(g2d);
            }
        }
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
        g2d.drawString("Press E to Mainmenu.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 80);
        if(playerRocket.landed)
        {
//            firebaseFirestore.collection("Login").document(id).set(score);

            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in " + gameTime / Framework.secInNanosec + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
            g2d.drawString("Get score: " + total + " Points.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 30);
        }
        else if(playerRocket.crashed)
        {
            g2d.setColor(Color.red);
            g2d.drawString("You have crashed the rocket!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        }

        else
        {
            g2d.setColor(Color.yellow);
            g2d.drawString("Your rocket rocket went out of gravity!", Framework.frameWidth/2-110, Framework.frameHeight / 3);
        }
    }


}
