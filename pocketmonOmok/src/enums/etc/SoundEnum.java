package enums.etc;

public enum SoundEnum {
	GAME_BLACK_TURN("resources/sound/black.wav"),
	GAME_WHITE_TURN("resources/sound/white.wav"),
	GAME_START_SOUND("resources/sound/start.wav"),
	GAME_STONE_DROP("resources/sound/stone.wav"),
	LOGIN_SUCCESS_SOUND("resources/sound/login.WAV");
	
	private String sound;
	
	private SoundEnum(String sound) {
		this.sound = sound;
	}
	
	public String getSound() {
		return sound;
	}
}
