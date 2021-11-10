package Database;

import java.util.ArrayList;

public class Database {
    private final ArrayList<String> userCommnads = new ArrayList<>();
    private String passwordFile = "";
    private String RoleFromRBfile = "";

    Printer printer1 = new Printer("p1");
    Printer printer2 = new Printer("p2");
    Printer printer3 = new Printer("p3");

    public ArrayList<Printer> getPrinterList()
    {
        ArrayList<Printer> printers = new ArrayList<>();
        printers.add(printer1);
        printers.add(printer2);
        printers.add(printer3);
        return  printers;
    }

    public String getPassword(String username)
    {
        passwordFile = "";
        return getPasswordFromFile(username);
    }

    public boolean checkCommands(String username, String command)
    {
        commandsFromFile(username);
        for (String s : userCommnads) {
            if (s.matches(command)) {
                userCommnads.clear();
                return true;
            }
        }
        userCommnads.clear();
        System.out.println("User: " + username + " tried using unathorised command: " + command);
        return false;
    }
}
