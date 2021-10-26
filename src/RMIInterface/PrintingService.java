package RMIInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public  class PrintingService extends UnicastRemoteObject implements rmiInterface  {
    private TreeMap clients = new TreeMap<String,String>();

    public PrintingService() throws RemoteException {
        super();
    }

    @Override
    public String test1(String name) throws RemoteException {
        return "Hello " + name;
    }

    @Override
    public String LogIn(String name, String password) throws RemoteException {
        init();
        String response = search(name,password);

        return response;
    }


    @Override
    public String print(String filename, String printer) throws RemoteException {
        return  "File " + filename + " started printing at printer " + printer;
    }

    @Override
    public String queue(String printer)  throws RemoteException {
        return  "The queue list of printer " + printer;
    }

    @Override
    public String topQueue(String printer, int job)  throws RemoteException {
        return  "Job " + job + "is moved to the top of the list for printer" + printer;
    }

    @Override
    public String start()  throws RemoteException{
        return  "Printing Started" ;
    }

    @Override
    public String stop()  throws RemoteException {
        return  "Stopping the print server";
    }

    @Override
    public String restart()  throws RemoteException {
        return "Restarting the print server" ;
    }

    @Override
    public String status(String printer)  throws RemoteException {
        return  "Status of printer " + printer;
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return  "Reading config of " + parameter;

    }

    @Override
    public String setConfig(String parameter, String value)   throws RemoteException{
        parameter = value;
        return  "the following parameter " + parameter +" is set to " + value;
    }


    private String search(String username, String password)
    {
        String response = "";

        Set set = clients.entrySet();
        Iterator itr = set.iterator();
        boolean flag = false;

        while(itr.hasNext()){
            response = "";
            Map.Entry entry = (Map.Entry) itr.next();
            String user = entry.getKey().toString();
            String pass = entry.getValue().toString();

            if(username.equals(user)){
                flag = true;
                if(password.equals(pass)){
                    response = "LOGIN_SUCCESFUL";
                }else{
                    response = "PASSWORD_INCORRECT";
                }
                break;
            }
        }
        if(! flag){
            response = "USER_NOT_EXISTS";
        }

        return response;
    }

    private void init() {
        clients.put("user1", 1234567);
        clients.put("admin", "admin");
    }
}