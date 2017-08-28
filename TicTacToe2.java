package lab7;

public class TicTacToe2 {
	String board[][] = new String[3][3] ;
	Player currentPlayer;
	
	void playerWon(){
		
	}
	
	public synchronized boolean move(int i, int j, Player player){
		if (player == currentPlayer && board[i][j] == null){
			board[i][j] = player.getMark();
			currentPlayer.getOpponent().playerMoved(i,j);
			currentPlayer = player.getOpponent();
			
			return true;
		}
		else
			return false; 
		
	}
	
	
	public static void main(String[] args){
	
	}
}
