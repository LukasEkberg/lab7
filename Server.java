package lab7;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	static Player p1;
	static Player p2;
    public static void main( String[] args) throws Exception {
    	ServerSocket sock = new ServerSocket(6700,100);
    	System.out.println(sock.getLocalPort());
    	System.out.println("Server is active");
		try {
			p1 = new Player(sock.accept(), "X");
			System.out.println("Spelare 1");
	    	p2 = new Player(sock.accept(), "O");
	    	System.out.println("Spelare 2");
	    	p1.start();
	    	p2.start();
			
		}
		catch(IOException e)
		    {System.err.println(e);
		}	
    }  
} 

class Player extends Thread{
    BufferedReader in;
    PrintWriter ut;
    String mark;
    
    public Player(Socket socket, String mark){
    	this.mark = mark;
    	
	try {
	    in = new BufferedReader(new InputStreamReader
				    (socket.getInputStream()));
	    ut= new PrintWriter(socket.getOutputStream(), true);
	}
	catch(IOException e) {System.err.println(e);}
    }
    
    public void run() {
	
		try {
			
				
	
		}
	    catch(Exception e) {
		    System.err.println(e);
		}
    }
}