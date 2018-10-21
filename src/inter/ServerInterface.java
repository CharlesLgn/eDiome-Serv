package inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{

    ArrayList<String> getMessages() throws RemoteException;
    void send(String str)           throws RemoteException;

}
