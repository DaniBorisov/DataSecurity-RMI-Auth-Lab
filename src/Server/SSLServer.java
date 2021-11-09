package Server;

import PrintingServiceInterface.LogIn;
import PrintingServiceInterface.PrintingService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SSLServer extends PrintingService  {

    private static int Port = 5098;
    private static int SSlSocketPort = 5051;

    protected SSLServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException {
        System.out.println("Server Starting... beep boop");
        Registry registry = LocateRegistry.createRegistry(Port);
        try {
            LogIn login = new LogIn();
            registry.rebind("logIn",login);
            System.err.println("Server ready");
            PrintingService printService = new PrintingService();
            registry.rebind("service",printService);
        } catch (Exception e){
            System.out.println("Server e" + e.toString());
            e.printStackTrace();
        }
    }




    private void StartPrintingService(Registry registry) throws RemoteException {
        PrintingService printService = new PrintingService();
        registry.rebind("service",printService);
    }
}

