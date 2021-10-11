package moon_lander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Createlife {
    private Random random;

    public BufferedImage fixedlifeImg;

    public int lx; // ������ ��ġ x��
    public int ly; // ������ ��ġ y��

    public int lifeImgx;
    public int lifeImgy;

    public boolean fixedlife;


    public Createlife() {
        Initialize();
        LoadContent();
        lx = (int) (Math.random() * 500);
        ly = (int) (Math.random() * 400);


    }


    private void Initialize() {
        // TODO Auto-generated method stub
        lx = (int) (Math.random() * 500);
        ly = (int) (Math.random() * 400);
    }


    public void Create() {
        fixedlife = false;
        lx = (int) (Math.random() * 500);
        ly = (int) (Math.random() * 400);


    }
    public void eraser() {
        fixedlife = true;
        lx = -100;
        ly = -100;


    }

    private void LoadContent() {
        try {
            URL fixedLifeImgUrl = this.getClass().getResource("/resources/images/Life.png");
            fixedlifeImg = ImageIO.read(fixedLifeImgUrl);
            lifeImgx = fixedlifeImg.getWidth();
            lifeImgy = fixedlifeImg.getHeight();


        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Resetlife(Createlife life) {
        fixedlife = false;

        this.lx = (int) (Math.random() * 500);
        this.ly = (int) (Math.random() * 400);


    }

    public void Draw(Graphics2D g2d) {
        if (fixedlife) {
            g2d.drawImage(null, lx, ly, null);
        } else {

            g2d.drawImage(fixedlifeImg, lx, ly, null);
        }
    }
}