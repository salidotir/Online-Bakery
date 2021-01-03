package online.bakery.decorators;

import online.bakery.sweets.Cake;
import online.bakery.sweets.Color;
import online.bakery.sweets.Sweets;

import java.util.ArrayList;

public class Design {
    protected ArrayList<Color>colors;
    protected ArrayList<DesignDecoration>DesignDecorations;
    protected Sweets cake;
    public Design(ArrayList<Color>colors,ArrayList<DesignDecoration>designDecorations,Sweets cake){
        this.colors=colors;
        this.DesignDecorations=designDecorations;
        this.cake=cake;
    }

    public Sweets getCake() {
        return cake;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public ArrayList<DesignDecoration> getDesignDecorations() {
        return DesignDecorations;
    }
}
