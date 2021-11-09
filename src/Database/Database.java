package Database;

import java.util.ArrayList;

public class Database {


    Users user1 = new Users("alice","522b276a356bdf39013dfabea2cd43e141ecc9e8", "admin",new String[] {"print","queue","topQueue","start","stop","restart","status","readConfig","setConfig"});
    Users user2 = new Users("bob","48181acd22b3edaebc8a447868a7df7ce629920a", "technician",new String[] {"start", "stop", "restart", "status", "readConfig", "setConfing"});
    Users user3 = new Users("cecilia","6e4ca0dced8ff091780a3f13375642645960e0b6", "power user",new String[]{"print", "queue", "topQueue", "restart"});
    Users user4 = new Users("david", "aa743a0aaec8f7d7a1f01442503957f4d7a2d634", "user", new String[]{"print","queue"});
//    Users user5 = new Users("Erica", "Erica", "user", new String[]{"print","queue"});
//    Users user6 = new Users("Fred", "Fred", "user", new String[]{"print","queue"});
//    Users user7 = new Users("George", "George", "user", new String[]{"print","queue"});


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
//        users.add(user5);
//        users.add(user6);
//        users.add(user7);
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

    public boolean getCommands(String username, String command)
    {
        for (Users u : getUserList())
        {
            if (u.getUsername().matches(username))
            {
                for (String s : u.getCommands()) {
                    if (s.matches(command))
                        return true;
                }
            }
        }
        return false;
    }
}
