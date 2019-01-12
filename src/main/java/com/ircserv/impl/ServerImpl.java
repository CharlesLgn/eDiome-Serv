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

/**
 * implementation of the server in RMI
 *
 * @see com.ircserv.inter.ServerInterface
 *
 * @author  Charles Ligony
 * @author  Cyril   Challouatte
 * @author  Loic    Noel
 * @author  Lucas   Cuoco
 * @since   eDiome 1.0.0
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private List<Message> message;
    private int numServ;

    public ServerImpl(int port, int numServ) throws RemoteException {
        super(port);
        this.numServ = numServ;
        this.message = getMessageFromBdd();
    }

    /**
     * @return the list of all message of the server (to initialize with the bdd if the server is shut down)
     */
    private List<Message> getMessageFromBdd() {
        MessageManager mm = new MessageManager();
        mm.setup();
        Server serv = getServ();
        return mm.getMessagesByServ(serv);
    }

    @Override
    public List<Message> getMessages() {
        return message;
    }

    @Override
    public List<Message> getMessages(int nbLastMessage) {
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

     /**
     * add the message to the message list (bdd + serverList)
     * @param userId the id of the user
     * @param message the content of the message
     * @param pieceJointe the file send if there is one
     */
    private void send(int userId, String message, PieceJointe pieceJointe) {
        //get the server
        Server server = getServ();
        //get the user
        UtilisateurManager um = new UtilisateurManager();
        um.setup();
        Utilisateur user = um.read(userId);
        //create the message
        MessageManager messageManager = new MessageManager();
        messageManager.setup();
        //using a patern Builder
        Message message1 = new Message.MessageBuilder()
                .setContenu(message)
                .setDate(Timestamp.from(Instant.now()))
                .setUser(user)
                .addServ(server)
                .addPieceJointe(pieceJointe).build();
        this.message.add(message1);
        messageManager.create(message1);
    }

    @Override
    public void send(int userId, String message) {
        send(userId, message, null);
    }

    @Override
    public void uploadFile(int userId, byte[] data, String filename) {
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

            send(userId, "", pj);
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
        return utilisateurManager.readAllUserInServer(getServ());
    }

    @Override
    public void linkUserToServer(Utilisateur utilisateur) {
        linkOrUnlinkUserToServer(utilisateur, true);
    }

    @Override
    public void unlinkUserToServer(Utilisateur utilisateur) {
        linkOrUnlinkUserToServer(utilisateur, false);
    }

    private void linkOrUnlinkUserToServer(Utilisateur utilisateur, boolean create) {
        Server server = getServ();
        UtilisateurServer utilisateurServer = new UtilisateurServer();
        utilisateurServer.setServer(server);
        utilisateurServer.setUser(utilisateur);

        UtilisateurServerManager usm = new UtilisateurServerManager();
        usm.setup();
        if (create) {
            usm.create(utilisateurServer);

            DroitManager droitManager = new DroitManager();
            droitManager.setup();

            this.setDroit(utilisateur, droitManager.read(3));
        } else {
            usm.delete(utilisateurServer.getId());
        }
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

    @Override
    public void setDroit(Utilisateur utilisateur, Droit droit) {
        UtilisateurDroitServerManager utilisateurDroitServerManager = new UtilisateurDroitServerManager();
        utilisateurDroitServerManager.setup();
        UtilisateurDroitServer uds = utilisateurDroitServerManager.read(getServ(), utilisateur);
        uds.setDroit(droit);
        utilisateurDroitServerManager.update(uds);
    }

    @Override
    public List<UtilisateurDroitServer> getAllDroit() {
        UtilisateurDroitServerManager udsm = new UtilisateurDroitServerManager();
        udsm.setup();
        return udsm.getDroit(getServ());
    }


    private Server getServ() {
        ServerManager serverManager = new ServerManager();
        serverManager.setup();
        return serverManager.read(numServ);
    }
}
