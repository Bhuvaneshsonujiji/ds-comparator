public class UserStorage {
    private static String storedUsername;
    private static String storedPassword;

    public static void saveUser(String username, String password) {
        storedUsername = username;
        storedPassword = password;
    }

    public static boolean authenticate(String username, String password) {
        return storedUsername != null && storedUsername.equals(username) && storedPassword.equals(password);
    }
}
