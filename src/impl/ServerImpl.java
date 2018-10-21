package impl;

import inter.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private ArrayList<String> message;

    public ServerImpl() throws RemoteException {
        super();
        message = new ArrayList<>(Arrays.asList("test1","test2"));
    }

    @Override
    public ArrayList<String> getMessages() throws RemoteException {
        return message;
    }

    @Override
    public void send(String str) {
        message.add(str);
    }
}
