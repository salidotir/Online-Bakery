package online.bakery;

import online.bakery.sweets.Sweets;

import java.util.List;

public interface Confectioner {

    void setScore(double lastScore,double newScore, Sweets sweet);

    void setDescription(String description);

    String getProfile();

    double getScore();
    String getDescription();
    List<Sweets> getPost();
    List<Order> getOrderList();


    void addPost(Sweets sweet);
    void addOrder(Order order,List<SweetType> s);

    //ConfectionerStatus sweetToOrder(List<Sweets> s,List<SweetType> st,Customer c);
    void addDiscount(Discount discount);

    boolean addNote(Note note, String extraText);



}