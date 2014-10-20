//This class serves as the game canvas.
//It is used as a class variable of GameDriver.

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

	class GameBufferedImage extends BufferedImage {

		private int resolutionX;
		private int resolutionY;

		public GameBufferedImage(int width, int height, int imageType, int resolutionX, int resolutionY) {
			super(width, height, imageType);
			this.resolutionX = resolutionX;
			this.resolutionY = resolutionY;
		}

		public void drawCharacter(char representation, int[] coords) {
			Graphics2D g2 = this.createGraphics();

			Color color = Color.BLACK;

			g2.setPaint(color);
			g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, resolutionY));

			g2.drawString("" + representation, coords[0] * resolutionX, (coords[1] + 1) * resolutionY);
		}
		
		public void drawCharacterImage(char representation, int[] coords) {
			
			Graphics2D g = this.createGraphics();
			
			BufferedImage img = null;
			try {
				switch(representation) {
				case 'Z':
				case 'z':
					img = ImageIO.read(new File("src/zombie.png"));
					break;
				case 'H':
				case 'h':
					img = ImageIO.read(new File("src/beiber.jpg")); //you're welcome
					break;
				case 'F':
				case 'f':
					img = ImageIO.read(new File("src/pear.png"));
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			g.drawImage(img, coords[0] * resolutionX, (coords[1]) * resolutionY, null);
		}
		
		public void drawSquare(int[] coords, boolean traversable){
			Graphics2D g2 = this.createGraphics();

			Color color = Color.RED;
			if(traversable){
				color = Color.GREEN;
			}
			
			g2.setPaint(color);
			
			g2.fillRect(coords[0] * resolutionX, coords[1] * resolutionY, resolutionX, resolutionY);
		}
	}
