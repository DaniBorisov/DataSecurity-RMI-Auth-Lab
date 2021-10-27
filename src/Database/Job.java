package Database;

import java.util.Random;

public class Job {


        private String Username;
        private String FileName;
        private int ID = 000;

    public Job (String filename, String username)
        {
            this.FileName = filename;
            this.Username = username;
            this.ID += 1;
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
