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

    public Test() {
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 4 Confectioner");
        this.AddConfectioner();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakeries");
        this.ShowAllBakeries();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakers");
        this.ShowAllBakers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakeries:(substring='baker2')");
        this.SearchByNameBakeries("baker2");

        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakers:(substring='reza')");
        this.SearchByNameBakers("reza");
    }


    void AddConfectioner() {
        ArrayList<Confectioner> conf = new ArrayList<>();
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

    private void SearchByNameBakeries(String subString) {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName(subString);
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
    private void SearchByNameBakers(String subString) {
        List<Baker> bakers = Admin.getInstance().searchBakerByName(subString);
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
}
