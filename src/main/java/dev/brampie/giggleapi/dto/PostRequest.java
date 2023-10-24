package dev.brampie.giggleapi.dto;

public abstract class PostRequest {
    public class Create {
        public String title;
        public String image;
    }

    public class Upvote {
        public String id;
    }

    public class Downvote {
        public String id;
    }
}
