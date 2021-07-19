package yuce.kerem.thesis.dto.payload.request;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/21/21 8:11 PM
 */
public class LoginRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
