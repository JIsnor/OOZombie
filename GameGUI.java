import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;

public class GameGUI extends JFrame implements KeyListener{

	private final int WIDTH = 640;
	private final int HEIGHT = 640;

	//resolution of our game
	private final int DIMX = 20;
	private final int DIMY = 20;
	
	MyBufferedImage buffImage;
	private int keyInput = 0;
	
	public GameGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		addKeyListener(this);
      		addComponentsToPane();
      		pack();
      		setVisible(true);
    }
    
    private void addComponentsToPane() {
        buffImage = new MyBufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        ImageIcon icon = new ImageIcon(buffImage);
        JLabel label = new JLabel(icon);
        add(label);
    }

    public int getKey(){
    	int keyHolder = keyInput;
    	keyInput = -1;
    	return keyHolder;
    }
    
    public void addCharacter(char representation, int[] coords){
    	buffImage.drawCharacter(representation, coords);
    }
    
    /* Unused method from KeyListener interface */
    public void keyTyped(KeyEvent e) {}

    /* Unused method from KeyListener interface */
    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
    	if(keyCode >= 37 && keyCode <= 40){
        	keyInput = keyCode;
        }
    }
    
    //MyBufferedImage------------------------------------------------------------------------//
    
    class MyBufferedImage extends BufferedImage{
    	
		public MyBufferedImage(int width, int height, int imageType) {
			super(width, height, imageType);
		}

		public void drawCharacter(char representation, int[] coords){
			Graphics2D g2 = this.createGraphics();
			
			Color color = Color.WHITE;
			int dimensionX = WIDTH / DIMX;
			int dimensionY = HEIGHT / DIMY;
			
			g2.setPaint(color);
			g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, dimensionY));
			
			g2.drawString("" + representation, coords[0] * dimensionX, coords[1] * dimensionY);
    	}
    }
    
    public static void main(String[] args){
    	GameGUI myGUI = new GameGUI();
    	int[] coords1 = {1, 1};
//    	int[] coords2 = {0, 0};
    	int[] coords3 = {0, 1};
    	
    	
    	myGUI.addCharacter('Z', coords1);
//    	myGUI.addCharacter('Z', coords2);
    	myGUI.addCharacter('Z', coords3);
    }
    
}
