package PrintingServiceInterface;

import Database.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;



public  class PrintingService extends UnicastRemoteObject implements PrintingInterface {
    private TreeMap Users = new TreeMap<String,String>();
    private List<Printer> printers = new ArrayList<>();
    private Database dbase = new Database();
    int jobCounter = 1;

    public PrintingService() throws RemoteException {
        super();
        System.out.println("Printing Server started");
        initPrinter();
    }

    @Override
    public String LogIn(String name, String password) throws RemoteException {
        initUSer();
        String response = userSearch(name,password);
        return response;
    }


    @Override
    public String print(String filename, String printer,String username) throws RemoteException {
        Job job = new Job(filename,username,jobCounter);
        for (Printer p : printers)
        {
            if(printer.matches(p.getPrinterName())) {
                p.PutJobInPrinter(job);
                jobCounter++;
            }
        }
        System.out.println("File " + filename + " added for printing at printer " + printer);
        return  "File " + filename + " started printing at printer " + printer;
    }

    @Override
    public String queue(String printer)  throws RemoteException {
        List<Job> jobs = new  ArrayList<>();
        for (Printer p : printers)
        {
            if(printer.matches(p.getPrinterName())) {
                jobs = p.getJobsInQueue();
            }
        }
        System.out.println("The queue list of " + printer + ": ");
        String queueString = "";

        for (int i = 0 ; i <jobs.size() ; i++) {
            queueString += "\n" +"Job #"+ i + " - Filename: "+ jobs.get(i).getFileName() + " - ID: " + jobs.get(i).getID();
        };
        System.out.println(queueString);
        return queueString;
    }

    @Override
    public String topQueue(String printer, int job)  throws RemoteException {
        for (Printer p : printers)
        {
            if(printer.matches(p.getPrinterName()))
            {
               p.moveToTop(job);
            }
        }
        return  "Job " + job + "is moved to the top of the list for printer" + printer;
    }

    @Override
    public String start() throws RemoteException{
        return  "Print Server Started" ;
    }

    @Override
    public String stop() throws RemoteException {
        return  "Stopping the print server";
    }

    @Override
    public String restart()  throws RemoteException
    {
        for (Printer p : printers)
        {
            p.clearQueue();
        }
        return "Restarting the print server" ;
    }

    @Override
    public String status(String printer)  throws RemoteException {
        return  "Status of printer " + printer;
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return  "Reading config of " +"\0"+ parameter;

    }

    @Override
    public String setConfig(String parameter, String value)   throws RemoteException{
        return  "the following parameter " + parameter +" is set to " + value;
    }


    private String userSearch(String username, String password)
    {
        String response = "";

        Set set = Users.entrySet();
        Iterator itr = set.iterator();
        boolean LoggedIn = false;

        while(itr.hasNext()){
            response = "";
            Map.Entry entry = (Map.Entry) itr.next();
            String user = entry.getKey().toString();
            String pass = entry.getValue().toString();

            if(username.equals(user)){
                LoggedIn = true;
                if(password.equals(pass)){
                    response = "LOGIN_SUCCESSFUL";
                }else{
                    response = "PASSWORD_INCORRECT";
                }
                break;
            }
        }
        if(! LoggedIn){
            response = "USER_DOES_NOT_EXISTS";
        }

        return response;
    }

    private void initUSer() {
        for (Users user :  dbase.getUserList())
        {
            Users.put(user.getUsername(), user.getPassword());

        }
    }
    private void initPrinter() {
        for (Printer printer :  dbase.getPrinterList())
        {
            printers.add(printer);
        }
    }
}
