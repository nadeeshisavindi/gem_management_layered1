package lk.ijse.gem_management_layered.dto;

public class UserDTO {

    private int userId;
    private String username;
    private String password;
    private String role;

    public UserDTO() {
    }

    // SAVE
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // UPDATE / TABLE
    public UserDTO(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // FULL
    public UserDTO(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }


}