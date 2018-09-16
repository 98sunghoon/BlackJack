package sh.blackjack.client.view;

import java.io.*;
import java.net.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import sh.blackjack.client.*;

public class SignUpController {
	@FXML
	private TextField id_field;
	@FXML
	private TextField name_field;
	@FXML
	private PasswordField pw_field;
	@FXML
	private PasswordField pwc_field;
	@FXML
	private Button sign_btn;

	private Stage dialogStage;
	// private boolean okClicked = false;

	public static final String serverIP = "117.17.187.54";

	public void initialize() {

		sign_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				signup();
			}
		});
		pwc_field.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sign_btn.fireEvent(new ActionEvent());
			}
		});
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// public boolean isOkClicked() {
	// return okClicked;
	// }
	//
	// @FXML
	// private void handleOk() {
	// okClicked = true;
	// dialogStage.close();
	// }
	//
	// @FXML
	// private void handleCancel() {
	// dialogStage.close();
	// }

	public void signup() {
		TextManager tm = new TextManager();
		if (tm.isValidID(id_field.getText()) && tm.isValidName(name_field.getText())
				&& tm.isValidPassword(pw_field.getText())) {

			Socket socket = null;
			DataOutputStream out = null;
			DataInputStream in = null;

			int signup_result = -1;

			try {
				socket = new Socket(serverIP, 7000);
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				// signup request send
				out.writeUTF(tm.makeSignupRequest(id_field.getText(), name_field.getText(), pw_field.getText()));
				// signup result receive
				do {
					signup_result = in.readInt();
				} while (signup_result == -1);
			} catch (UnknownHostException e) {
			} catch (IOException e) {
			}

			if (signup_result == 200) {
				// 회원가입 성공 창
				dialogStage.close();
			} else if (signup_result == 201) {
				// id중복
				System.out.println("return is 201");
			} else if (signup_result == 202) {
				// name중복
				System.out.println("return is 202");
			} else {
				System.out.println("signup failed");
			}

		}

	}
}