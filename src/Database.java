import java.io.*;

public class Database implements DatabaseInterface {

    private static final Object lock = new Object();


    //Return true if user successfully registers
    public boolean register(User user, String username, String password) {
        synchronized (lock) {
            if (!(searchUsername(username))) {
                user.setUsername(username);
                user.setPassword(password);
                insertUser(user.getId(), username, password);
                return true;
            }
        }
        System.out.println("already registered");
        return false;
    }

    //Returns true if user successfully logins
    public boolean login(String username, String password) {
        return (searchUsername(username) && matchPassword(username, password));
    }

    //Returns if a specific username exists in 'userData.txt'
    public boolean searchUsername(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
            String line;
            String usernames;
            while((line = reader.readLine()) != null) {
                usernames = getData(line, 1);
                if(usernames.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    //
    public boolean matchPassword(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
            String line;
            String usernames;
            String passwords;
            while((line = reader.readLine()) != null) {
                usernames = getData(line, 1);
                passwords = getData(line, 2);
                if(usernames.equals(username) && passwords.equals(password)) {
                    return true;
                }
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

    //Inserts the users information into 'userData.txt'
    public void insertUser(int id, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt", true))) {
            writer.write(String.format("%d,%s,%s", id, username, password));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns the chosen data(id, username, or password)
    public String getData(String line, int element) {
        if(element == 0) {
            return line.substring(0, line.indexOf(","));
        }
        else if(element == 1) {
            line = line.substring(line.indexOf(",") + 1);
            return line.substring(0, line.indexOf(","));
        }
        else if(element == 2) {
            line = line.substring(line.indexOf(",") + 1);
            return line.substring(line.indexOf(",") + 1);
        }
        return null;
    }

    //Returns the line in 'userData.txt' that contains the specific username
    public String returnLine(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                if(getData(line, 1).equals(username)) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
