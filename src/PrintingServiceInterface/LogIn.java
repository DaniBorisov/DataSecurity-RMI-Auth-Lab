package PrintingServiceInterface;

import Database.Database;
import Database.Users;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;



public class LogIn extends UnicastRemoteObject implements LogInInterface {

    private Database dbase = new Database();
    private TreeMap Users = new TreeMap<String, String>();

    public LogIn() throws RemoteException {
        super();
        System.out.println("LogIn Service started");
//        initUSer();
    }

    @Override
    public boolean LogIn(String name, String password) throws NoSuchAlgorithmException, IOException {
//        initUSer();
        System.out.println("User " + name + " is trying to log in!");
        String response = userSearch(name, password);
        System.out.println(response);
        if (response.matches("LOGIN_SUCCESSFUL"))
            return true;
        return false;
    }

    private String userSearch(String username, String password) throws NoSuchAlgorithmException, IOException {
        String response = "";
//        Set set = Users.entrySet();
//        Iterator itr = set.iterator();
        String shapass = sha1(password);
//        boolean LoggedIn = false;
//        while (itr.hasNext()) {
//            response = "";
//            Map.Entry entry = (Map.Entry) itr.next();
//            String user = entry.getKey().toString();
//            String pass = entry.getValue().toString();
            if (shapass.equals(dbase.getPassword(username))) {
                response = "LOGIN_SUCCESSFUL";
            }
            else
            {
                response = "PASSWORD_INCORRECT";
            }
//                break;
//            }
//        }
//        if (!LoggedIn) {
//            response = "USER_DOES_NOT_EXISTS";
//        }
        return response;
    }

//    private void initUSer() {
//        for (Users user : dbase.getUserList()) {
//            Users.put(user.getUsername(), user.getPassword());
//        }
//    }

    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}

