package dev.brampie.giggleapi.dto;

public abstract class UserResponse {
    public static class Register {
        public String token;
    }
    public static class Login {
        public String token;
    }
    public static class Update {
        public String username;
        public String email;
        public String profilePicture;
    }
}
