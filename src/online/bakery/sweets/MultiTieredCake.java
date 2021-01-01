package online.bakery.sweets;

import online.bakery.decorators.Design;
import online.bakery.decorators.DesignDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class MultiTieredCake extends Sweets {
    int tier;
    //ArrayList<Sweets> cakes;
    Map<Sweets, Design> CakeWithDesign;

    public MultiTieredCake(int tier, Map<Sweets, Design> cakeWithDesign) {

        assert CakeWithDesign != null;
        if (CakeWithDesign.size() != tier) {
            System.out.println("tire not matches to cakes.");
        } else {
            this.tier = tier;
            this.CakeWithDesign = cakeWithDesign;
            this.description = "MultiTieredCake: ";
            atomicInteger.incrementAndGet();
            this.SweetId = atomicInteger.get();
            this.TOTAL_COST = this.cost();
        }
    }

    @Override
    public String getDescription() {
        this.description = super.getDescription();
        this.description += "\nDesign: ";
        int i = 1;
        StringBuilder extra_desc = new StringBuilder();
        extra_desc.append(this.description);
        for (Map.Entry<Sweets, Design> entry : this.CakeWithDesign.entrySet()) {
            extra_desc.append("tier").append(i).append(": COLORS:");
            i += 1;
            ArrayList<Color> colors = entry.getValue().getColors();
            for (Color c : colors
            ) {
                extra_desc.append(c.toString());
            }
            extra_desc.append("Decoration");
            ArrayList<DesignDecoration> designDecorations = entry.getValue().getDesignDecorations();
            for (DesignDecoration dd : designDecorations
            ) {
                extra_desc.append(dd.toString());
            }
        }
        this.description = extra_desc.toString();

        return description;
    }

    @Override
    public BigDecimal cost() {
        BigDecimal mycost = new BigDecimal(0);
        for (Map.Entry<Sweets, Design> entry : this.CakeWithDesign.entrySet()) {
            mycost = mycost.add(entry.getKey().TOTAL_COST);
            BigDecimal extra_cost = new BigDecimal(0);
            ArrayList<DesignDecoration> designDecorations = entry.getValue().getDesignDecorations();
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
