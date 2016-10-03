package datasDTO;

import enums.etc.UserPositionEnum;

public class GameBoardVO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 3970343263986324779L;

	private String nowTurnUser;
	private String nextTurnUser;
	private String winUser;
	private String loseUser;
	private int gameBoard[][];
	private int x;
	private int y;

	public GameBoardVO(UserPositionEnum position) {
		super(position);
	}

	public String getNowTurnUser() {
		return nowTurnUser;
	}

	public void setNowTurnUser(String nowTurnUser) {
		this.nowTurnUser = nowTurnUser;
	}

	public String getNextTurnUser() {
		return nextTurnUser;
	}

	public void setNextTurnUser(String nextTurnUser) {
		this.nextTurnUser = nextTurnUser;
	}

	public int[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(int[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public String getWinUser() {
		return winUser;
	}

	public void setWinUser(String winUser) {
		this.winUser = winUser;
	}

	public String getLoseUser() {
		return loseUser;
	}

	public void setLoseUser(String loseUser) {
		this.loseUser = loseUser;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
