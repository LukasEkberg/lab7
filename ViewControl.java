package lab7;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

import lab7.TicTacToe;


//lämpliga import-satser här
class ViewControl extends JFrame implements ActionListener {

	private int size;
	private Square[][] board;        // Square är subklass till JButton
	private JLabel mess = new JLabel();
	int currentI;
	int currentJ;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter ut;
	
	
	ViewControl (){  
		
		size = 3;
		
		try {
			socket=new Socket("localhost",6702);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ut=new PrintWriter(socket.getOutputStream(), true);
		   }
		catch(IOException e){
			System.err.println(e);
		}
		
	    
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
		
		currentI = i;
		currentJ = j;
    	ut.println("move " + i + j);
    	
		
	}
	
	public void play() throws Exception{
		String received;
		String opponentMark = null;
		String mark = null;
		
		try{
			received = in.readLine();
			if (received.startsWith("MARK")){
				mark = received.substring(5, 6);
				
				if (mark.equals("X"))
					opponentMark = "O";
				else
					opponentMark = "X";
			}
			
			while(true){
				received = in.readLine();
				if (received.startsWith("valid")){
					board[currentI][currentJ].setText(mark);
				}
				if(received.startsWith("message")){
					mess.setText(received.substring(8));
				}
				if (received.startsWith("opponent_move")){
					int i = Integer.parseInt(received.substring(14,15));
					int j = Integer.parseInt(received.substring(15,16));
					board[i][j].setText(opponentMark); 
				}
					
			}
			
		}
		
		finally{
			socket.close();
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception{
		ViewControl v = new ViewControl();
		v.setVisible(true);
		v.play();
		
	}
 }