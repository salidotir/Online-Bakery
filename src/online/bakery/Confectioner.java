package online.bakery;

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

    void addPost(int id);
    void addSweet(int id);
    void addOrder(int id);
    void addItem(int id);



}
