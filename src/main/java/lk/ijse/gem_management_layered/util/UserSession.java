package lk.ijse.gem_management_layered.util;
import lk.ijse.gem_management_layered.dto.UserDTO;

 public class UserSession {

        private static UserDTO loggedUser;

        public static void setUser(UserDTO user) {

            loggedUser = user;

        }

        public static UserDTO getUser() {

            return loggedUser;

        }

        public static void clear() {

            loggedUser = null;

        }

}
