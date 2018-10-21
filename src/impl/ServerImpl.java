package impl;

import inter.ServerInterface;
import metier.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<Message> message;

    public ServerImpl() throws RemoteException {
        super();
        message = new ArrayList<>(Arrays.asList(new Message("toto", LocalDate.now(), "tes1"),
                                                new Message("toto", LocalDate.now(), "tes2")));
    }

    @Override
    public ArrayList<Message> getMessages() throws RemoteException {
        return message;
    }

    @Override
    public void send(String pseudo, String message) {
        this.message.add(new Message(pseudo, LocalDate.now(), message));
    }
}
