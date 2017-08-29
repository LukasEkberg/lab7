package lab7;

public class TicTacToe2 {
	String board[][] = new String[3][3] ;
	Player currentPlayer;
	
	
	
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
	
	public boolean getWinner() {
		
		for(int i=0; i<3; i++) {
			boolean h = board[i][0]!= null && board[i][0] == board[i][1] && board[i][0]==board[i][2];
			boolean v = (board[0][i]!= null && board[0][i]==board[1][i] && board[0][i]==board[2][i]);
			if (h || v)
				return true;
			}
		
		if (board[0][0]!= null && board[0][0]==board[1][1] && board[0][0]==board[2][2] ||
			board[0][2]!= null && board[0][2]==board[1][1] && board[0][2]==board[2][0] )
			return true;
		
		return false;
		
	}
	
}
