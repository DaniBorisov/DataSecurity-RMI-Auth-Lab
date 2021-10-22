package Client;

import RMIInterface.rmiInterface;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class SSLClient {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        String text = JOptionPane.showInputDialog("Hello there");


        try{

            Registry registry = LocateRegistry.getRegistry(null);

            rmiInterface stub =(rmiInterface) registry.lookup("Hello");

            stub.test1();
        }catch (Exception e){
            System.err.println("Client exception" + e.toString());
            e.printStackTrace();
        }


//        SSLSocketFactory sslsf = (SSLSocketFactory) SSLSocketFactory.getDefault();
//        SSLSocket socket = (SSLSocket) sslsf.createSocket("localhost", 5099);


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

//        SSLParameters sslp = socket.getSSLParameters();
//
//
//        String[] ClientAPs = {"Hello", "There"};
//        sslp.setApplicationProtocols(ClientAPs);
//
//
//        socket.setSSLParameters(sslp);
//
//        socket.startHandshake();
//
//        String somthing = socket.getApplicationProtocol();
//
//        System.out.println(" printing Client side AP" + somthing);
//
//        InputStream IS = socket.getInputStream();
//        OutputStream OS = socket.getOutputStream();
//
//        OS.write(280);
//        OS.flush();
//        IS.read();
//        socket.close();
    }


}
