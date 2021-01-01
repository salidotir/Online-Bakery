package online.bakery.decorators;

import online.bakery.sweets.Color;

import java.util.ArrayList;

public class Design {
    protected ArrayList<Color>colors;
    protected ArrayList<DesignDecoration>DesignDecorations;
    public Design(ArrayList<Color>colors,ArrayList<DesignDecoration>designDecorations){
        this.colors=colors;
        this.DesignDecorations=designDecorations;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public ArrayList<DesignDecoration> getDesignDecorations() {
        return DesignDecorations;
    }
}
