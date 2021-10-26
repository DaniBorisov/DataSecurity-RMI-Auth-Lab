package Client;

import RMIInterface.rmiInterface;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyStore;
import java.util.Scanner;


public class SSLClient {

    private static int Port = 5098;
    private static int SSlSocketPort = 5051;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        System.out.println("Client started");

        try{
            Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostAddress(),Port);
            rmiInterface service = (rmiInterface) registry.lookup("service");

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter username ");
            String username = scanner.nextLine();  // Read user input
            System.out.println("Enter pass ");
            String pass = scanner.nextLine();  // Read user input

            String response = service.LogIn(username,pass);

            System.out.println("response from server is: " + response);  // Output user input


            System.out.println("Enter command");

            String command = scanner.nextLine();  // Read user input
            System.out.println("Command is: " + command);  // Output user input



            String printer = "";
                switch (command) {
                    case "print":
                        System.out.println("What file you want to print? ");
                        String file = scanner.nextLine();  // Read user input
                        System.out.println(service.print(file, "printer1"));
                        break;
                    case "hello":
                        System.out.println("Which printer you want to check? ");
                        printer = scanner.nextLine();  // Read user input
                        System.out.println(service.queue(printer));
                        break;
//                    case "queue":
//                        rmiInterface stubQueue = (rmiInterface) registry.lookup("Queue");
//                        printer = JOptionPane.showInputDialog("Which printer you want to check? ");
//                        System.out.println(stubQueue.queue(printer));
//                       isBad = false;
//                        break;
//                    case "topQueue":
//                        rmiInterface stubTopQueue = (rmiInterface) registry.lookup("TopQueue");
//                        printer = JOptionPane.showInputDialog("Which printer's queue you want to modify? ");
//                        int job = Integer.parseInt(JOptionPane.showInputDialog("Which job you want? "));
//                        System.out.println(stubTopQueue.topQueue(printer, job));
//                        isBad = false;
//                        break;
//                    case "start":
//                        rmiInterface stubStart = (rmiInterface) registry.lookup("Start");
//                        System.out.println(stubStart.start());
//                        isBad = false;
//                        break;
//                    case "stop":
//                        rmiInterface stubStop = (rmiInterface) registry.lookup("Stop");
//                        System.out.println(stubStop.stop());
//                        isBad = false;
//                        break;
//                    case "restart":
//                        rmiInterface stubRestart = (rmiInterface) registry.lookup("Restart");
//                        System.out.println(stubRestart.restart());
//                        isBad = false;
//                        break;
//                    case "status":
//                        rmiInterface stubStatus = (rmiInterface) registry.lookup("Status");
//                        printer = JOptionPane.showInputDialog("Which printer's status you want to know? ");
//                        System.out.println(stubStatus.status(printer));
//                        isBad = false;
//                        break;
//                    case "readConfig":
//                        rmiInterface stubReadConfig = (rmiInterface) registry.lookup("ReadConfig");
//                        System.out.println(stubReadConfig.readConfig("Whatever "));
//                        isBad = false;
//                        break;
//                    case "setConfig":
//                        rmiInterface stubSetConfig = (rmiInterface) registry.lookup("SetConfig");
//                        String param = JOptionPane.showInputDialog("What param you want to set?");
//                        String value = JOptionPane.showInputDialog("T what value you want to set it? ");
//                        System.out.println(stubSetConfig.setConfig(param, value));
//                        isBad = false;
//                        break
                    default:
                        // code block
                        System.out.println("Well... I think there is a typo ;)");
                        break;
                }




//            socket connection
//
//            SSLSocket socket = (SSLSocket) factory.createSocket(InetAddress.getLocalHost().getHostAddress(), SSlSocketPort);
//
//            socket.setEnabledCipherSuites(new String[]{
//                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_anon_WITH_RC4_128_SHA"});
//
//            socket.setEnabledProtocols(new String[] {"TLSv1.2"});
//
//            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
//
//

            KeyStore ks = KeyStore.getInstance("JKS");

            String KeyPas = "1234567";

            char[] password = pass.toCharArray();
            System.out.println(password);

//            System.out.println( ks.getKey("ca", password));
            String path = "C:\\Users\\Dani\\.keystore";
            InputStream ksIs = new FileInputStream(path);

            ks.load(ksIs, "password".toCharArray());

            System.out.println(ks.getKey("ca",password ));

//            System.out.println("TESTS");
//
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
//                    .getDefaultAlgorithm());
//            kmf.init(ks, "password".toCharArray());
//
//            ctx.init(kmf.getKeyManagers(),null,null);
//
//            SSLParameters sslp = socket.getSSLParameters();
//            String[] ClientAPs = {"Hello", "There"};
//            System.out.println(" printing Client side AP:   " + ClientAPs);
//            sslp.setApplicationProtocols(ClientAPs);
//
//            socket.setSSLParameters(sslp);
//
////            socket.startHandshake();
//            System.err.println("DONE");
////            System.out.println("socket.startHandshake");
////
//            String somthing = socket.getApplicationProtocol();
//            System.out.println(" printing Client side AP" + somthing);
////
//            InputStream IS = socket.getInputStream();
//            OutputStream OS = socket.getOutputStream();
//
//            OS.write(280);
//            OS.flush();
//            IS.read();
//            socket.close();

        }catch (Exception e){
            System.err.println("Client exception" + e.toString());
            e.printStackTrace();
        }
    }


}
