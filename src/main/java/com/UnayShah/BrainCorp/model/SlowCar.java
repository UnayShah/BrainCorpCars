package com.UnayShah.BrainCorp.model;

public class SlowCar extends AverageCar {
    public SlowCar() {
        super();
        this.accelaration = 3.75;
        this.maxSpeed = 37.5;
        this.brakeEfficiency = -20.0;
    }

    @Override
    public String checkDashboard() {
        System.out.print("Slow Car ");
        return "Slow Car " + super.checkDashboard();
    }
}
