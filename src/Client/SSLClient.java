package Client;

import RMIInterface.rmiInterface;

import javax.net.ssl.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyFactory;
import java.security.KeyStore;


public class SSLClient {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        System.out.println("Client started");
        String text = "Testing";

//                JOptionPane.showInputDialog("What is your name? ");
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();


        try{

            Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostAddress(),5099);

            rmiInterface stub =(rmiInterface) registry.lookup("Hello");

            System.out.println(stub.test1(text));

//            socket connection

            SSLSocket socket = (SSLSocket) factory.createSocket(InetAddress.getLocalHost().getHostAddress(), 5050);

            socket.setEnabledCipherSuites(new String[]{
                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                    "TLS_ECDH_anon_WITH_RC4_128_SHA"});

            socket.setEnabledProtocols(new String[] {"TLSv1.2"});

            SSLContext ctx = SSLContext.getInstance("TLSv1.2");



            KeyStore ks = KeyStore.getInstance("JKS");

            String pass = "1234567";

            char[] password = pass.toCharArray();

//            System.out.println( ks.getKey("ca", password));
//            InputStream ksIs = new FileInputStream("");


            ks.load(null, "password".toCharArray());

//            try {
//                ks.load(ksIs, "password".toCharArray());
//            } finally {
//                if (ksIs != null) {
//                    ksIs.close();
//                }
//            }


            System.out.println(ks.getKey("ca", password));

            System.out.println("TESTS");


            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                    .getDefaultAlgorithm());
            kmf.init(ks, "password".toCharArray());






            ctx.init(kmf.getKeyManagers(),null,null);

//            SSLParameters sslp = socket.getSSLParameters();




//            String[] ClientAPs = {"Hello", "There"};
//            sslp.setApplicationProtocols(ClientAPs);
//
//
//            socket.setSSLParameters(sslp);

            socket.startHandshake();
            System.err.println("DONE");
//            System.out.println("socket.startHandshake");
//
//            String somthing = socket.getApplicationProtocol();
//
//            System.out.println(" printing Client side AP" + somthing);
//
//            InputStream IS = socket.getInputStream();
//            OutputStream OS = socket.getOutputStream();
//
//            OS.write(280);
//            OS.flush();
//            IS.read();
////            socket.close();



        }catch (Exception e){
            System.err.println("Client exception" + e.toString());
            e.printStackTrace();
        }




//        KeySpec clientPubKey = new KeySpec() ;
//        KeyFactory keyFactory = KeyFactory.getInstance("DiffieHellman");
//        int clientPass = 123456;
//        String pass = "password";
//
//        KeyStore keyStore = (KeyStore) KeyStore.getInstance(pass);
//
//
//
//
//        Signature sig = Signature.getInstance("DiffieHellman");
//        sig.sign();
//
//
//        String pass = "password";
//        System.setProperty("javax.net.ssl.debug", "all");
//        System.setProperty("javax.net.ssl.keyStore", "C:\\ssl\\clientkeystore.jks");
//        System.setProperty("javax.net.ssl.keyStorePassword", pass);
//        System.setProperty("javax.net.ssl.trustStore", "C:\\ssl\\clienttruststore.jks");
//        System.setProperty("javax.net.ssl.trustStorePassword", pass);

    }


}
