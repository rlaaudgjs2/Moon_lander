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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Obstacle {
    private BufferedImage obstacle1Img;
    public int obstacle1ImgWidth;
    public int obstacle1ImgHeight;
    public int x;
    public int y;
    public boolean obstacle;

    public Obstacle() {
        this.Initialize();
        this.LoadContent();
    }

    private void Initialize() {
        this.x = (int) ((double) Framework.frameWidth * 0);
        this.y = (int) ((double) Framework.frameHeight * 0.70D);
    }

    private void LoadContent() {
        try {
            URL obstacle1ImgUrl = this.getClass().getResource("/resources/images/obstacle.png");
            this.obstacle1Img = ImageIO.read(obstacle1ImgUrl);
            this.obstacle1ImgWidth = this.obstacle1Img.getWidth();
            this.obstacle1ImgHeight = this.obstacle1Img.getHeight();
        } catch (IOException var2) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, (String) null, var2);
        }
    }
    public void Resetobs(Obstacle obstacle)
    {
        this.x = (int) ((double) Framework.frameWidth * 0);
        this.y = (int) ((double) Framework.frameHeight * 0.70D);

    }

    public void Create() {
        obstacle = false;
        this.x = (int) ((double) Framework.frameWidth * 0);
        this.y = (int) ((double) Framework.frameHeight * 0.70D);
    }


    public void Draw(Graphics2D g2d) {
        if (obstacle) {
            g2d.drawImage(null, x, y,  null);

        }
        else
        {
            g2d.drawImage(this.obstacle1Img, this.x, this.y, null);

        }
    }
}