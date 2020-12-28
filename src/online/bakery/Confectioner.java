package online.bakery;

import online.bakery.sweets.Sweets;

import java.util.List;

public interface Confectioner {


    void setNumber(String number);
    void setScore(int score);
    void increaseScore(int a);
    void decreaseScore(int a);

    void setDescription(String description);

    String getProfile();

    String getNumber();
    int getScore();
    String getDescription();
    int getId();
    List<Integer> getPost();
    List<Integer> getSweetId();
    List<Integer> getOrderList();
    List<Integer> getBirthdayItemId();

    void addPost(int id);
    void addSweet(int id);
    void addOrder(int id);
    void addBirthdayItem(int id);
    ConfectionerStatus sweetToOrder(Sweets s);
    void addDiscount(int id);



}
