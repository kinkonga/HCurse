package hcurse.game;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import hcurse.console.PixelCanvas;

public class InputHandler implements KeyListener {
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	public InputHandler(PixelCanvas game) {
		game.addKeyListener(this);
	}
	
	public class Key{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public int getNumTimesPressed() {
			return numTimesPressed;
		}
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if(isPressed) numTimesPressed++;
		}
		public boolean isPressed() {
			return pressed;
		}	
		
	}
	
	
	public ArrayList<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(),true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(),false);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if(keyCode == KeyEvent.VK_Z || keyCode == KeyEvent.VK_UP) { 
			up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) { 
			down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_LEFT) { 
			left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) { 
			right.toggle(isPressed);
		}
	}
	
	
}
