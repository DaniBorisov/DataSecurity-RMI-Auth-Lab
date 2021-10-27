package Database;

import java.util.Random;

public class Job {


        private String Username;
        private String FileName;
        private int ID;

    public Job (String filename, String username,int ID)
        {
            this.FileName = filename;
            this.Username = username;
            this.ID = ID;
        }

        public String getUsername()
        {
            return this.Username;
        }

        public String getFileName()
        {
            return this.FileName;
        }

        public int getID()
        {
            return this.ID;
        }
}
