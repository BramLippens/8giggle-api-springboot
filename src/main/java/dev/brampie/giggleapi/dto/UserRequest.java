package dev.brampie.giggleapi.dto;
public abstract class UserRequest {
    public static class Register {
        public String username;
        public String email;
        public String password;
    }

    public static class Login {
        public String username;
        public String password;
    }
    public static class Update {
        public String username;
        public String email;
        public String password;
        public String profilePicture;
    }
}
