package com.codestates.seb.kiosk_program.product.subProduct;

import com.codestates.seb.kiosk_program.product.Product;

public class Tteokbokki extends Product {
    private int spicyStep;

    public Tteokbokki(int productId, String name, int amount, int price, int spicyStep) {
        super(productId, name, amount, price);
        this.spicyStep = spicyStep;
    }

    public Tteokbokki(Tteokbokki tteokbokki) {
        super(tteokbokki.getProductId(), tteokbokki.getName(), tteokbokki.getAmount(), tteokbokki.getPrice());
        this.spicyStep = tteokbokki.spicyStep;
    }

    public int getSpicyStep() {
        return spicyStep;
    }

    public void setSpicyStep(int spicyStep) {
        this.spicyStep = spicyStep;
    }

    public String spicySepName(int spicyStep) {
        String spicyStepName;
        switch (spicyStep){
            case 2 : spicyStepName = "중간맛"; break;
            case 3 : spicyStepName = "매운맛"; break;
            case 4 : spicyStepName = "지옥맛"; break;
            default: spicyStepName = "순한맛";
        }

        return spicyStepName;
    }
}
