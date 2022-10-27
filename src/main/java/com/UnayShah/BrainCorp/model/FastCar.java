package com.UnayShah.BrainCorp.model;

public class FastCar extends AverageCar {
    public FastCar() {
        super();
        this.accelaration = 10.0;
        this.maxSpeed = 150.0;
        this.brakeEfficiency = -10.0;
    }

    @Override
    public String checkDashboard() {
        System.out.print("Fast Car ");
        return "Fast Car " + super.checkDashboard();
    }
}
