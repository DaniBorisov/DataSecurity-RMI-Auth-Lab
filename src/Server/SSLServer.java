package Server;

import Database.Database;
import RMIInterface.PrintingService;
import RMIInterface.rmiInterface;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SSLServer extends PrintingService {

    private static int Port = 5098;
    private static int SSlSocketPort = 5051;

    protected SSLServer() throws RemoteException {
        super();
    }


    public static void main(String[] args) throws RemoteException {
        System.out.println("Server Starting... beep boop");

        try {
            Registry registry = LocateRegistry.createRegistry(Port);
            PrintingService printService = new PrintingService();
            registry.rebind("service",printService);

            System.err.println("Server ready");

        } catch (Exception e){
            System.out.println("Server e" + e.toString());
            e.printStackTrace();
        }
    }
}

