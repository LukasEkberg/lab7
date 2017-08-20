package lab7;

import lab7.Boardgame;

public class TicTacToe implements Boardgame {
	
	private String currentMessage = "Player 1 begins";
	private String[][] status = new String[3][3]; //spelbr�det
	private int player1C = 0, player2C = 0; //r�knar antal drag
	private int state = 0;	//sl� om n�r man tar bort ett "X" eller "O" vid utplaceringsfasen, f�rst ta bort en, sen l�gga till
	
	public String getStatus(int i, int j){
		return status[i][j];
	}
	public String getMessage(){
		return currentMessage;
	}
	
	public boolean move(int i, int j){
		i = i -1; //f�r att hantera index
		j = j -1;
		
		if (state==0){
			if(player1C>2)
				state=1;
			return move1(i, j); //utplaceringsfas
		}
		else
			return move2(i, j); //flyttfas
		
			
	}
	
	public boolean move1(int i, int j){
		//utplaceringsfas, en spelare i taget och bara p� tomma platser
		//Om counten f�r spelare 1 och 2 �r samma �r det spelare 1's tur, annars spelare 1
		if (status[i][j] == null){
			if (player1C == player2C){
				status[i][j] = "X";
				currentMessage = "Now it's player 2's turn";
				player1C ++;
				playerWon("X", i, j);
				return true;
			}
			
			else{
				status[i][j] = "O";
				currentMessage = "Now it's player 1's turn";
				player2C ++;
				playerWon("O", i, j);
				return true;
		
			}
		
		}
		
		currentMessage = "Unvalid move";
			return false;
	}
		
	

	
	public boolean move2(int i, int j){
		
		//Efter 6 pj�ser s� ska vi ta bort en spelares symbol, b�rjan av flyttfasen
		
			if (status[i][j] != null){
				if ((player2C == player1C) && (status[i][j] == "X")){					
					status[i][j] = null;
					state = 0;
					return true;
					
				}
				if(((player2C != player1C) && (status[i][j] == "O"))){
					status[i][j] = null;
					state = 0;
					return true;
				}
					
				
			}
			
		
		currentMessage = "Unvalid move";
		return false;
	}
	
	//Avg�r vinnare och unders�ker kolumn, rad och diag mha m�nster
	void playerWon(String m, int i, int j){
		
		//vilken spelare, hj�lper utskrift av vinnare
		String p;
		if (m.equals("X"))
			p = "1";
		else
			p = "2";
			
		//rad
		for(int x=0; x<3; x++){
			if(status[i][x]!=m)
				break;
			if (x == 2)
				currentMessage = "Player " + p + " won";
				
		}
		
		//kolumn
		for(int x=0; x<3; x++){
			if(status[x][j]!=m)
				break;
			if (x== 2)
				currentMessage = "Player " + p + " won";
		}
		
		//diag
		if(i==j){
			for(int x = 0; x < 3; x++){
				if(status[x][x]!=m)
					break;
				if(x==2)
					currentMessage = "Player " + p + " won";
			}
		}
		//antidia
		if(i+j == 2){
			for(int x = 0; x < 3; x++){
				if(status[x][2-x]!=m)
					break;
				if(x==2)
					currentMessage = "Player " + p + " won";
			}
		}
		
	}
	
	//anv�nds inte atm, b�rjan till att starta om
	void freeBoard(){
		status = new String[3][3];
		player1C = 0;
		player2C = 0;
	}

}

