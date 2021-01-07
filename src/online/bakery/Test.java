package online.bakery;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    public static final String[] FOREGROUNDS = {
            ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW,
            ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE,
            ANSI_BRIGHT_BLACK, ANSI_BRIGHT_RED, ANSI_BRIGHT_GREEN, ANSI_BRIGHT_YELLOW,
            ANSI_BRIGHT_BLUE, ANSI_BRIGHT_PURPLE, ANSI_BRIGHT_CYAN, ANSI_BRIGHT_WHITE
    };

    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    public static final String ANSI_BRIGHT_BG_BLACK = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";

    public static final String[] BACKGROUNDS = {
            ANSI_BG_BLACK, ANSI_BG_RED, ANSI_BG_GREEN, ANSI_BG_YELLOW,
            ANSI_BG_BLUE, ANSI_BG_PURPLE, ANSI_BG_CYAN, ANSI_BG_WHITE,
            ANSI_BRIGHT_BG_BLACK, ANSI_BRIGHT_BG_RED, ANSI_BRIGHT_BG_GREEN, ANSI_BRIGHT_BG_YELLOW,
            ANSI_BRIGHT_BG_BLUE, ANSI_BRIGHT_BG_PURPLE, ANSI_BRIGHT_BG_CYAN, ANSI_BRIGHT_BG_WHITE};

    ArrayList<Confectioner> conf = new ArrayList<>();

    public Test() {
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 4 Confectioners");
        this.AddConfectioner();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakeries");
        this.ShowAllBakeries();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakers");
        this.ShowAllBakers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakeries:(substring='baker2')");
        this.SearchByNameBakeries();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakers:(substring='reza')");
        this.SearchByNameBakers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 4 Customers");
        this.AddCustomers();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 3 Emploee");
        //this.AddCustomers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Buy a Gift Card (negin to sara)");
        this.BuyGiftCard();

    }


    void AddConfectioner() {
        Bakery bakery1 = new Bakery("Bakery1", "usermane1", "pass1", "Have a Good Time", "07131111110", "Eram");
        bakery1.setFirstname("Mitra");
        bakery1.setLastname("Hejazi");

        Baker baker1 = new Baker("Baker1", "usermane2", "pass2", "Have a Good Time", "07131111112", "Khalili");
        baker1.setFirstname("Ali");
        baker1.setLastname("Abedi");

        Bakery bakery2 = new Bakery("Bakery2", "usermane3", "pass3", "Have a Good Time", "07131111113", "Farhang Shahr");
        bakery2.setFirstname("sara");
        bakery2.setLastname("Ahmadi");

        Baker baker2 = new Baker("Baker2", "usermane4", "pass4", "Have a Good Time", "07131111114", "Tachara");
        baker2.setFirstname("reza");
        baker2.setLastname("Salmani");
        conf.add(baker1);
        conf.add(baker2);
        conf.add(bakery1);
        conf.add(bakery2);

        System.out.println(ANSI_RESET);
    }

    void ShowAllBakeries() {
        List<Bakery> All_bakeries = Admin.getInstance().ShowAllBakery();
        for (Bakery bakery : All_bakeries
        ) {
            System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + bakery.getProfile());
            System.out.println(ANSI_RESET);

        }
    }

    void ShowAllBakers() {
        List<Baker> All_bakeries = Admin.getInstance().ShowAllBakers();
        for (Baker bakery : All_bakeries
        ) {
            System.out.println(BACKGROUNDS[3] + FOREGROUNDS[15] + bakery.getProfile());
            System.out.println(ANSI_RESET);

        }
    }

    private void SearchByNameBakeries() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("baker2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            for (Bakery bakery : bakeries
            ) {
                System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + bakery.getProfile());
                System.out.println(ANSI_RESET);

            }
        }
    }

    private void SearchByNameBakers() {
        List<Baker> bakers = Admin.getInstance().searchBakerByName("reza");
        if (bakers.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            for (Baker bakery : bakers
            ) {
                System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + bakery.getProfile());
                System.out.println(ANSI_RESET);

            }
        }
    }

    void AddCustomers() {
        Customer c = new Customer("fm", "1234");
        c.setFirstname("Fateme");
        c.setLastname("Masoudi");
        c.setContactNo("0917863533");
        c.setAddress("Bahonar");
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c.getProfile());
        System.out.println(ANSI_RESET);
        Customer c2 = new Customer("melika", "1234");
        c2.setFirstname("Melika");
        c2.setLastname("zare");
        c2.setContactNo("0917863532");
        c2.setAddress("Mellat");

        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c2.getProfile());
        System.out.println(ANSI_RESET);
        Customer c3 = new Customer("neg", "4444");
        c3.setFirstname("Negin");
        c3.setLastname("Khalifat");
        c3.setContactNo("0914863532");
        c3.setAddress("Bagh shah");

        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c3.getProfile());
        System.out.println(ANSI_RESET);
        Customer c4 = new Customer("sara", "4434");
        c4.setFirstname("Sara");
        c4.setLastname("LimooE");
        c4.setContactNo("0914843533");
        c4.setAddress("MaliAbad");

        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c4.getProfile());
        System.out.println(ANSI_RESET);

    }

    private void BuyGiftCard() {
        Customer sara = Admin.getInstance().searchCustomerByContactNo("0914843533");
        Customer negin = Admin.getInstance().searchCustomerByContactNo("0914863532");
        negin.BuyGiftCardTo(sara);
    }
}
