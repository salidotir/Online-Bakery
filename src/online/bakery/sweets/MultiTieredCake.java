package online.bakery.sweets;

import com.sun.istack.internal.NotNull;
import online.bakery.decorators.Design;
import online.bakery.decorators.DesignDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class MultiTieredCake extends Sweets {
    int tier;
    //ArrayList<Sweets> cakes;
    ArrayList<Design> CakeWithDesign;

    public MultiTieredCake(int tier, @NotNull ArrayList<Design> cakeWithDesign) {


        if (cakeWithDesign.size() != tier) {
            System.out.println("tire not matches to cakes.");
        } else {

            this.type = SweetType.CAKE;
            this.tier = tier;
            this.CakeWithDesign = cakeWithDesign;
            this.description = "Multi Tiered Cake " + getDescription();
            for (Design d : this.CakeWithDesign) {
                this.TOTAL_Grams = this.getTOTAL_Grams().add(d.getCake().getTOTAL_Grams());
            }
            atomicInteger.incrementAndGet();
            this.SweetId = atomicInteger.get();
            this.TOTAL_COST = this.cost();
        }
    }

    @Override
    public String getDescription() {
        int i = 1;
        StringBuilder extra_desc = new StringBuilder();
        for (Design d : this.CakeWithDesign) {
            extra_desc.append("\n{tier: ")
                    .append(i).append("}").append("\n").append("Condiment:\n").append(d.getCake().getDescription()).append("\n").append("Design:\n")
                    .append("COLORS:  ");
            i += 1;
            // ArrayList<Color> colors = this.CakeWithDesign.();
            for (Color c : d.getColors()
            ) {
                extra_desc.append(c.toString()).append("\t");
            }
            extra_desc.append("\nDecoration:  ");
            for (DesignDecoration dd : d.getDesignDecorations()
            ) {
                extra_desc.append(dd.toString()).append("\t");
            }
        }
        this.description = extra_desc.toString();

        return description;
    }

    @Override
    public BigDecimal cost() {
        BigDecimal mycost = new BigDecimal(0);
        for (Design d : this.CakeWithDesign) {
            mycost = mycost.add(d.getCake().getTOTAL_COST());
            BigDecimal extra_cost = new BigDecimal(0);
            ArrayList<DesignDecoration> designDecorations = d.getDesignDecorations();
            for (DesignDecoration dd : designDecorations
            ) {
                switch (dd) {
                    case CHOCOLATE:
                        extra_cost = extra_cost.add(new BigDecimal(10000));
                        break;
                    case BLUEBERRY:

                        extra_cost = extra_cost.add(new BigDecimal(20000));
                        break;
                    case STRAWBERRY:

                        extra_cost = extra_cost.add(new BigDecimal(30000));
                        break;
                    case SIMPLE_FONDANT:

                        extra_cost = extra_cost.add(new BigDecimal(10000));
                        break;
                    case COCONUT_POWDER:

                        extra_cost = extra_cost.add(new BigDecimal(30000));
                        break;
                    case CREAM:

                        extra_cost = extra_cost.add(new BigDecimal(90000));
                        break;
                    case SMARTIES:
                        extra_cost = extra_cost.add(new BigDecimal(80000));
                        break;
                }
                mycost = mycost.add(extra_cost);

            }

        }
        return mycost;
    }
}
