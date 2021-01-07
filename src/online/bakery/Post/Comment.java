package online.bakery.Post;

import online.bakery.Admin;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Comment {
    final private int id;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    final private int authorId;
    final private int postId;
    String text;

    public Comment(String text,int authorId,int postId) {
        this.id = atomicInteger.incrementAndGet();
        this.text = text;
        this.authorId = authorId;
        this.postId = postId;
        Admin.getInstance().addComment(this);
    }

    public String getText() {
        return text;
    }


    public int getAuthorId() {
        return authorId;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }
}
