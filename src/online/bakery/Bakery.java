package online.bakery;

import online.bakery.sweets.Sweets;

import java.math.BigDecimal;
import java.util.*;

public class Bakery extends Account implements Confectioner {

    private final int id ;
    private String name;
    private String description;
    private int score;
    private int numScore;
    private List<Sweets> post = new ArrayList<Sweets>(); // post id
    private  Map<Sweets , Integer> readySweet  = new HashMap<Sweets , Integer>(); //list of sweets and number is ready to buy
    private List<Sweets> menu = new ArrayList<Sweets>(); //List of sweets id to order
    private List<Order> orderList = new ArrayList<Order>(); // list order id
    private  Map<BirthdayItems , Integer> birthdayItem = new HashMap<BirthdayItems , Integer>(); //list of sweets and number is ready to buy
    private List<Discount> discountList = new ArrayList<Discount>();




    public Bakery(String name,String username,String password,String firstName, String lastName, String description, String number, String address) {
        super();
        this.id = super.ID;
        super.SignUp(username, password, Role.BAKER);
        super.setFirstname(firstName);
        super.setLastname(lastName);
        this.name = name;
        this.description = description;
        super.setContactNo(number);
        super.setAddress(address);
        this.score = 0;
        this.numScore = 0;
        Admin.getInstance().createBakery(this); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    public int getScore() {
        return score;
    }

    public void setScore(double lastScore,double newScore, Sweets sweet) {
        this.score = (score*numScore - lastScore + newScore) / (numScore + 1) ;
        numScore += 1;
    }



    public void addPost(Sweets sweet){this.post.add(sweet);}

    public void addReadySweet(Sweets sweet,int number){this.readySweet.put(sweet,number);}

    public void addOrder(Order order,List<SweetType> s){
        this.orderList.add(order);
        List<Sweets> sweet = new ArrayList<Sweets>(order.getSweets());
        List <BirthdayItems> item = order.getItems();
        for(int i=0; i<sweet.size();i++){
            if(s.get(i) == SweetType.READY) {
                int n = readySweet.get(sweet.get(i));
                readySweet.put(sweet.get(i),n-1);
            }
        }
        for(int i=0; i<item.size();i++){
            int n = birthdayItem.get(item.get(i));
            birthdayItem.put(item.get(i),n-1);
        }
    }

    public void addMenu(Sweets sweet){this.menu.add(sweet);}

    public void addBirthdayItem(BirthdayItems item,int number) {this.birthdayItem.put(item,number);}

    public List<ConfectionerStatus> sweetToOrder(List<Sweets> s,List<SweetType> st,BirthdayItems b,Customer c){
        List<ConfectionerStatus> out = new ArrayList<ConfectionerStatus>();
        System.out.println("Customer");
        System.out.println(c.getProfile());
        System.out.println("Sweet");
        for (int i = 0;i < s.size();i++) {

            System.out.println(st.get(i));
            System.out.println(s.get(i).description);
            System.out.println(s.get(i).getTOTAL_Grams());
            System.out.println(s.get(i).getTOTAL_COST());

            int indexCS = 1;
            for (ConfectionerStatus CS : ConfectionerStatus.values()) {
                System.out.println(indexCS + " : " + CS);
                indexCS += 1;
            }
            Scanner scan = new Scanner(System.in);
            System.out.printf("Please Enter your Status for this Order : ");
            int num = scan.nextInt();
            out.add(ConfectionerStatus.values()[num - 1]);
        }
        System.out.println("BirthdayItems");
        for (int i = 0;i < s.size();i++) {
            System.out.println(b.getDescription());

            int indexCS = 1;
            for (ConfectionerStatus CS : ConfectionerStatus.values()) {
                System.out.println(indexCS + " : " + CS);
                indexCS += 1;
            }
            Scanner scan = new Scanner(System.in);
            System.out.printf("Please Enter your Status for this Order : ");
            int num = scan.nextInt();
            out.add(ConfectionerStatus.values()[num - 1]);
        }

        return out;

    }




    public List<Sweets> getPost() {
        return post;
    }

    public Map<Sweets , Integer> getReadySweet() { return readySweet; }

    public List<Sweets> getMenu() {
        return menu;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public Map<BirthdayItems, Integer> getBirthdayItemId() {
        return birthdayItem;
    }

    public void addDiscount(Discount discount){this.discountList.add(discount);}

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public String getProfile() {
        String bakeryP = name +"\n" + description + "\n" + "Number : " + super.getContactNo() + "\n" + "Address : " + super.getAddress() + "\n" + "Score : " +score + "/5 " + "(" + numScore + ") \n" ;
        return bakeryP;
    }

    public boolean addNote(Note note, String extraText) {
        if (this.id == note.getNoteSellerId()) {
            note.setNoteSellerText(extraText);
        }
        return true;
    }

}