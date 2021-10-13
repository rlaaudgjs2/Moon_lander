//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class obstacles {
    private Random randomNumber;
    public int x;
    public int y;
    public boolean crashed;
    private int speedx;
    public int speedy;
    private BufferedImage obstacleImg;
    private BufferedImage obstacleExplosionImg;
    private BufferedImage obstacleFireImg;
    public int obstacleImgWidth;
    public int obstacleImgHeight;
    private boolean obstacleFlyDirection;
    private final int STARTING_POSITION = -110;
    private final int MAX_RANDOM_SPEED = 5;
    private final int SMALLEST_X_SPEED = 3;
    private final int SMALLEST_Y_SPEED = 4;
    private int speedAccelerating;
    private int speedStopping;

    obstacles() {
        this.Initialize();
        this.LoadContent();
    }

    private void Initialize() {
        this.randomNumber = new Random();
        this.resetObstacles();
        this.speedAccelerating = 1;
        this.speedStopping = 1;
    }

    private void LoadContent() {
        try {
            URL obstacleImgUrl = this.getClass().getResource("/resources/images/obstacles.png");
            this.obstacleImg = ImageIO.read(obstacleImgUrl);
            this.obstacleImgWidth = this.obstacleImg.getWidth();
            this.obstacleImgHeight = this.obstacleImg.getHeight();

        } catch (IOException var4) {
            Logger.getLogger(obstacles.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public void resetObstacles() {
        this.crashed = false;
        this.x = this.randomNumber.nextInt(Framework.frameWidth - this.obstacleImgWidth);
        this.y = -110;
        this.obstacleFlyDirection = this.randomNumber.nextBoolean();
        if (this.obstacleFlyDirection) {
            this.speedx = this.randomNumber.nextInt(5) + 3;
            this.speedy = this.randomNumber.nextInt(5) + 4;
        } else {
            this.speedx = -this.randomNumber.nextInt(5) - 3;
            this.speedy = this.randomNumber.nextInt(5) + 4;
        }

    }

    public void Update() {
        if (this.crashed) {
            this.speedx = 0;
            this.speedy = 0;
            this.resetObstacles();
        }

        this.x += this.speedx;
        this.y += this.speedy;
    }

    public void DrawobstacleCrash(Graphics2D g2d) {
        g2d.drawImage(this.obstacleExplosionImg, this.x, this.y - 50, (ImageObserver)null);
    }

    public void Draw(Graphics2D g2d) {
        g2d.drawImage(this.obstacleImg, this.x, this.y, (ImageObserver)null);
        if (Canvas.keyboardKeyState(87)) {
            g2d.drawImage(this.obstacleFireImg, this.x + 12, this.y + 66, (ImageObserver)null);
        }

    }
}
