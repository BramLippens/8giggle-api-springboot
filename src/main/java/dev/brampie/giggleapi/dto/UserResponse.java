package dev.brampie.giggleapi.dto;

public abstract class UserResponse {
    public static class Login {
        public String id;
        public String email;
        public String username;
        public String accessToken;
    }
    public static class Update {
        public String username;
        public String email;
        public String profilePicture;
    }
}
