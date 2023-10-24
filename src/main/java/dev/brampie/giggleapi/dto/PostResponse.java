package dev.brampie.giggleapi.dto;

public abstract class PostResponse {
    public static class Create {
        public String title;
        public String image;
    }
    public static class Update {
        public String title;
        public String image;
    }
    public static class Get {
        public String id;
        public String title;
        public String image;
        public String author;
        public int likes;
        public int dislikes;
    }
}
