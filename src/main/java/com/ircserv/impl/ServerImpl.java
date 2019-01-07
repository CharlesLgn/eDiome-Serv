package com.ircserv.impl;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Message;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

  private ArrayList<Message> message;
  private int numServ;

  public ServerImpl(int port, int numServ) throws RemoteException {
    super(port);
    message = new ArrayList<>();
    this.numServ = numServ;
    send("server", "server open !!");
  }

  @Override
  public ArrayList<Message> getMessages() {
    return message;
  }

  @Override
  public ArrayList<Message> getMessages(int nbLastMessage) {
    if (nbLastMessage > message.size()) {
      return message;
    } else {
      ArrayList<Message> res = new ArrayList<>();
      for (int i = nbLastMessage; i > 0; i--) {
        res.add(message.get(message.size() - i));
      }
      return res;
    }
  }

  private void send(String pseudo, String typeMessage, String message) {
    //this.message.add(new Message(pseudo, LocalDateTime.now(), typeMessage, message));
  }

  @Override
  public void send(String pseudo, String message) {
    send(pseudo, "message", message);
  }

  @Override
  public void uploadFile(String pseudo, byte[] data, String filename) {
    try {
      String content = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(data));
      String typeMessage;
      filename = filename.replaceAll(" ","_");
      File file = new File("../data/server"+numServ+"/"+filename);
      System.out.println(file.getAbsolutePath().replace(" ", "?"));
      FileUtils.writeByteArrayToFile(file, data);
      String path = file.getAbsolutePath();
      String extension = filename.replaceAll(".*[.]", "");


      if (content != null && content.contains("image")) {
        typeMessage = "picture";
      } else if (Constante.AUDIO.contains(extension)) {
        typeMessage = "audio";
      } else if (Constante.ARCHIVE.contains(extension)) {
        typeMessage = "archive";
      } else if (Constante.CODE.contains(extension)) {
        typeMessage = "code";
      } else {
        typeMessage = "file";
      }
      send(pseudo, typeMessage, path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
