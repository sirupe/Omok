package utility;

import java.util.regex.Pattern;
// ÀºÁ¤
public class RegexCheck {
	// ÀÌ¸ŞÀÏ Çü½ÄÃ¼Å©. Çü½Ä¿¡ ¸ÂÀ¸¸é true, Çü½Ä¿¡ ¸ÂÁö ¾ÊÀ¸¸é false return
	// (¾ÆÀÌµğ@»çÀÌÆ®.com ÀÇ Çü½Ä)
	public static boolean emailRegexCheck(String email) {
		String regex = "^[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		return Pattern.matches(regex, email);
	}

	// (»çÀÌÆ®.com Çü½Ä Ã¼Å©)
	public static boolean emailDomainRegexCheck(String domain) {
		String regex = "^[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		return Pattern.matches(regex, domain);
	}
	
	// ¾ÆÀÌµğ Çü½ÄÃ¼Å©. Çü½Ä¿¡ ¸ÂÀ¸¸é true, Çü½Ä¿¡ ¸ÂÁö ¾ÊÀ¸¸é false return
	// (¿µ¹®ÀÚ+¼ıÀÚ Á¶ÇÕÀÇ Çü½Ä. ¿µ¹®ÀÚ¸¸À¸·Î´Â °¡´ÉÇÏ³ª ¼ıÀÚ¸¸À¸·Î´Â ºÒ°¡´ÉÇÏ´Ù.)
	public static boolean idRegexCheck(String id) {
		String regex = "^[a-zA-Z0-9]*$";
		boolean result = Pattern.matches(regex, id);
		if(Pattern.matches("^[0-9]*$", id)) {
			result = false;
		}
		return result;
	}
	
	// ºñ¹Ğ¹øÈ£ Çü½ÄÃ¼Å©. Çü½Ä¿¡ ¸ÂÀ¸¸é true, Çü½Ä¿¡ ¸ÂÁö ¾ÊÀ¸¸é false return
	// (¿µ¹®ÀÚ + ¼ıÀÚ + Æ¯¼ö¹®ÀÚ 1°³ ÀÌ»ó¾¿ Æ÷ÇÔ. 6~16 ÀÚ ÀÌ³»ÀÇ ±ÛÀÚ¼ö¸¸ true.
	public static boolean passwdRegexCheck(String passwd) {
		String regex = "^(?=.*[~`!@%$%\\^&*()-])(?=.*[0-9])(?=.*[a-zA-Z]).{6,16}$";
		return Pattern.matches(regex, passwd);
	}
	
	// ÀÌ¸§ Çü½ÄÃ¼Å©. Çü½Ä¿¡ ¸ÂÀ¸¸é true, Çü½Ä¿¡ ¸ÂÁö ¾ÊÀ¸¸é false return
	// (ÇÑ±Û¸¸ true ¹İÈ¯.)
	public static boolean nameRegecCheck(String name) {
		String regex = "^([¤¡-¤¾°¡-ÆR])*$";
		return Pattern.matches(regex, name);
	}
}
