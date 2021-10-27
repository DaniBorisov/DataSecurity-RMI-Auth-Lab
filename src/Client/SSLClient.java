package Client;

import Database.Job;
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
    private static Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    private static String username = "";
    private static String pass = "";

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        System.err.println("Client started");

        try{
            Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostAddress(), Port);
            rmiInterface service = (rmiInterface) registry.lookup("service");

            boolean loggedIn = false;
            while (loggedIn == false) {
                try {
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();  // Read user input
                    System.out.print("Enter password: ");
                    pass = scanner.nextLine();  // Read user input

                    String response = service.LogIn(username, pass);

                    if (response.matches("LOGIN_SUCCESSFUL")) {
                        loggedIn = true;
                        System.out.println("response from server is: " + response);  // Output user input
                        System.out.println("Printers names: p1 -- p2 -- p3.");
                    }
                    else {
                        loggedIn = false;
                        System.out.println("response from server is: " + response);  // Output user input
                    }

                } catch (Exception e) {
                    System.err.println("Client exception" + e.toString());
                    e.printStackTrace();
                }
            }
            boolean isRunning = true;
            boolean shouldRepeat = true;
           // while (shouldRepeat == true) {
            do {
                System.out.println("Enter command - enter 'help' for command list.");
                String command = scanner.nextLine();  // Read user input
                System.out.println("Command is: " + command);  // Output user input
                String printer = "";

                switch (command) {
                    case  "help":
                        System.out.println("########################################\n"+
                                "print - prints file filename on a specified printer\n" +
                                "queue - lists the print queue for a given printer\n" +
                                "topQueue - moves job to the top of the queue\n" +
                                "start - starts the print server\n" +
                                "stop - stops the print server\n" +
                                "restart - stops the print server, clears the print queue and starts the print server again\n" +
                                "status - prints the status of a printer\n" +
                                "readConfig - prints the value of the parameter\n" +
                                "setConfig - sets the parameter to value\n" +
                                "########################################");

                        break;
                    case "print":
                        System.out.print("What file you want to print? ");
                        String file = scanner.nextLine();  // Read user input
                        System.out.print("Which printer you want to use? ");
                        printer  = scanner.nextLine();  // Read user input
                        System.out.println(service.print(file, printer, username));
                        shouldRepeat = false;

                        break;
                    case "queue":
                        System.out.print("Which printer you want to check? ");
                        printer = scanner.nextLine();  // Read user input
                        System.out.println();service.queue(printer);
                        shouldRepeat = false;
                        break;
                    case "topQueue":
                        System.out.print("Which printer's queue you want to modify? ");
                        printer = scanner.nextLine();  // Read user input
                        System.out.print("Which job you want? ");
                        int job = scanner.nextInt();
                        System.out.println(service.topQueue(printer, job));
                        shouldRepeat = false;
                        break;
                    case "start":
                        System.out.println(service.start());
                        shouldRepeat = false;
                        break;
                    case "stop":
                        System.out.println(service.stop());
                        shouldRepeat = false;
                        isRunning = false;
                        break;
                    case "restart":
                        System.out.println(service.restart());
                        shouldRepeat = false;
                        break;
                    case "status":
                        System.out.print("Which printer's status you want to know? ");
                        printer = scanner.nextLine();  // Read user input
                        System.out.println(service.status(printer));
                        shouldRepeat = false;
                        break;
                    case "readConfig":
                        System.out.print("Which parameter's value you want to see? ");
                        String parameter = scanner.nextLine();  // Read user input
                        System.out.println(service.readConfig(parameter));
                        shouldRepeat = false;
                        break;
                    case "setConfig":
                        System.out.print("What param you want to set? ");
                        parameter = scanner.nextLine();  // Read user input
                        System.out.print("To what value you want to set it? ");
                        String value = scanner.nextLine();
                        System.out.println(service.setConfig(parameter, value));
                        shouldRepeat = false;
                        break;
                    default:
                        shouldRepeat = true;
                        System.out.println("Command unclear - Try again.");
                }
            } while (true);


//            KeyStore ks = KeyStore.getInstance("JKS");
//
//            String KeyPas = "1234567";
//
//            char[] password = pass.toCharArray();
//            System.out.println(password);
//
//            String path = "C:\\Users\\Dani\\.keystore";
//            InputStream ksIs = new FileInputStream(path);
//
//            ks.load(ksIs, "password".toCharArray());
//
//            System.out.println(ks.getKey("ca",password ));

        }catch (Exception e){
            System.err.println("Client exception" + e.toString());
            e.printStackTrace();
        }

//      public void startSession (){
//            boolean loggedIn = false;
//            while (loggedIn == false) {
//                try {
//                    System.out.println("Enter username:  ");
//                    username = scanner.nextLine();  // Read user input
//                    System.out.println("Enter pass ");
//                    pass = scanner.nextLine();  // Read user input
//
//                    String response = service.LogIn(username, pass);
//
//                    if (response.matches("LOGIN_SUCCESSFUL")) {
//                        loggedIn = true;
//                        System.out.println("response from server is: " + response);  // Output user input
//                        System.out.println("Printers names: p1 -- p2 -- p3.");
//                    }
//                    else {
//                        loggedIn = false;
//                        System.out.println("response from server is: " + response);  // Output user input
//                    }
//
//                } catch (Exception e) {
//                    System.err.println("Client exception" + e.toString());
//                    e.printStackTrace();
//                }
//            }
//        }
    }


}
