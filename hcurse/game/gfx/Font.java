package hcurse.game.gfx;

public class Font {
	
	private static String chars=""+
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+
			"abcdefghijklmnopqrstuvwxyz      "+
			"0123456789.,:;'\"!?              ";
	
	public static void render(String msg, Screen screen,int x,int y,int colour) {
		for(int i=0;i<msg.length();i++) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if(charIndex >= 0) screen.render(x+(i*8), y, charIndex+29*32, colour);
		}
	}
}
