package com.ircserv.impl;

import com.ircserv.inter.ServerInterface;
import com.ircserv.manager.*;
import com.ircserv.metier.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

  private ArrayList<Message> message;
  private int numServ;

  public ServerImpl(int port, int numServ) throws RemoteException {
    super(port);
    message = new ArrayList<>();
    this.numServ = numServ;
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

  private void send(int userId, int servId, String message, PieceJointe pieceJointe) {

    ServerManager sm = new ServerManager();
    sm.setup();
    UtilisateurManager um = new UtilisateurManager();
    um.setup();

    Server server = sm.readServer(servId);
    Utilisateur user = um.readUser(userId);

    MessageManager messageManager = new MessageManager();
    messageManager.setup();

    Message message1 = new Message.MessageBuilder().setContenu(message).setDate(Timestamp.from(Instant.now())).setUser(user).addServ(server).addPieceJointe(pieceJointe).build();
    this.message.add(message1);
    messageManager.create(message1);
  }

  @Override
  public void send(int userId, int servId, String message) {
    send(userId, servId, message, null);
  }

  @Override
  public void uploadFile(int userId, int servId, byte[] data, String filename) {
    try {
      filename = filename.replaceAll(" ", "_");
      File file = new File("../data/server" + numServ + "/" + filename);
      System.out.println(file.getAbsolutePath().replace(" ", "?"));
      FileUtils.writeByteArrayToFile(file, data);
      String path = file.getPath();
      System.out.println(path);
      String extension = filename.replaceAll(".*[.]", "");

      TypePieceJointeManager tpjm = new TypePieceJointeManager();
      tpjm.setup();
      TypePieceJointe tpj = tpjm.getPJbyExtension(extension);
      if (tpj.getLibelle() == null) {
        tpj.setExtension(extension);
        tpj.setLibelle("file");
        tpjm.create(tpj);
        tpj = tpjm.getPJbyExtension(extension);
      }

      System.out.println(tpj.getLibelle());
      PieceJointeManager pjm = new PieceJointeManager();
      pjm.setup();

      PieceJointe pj = new PieceJointe(path, tpj);
      pjm.create(pj);

      send(userId, servId, "", pj);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Utilisateur> getAllUserNotInServer() {
    UtilisateurManager utilisateurManager = new UtilisateurManager();
    utilisateurManager.setup();
    return utilisateurManager.readAllUserNotInServer(getServ());
  }

  @Override
  public List<Utilisateur> getAllUserInServer() {
    UtilisateurManager utilisateurManager = new UtilisateurManager();
    utilisateurManager.setup();
    return utilisateurManager.readAllUserNotInServer(getServ());
  }

  @Override
  public void linkUserToServer(Utilisateur utilisateur) {
    Server server = getServ();
    UtilisateurServer utilisateurServer = new UtilisateurServer();
    utilisateurServer.setServer(server);
    utilisateurServer.setUser(utilisateur);

    UtilisateurServerManager usm = new UtilisateurServerManager();
    usm.setup();
    usm.create(utilisateurServer);
  }

  @Override
  public Droit getDroit(Utilisateur utilisateur) {
    Server server = getServ();
    UtilisateurServer utilisateurServer = new UtilisateurServer();
    utilisateurServer.setServer(server);
    utilisateurServer.setUser(utilisateur);

    UtilisateurDroitServerManager udsm = new UtilisateurDroitServerManager();
    udsm.setup();
    return udsm.getDroit(server, utilisateur);
  }

  private Server getServ(){
    ServerManager serverManager = new ServerManager();
    serverManager.setup();
    return serverManager.readServer(numServ);
  }
}
