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
	
	// 메인 애플리케이션 참조
	private MainApp mainApp;

	/**
	 * 생성자. initialize() 메서드 이전에 호출된다.
	 */
	public LogInController() {
	}

	/**
	 * 컨트롤러 클래스를 초기화한다. fxml 파일이 로드되고 나서 자동으로 호출된다.
	 */
	@FXML
	private void initialize() {
		// 연락처 테이블의 두 열을 초기화한다.
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
	 * 참조를 다시 유지하기 위해 메인 애플리케이션이 호출한다.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// 테이블에 observable 리스트 데이터를 추가한다.
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
