package sh.blackjack.client.view;

import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import sh.blackjack.client.*;

public class WaitingRoomController {
	@FXML
	private TextArea chat_log;
	@FXML
	private TextField send_field;
	@FXML
	private Button send_btn;
	@FXML
	private Label balance_label;
	@FXML
	private Label username_label;
	// @FXML
	// private PasswordField pw_field;
	// @FXML
	// private Button login_btn;
	// @FXML
	// private Button signup_btn;

	private MainApp mainApp;
	private Client client;

	public WaitingRoomController() {
	}

	public void initialize() {

		// ¸®½º³Ê ºÎÂø
		send_field.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				send_btn.fireEvent(new ActionEvent());
			}
		});

		send_btn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				send_btn.fireEvent(new ActionEvent());
			}

		});

		send_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (!send_field.getText().equals("")) {
					client.sendMsg(send_field.getText());
					send_field.setText("");
				}
			}
		});

		chat_log.setEditable(false);
	}

	public void setClient(Client client) {
		this.client = client;
		client.setWaitingRoomController(this);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void showMsg(String msg) {
		chat_log.appendText(msg);
	}

	public void setBalance(int balance) {
		balance_label.setAlignment(Pos.CENTER_RIGHT);
		balance_label.setText(moneyStyle(balance) + "¿ø");
	}

	public String moneyStyle(int num) {// 10~99 -> 1
		String result = "";
		int m = (int) Math.log10(num);
		char[] tmp = new char[m + 1];
		for (int i = 0; i < m + 1; i++) {
			tmp[i] = (char) (num % 10 + '0');
			num /= 10;
		}
		for (int j = m; j >= 0; j--) {
			result += tmp[j];
			if (j % 3 == 0 && j != 0)
				result += ",";
		}
		return result;
	}

	public void setUserInfo(String username, String userID) {
		username_label.setAlignment(Pos.CENTER_RIGHT);
		username_label.setText(username + "(" + userID + ")´Ô");
	}
}
