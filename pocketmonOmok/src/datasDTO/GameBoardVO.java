package datasDTO;

import enums.etc.UserPositionEnum;

public class GameBoardVO extends AbstractEnumsDTO {
	private static final long serialVersionUID = 3970343263986324779L;

	private String turnUser;
	private String winUser;
	private String loseUser;
	private int gameBoard[][];
	private int x;
	private int y;

	public GameBoardVO(UserPositionEnum position) {
		super(position);
	}

	public String getTurnUser() {
		return turnUser;
	}

	public void setTurnUser(String turnUser) {
		this.turnUser = turnUser;
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
