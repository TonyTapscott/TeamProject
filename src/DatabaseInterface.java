public interface DatabaseInterface {
    boolean register(User user, String username, String password);
    boolean login(String username, String password);
    boolean searchUsername(String username);
    boolean matchPassword(String username, String password);
    void insertUser(int id, String username, String password);
    String getData(String line, int element);
    String returnLine(String username);

}
