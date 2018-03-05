package hcurse.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class KinConsole extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final char TOP_C = '0';
	public static final char LEFT_C = '1';
	public static final char RIGHT_C = '2';
	
	public JTextPane topConsole;
	public JTextPane rightConsole;
	public JTextPane leftConsole;
	
	public StyledDocument topDocument;
	public StyledDocument leftDocument;
	public StyledDocument rightDocument;
	
	public JTextField input;

	Font font = new Font("Courier", Font.PLAIN, 14);
	Color backgroundColor = new Color(50,50,50);
	Dimension ConsSize = new Dimension(800, 600);

	boolean trace = false;

	public KinConsole(){
			super();
			build();
		}

	private void build() {
		
		setTitle("Human Curse"); 
		setSize(ConsSize); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		topConsole = new JTextPane();
		topConsole.setForeground(Color.WHITE);
		topConsole.setEditable(false);
		topConsole.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		topConsole.setBackground(backgroundColor);
		topConsole.setFont(font);
		
		rightConsole = new JTextPane();
		rightConsole.setMinimumSize(new Dimension(330, 100));
		rightConsole.setBorder(null);
		rightConsole.setForeground(Color.WHITE);
		rightConsole.setEditable(false);
		rightConsole.setBackground(backgroundColor);
		rightConsole.setFont(font);
		
		leftConsole = new JTextPane();
		leftConsole.setBorder(null);
		leftConsole.setForeground(Color.WHITE);
		leftConsole.setEditable(false);
		leftConsole.setBackground(backgroundColor);
		leftConsole.setFont(font);
		
		input = new JTextField();
		input.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
		input.setBackground(backgroundColor);
		input.setForeground(Color.WHITE);
		input.setCaretColor(Color.WHITE);
		input.setFont(font);
		
		topDocument = topConsole.getStyledDocument();
		leftDocument = leftConsole.getStyledDocument();
		rightDocument = rightConsole.getStyledDocument();
		
		
		setContentPane(buildContentPane());
		setVisible(true);
	}

	private JPanel buildContentPane(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1,2));
		gridPanel.add(leftConsole);
		gridPanel.add(rightConsole);
		
		panel.add(topConsole, BorderLayout.NORTH);
		panel.add(gridPanel, BorderLayout.CENTER);
		panel.add(input, BorderLayout.SOUTH);
		
	 
		return panel;
	}	
	
// PRINT/CLEAR METHODES ----------------------------------------------------
	
	/**
	 * Print on left Console
	 * @param s Text
	 */
	public void leftPrint(String s) {
		leftPrint(s, new Color(255, 255, 255));
	}
	/**
	 * Print on left Console
	 * @param s Text
	 * @param c Color
	 */
	public void leftPrint(String s, Color c) {
		
		Style style = leftConsole.addStyle("Color Style", null);
		StyleConstants.setForeground(style, c);
		
		try {
			leftDocument.insertString(leftDocument.getLength(), s, style);
		} catch (Exception e) { e.printStackTrace();}
		
		
	}

	public void leftPrintln(String s) {
		leftPrintln(s, Color.WHITE);
	}
	public void leftPrintln(String s, Color c) {
		leftPrint(s + "\n", c);
	}
	
	public void rightPrint(String s) {
		this.rightPrint(s, new Color(255, 255, 255));
	}
	public void rightPrint(String s, Color c) {
		
		Style style = rightConsole.addStyle("Color Style", null);
		StyleConstants.setForeground(style, c);
		
		try {
			rightDocument.insertString(rightDocument.getLength(), s, style);
		} catch (Exception e) { e.printStackTrace();}
		
		
	}
	
	public void rightPrintln(String s) {
		rightPrintln(s, Color.WHITE);
	}
	public void rightPrintln(String s, Color c) {
		rightPrint(s + "\n", c);
	}
	
	public void topPrint(String s) {
		topPrint(s, new Color(255, 255, 255));
	}
	public void topPrint(String s, Color c) {
		
		Style style = topConsole.addStyle("Color Style", null);
		StyleConstants.setForeground(style, c);
		
		try {
			topDocument.insertString(topDocument.getLength(), s, style);
		} catch (Exception e) { e.printStackTrace();}
		
		
	}
	
	public void topPrintln(String s) {
		topPrintln(s, Color.WHITE);
	}
	public void topPrintln(String s, Color c) {
		topPrint(s + "\n", c);
	}
	
	public void sPrint(String s) {
		sPrint(s, Color.WHITE);
	}
	public void sPrint(String s, Color c) {
		sPrint(s, c, '1');
	}
	public void sPrint(String s, Color c, char co) {
		sPrint(s, c, co, false);
	}
	public void sPrint(String s, Color c, char co, boolean bold) {
		sPrint(s, c, co, bold, 14);
	}
	public void sPrint(String s, Color c, char co, boolean bold, int fSize) {
		sPrint(s, c, co, bold, fSize, font.getName());
	}
	/**
	 * Print on Choose Console
	 * @param s String Text
	 * @param c Color Color
	 * @param co kConsole.        
	 *			 TOP_C = top Console
	 *			 LEFT_C = left Console
	 *			 RIGHT_C = Right Console 
	 * @param bold boolean bold
	 * @param fSize int Font Size
	 * @param font String Font Name
	 */
	public void sPrint(String s, Color c, char co, boolean bold, int fSize, String font ) {
		
		Style style = null;
		
		if(co == '0') {
			style = topConsole.addStyle("Color Style", null);
		} 
		else if(co == '1') {
			style = leftConsole.addStyle("Color Style", null);
		}
		else if(co == '2') {
			style = rightConsole.addStyle("Color Style", null);
		}
		
		StyleConstants.setForeground(style, c);
		StyleConstants.setBold(style, bold);
		StyleConstants.setFontFamily(style, font);
		StyleConstants.setFontSize(style, fSize);
		
		try {
		
			if(co == '0') {
				topDocument.insertString(topDocument.getLength(), s, style);
			} 
			else if(co == '1') {
				leftDocument.insertString(leftDocument.getLength(), s, style);
			}
			else if(co == '2') {
				rightDocument.insertString(rightDocument.getLength(), s, style);
			}
		
		} catch (Exception e) { e.printStackTrace();}
	}
	
	public void leftClear() {
		try {
			leftDocument.remove(0, leftDocument.getLength());
		} catch (Exception ex) {
		}
	}
	public void rightClear() {
		try {
			rightDocument.remove(0, rightDocument.getLength());
		} catch (Exception ex) {
		}
	}
	public void topClear() {
		try {
			topDocument.remove(0, topDocument.getLength());
		} catch (Exception ex) {
		}
	}
	public void ClearAll() {
		leftClear();
		rightClear();
		topClear();
	}
	
// TOOLS METHODES ----------------------------------------------------
	
	public void leftScrollBottom() {
		leftConsole.setCaretPosition(leftConsole.getDocument().getLength());
	}
	public void rightScrollBottom() {
		rightConsole.setCaretPosition(rightConsole.getDocument().getLength());
	}
	public void topScrollBottom() {
		topConsole.setCaretPosition(topConsole.getDocument().getLength());
	}
	
	






}
