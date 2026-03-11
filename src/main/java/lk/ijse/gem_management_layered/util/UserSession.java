package lk.ijse.gem_management_layered.util;

import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.dto.UserDTO;

public class UserSession {

    private static UserDTO loggedUser;

    // Set currently logged-in user
    public static void setUser(UserDTO user) {
        loggedUser = user;
    }

    // Get currently logged-in user
    public static UserDTO getUser() {
        return loggedUser;
    }

    // Clear session
    public static void clear() {
        loggedUser = null;
    }

}