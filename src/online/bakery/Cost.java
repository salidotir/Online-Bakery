
package online.bakery;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author melika
 */
public class Cost {
    private final Order order;
    private final BigDecimal sweetCost;
    private final BigDecimal itemCost;
    private final BigDecimal deliveryCost;
    private final BigDecimal actuallCost;
    
    private BigDecimal costWithDisocunt = null;
    private BigDecimal costSweetWithGiftCard = null;
    private BigDecimal costDeliveryWithGiftCard = null;
    private BigDecimal finalCost = null;
    
    private BigDecimal adminCostDiscount = null;
    private BigDecimal adminCostGiftCardBaker = null;
    private BigDecimal adminCostGiftCardEmploee = null;

    public Cost(Order order, BigDecimal sweetCost, BigDecimal itemCost, BigDecimal deliveryCost) {
        this.order = order;
        this.sweetCost = sweetCost;
        this.itemCost = itemCost;
        this.deliveryCost = deliveryCost;
        this.actuallCost = sweetCost.add(itemCost).add(deliveryCost);
    }   
     
    public String getDetailCosts(){
        String s;
        BigDecimal temp = sweetCost;
        temp = temp.add(itemCost);
        s = "Actuall Cost sweets and birthday items: "+ temp + "\n"+
            "Actuall Cost delivery: "+ this.deliveryCost + "\n";
        if(this.costWithDisocunt != null)
            s += "Cost payed for sweets and birthday items with discount: " + this.costWithDisocunt + "\n"; 
        if(this.costSweetWithGiftCard != null)
            s += "Cost payed for sweets and birthday items with Gift Card: "+ this.costSweetWithGiftCard + "\n";
        if(this.costDeliveryWithGiftCard != null)
            s += "Cost payed for delivery: "+ this.costDeliveryWithGiftCard + "\n" ;
        if(this.finalCost != null)
            s += "Final Cost: " + this.finalCost + "\n";
        return s;
    }

