import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Scenario extends JFrame implements KeyListener {

	JLabel characterOne, square;
	int speed = 15;
	boolean direction = true;

	public void config() {
		setSize(1024, 600);
		setTitle("Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		moveSquare();
		collide();
		initScreen();
		setVisible(true);
	}

	public void initScreen() {
		characterOne = new JLabel();
		characterOne.setBackground(Color.ORANGE);
		characterOne.setOpaque(true);
		characterOne.setSize(40, 150);
		characterOne.setLocation(50, 200);
		this.add(characterOne);
		addKeyListener(this);

		square = new JLabel();
		square.setSize(25, 25);
		square.setOpaque(true);
		square.setBackground(Color.RED);
		square.setLocation(200, 240);

		add(square);

	}

	public void pause(long speed) {
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	public void collide() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					pause(25);
					if (characterOne.getX() + characterOne.getWidth() > square.getX()) {
						// d square.getY() + square.getHeight()
						if (characterOne.getY() <= square.getY() && square.getY()
								+ square.getHeight() <= characterOne.getY() + characterOne.getHeight()) {
						
						direction = !(direction);
						JOptionPane.showMessageDialog(null, "collided");
						}
					}
				}

			}
		}).start();
	}

	public void moveSquare() {
		int speed = 5;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					pause(25);
					if (square.getX() + square.getWidth() > getWidth()) {
						direction = false;

					}
					if (square.getX() < 0) {
						direction = true;

					}
					if (direction) {
						square.setLocation(square.getX() + speed, square.getY());

					} else {
						square.setLocation(square.getX() - speed, square.getY());

					}

				}
			}
		}).start();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// w = 87
		// s = 83
		// ^ = 38
		// v = 40

		if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
			if (characterOne.getY() <= 0) {
				characterOne.setLocation(characterOne.getX(), 0);

			} else {
				characterOne.setLocation(characterOne.getX(), characterOne.getY() - speed);
			}
		} else if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {

			if (!((characterOne.getY() + characterOne.getHeight()) > getHeight())) {

				characterOne.setLocation(characterOne.getX(), characterOne.getY() + speed);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
