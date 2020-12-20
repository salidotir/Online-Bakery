package online.bakery;

public interface Confectioner {

    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setNumber(String number);
    void setScore(int score);
    void increaseScore(int a);
    void decreaseScore(int a);


    String getFirstName();
    String getLastName();
    String getNumber();
    int getScore();



}