    public AbstractMap.SimpleEntry getCostWithDisocunt() {
        if (costWithDisocunt != null)
            return new AbstractMap.SimpleEntry(true, costWithDisocunt);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getCostSweetWithGiftCard() {
        if (costSweetWithGiftCard != null)
            return new AbstractMap.SimpleEntry(true, costSweetWithGiftCard);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getCostDeliveryWithGiftCard() {
        if (costDeliveryWithGiftCard != null)
            return new AbstractMap.SimpleEntry(true, costDeliveryWithGiftCard);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getAdminCostDiscount() {
        if (adminCostDiscount != null)
            return new AbstractMap.SimpleEntry(true, adminCostDiscount);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getAdminCostGiftCardBaker() {
        if (adminCostGiftCardBaker != null)
            return new AbstractMap.SimpleEntry(true, adminCostGiftCardBaker);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getAdminCostGiftCardEmploee() {
        if (adminCostGiftCardEmploee != null)
            return new AbstractMap.SimpleEntry(true, adminCostGiftCardEmploee);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }

    public AbstractMap.SimpleEntry getFinalCost() {
        if (finalCost != null)
            return new AbstractMap.SimpleEntry(true, finalCost);
        else
            return new AbstractMap.SimpleEntry(false, null);
    }
    
    private BigDecimal applyDiscount(BigDecimal cost){
        BigDecimal newcost = cost;
        this.adminCostDiscount = BigDecimal.ZERO;
        Discount victoryDiscount = null;
        boolean usedAppDiscount = false;               
        
        List<Discount> listAdmin = Admin.getInstance().getActiveDiscount(Admin.getInstance().getID());
        if(listAdmin.size() > 0){
            System.out.println("___________________________Our App discount______________________________");
            System.out.print("Actuall Cost of sweets and birthday items: ");
            System.out.println(cost);
            
            victoryDiscount = listAdmin.get(0);
        }
        for(Discount discount: listAdmin){           
            if(discount.canUseDiscount()){
                if(discount.getName().equals("تخفیف اولین سفارش در اپ ما") &&
                        Admin.getInstance().firstOrder(order.getCustomerId())){
                    victoryDiscount = discount;
                    break;
                }else{
                    if(discount.getPercent() > victoryDiscount.getPercent()){
                        victoryDiscount = discount;
                    }
                }    
            }           
        }
        if(victoryDiscount != null && victoryDiscount.useDiscount()){
            usedAppDiscount = true;
            order.addDiscount(victoryDiscount);
            System.out.println(victoryDiscount.getDiscountInformation());
            System.out.println("***************\n");
            newcost = newcost.multiply(new BigDecimal(100 - victoryDiscount.getPercent()));
            newcost = newcost.divide(new BigDecimal(100));
            System.out.print("Cost after discount: ");
            System.out.println(newcost);
            
            this.adminCostDiscount = cost;
            this.adminCostDiscount = this.adminCostDiscount.subtract(newcost);
        }
        
        victoryDiscount = null;
        
        List<Discount> list = Admin.getInstance().getActiveDiscount(order.getBakerId());
        if(list.size() > 0){
            System.out.println("___________________________Bakery discount_____________________________");
            System.out.print("Actuall Cost of sweets and birthday items: ");
            System.out.println(cost);
            if(usedAppDiscount){
                System.out.print("Cost after App discount: ");
                System.out.println(newcost);
            }    
            victoryDiscount = list.get(0);
        }
        for(Discount discount: list){            
            if(discount.canUseDiscount()){
                if(discount.getPercent() > victoryDiscount.getPercent()){
                    victoryDiscount = discount;
                }
            }
        }
        if(victoryDiscount != null && victoryDiscount.useDiscount()){       
            order.addDiscount(victoryDiscount);
            System.out.println(victoryDiscount.getDiscountInformation());
            System.out.println("***************\n");
            BigDecimal bakerCost = cost;
            bakerCost = bakerCost.multiply(new BigDecimal(100 - victoryDiscount.getPercent()));
            bakerCost = bakerCost.divide(new BigDecimal(100));
            bakerCost = cost.subtract(bakerCost);            
            newcost = newcost.subtract(bakerCost);
            System.out.print("Cost after discount: ");
            System.out.println(newcost);
        }
        this.costWithDisocunt = newcost;
        return newcost;
    }

    private BigDecimal applyGiftCard(BigDecimal cost){
        this.adminCostGiftCardBaker = BigDecimal.ZERO;
        this.adminCostGiftCardEmploee = BigDecimal.ZERO;
        this.costSweetWithGiftCard = cost;
        this.costDeliveryWithGiftCard = this.deliveryCost;
        BigDecimal newcost = cost;
        
        List<GiftCard> giftCards = order.getCustomer().getGiftCards();
        if(giftCards.size() > 0){
            System.out.println("___________________________Gift Cards______________________________");
            System.out.print("Actuall Cost of sweets and birthday items: ");
            System.out.println(this.actuallCost.subtract(this.deliveryCost));           
            if (this.costWithDisocunt.compareTo(this.actuallCost.subtract(this.deliveryCost)) != 0){
                System.out.print("Cost of sweets and birthday items after discount: ");
                System.out.println(this.costWithDisocunt);
            }
            System.out.print("Delivery Cost: ");
            System.out.println(this.deliveryCost);
            System.out.println("____________list GiftCards_____________");
            this.costSweetWithGiftCard = cost;           
            this.costDeliveryWithGiftCard = this.deliveryCost;
        }else{
            System.out.print("Final Cost with delivery: ");
            newcost = newcost.add(this.deliveryCost);
            System.out.println(newcost);
        }
        
        int i = 1;
        for(GiftCard gift: order.getCustomer().getGiftCards()){
            System.out.print("GiftCard number ");
            System.out.print(i);
            System.out.println(": ");
            System.out.println(gift.GiftCardInformation());
            System.out.println("**************\n");
            i += 1;
        }
        
        if(giftCards.size() > 0){
            int choose = -1;
            boolean getCorrectInput = false;
            newcost = cost;
            while(!getCorrectInput){
                System.out.print("Choose one of gift cards from above(if you don't want to please enter 0): ");            
                Scanner sc = new Scanner(System.in);
                choose = sc.nextInt();
                if(choose >= 0 && choose < i){
                    getCorrectInput = true;
                }else
                    System.out.println("Wrong number!");
            }
            if (choose != -1 && choose != 0 && getCorrectInput == true){
                GiftCard chosen = giftCards.get(choose - 1);
                
                // remain price giftCard <= cost of order without delivery
                if(chosen.getRemainPrice().compareTo(newcost) != 1 ){ 
                    BigDecimal subtractAmount = chosen.getRemainPrice();
                    newcost = newcost.subtract(subtractAmount);
                    chosen.reduceFromRemain(subtractAmount);
                    this.adminCostGiftCardBaker = this.adminCostGiftCardBaker.add(subtractAmount);
                    System.out.print("Cost Sweets and birthday items after subtracting GiftCard: ");
                    System.out.println(newcost);
                    this.costSweetWithGiftCard = newcost;
                    System.out.print("Final Cost with delivery: ");
                    newcost = newcost.add(this.deliveryCost);
                    System.out.println(newcost);
                    this.costDeliveryWithGiftCard = this.deliveryCost;
                }
                // remain price giftCard > cost of order without delivery
                else if(chosen.getRemainPrice().compareTo(newcost) == 1){ 
                    chosen.reduceFromRemain(newcost);
                    this.adminCostGiftCardBaker = this.adminCostGiftCardBaker.add(newcost);
                    newcost = BigDecimal.ZERO;                   
                    System.out.print("Cost Sweets and birthday items after subtracting GiftCard: ");
                    System.out.println(newcost);
                    this.costSweetWithGiftCard = newcost;

                    // remain price giftCard <= delivery cost
                    if(chosen.getRemainPrice().compareTo(this.deliveryCost) != 1){ 
                        System.out.print("Acutall Cost delivery: ");
                        System.out.println(this.deliveryCost);
                        BigDecimal subtractAmount = chosen.getRemainPrice();
                        BigDecimal remainDeliveryCost = this.deliveryCost;
                        remainDeliveryCost = remainDeliveryCost.subtract(subtractAmount);
                        chosen.reduceFromRemain(subtractAmount);
                        this.adminCostGiftCardEmploee = this.adminCostGiftCardEmploee.add(subtractAmount);
                        System.out.print("Delivery Cost after subtracting GiftCard: ");
                        System.out.println(remainDeliveryCost);
                        this.costDeliveryWithGiftCard = remainDeliveryCost;
                        newcost = newcost.add(remainDeliveryCost);
                    }
                    // remain price giftCard > delivery cost
                    else if(chosen.getRemainPrice().compareTo(this.deliveryCost) == 1){
                        System.out.print("Acutall Cost delivery: ");
                        System.out.println(this.deliveryCost);
                        BigDecimal remainDeliveryCost = BigDecimal.ZERO;  
                        chosen.reduceFromRemain(this.deliveryCost);
                        this.adminCostGiftCardEmploee = this.adminCostGiftCardEmploee.add(this.deliveryCost);
                        System.out.print("Delivery Cost after subtracting GiftCard: ");
                        System.out.println(remainDeliveryCost);
                        this.costDeliveryWithGiftCard = remainDeliveryCost;
                        newcost = newcost.add(remainDeliveryCost);
                    }
                }   
            }
        }
        System.out.print("Amount of Cost you must pay: ");
        System.out.println(newcost);
        return newcost;
    }
    
    public BigDecimal getCost(){
        BigDecimal temp = sweetCost;
        temp = temp.add(itemCost);
        this.costWithDisocunt = applyDiscount(temp);
        this.finalCost = applyGiftCard(this.costWithDisocunt);
        return this.finalCost;
    }
    
}
