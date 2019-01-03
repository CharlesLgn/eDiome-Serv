package com.ircserv.impl;

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
import java.util.Collections;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

  private ArrayList<Message> message;

  public ServerImpl(int port) throws RemoteException {
    super(port);
    message = new ArrayList<>(Collections.singletonList(new Message("serveur", LocalDateTime.now(), "server ouvert")));
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

  @Override
  public void send(String pseudo, String message) {
    this.message.add(new Message(pseudo, LocalDateTime.now(), message));
  }

  @Override
  public void uploadFile(String pseudo, byte[] data, String filename) {
    int i = 0;
    try {
      String content = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(data));
      filename = filename.replaceAll(" ","_");
      String path = "C:\\Users\\ligony\\Documents\\cours%20LP\\condProj\\eDiome\\file\\" + filename.replaceAll(" ", "_");
      FileUtils.writeByteArrayToFile(new File(path), data);
      if (content != null && content.contains("image")) {
        send(pseudo, ":image:" + path);
      } else if (filename.matches("[A-z0-9_.]+.wav") || (filename.matches("[A-z0-9_.]+.mp3"))) {
        send(pseudo, ":audio:" + path);
      } else {
        send(pseudo, ":file:" + path);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
