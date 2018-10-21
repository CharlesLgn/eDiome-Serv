import impl.MenuImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
        public static void main(String[] args) {
            try {
                int port = 8000;
                LocateRegistry.createRegistry(port);
                Naming.rebind("rmi://localhost:"+port+"/menu", new MenuImpl());
                System.out.println("server ready");
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("echec : " + e);
            }
        }
}
