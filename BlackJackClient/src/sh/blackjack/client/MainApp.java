package sh.blackjack.client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import sh.blackjack.client.view.*;

public class MainApp extends Application {

	private Stage mainStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage stage) {
		this.mainStage = stage;
		this.mainStage.setTitle("BlackJack for ITM");
		
		initRootLayout();
		showLogInview();
	}

	/**
	 * 상위 레이아웃을 초기화한다.
	 */
	public void initRootLayout() {
		try {
			// fxml 파일에서 상위 레이아웃을 가져온다.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// 상위 레이아웃을 포함하는 scene을 보여준다.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("view/Theme.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 상위 레이아웃 안에 로그인 판을 보여준다.
	 */

	public void showLogInview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LogInView.fxml"));
			AnchorPane LogIn = (AnchorPane) loader.load();
			LogIn.setStyle("");
			rootLayout.setCenter(LogIn);

			LogInController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showWatingRoomView(Client client) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WaitingRoomView.fxml"));
			AnchorPane WaitingRoom = (AnchorPane) loader.load();
			rootLayout.setCenter(WaitingRoom);

			WaitingRoomController controller = loader.getController();
			controller.setMainApp(this);
			controller.setClient(client);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showSignUpView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SignUpView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("sign up");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SignUpController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			// return controller.isOkClicked();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 메인 스테이지를 반환한다.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return mainStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
