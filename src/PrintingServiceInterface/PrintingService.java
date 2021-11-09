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
    public String print(String filename, String printer,String username) throws RemoteException {

        if(dbase.getCommands(username, "print"))
        {
            Job job = new Job(filename, username, jobCounter);
            for (Printer p : printers) {
                if (printer.matches(p.getPrinterName())) {
                    p.PutJobInPrinter(job);
                    jobCounter++;
                }
            }
            System.out.println("File: " + filename + " , added for printing at printer " + printer + " by user " + username);
            return "File " + filename + " started printing at printer " + printer;
        }
        else {
            return "Permission Denied";
        }
    }

    @Override
    public String queue(String printer,String username)  throws RemoteException {
        if(dbase.getCommands(username, "queue")) {
            List<Job> jobs = new ArrayList<>();
            for (Printer p : printers) {
                if (printer.matches(p.getPrinterName())) {
                    jobs = p.getJobsInQueue();
                }
            }
            System.out.println("The queue list of " + printer + ": ");
            String queueString = "";

            for (int i = 0; i < jobs.size(); i++) {
                queueString += "\n" + "Job #" + i + " - Filename: " + jobs.get(i).getFileName() + " - ID: " + jobs.get(i).getID();
            }
            ;
            System.out.println(queueString);
            return queueString;
        }else {
            return "Permission Denied";
        }
    }

    @Override
    public String topQueue(String printer, int job,String username)  throws RemoteException {
        if(dbase.getCommands(username, "topQueue")) {
            for (Printer p : printers) {
                if (printer.matches(p.getPrinterName())) {
                    p.moveToTop(job);
                }
            }
            return "Job " + job + "is moved to the top of the list for printer" + printer;
        }else {
            return "Permission Denied";
        }

    }

    @Override
    public String start(String username) throws RemoteException{
        if(dbase.getCommands(username, "start")) {
            return "Print Server Started";
        }else {
            return "Permission Denied";
        }
    }

    @Override
    public String stop(String username) throws RemoteException {
        if(dbase.getCommands(username, "stop")) {
            return  "Stopping the print server";
        }else {
            return "Permission Denied";
        }
    }

    @Override
    public String restart(String username)  throws RemoteException
    {
        if(dbase.getCommands(username, "restart")) {
            for (Printer p : printers) {
                p.clearQueue();
            }
            return "Restarting the print server";
        }else {
                return "Permission Denied";
            }
    }

    @Override
    public String status(String printer, String username)  throws RemoteException {
        if(dbase.getCommands(username, "status")) {
            return  "Status of printer " + printer;
        }else {
            return "Permission Denied";
        }
    }

    @Override
    public String readConfig(String parameter, String username) throws RemoteException {
        if(dbase.getCommands(username, "readConfig")) {
            return  "Reading config of " +"\0"+ parameter;
        }else {
            return "Permission Denied";
        }
    }

    @Override
    public String setConfig(String parameter, String value, String username)   throws RemoteException{
        if(dbase.getCommands(username, "setConfig")) {
            return  "the following parameter " + parameter +" is set to " + value;
        }else {
            return "Permission Denied";
        }
    }

    private void initPrinter() {
        for (Printer printer :  dbase.getPrinterList())
        {
            printers.add(printer);
        }
    }
}
