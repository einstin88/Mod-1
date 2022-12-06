package practice.users;

import java.util.HashMap;
import java.util.LinkedList;

import practice.shop.Cart;

public class Users {
    /*
     * Class to store users who have logged in
     */
    private LinkedList<String> registeredUsers;
    private HashMap<String, Cart> users = new HashMap<>();

    public Users() {
        this.registeredUsers = new LinkedList<>();
    }

    // Function to log a user in
    public void LoginAction(String user) {
        if (users.containsKey(user)) {
            System.out.println(user + " has already logged in.");
        } else {

        }
        registeredUsers.add(user);
    }

    public void ListUsers() {

    }
}
