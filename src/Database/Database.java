package Database;

import java.util.ArrayList;

public class Database {

    Users user1 = new Users("user1","1234567");
    Users user2 = new Users("user2","abcdefg");
    Users user3 = new Users("user3","7654321");
    Users user4 = new Users("admin","admin");

    Printer printer1 = new Printer("p1");
    Printer printer2 = new Printer("p2");
    Printer printer3 = new Printer("p3");

    public ArrayList<Users> getUserList()
    {
        ArrayList<Users> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
       return  users;
    }
    public ArrayList<Printer> getPrinterList()
    {
        ArrayList<Printer> printers = new ArrayList<>();
        printers.add(printer1);
        printers.add(printer2);
        printers.add(printer3);
        return  printers;
    }
}
