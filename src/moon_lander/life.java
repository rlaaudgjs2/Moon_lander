package moon_lander;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class life {
	
	private BufferedImage LifeImg;

	private int lx;
	private int ly;

	private int LifeImgx;
	private int LifeImgy;
	private boolean heart;
	
	public life() {
		Initialize();
		LoadContent(); 
	}

	

	private void Initialize() {
		// TODO Auto-generated method stub
		
		lx = 430;
		ly = 2;
		
	}
	private void LoadContent() {
		// TODO Auto-generated method stub
		try {
			URL LifeImgUrl = this.getClass().getResource("/resources/images/Life.png");
			LifeImg = ImageIO.read(LifeImgUrl);
			LifeImgx = LifeImg.getWidth();
			LifeImgy = LifeImg.getHeight();

            
           
		}
		 catch (IOException ex) {
	            Logger.getLogger(life.class.getName()).log(Level.SEVERE, null, ex);
		 		}
		
		
	}
	public void Create() {
		
		heart = false;
		
		lx = 430;
		ly = 2;
		
	}
	public void ResetLife(life life)
    {
//		LifeImg = false;
				
		 lx = 430;
	     ly = 2;
        
    }
	public void Draw(Graphics2D g2d)
	{
		if(heart)
		{
			g2d.drawImage(null,lx,ly,null);
		}
		else
		{
			
			g2d.drawImage(LifeImg, lx, ly, null);
			
			}

}
}
