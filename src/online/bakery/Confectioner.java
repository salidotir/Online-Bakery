package online.bakery;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

import java.util.List;

public interface Confectioner {

    int getID();
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

    boolean addDiscount();

    boolean addNote(Note note, String extraText);

    Sweets CreateSweets();
    boolean addOrderSweet();

    List<Sweets> getOrderSweets();
    boolean deleteOrderSweet(Sweets sweet);

    boolean addOrder(Order order, List<SweetType> s);


    BigDecimal getProfit(Date start , Date end);






}