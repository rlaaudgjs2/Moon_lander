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

public class Item {
	private Random random;

	public BufferedImage fixedItemImg;

	public int ix; // ������ ��ġ x��
	public int iy; // ������ ��ġ y��

	public int ItemImgx;
	public int ItemImgy;
	
	public boolean fixedItem;
	
	
	
	
	public Item() {
		Initialize();
		LoadContent();
		ix = (int) (Math.random() * 300);
		iy = (int) (Math.random() * 300)+100;

	}
	
		
	

	private void Initialize() {
		// TODO Auto-generated method stub
		ix = (int) (Math.random() * 300);
		iy = (int) (Math.random() * 300)+100;
	}

	

	
	public void Create() {
		fixedItem = false;
		ix = (int) (Math.random() * 300);
		iy = (int) (Math.random() * 300)+100;

	
	}

	private void LoadContent() {
		try {
			URL fixedItemImgUrl = this.getClass().getResource("/resources/images/Item.png");
			fixedItemImg = ImageIO.read(fixedItemImgUrl);
			ItemImgx = fixedItemImg.getWidth();
			ItemImgy = fixedItemImg.getHeight();
            
           
		}
		 catch (IOException ex) {
	            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
		 		}
		
	}
		public void Resetitem(Item item)
	    {
			fixedItem = false;

			ix = (int) (Math.random() * 300);
			iy = (int) (Math.random() * 300)+100;


			
			
	        
	    }
//	public void ItemUpdate()
//	{
//		if(ix == playerRocket.rocketImgWidth && iy == playerRocket.rocketImgHeight)
//		{
//			fixedItem = true;
//			
//		}
//		else
//		{
//			fixedItem = false;
//		}
//		   
//		}
public void DrawItemReset(Graphics2D g2d) {
	g2d.drawImage(fixedItemImg, ix, iy, null);
}
	public void Draw(Graphics2D g2d)
	{
		if(fixedItem)
		{
			g2d.drawImage(null,ix,iy,null);
		}
		else
		{
			
			g2d.drawImage(fixedItemImg, ix, iy, null);
		}
//		if(moveItem)
//		{
//			g2Item.drawImage(null, movex,movey, null);
//		}
//		else
//		{
//			if(Canvas.keyboardKeyState(KeyEvent.VK_W))
//			g2Item.drawImage(moveItemImg, movex, movey, null);
//		}
	}
}
