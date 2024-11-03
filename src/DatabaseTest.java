import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseTest {

    @Test
    void registerTest() {
        Database database = new Database();
        User user = new User();
        boolean result = database.register(user, "JohnDoe", "UniquePassword");
        Assertions.assertEquals("JohnDoe", user.getUsername());
        Assertions.assertEquals("UniquePassword", user.getPassword());
        Assertions.assertTrue(result);
        Assertions.assertEquals("1,JohnDoe,UniquePassword", database.returnLine("JohnDoe"));

    }

    @Test
    void loginTest() {
        Database database = new Database();
        User user = new User();
        database.register(user, "JaneDoe", "UniquePassword");
        Assertions.assertTrue(database.login("JaneDoe", "UniquePassword"));
    }

    @Test
    void searchUsernameTest() {
        Database database = new Database();
        Assertions.assertTrue(database.searchUsername("JohnDoe"));
    }

    @Test
    void matchPasswordTest() {
        Database database = new Database();
        Assertions.assertTrue(database.matchPassword("JohnDoe", "UniquePassword"));
    }

    @Test
    void insertUserTest() {
        Database database = new Database();
        database.insertUser(-99, "Bob", "UniquePassword");
        Assertions.assertEquals("-99,Bob,UniquePassword", database.returnLine("Bob"));
    }

    @Test
    void getDataTest() {
        Database database = new Database();
        Assertions.assertEquals("1", database.getData(database.returnLine("JohnDoe"), 0));
        Assertions.assertEquals("JohnDoe", database.getData(database.returnLine("JohnDoe"), 1));
        Assertions.assertEquals("UniquePassword", database.getData(database.returnLine("JohnDoe"), 2));
    }

    @Test
    void returnLineTest() {
        Database database = new Database();
        Assertions.assertEquals("1,JohnDoe,UniquePassword", database.returnLine("JohnDoe"));
    }

}