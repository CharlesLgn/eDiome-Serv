package com.ircserv.impl;

import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<Message> message;

    public ServerImpl(int port) throws RemoteException {
        super(port);
        message = new ArrayList<>(Collections.singletonList(new Message("serveur", LocalDateTime.now(), "server ouvert")));
    }

    @Override
    public ArrayList<Message> getMessages() throws RemoteException {
        return message;
    }

    @Override
    public ArrayList<Message> getMessages(int nbLastMessage) throws RemoteException {
        if (nbLastMessage > message.size()){
            return message;
        }else {
            ArrayList<Message> res = new ArrayList<>();
            for (int i = nbLastMessage ; i > 0; i--){
                res.add(message.get(message.size()-i));
            }
            return res;
        }
    }

    @Override
    public void send(String pseudo, String message) {
        this.message.add(new Message(pseudo, LocalDateTime.now(), message));
    }
}
