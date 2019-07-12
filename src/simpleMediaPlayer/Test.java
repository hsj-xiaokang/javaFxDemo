package simpleMediaPlayer;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 测试文件
 */
public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 创建测试窗口
		primaryStage.getIcons().add(new Image(  this.getClass().getResourceAsStream("/simpleMediaPlayer/icon/py.jpg") 
		/* new ClassPathResource("icon/py.jpg").getURL().toString() */));
		primaryStage.setTitle("视频播放");
		Group root = new Group();
		BorderPane pane = new BorderPane();
		root.getChildren().add(pane);


		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		pane.setBottom(hbox);

		Button popup = new Button("默认窗口播放");
		Button popup2 = new Button("小窗口播放");
		hbox.getChildren().addAll(popup, popup2);

		// 测试弹窗式调用
		popup.setOnAction((ActionEvent e) -> {
			SimpleMediaPlayer.popup(/* getClass().getResource("video/hsj-test-6.mp4").toString() */"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
		});
		popup2.setOnAction((ActionEvent e) -> {
			SimpleMediaPlayer.popup(/* getClass().getResource("video/hsj-test-6.mp4").toString()*/"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", 550, 400 );
		});

		Scene scene = new Scene(root, 180, 25);
		primaryStage.setScene(scene);
        primaryStage.show();

//		  primaryStage.hide();
//		SimpleMediaPlayer.popup(/* getClass().getResource("video/GoogleFuchsiaOS.mp4").toString() */"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
		 

	}

	public static void main(String[] args) {
		launch(args);
	}
}
