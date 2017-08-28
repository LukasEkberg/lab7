package lab7;

import java.net.*;
import java.io.*;
import java.util.*;
import TicTacToe2.*;

public class Server {
	
    public static void main( String[] args) throws Exception {
    	ServerSocket sock = new ServerSocket(6700,100);
    	System.out.println("Server is active");
    	TicTacToe2 game =  new TicTacToe2();
		try {
			while (true){
				Player p1 = new Player(sock.accept(), "X", game);
				System.out.println("Spelare 1");
		    	Player p2 = new Player(sock.accept(), "O", game);
		    	System.out.println("Spelare 2");, 
		    	p1.setOpponent(p2);
		    	p2.setOpponent(p1);
		    	p1.start();
		    	p2.start();
			}
		}
		catch(IOException e)
		    {System.err.println(e);
		}	
    }  
} 

class Player extends Thread{
    Player opponent;
	BufferedReader in;
    PrintWriter ut;
    String mark;
    TicTacToe2 game;
    
    public Player(Socket socket, String mark, TicTacToe2 game){
    	this.mark = mark;
    	this.game = game;
    	
		try {
		    in = new BufferedReader(new InputStreamReader
					    (socket.getInputStream()));
		    ut= new PrintWriter(socket.getOutputStream(), true);
		}
		
		catch(IOException e) {System.err.println(e);}
	}
    public void setOpponent(Player opponent){
    	this.opponent = opponent;
    }
    
    public Player getOpponent(){
    	return opponent;
    }
    
    public String getMark(){
    	return mark;
    }
    
    public void playerMoved(int i, int j){
    	ut.println("opponent_move " + i + j );
    }
    
    public void run() {
	
		try {
			//Går igång när alla spelare har connectat
			ut.println("message All players have connected");
			if (mark == "X")
				ut.println("message Your turn");
			else
				ut.println("message Waiting for player 1");
			
			while(true){
				String mess = in.readLine();
				if (mess.startsWith("move")){
					int i = Integer.parseInt(mess.substring(5,6));
					int j = Integer.parseInt(mess.substring(6,7));
					if (game.move(i,j,this)){
						
					}
				}
				
			}
			
				
	
		}
	    catch(Exception e) {
		    System.err.println(e);
		}
    }
}