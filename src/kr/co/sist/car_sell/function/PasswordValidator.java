package kr.co.sist.car_sell.function;

import java.util.regex.Pattern;

/**
 * 정규식을 이용한 패스워드 Validator<br>
 * 규칙<br>
 * (?=.*[A-Z]): 대문자가 한 글자 이상 포함되어야 함.<br>
 * (?=.*[0-9]): 숫자가 포함되어야 함.<br>
 * (?=.*[a-z]): 문자가 포함되어야 함.<br>
 * (?=.*[!@#$%^&*()-+=]): 특수문자가 포함되어야 함.<br>
 * .{8,}$: 최소 8자 이상이어야 함.<br>
 */
public class PasswordValidator {

	   private static final String PASSWORD_PATTERN =
//	            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$";
	   "(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$";

	    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	    public static boolean isValid(String password) {
	        if (password == null) {
	            return false;
	        }
	        return pattern.matcher(password).matches();
	    }

}