package hcurse.game.level.tiles;

import hcurse.game.gfx.Screen;
import hcurse.game.level.Level;

public class BasicTile extends Tile {

	protected int tileID;
	protected int tileColour;
	
	public BasicTile(int id, int x, int y, int tileColour) {
		super(id, false, false);
		this.tileID = x + y;
		this.tileColour = tileColour;
	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileID, tileColour);
		
	}

}
