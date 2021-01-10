package online.bakery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import online.bakery.birthdayItems.BirthdayItems;
import online.bakery.decorators.Design;
import online.bakery.decorators.DesignDecoration;
import online.bakery.sweets.Color;
import online.bakery.sweets.MultiTieredCake;
import online.bakery.sweets.Sweets;

import static online.bakery.Test.*;

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

    public List<GiftCard> GetAllGiftCards() {
        return Admin.getInstance().GetAllGiftCards(this);
    }

    public void addGiftCard(GiftCard giftCards) {
        GiftCards.add(giftCards);
    }

    public List<GiftCard> getGiftCards() {
        return GiftCards;
    }

    public void ShowReceivedGiftCard(GiftCard giftCard) {
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + giftCard.GiftCardInformation());
        System.out.println(ANSI_RESET);
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
        System.out.println(BACKGROUNDS[3] + FOREGROUNDS[15] + "please select Number of tier: ");
        int tiers = sc.nextInt();
        DesignDecoration[] designDecor = DesignDecoration.values();

        Color[] allcolors = Color.values();

        ArrayList<Design> design = new ArrayList<>();
        for (i = 1; i < tiers + 1; i += 1) {
            System.out.println("Tier " + i + ": ");
            int m = 1;
            for (String cake : AllPossibleTypeOfCake
            ) {
                System.out.print(m + " "+ cake+"\t");
                m += 1;
            }
            System.out.println("\nPlease select one of the above: ");
            int WhichCake = sc.nextInt();
            Sweets sweets = Admin.getInstance().GetSweetsFromConfectioner(confectioner, AllPossibleTypeOfCake.get(WhichCake-1));
            System.out.println("Please select one of the above: ");
            ArrayList<DesignDecoration> mydesignDecorations = new ArrayList<>();
            int j = 1;
            for (DesignDecoration dd : designDecor
            ) {
                System.out.print(j + " " + dd.toString()+"\t");
                j += 1;
            }
            System.out.println('\n');

            String[] numbers= sc.nextLine().split(" ");
            numbers= sc.nextLine().split(" ");
            for (String num:numbers
                 ) {
                mydesignDecorations.add(designDecor[Integer.parseInt(num) - 1]);
            }




            int k = 1;
            ArrayList<Color> mycolors = new ArrayList<>();

            for (Color color : allcolors
            ) {
                System.out.print(k + " " + color.toString()+"\t");
                k += 1;
            }
            System.out.print('\n');
            String[] numbers2= sc.nextLine().split(" ");

            for (String num:numbers2
            ) {
                mycolors.add(allcolors[Integer.parseInt(num) - 1]);
            }


            design.add(new

                    Design(mycolors, mydesignDecorations, sweets));

        }


        return new

                MultiTieredCake(tiers, design);

    }

    public Boolean BuyGiftCardTo(@NotNull Customer customer) {

        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + "Gift Card Info.");

        System.out.println("How much?");
        Scanner sc = new Scanner(System.in);
        BigDecimal price = sc.nextBigDecimal();
        System.out.println("Note(if you don't want to add note please enter space key)");
        //String yes_no=sc.nextLine();
        //System.out.println(yes_no);
        StringBuilder note = new StringBuilder();
        String line;
        while (true) {
            line = sc.nextLine();
            if (line.equals(" ")) {
                break;
            } else {
                if (line.equals("")) continue;
                System.out.println(line);
                note.append(line);
                note.append('\n');

            }
        }
        System.out.println(BACKGROUNDS[3] + FOREGROUNDS[8] + "Note message:");
        System.out.println(note.toString());
        System.out.println(ANSI_RESET);

        PaymentType paymentType = Payment.howToPay();
        Payment payment = new Payment(new Date(), price, "Buy Gift Card", paymentType);
        this.addPayment(payment);
        boolean result = payment.pay(payment, this, Admin.getInstance());
        if (result) {
            GiftCard giftCard;
            if (line.equals("")) {
                giftCard = new GiftCard(customer, price, note.toString(), this);
            } else {
                giftCard = new GiftCard(customer, price, null, this);
            }

            return Admin.getInstance().SaveGiftCartForCustomer(customer, giftCard);
        } else {
            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Do you want to charge your wallet?(y/n)");
            char y = sc.next().charAt(0);
            if (y == 'y') {
                System.out.println("How much?");
                BigDecimal price_add = sc.nextBigDecimal();
                this.getWallet().addAmount(price_add);
                Payment payment_new = new Payment(new Date(), price, "Buy Gift Card", paymentType);
                this.addPayment(payment_new);
                boolean result_new = payment_new.pay(payment_new, this, Admin.getInstance());
                if (result_new) {
                    GiftCard giftCard;
                    if (note.equals("")) {
                        giftCard = new GiftCard(customer, price, note.toString(), this);
                    } else {
                        giftCard = new GiftCard(customer, price, null, this);
                    }

                    return Admin.getInstance().SaveGiftCartForCustomer(customer, giftCard);
                } else {
                    return false;
                }
            }
            return false;
        }
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

        System.out.println("Choose date for order (2020/01/01) : ");
        Scanner scan = new Scanner(System.in);
        String D = scan.nextLine();
        LocalDate expectedDeliveryTimeLD;
        try {
            expectedDeliveryTimeLD = LocalDate.parse(D, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("Unexpected Date : " + D);
        }
        Date expectedDeliveryTime = new Date(expectedDeliveryTimeLD.getYear()- 1900,expectedDeliveryTimeLD.getMonthValue() - 1, expectedDeliveryTimeLD.getDayOfMonth());
        Order order = new Order(this, sweetlist, items, expectedDeliveryTime);
        this.Orders.add(order);
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
