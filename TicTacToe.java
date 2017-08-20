package lab7;

import lab7.Boardgame;

public class TicTacToe implements Boardgame {
	
	private String currentMessage = "Player 1 begins";
	private String[][] status = new String[3][3]; //spelbrädet
	private int player1C = 0, player2C = 0; //räknar antal drag
	private int state = 0;	//slå om när man tar bort ett "X" eller "O" vid utplaceringsfasen, först ta bort en, sen lägga till
	
	public String getStatus(int i, int j){
		return status[i][j];
	}
	public String getMessage(){
		return currentMessage;
	}
	
	public boolean move(int i, int j){
		i = i -1; //för att hantera index
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
		//utplaceringsfas, en spelare i taget och bara på tomma platser
		//Om counten för spelare 1 och 2 är samma är det spelare 1's tur, annars spelare 1
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
		
		//Efter 6 pjäser så ska vi ta bort en spelares symbol, början av flyttfasen
		
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
	
	//Avgör vinnare och undersöker kolumn, rad och diag mha mönster
	void playerWon(String m, int i, int j){
		
		//vilken spelare, hjälper utskrift av vinnare
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
	
	//används inte atm, början till att starta om
	void freeBoard(){
		status = new String[3][3];
		player1C = 0;
		player2C = 0;
	}

}

