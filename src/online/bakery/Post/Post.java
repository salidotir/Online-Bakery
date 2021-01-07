package online.bakery.Post;

import online.bakery.Account;
import online.bakery.Admin;
import online.bakery.Confectioner;
import online.bakery.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Post {
    final private int id;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private List<String> images;
    private List<Integer> likeId;
    final private int authorId;
    String caption;

    private Post(String caption,List<String> images,int authorId) {
        this.id = atomicInteger.incrementAndGet();
        this.caption = caption;
        this.images = images;
        this.authorId = authorId;
        Admin.getInstance().addPost(this);
    }

     static Post createPost(Account account, String caption, List<String> images, int authorId){
        if(account.role == Role.BAKERY || account.role == Role.BAKER || account.role == Role.ADMIN){
            return new Post( caption,images,authorId);
        }
        else{
            return null;
        }

    }

    public boolean editCaption(String caption) {
        this.caption = caption;
        return Admin.getInstance().editPost(this);
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public List<Integer> getLikeId() {
        return likeId;
    }
    public boolean addLike(int id) {
        this.likeId.add(id);
        return Admin.getInstance().editPost(this);
    }
    public boolean deleteLike(int id) {
        this.likeId.remove(new Integer(id));
        return Admin.getInstance().editPost(this);
    }
    public void setLikeId(List<Integer> likeId) {
        this.likeId = likeId;
    }


    public List<String> getImages() {
        return images;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getId() {
        return id;
    }
}
