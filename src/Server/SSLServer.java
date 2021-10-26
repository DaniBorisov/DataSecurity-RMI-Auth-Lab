package Server;

import RMIInterface.PrintingService;
import RMIInterface.rmiInterface;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SSLServer extends PrintingService {

    private static int Port = 5098;
    private static int SSlSocketPort = 5051;

    protected SSLServer() throws RemoteException {
        super();
    }


    public static void main(String[] args) {

        System.out.println("Server Starting... beep boop");

        try {


            Registry registry = LocateRegistry.createRegistry(Port);
            PrintingService printService = new PrintingService();
            registry.rebind("service",printService);

            System.err.println("Server ready");


            //        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//        rmiInterface stub = (rmiInterface) UnicastRemoteObject.exportObject(printService,Port);


//
//            SSLServerSocket sSocket = (SSLServerSocket) factory.createServerSocket(SSlSocketPort);
//
//            sSocket.setNeedClientAuth(true);
//
//            sSocket.setEnabledCipherSuites(new String[]{
//                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_anon_WITH_RC4_128_SHA"});
//
//            sSocket.setEnabledProtocols(new String[] {"TLSv1.2"});
//
//            SSLSocket socket = (SSLSocket) sSocket.accept();
//
//            SSLParameters sslp = socket.getSSLParameters();
//            String[] ServerAPs = {"Hello"};
//            sslp.setApplicationProtocols(ServerAPs);
//
//
////            socket.startHandshake();
//
//            socket.setSSLParameters(sslp);
//            String somthing = socket.getApplicationProtocol();
//            System.out.println(" printing Server side AP  " + somthing);
//
//            InputStream IP = socket.getInputStream();
//            OutputStream OP = socket.getOutputStream();
//
//            IP.read();
//            System.out.println( IP.read() + "gdf");
//            OP.write(85);
//            OP.flush();
//            socket.close();
//            sSocket.close();
        }
        catch (Exception e){
            System.out.println("Server e" + e.toString());
            e.printStackTrace();
        }

    }


//    private void init() {
//        clients.put("user1", 1234);
//        clients.put("admin", "admin");
//    }
}

//        finally {
//
//            System.err.println("Server ready");
//            //simple connection
//
//            registry.rebind("Hello" , stub);
//
//            registry.rebind("Start" , stub);
//            registry.rebind("Stop" , stub);
//            registry.rebind("Queue" , stub);
//            registry.rebind("TopQueue" , stub);
//            registry.rebind("Print" , stub);
//            registry.rebind("Restart" , stub);
//            registry.rebind("Status" , stub);
//            registry.rebind("ReadConfig" , stub);
//            registry.rebind("SetConfig" , stub);
//
//        }