package online.bakery.Confectioner;

import java.math.BigDecimal;
import java.util.Date;

import online.bakery.Order;
import online.bakery.Post.Post;
import online.bakery.Status.ConfectionerStatus;
import online.bakery.Type.SweetType;
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



    List<ConfectionerStatus> acceptOrder(Order order, List<SweetType> st);

    boolean addDiscount();

    Sweets CreateSweets();
    boolean addOrderSweet(Sweets sweets);

    List<Sweets> getOrderSweets();
    boolean deleteOrderSweet(Sweets sweet);

    boolean addOrder(Order order, List<SweetType> s);


    BigDecimal getProfit(Date start , Date end);


    Post createPost();
    List<Post> getPosts();




}