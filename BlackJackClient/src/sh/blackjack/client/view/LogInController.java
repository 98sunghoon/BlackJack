package sh.blackjack.client.view;

import java.awt.event.*;

import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import sh.blackjack.client.*;

public class LogInController {
	@FXML
	private Label id_label;
	@FXML
	private Label pw_label;
	@FXML
	private TextField id_field;
	@FXML
	private PasswordField pw_field;
	@FXML
	private Button login_btn;
	@FXML
	private Button signup_btn;
	@FXML
	private ImageView imgView;
	
	// ���� ���ø����̼� ����
	private MainApp mainApp;

	/**
	 * ������. initialize() �޼��� ������ ȣ��ȴ�.
	 */
	public LogInController() {
	}

	/**
	 * ��Ʈ�ѷ� Ŭ������ �ʱ�ȭ�Ѵ�. fxml ������ �ε�ǰ� ���� �ڵ����� ȣ��ȴ�.
	 */
	@FXML
	private void initialize() {
		// ����ó ���̺��� �� ���� �ʱ�ȭ�Ѵ�.
		// firstNameColumn.setCellValueFactory(cellData ->
		// cellData.getValue().firstNameProperty());
		// lastNameColumn.setCellValueFactory(cellData ->
		// cellData.getValue().lastNameProperty());
//		imgView = new ImageView(new Image("file:\background.png"));
		
		
		pw_field.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				login_btn.fireEvent(new ActionEvent());
			}
		});
		login_btn.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				login_btn.fireEvent(new ActionEvent());
			}
		});
		signup_btn.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				signup_btn.fireEvent(new ActionEvent());
			}
		});
	}

	/**
	 * ������ �ٽ� �����ϱ� ���� ���� ���ø����̼��� ȣ���Ѵ�.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// ���̺� observable ����Ʈ �����͸� �߰��Ѵ�.
		// personTable.setItems(mainApp.getPersonData());
	}

	public void handleLogin() {
		String id = id_field.getText();
		String pw = pw_field.getText();
		Client client = new Client();
		if (client.LogIn(id, pw) == 200) {
			mainApp.showWatingRoomView(client);
			client.activateChat();
		}
	}

	public void handleSignup() {
		mainApp.showSignUpView();
	}
}
