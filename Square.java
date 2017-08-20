package lab7;


import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
	private int j;
	private int i;
	
	public Square(String text, int i, int j){
		super(text);
		this.setForeground(Color.BLUE);
		this.setBackground(Color.PINK);
		this.j = j;
		this.i = i;
	
	
	}
	
	public int geti(){
		return i;
	}
	
	public int getj(){
		return j;
	}
}
