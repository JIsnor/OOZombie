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

		//these variables store the number of pixels per square,
		//and are used to calculate pixel offsets for drawing
		private int resolutionX;
		private int resolutionY;

		public GameBufferedImage(int width, int height, int imageType, int resolutionX, int resolutionY) {
			super(width, height, imageType);
			this.resolutionX = resolutionX;
			this.resolutionY = resolutionY;
		}

		/**
		 * Draws the icons (the human, zombies, fruit(s))
		 * @param representation What kind of Entity to draw
		 * @param coords coordinates of Entity
		 */
		public void drawCharacterImage(Representation representation, int[] coords) {
			
			Graphics2D g = this.createGraphics();
			BufferedImage img = null;
			
			try {
				switch(representation) {
				case ZOMBIE:
					img = ImageIO.read(new File("src/zombie.png"));
					break;
				case HUMAN:
					img = ImageIO.read(new File("src/beiber.jpg")); //you're welcome
					break;
				case FRUIT:
					img = ImageIO.read(new File("src/pear.png"));
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			g.drawImage(img, coords[0] * resolutionX, (coords[1]) * resolutionY, null);
		}

		/**
		 * Draws the squares that compose the board
		 * @param coords coordinates of the square : a size-2 array.
		 * @param traversable whether or not this tile is traversable
		 */
		public void drawSquare(int[] coords, boolean traversable){
			Graphics2D g2 = this.createGraphics();

			Color color = Color.BLACK;
			if(traversable){
				color = Color.WHITE;
			}
			
			g2.setPaint(color);
			g2.fillRect(coords[0] * resolutionX, coords[1] * resolutionY, resolutionX, resolutionY);
		}

		/**
		 * Displays end of the game image
		 * @param victory true or false if the game is over
		 */
		public void showEndImage(boolean victory) {
			String filename;
				if(victory){
				filename = "src/victory.jpg";
			}
			else{
				filename = "src/defeat.jpg";
			}
			
			Graphics2D g = this.createGraphics();
			BufferedImage image = null;

			try {
				image = ImageIO.read(new File(filename));
			} catch (IOException e) {}
			
			g.drawImage(image, 0, 0, null);
		}
	}
