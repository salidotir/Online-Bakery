package online.bakery.Post;

import online.bakery.Account;
import online.bakery.Admin;
import online.bakery.Type.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static online.bakery.Test.BACKGROUNDS;
import static online.bakery.Test.FOREGROUNDS;

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
        this.likeId = new ArrayList<Integer>();
        Admin.getInstance().addPost(this);
    }

     public static Post createPost(Account account, String caption, List<String> images){
        if(account.role == Role.BAKERY || account.role == Role.BAKER || account.role == Role.ADMIN){

           return new Post( caption,images,account.getID());
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
    public boolean addLike(Account account) {
        this.likeId.add(account.getID());
        return Admin.getInstance().editPost(this);
    }
    public boolean deleteLike(Account account) {
        this.likeId.remove(new Integer(account.getID()));
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
    public boolean addComment(Account account){
        Scanner scan = new Scanner(System.in);
        System.out.println(BACKGROUNDS[4] + FOREGROUNDS[15]+"Enter text comment");
        String text = scan.nextLine();

        new Comment(text,account.getID(),this.id);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder comments=new StringBuilder();
        for(Comment comment:this.getComments()){
            comments.append("{ ").append(comment.getAuthorId()).append(" : ").append( comment.getText()).append('}').append('\n');
        }
        if (this.getComments().size()==0){
            comments=new StringBuilder();
            comments.append("No Comments Yet.");
        }

        return "Post{" +
                "images=" + images +
                ", likes=" + likeId.size() +
                ", authorId=" + authorId +"\n"+
                ", Comments= "+comments+

                ", caption='" + caption + '\'' +
                '}';
    }

    public List<Comment> getComments(){
        return Admin.getInstance().getComments(this.id);
    }
}
