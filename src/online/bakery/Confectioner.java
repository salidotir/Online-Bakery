package online.bakery;

public interface Confectioner {

    void setFirstName(String a);
    void setLastName(String a);
    void setNumber(String a);
    void setScore(int a);
    void increaseScore(int a);
    void decreaseScore(int a);


    String getFirstName();
    String getLastName();
    String getNumber();
    int getScore();



}
