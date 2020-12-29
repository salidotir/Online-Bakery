package online.bakery;

import online.bakery.sweets.Sweets;

import java.util.List;

public interface Confectioner {

    void setScore(int score);
    void increaseScore(int a);
    void decreaseScore(int a);

    void setDescription(String description);

    String getProfile();

    int getScore();
    String getDescription();
    List<Sweets> getPost();
    List<Sweets> getReadySweet();
    List<Order> getOrderList();


    void addPost(Sweets sweet);
    void addReadySweet(Sweets sweet);
    void addOrder(Order order);

    ConfectionerStatus sweetToOrder(List<Sweets> s,List<SweetType> st,Customer c);
    void addDiscount(Discount discount);

    boolean addNote(Note note, String extraText);



}
