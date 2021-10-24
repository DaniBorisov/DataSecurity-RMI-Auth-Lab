package Server;

import RMIInterface.ImplExample;
import RMIInterface.rmiInterface;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SSLServer extends ImplExample {


    public static void main(String[] args) {

        System.out.println("Server Starting... beep boop");
        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
//simple connection
            ImplExample obj = new ImplExample();
            rmiInterface stub = (rmiInterface) UnicastRemoteObject.exportObject(obj,5099);


            Registry registry = LocateRegistry.createRegistry(5099);

            registry.rebind("Hello" , stub);

            System.err.println("Server ready");

//            socket connection
//            SSLContext ctx = SSLContext.getInstance("TLSv1.2");



            SSLServerSocket sSocket = (SSLServerSocket) factory.createServerSocket(5050);

            sSocket.setNeedClientAuth(false);

            sSocket.setEnabledCipherSuites(new String[]{
                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_anon_WITH_RC4_128_SHA"});
            sSocket.setEnabledProtocols(new String[] {"TLSv1.2"});



            SSLSocket socket = (SSLSocket) sSocket.accept();

//            while (true)
//            {
//                System.out.println("dsaads");
//
//                try(SSLSocket socket = (SSLSocket) sSocket.accept()) {
//                    PrintWriter print = new PrintWriter(socket.getOutputStream(),true);
//                    print.println("Something happened ??");
//
//                }
//            }

            SSLParameters sslp = socket.getSSLParameters();
            String[] ServerAPs = {"Hello"};
            sslp.setApplicationProtocols(ServerAPs);


            socket.startHandshake();

//            socket.setSSLParameters(sslp);
//            String somthing = socket.getApplicationProtocol();
//            System.out.println(" printing Server side AP" + somthing);
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



    }


}
