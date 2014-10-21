import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 640;
	private final int HEIGHT = 640;

	private int dimensionX;
	private int dimensionY;

	private GameBufferedImage buffImage;
	private JLabel textLabel;
	
	private int keyInput = 0;

	public GameFrame(int dimensionX, int dimensionY) {
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		addComponentsToPane();
		pack();
		setVisible(true);
	}

	private void addComponentsToPane() {
		buffImage = new GameBufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB, WIDTH / dimensionX, HEIGHT
						/ dimensionY);

		ImageIcon icon = new ImageIcon(buffImage);
		JLabel iconLabel = new JLabel(icon);
		textLabel = new JLabel("garbage");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, 1));
		panel.add(iconLabel);
		panel.add(textLabel);
		panel.setVisible(true);
		add(panel);
	}

	public void setText(int health, int numPearsCollected, int numPearsTotal) {

		textLabel.setText("You have " + health
				+ " health remaining. You have " + "collected "
				+ numPearsCollected + " out of " + numPearsTotal + " pears.");
	}

	public void addCharacterImage(Representation representation, int[] coords) {
		buffImage.drawCharacterImage(representation, coords);
	}

	public void addSquare(int[] coords, boolean traversable) {
		buffImage.drawSquare(coords, traversable);
	}

	public void showEndScreen(boolean victory){
		buffImage.showEndImage(victory);
		repaint();
	}
	
	// returns key and resets the stored key value
	public int getKey() {
		int keyTemp = keyInput;
		keyInput = -1;
		return keyTemp;
	}

	/* Unused method from KeyListener interface */
	public void keyTyped(KeyEvent e) {
	}

	/* Unused method from KeyListener interface */
	public void keyReleased(KeyEvent e) {
	}

	/* event listener from KeyListener interface */
	public void keyPressed(KeyEvent e) {
		// this statement prevents another key from being read until the
		// previous keyInput is processed
		if (keyInput == -1) {
			int keyCode = e.getKeyCode();
			// if the key is an arrow key...
			if (keyCode >= 37 && keyCode <= 40) {
				keyInput = keyCode;
			}
		}
	}
}
