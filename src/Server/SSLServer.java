package Server;

import RMIInterface.ImplExample;
import RMIInterface.rmiInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SSLServer extends ImplExample {


    public static void main(String[] args) {

        System.out.println("Server Starting... beep boop");

        try {

            ImplExample obj = new ImplExample();
            rmiInterface stub = (rmiInterface) UnicastRemoteObject.exportObject(obj,0);


            System.out.println("stub");

            Registry registry = LocateRegistry.getRegistry();
            System.out.println("registy");

            registry.rebind("Hello" , stub);
            System.out.println("binding");
            System.err.println("Server ready");

        }
        catch (Exception e){
            System.out.println("Server e" + e.toString());
            e.printStackTrace();
        }
//
//        SSLContext ctx = SSLContext.getInstance("TLS");
//
////        KeyStore
//
//        KeyStore keyKS = KeyStore.getInstance("PKCS12");
//        keyKS.
//
//        // Generate KeyManager
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("PKIX");
//        kmf.init(keyKS, "password".toCharArray());
//        KeyManager[] kms = kmf.getKeyManagers();
//
//        // Code to substitute MyX509ExtendedKeyManager
//        if (!(kms[0] instanceof X509ExtendedKeyManager)) {
//            throw new Exception("kms[0] not X509ExtendedKeyManager");
//        }
//
//        // Create a new KeyManager array and set the first index
//        // of the array to an instance of MyX509ExtendedKeyManager.
//        // Notice how creating this object is done by passing in the
//        // existing default X509ExtendedKeyManager
//        kms = new KeyManager[] {
//                new MyX509ExtendedKeyManager((X509ExtendedKeyManager) kms[0])};
//
//        // Initialize SSLContext using the new KeyManager
//        ctx.init(kms, null, null);
//
//
//
////        SSLServerSocketFactory SFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//        SSLServerSocketFactory SFactory = ctx.getServerSocketFactory();
//        SSLServerSocket sSocket = (SSLServerSocket) SFactory.createServerSocket(5099);
//
//        System.out.println("Server started.... beep booop");
//        SSLSocket socket = (SSLSocket) sSocket.accept();
//
//        SSLParameters sslp = socket.getSSLParameters();
//        String[] ServerAPs = {"Hello"};
//        sslp.setApplicationProtocols(ServerAPs);
//
//        socket.setSSLParameters(sslp);
//
//        socket.startHandshake();
//
//        String somthing = socket.getApplicationProtocol();
//
//        System.out.println(" printing Server side AP" + somthing);
//
//        InputStream IP = socket.getInputStream();
//        OutputStream OP = socket.getOutputStream();
//
//        IP.read();
//        OP.write(85);
//        OP.flush();
//        socket.close();
//        sSocket.close();
//


    }


}
