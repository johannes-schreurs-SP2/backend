package be.ehb.sp2.backend.model;

public class LoginResponse {

    private String authToken;
    private String userId;

    public LoginResponse() {}

    public String getUserId() {
        return userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public void setUserId(String userId) { this.userId = userId; }
}
