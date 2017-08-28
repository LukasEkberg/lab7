package lab7;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import lab7.TicTacToe;


//lämpliga import-satser här
class ViewControl extends JFrame implements ActionListener {

	private int size;
	private Square[][] board;        // Square är subklass till JButton
	private JLabel mess = new JLabel();
	
	
	ViewControl (){  // OK med fler parametrar om ni vill!
	    size = 3;
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("View-Control");
        setMinimumSize(new Dimension(1280, 768));
        setSize(1280, 768);    

	    JPanel panel = new JPanel();
	    mess = new JLabel("Waiting for player to connect");
	    panel.add(mess);   
	    add(panel, BorderLayout.PAGE_START);
	    
	    board = new Square[size][size];
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(size, size));
	      
	      for (int i = 0; i < size; i++)
	    		for (int j = 0; j < size; j++)
	    		{
	    			board[i][j] = new Square(null, i, j);
	    			board[i][j].addActionListener(this);	
	    			buttonPanel.add(board[i][j]);
	    		}      
	     add(buttonPanel);
		     
	}
	
	public void actionPerformed(ActionEvent event) {
		Square a = (Square)event.getSource();
		int i = a.geti();
		int j = a.getj();
		int sum = i*3+j;
    	System.out.println("move " + sum);
		//out.println("move" + i + j)
		//game.move(i+1,j+1);			
		//skicka ut drag
		
		//updateButtons();
		//updateMessage();
		//ta emot drag och meddelande
	}
	//uppdatera med loop
	public void updateButtons(){
		for (int i = 0; i < size; i++)
    		for (int j = 0; j < size; j++)
    			board[i][j].setText(game.getStatus(i, j));
		
	}
	
	//antagligen ta bort
	public void updateMessage(){
		mess.setText(game.getMessage());
	}
	
	public static void main(String[] args){
		new ViewControl().setVisible(true);
	}
 }