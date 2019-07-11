package simpleMediaPlayer.hsj;

import javax.swing.event.DocumentEvent.EventType;

import com.sun.glass.events.WindowEvent;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * 首先我们创建一个Button，当我们点击Button的时候，改变Button的内容。
         */

        Button btnHello = new Button("Hello");
        /**
         * 设置btnHello按钮点击事件
         * 这里使用了Java8的Lambda表达式。setOnAction的参数为EventHandler<ActionEvent> value
         * EventHandler为一个接口，所以我们有三种方式实现EventHandler接口：
         * 1. 创建一个内部类
         * 2. 创建一个匿名类
         * 3. 使用Lambda表达式（适用于函数体不大的情况）
         */
        btnHello.setOnAction(event->{
            btnHello.setText("Hello World, I am JavaFX!");
        });
        
        //创建媒体对象:包含媒体资源
        String media_URL = getClass().getResource("video/hsj-test-8.mp4").toString(); //需要把多媒体文件放置到out目录上的运行class目录树下
        //or:直接使用网络资源：String media_URL = http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv；
        Media media = new Media(media_URL);

        //创建播放器对象，控制媒体播放行为
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true); //设置自动播放
        //创建媒体播放视图
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.autosize();

		/*
		 * mediaPlayer.play(); //播放 mediaPlayer.pause(); //暂停 mediaPlayer.stop(); //停止
		 * mediaPlayer.setAutoPlay(true); //设置自动播放
		 * mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
		 * mediaPlayer.dispose(); //释放 MediaPlayer 的 Media 资源 mediaPlayer.seek(new
		 * Duration(0.00d)); //重绕进度 mediaPlayer.getStartTime(); //获取开始时间(->Duration)
		 * mediaPlayer.getStopTime(); //获取视频长度(->Duration) mediaPlayer.getCurrentTime();
		 * //获取当前时间(->Duration) mediaPlayer.getVolume(); //获取视频音量（->double）
		 * mediaPlayer.setVolume(0.5d); //设置音量 0~0.1
		 */
        mediaPlayer.setOnReady(new Runnable(){
        	public void run(){
        	   double meidaHeight = media.getHeight();
        	   double meidaWidth = media.getWidth();
        	   //通过获取视频的显示数据可以解决 MediaView 窗口自适应的问题
        	   }
        	});
        
        
        
        /**
         *  BorderPane是一个用于布局的Pane，BoerderPane将面板分割为上下左右中五部分。
         *  我们可以将UI控件放置在BorderPane的上下左右和中间。
         *  这里将将Button放置在中间。
         */
//        BoerderPane  pane = new BoerderPane();
        Pane pane = new Pane();
//        pane.setTop(btnHello);
//        pane.setBottom(mediaView);
//        pane.setCenter(mediaView);
        pane.autosize();
        pane.getChildren().add(mediaView);

		
		/*
		 * mediaView.fitHeightProperty().bind(pane.heightProperty());
		 * mediaView.fitWidthProperty().bind(pane.widthProperty());
		 */
		/*
		 * mediaView.prefHeight(pane.heightProperty().doubleValue());
		 * mediaView.prefWidth(pane.widthProperty().doubleValue());
		 */

        // 将pane加入到Scen中
        Scene scene = new Scene(pane,630,480);
		/*
		 * pane.prefWidthProperty().bind(scene.widthProperty());
		 * pane.prefHeightProperty().bind(scene.heightProperty());
		 */
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/py.jpg")));
        // 设置stage的scen，然后显示我们的stage
        primaryStage.setFullScreen(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Hello World,my first javaFx app");
        primaryStage.show();
        
        primaryStage.addEventHandler(javafx.stage.WindowEvent.ANY, event -> {
//                 System.out.println("============Stage事件=============="+event.getEventType().getName());
            });
        
        if (scene!= null) {
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                    setSize(newValue.intValue(),currentHeight);}
                    System.out.println("============scene事件Width=============="+newValue.intValue());
//                    mediaView.prefWidth(newValue.intValue());
                    mediaView.setFitWidth(newValue.intValue());
                    mediaView.setFitHeight(mediaView.getFitHeight());
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                    setSize(currentWidth,newValue.intValue());
                    System.out.println("============scene事件Height=============="+newValue.intValue());
//                    mediaView.prefHeight(newValue.intValue());
                	mediaView.setFitWidth(mediaView.getFitWidth());
                    mediaView.setFitHeight(newValue.intValue());
                }
            });
        }
       

    }

    public static void main(String[] args) {
        // JavaFX中main函数必须需要调用launch函数
        launch(args);
    }

}