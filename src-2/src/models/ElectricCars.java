package models;

public class ElectricCars extends Cars {
    private Boolean sportsMode = false;

    public ElectricCars() {
        super("");
    }

    public ElectricCars(String colour) {
        super(colour);
    }

    public Boolean getSportsMode() {
        return sportsMode;
    }

    public void setSportsMode(Boolean sportsMode) {
        this.sportsMode = sportsMode;
    }

    @Override
    public void accelerate() {
        if (sportsMode) {
            super.accelerate();
            super.accelerate();
            super.accelerate();
            super.accelerate();
        } else {
            super.accelerate();
        }
    }
}
