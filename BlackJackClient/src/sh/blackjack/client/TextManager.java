package sh.blackjack.client;

public class TextManager {

	private String spliter = "y12z0f28s0x0asdf9";

	public String makeLoginRequest(String id, String pw) {
		String result = "LoginRequest" + spliter + id + spliter + pw;
		return result;
	}

	public String makeSignupRequest(String id, String name, String pw) {
		String result = "SignupRequest" + spliter + id + spliter + name + spliter + pw;
		return result;
	}

	public String makeChatMsg(String sender, String msg) {
		String result = "chat" + spliter + sender + spliter + msg;
		return result;
	}

	public boolean isValidText() {
		// 특수문자 제외
		return true;
	}

	public boolean isValidID(String id) {
		// spliter 제외
		// 특수문자 제외
		return true;

	}

	public boolean isValidName(String name) {
		// spliter 제외
		// 특수문자 제외
		return true;
	}

	public boolean isValidPassword(String pw) {
		// spliter 제외
		// 특수문자 제외
		// 일치 검사
		return true;
	}
}
