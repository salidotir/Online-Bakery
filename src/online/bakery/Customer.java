package online.bakery;

import java.math.BigDecimal;
import java.util.*;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import online.bakery.decorators.Design;
import online.bakery.decorators.DesignDecoration;
import online.bakery.sweets.Color;
import online.bakery.sweets.MultiTieredCake;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

/**
 * @author melika
 */
public class Customer extends Account {
    private List<Order> Orders = new ArrayList<Order>();
    private List<Sweets> designs = new ArrayList<Sweets>();
    private List<GiftCard> GiftCards = new ArrayList<>();

    public Customer(String username, String password) {
        super();
        super.SignUp(username, password, Role.CUSTOMER);
        Admin.getInstance().createCustomer(this);
    }

    public List<GiftCard> GetAllGiftCards(){
        return Admin.getInstance().GetAllGiftCards(this);
    }

    public void addGiftCard(GiftCard giftCards) {
        GiftCards.add( giftCards);
    }

    public List<GiftCard> getGiftCards() {
        return GiftCards;
    }

    public void ShowReceivedGiftCard(GiftCard giftCard) {
        System.out.println(giftCard.GiftCardInformation());
    }

    public List<Pair<Sweets, Integer>> getSweetsFromConfectioner(Confectioner confectioner) {
        Scanner sc = new Scanner(System.in);
        int m = 1;
        List<Pair<Sweets, Integer>> orderSweets = new ArrayList<>();
        ArrayList<String> AllPossibleTypeOfSweets = Admin.getInstance().GetAllPossibleTypeOfSweets(confectioner);
        for (String c_Sweets : AllPossibleTypeOfSweets
        ) {
            System.out.println(m + "\t" + c_Sweets);
            m += 1;
        }
        System.out.println("\n please select one the above: ");
        int WhichSweets = sc.nextInt();
        System.out.println("\n How many? ");
        Integer number = sc.nextInt();
        Pair<Sweets, Integer> mypair = new Pair<Sweets, Integer>(Admin.getInstance().GetSweetsFromConfectioner(confectioner, AllPossibleTypeOfSweets.get(WhichSweets - 1)), number);
        orderSweets.add(mypair);
        return orderSweets;

    }

    public Sweets MakeMultitieredCake(Confectioner confectioner) {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> AllPossibleTypeOfCake = Admin.getInstance().GetAllPossibleTypeOfCake(confectioner);
        System.out.println("please select Number of tier: ");
        int tiers = sc.nextInt();
        DesignDecoration[] designDecor = DesignDecoration.values();
        Color[] allcolors = Color.values();

        ArrayList<Design> design = new ArrayList<>();
        for (i = 1; i < tiers + 1; i += 1) {
            System.out.println("Tier: " + i + ": ");
            int m = 1;
            for (String cake : AllPossibleTypeOfCake
            ) {
                System.out.println(m + "\t" + cake);
                m += 1;
            }
            System.out.println("\n please select one of the above: ");
            int WhichCake = sc.nextInt();
            Sweets sweets = Admin.getInstance().GetSweetsFromConfectioner(confectioner, AllPossibleTypeOfCake.get(WhichCake));
            System.out.println("\n please select one of the above: ");
            ArrayList<DesignDecoration> mydesignDecorations = new ArrayList<>();
            int j = 1;
            for (DesignDecoration dd : designDecor
            ) {
                System.out.println(j + "\t" + dd.toString());
                j += 1;
                ArrayList<Integer> whichDecoration;
                while (sc.hasNext()) {

                    mydesignDecorations.add(designDecor[sc.nextInt() - 1]);

                }

            }
            int k = 1;
            ArrayList<Color> mycolors = new ArrayList<>();

            for (Color color : allcolors
            ) {
                System.out.println(k + "\t" + color.toString());
                j += 1;
                while (sc.hasNext()) {

                    mycolors.add(allcolors[sc.nextInt() - 1]);
                }


            }


            design.add(new Design(mycolors, mydesignDecorations, sweets));


        }


        return new MultiTieredCake(tiers, design);
    }

    public Boolean BuyGiftCardTo(@NotNull Customer customer) {
        System.out.println("How much?");
        Scanner sc = new Scanner(System.in);
        BigDecimal price = sc.nextBigDecimal();
        System.out.println("Note(if you don't want to add note please enter space key)");
        //String yes_no=sc.nextLine();
        //System.out.println(yes_no);
        ArrayList<String> note = new ArrayList<String>();
        String line;
        while (true) {
            line = sc.nextLine();
            if (line.equals("")) {
                break;
            } else {
                System.out.println(line);
                note.add(line);
            }
        }
        System.out.println(note);
        GiftCard giftCard;
        if (line.equals("")) {
            giftCard = new GiftCard(customer, price, note.toString(),this);
        } else {
            giftCard = new GiftCard(customer, price, null,this);
        }

        return Admin.getInstance().SaveGiftCartForCustomer(customer, giftCard);
    }

    public void addOrder(Order Order) {
        this.Orders.add(Order);
    }

    public List<Order> getOrders() {
        return Orders;
    }

    public List<Integer> getOrdersID() {
        List<Integer> ids = new ArrayList<Integer>();
        for (Order order : this.Orders) {
            ids.add(order.getOrderId());
        }
        return ids;
    }

    public Order createNewSweet(List<Sweets> sweetlist, List<BirthdayItems> items) {
        Order order = new Order(this, sweetlist, items, new Date());
        this.Orders.add(order);
        Admin.getInstance().addOrder(order);
        return order;
    }

    public boolean SweetaddtoOrder(Sweets sweet, Order order) {
        if (this.Orders.contains(order)) {
            return this.Orders.get(this.Orders.indexOf(order)).SweetaddtoOrder(sweet);
        } else
            return false;
    }

    public void addtoDesigns(Sweets sweet) {
        designs.add(sweet);
    }

    public List<Sweets> getDesigns() {
        return designs;
    }
    
}
