package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private BufferedReader in;
	private PrintWriter ut;
	
	//uppkoppling till servern (givet)
	public Client(){
		try {
			Socket socket=new Socket("localhost",1500);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ut=new PrintWriter(socket.getOutputStream());
			ut.println("Lukas"); ut.flush();
			in.readLine();
		   }
		catch(IOException e){
			System.err.println(e);
		}
	}
	
	//tar emot ett drag från servern och returnerar det
	public String move(){
		ut.println(" "); ut.flush();
		String serverMove;
		try {
			serverMove = in.readLine();
			return serverMove;
		} catch (IOException e) {
			String error = e.toString();
			return error;
		}
	
	}
	
	//avsluta connection genom att skicka null i unicode
	public void endConnection(){
		ut.println(); ut.flush();
	}
	
	//test
	public static void main (String[] u) {
    	Client server = new Client();
    	server.move();
    }
    
}
