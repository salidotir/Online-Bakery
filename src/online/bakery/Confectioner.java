package online.bakery;

import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

import java.util.List;

public interface Confectioner {


    double getScore();
    boolean setScore(Rate score, Sweets sweet);

    String getDescription();
    boolean setDescription(String description);

    String getName();
    boolean setName(String name);

    String getProfile();



    List<Order> getOrderList();
    //boolean addOrder(Order order,List<SweetType> s);

    //List<Sweets> getPost();
    //boolean addPost(Sweets sweet);

    List<ConfectionerStatus> acceptOrder(Order order,List<SweetType> st);

    boolean addDiscount(Discount discount);

    boolean addNote(Note note, String extraText);

    boolean addOrderSweet(Sweets s);
    List<Sweets> getOrderSweet();
    boolean deleteOrderSweet(Sweets s);








}