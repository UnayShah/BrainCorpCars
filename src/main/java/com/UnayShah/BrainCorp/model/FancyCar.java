package com.UnayShah.BrainCorp.model;

public class FancyCar extends AverageCar {
    private GEAR previousGear;

    public FancyCar() {
        super();
        previousGear = GEAR.FORWARD;
        this.maxSpeed = 100.0;
    }

    @Override
    public String checkDashboard() {
        System.out.print("Fancy Car ");
        return "Fancy Car " + super.checkDashboard();
    }

    @Override
    public void setCurrentGear(GEAR gear) {
        if (this.speed == 0) {
            this.currentGear = gear;
            this.previousGear = gear;
        }
    }

    @Override
    protected void setSpeed(Double speed) {
        if (this.isCarOn())
            this.speed = Math.max(Math.min(speed, this.maxSpeed), 0);
        if (this.speed == 0)
            this.currentGear = GEAR.PARK;
        else
            this.currentGear = previousGear;
    }

    @Override
    public void drive(Integer time) {
        if (this.isCarOn()) {
            this.distance += time * speed;
            this.distanceFromHome = this.getCurrentGear() == GEAR.FORWARD ? this.distanceFromHome + time * this.speed
                    : this.getCurrentGear() == GEAR.REVERSE ? this.distanceFromHome - time * this.speed
                            : this.distanceFromHome;
        }
    }

    public String horn() {
        System.out.println("beep beep");
        return "beep beep";
    }
}
