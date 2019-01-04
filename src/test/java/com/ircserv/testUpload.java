package com.ircserv;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.MenuInterface;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Message;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class testUpload extends Application {
  private static Stage prStage;

  private static String[] val;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    int port = Constante.PORT;
    ServerInterface obj;
    prStage = primaryStage;
    try {
      obj = (ServerInterface) Naming.lookup("//" + Constante.IP + ":" + port + "/serv0");
      com.sun.javafx.util.Logging.getJavaFXLogger().setLevel(sun.util.logging.PlatformLogger.Level.OFF);

      primaryStage.setScene(new Scene(new Pane(), 10,10));
      primaryStage.show();

      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("");
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        byte[] data = FileUtils.readFileToByteArray(file);
        obj.uploadFile("test", data, file.getName());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    primaryStage.close();
  }
}
