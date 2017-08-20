package lab7;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import lab7.TicTacToe;


//lämpliga import-satser här
class ViewControl extends JFrame implements ActionListener {

	private Boardgame game;
	private int size;
	private Square[][] board;        // Square är subklass till JButton
	private JLabel mess = new JLabel();
	
	
	ViewControl (Boardgame gm, int n){  // OK med fler parametrar om ni vill!
	    size = n;
	    game = gm;
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("View-Control");
        setMinimumSize(new Dimension(1280, 768));
        setSize(1280, 768);    

	    JPanel panel = new JPanel();
	    mess = new JLabel(game.getMessage());
	    panel.add(mess);   
	    add(panel, BorderLayout.PAGE_START);
	    
	    board = new Square[size][size];
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(size, size));
	      
	      for (int i = 0; i < size; i++)
	    		for (int j = 0; j < size; j++)
	    		{
	    			board[i][j] = new Square(game.getStatus(i, j), i, j);
	    			board[i][j].addActionListener(this);	
	    			buttonPanel.add(board[i][j]);
	    		}      
	     add(buttonPanel);
		     
	}
	
	public void actionPerformed(ActionEvent event) {
		Square a = (Square)event.getSource();
		int i = a.geti();
		int j = a.getj();
    	game.move(i+1,j+1);			
    
		updateButtons();
		updateMessage();
	}
	public void updateButtons(){
		for (int i = 0; i < size; i++)
    		for (int j = 0; j < size; j++)
    			board[i][j].setText(game.getStatus(i, j));
		
	}
	
	public void updateMessage(){
		mess.setText(game.getMessage());
	}
	
	public static void main(String[] args){
		new ViewControl(new TicTacToe(), 3).setVisible(true);
	}
 }