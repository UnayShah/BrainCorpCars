package com.UnayShah.BrainCorp.model;

/**
 * Class to setup common functions for a car.
 */
public class AverageCar {
    protected Double maxSpeed;
    protected Double accelaration;
    protected Double brakeEfficiency;
    protected Double speed;
    protected Double distance;
    protected Double distanceFromHome;

    protected Boolean carOn;
    protected Boolean headlights;
    protected Dashboard dashboard;
    private GEAR currentGear;

    public AverageCar() {
        this.maxSpeed = 50.0;
        this.accelaration = 5.0;
        this.brakeEfficiency = -10.0;
        this.speed = 0.0;
        this.distance = 0.0;
        this.distanceFromHome = 0.0;

        this.carOn = Boolean.FALSE;
        this.headlights = Boolean.FALSE;
        this.dashboard = new Dashboard();
        this.currentGear = GEAR.PARK;
    }

    protected void setSpeed(Double speed) {
        if (this.isCarOn())
            this.speed = Math.max(Math.min(speed, this.maxSpeed), 0);
        if (this.speed == 0)
            this.currentGear = GEAR.PARK;
        else
            this.currentGear = GEAR.FORWARD;
    }

    protected void setCurrentGear(GEAR gear) {
        if (this.speed == 0)
            this.currentGear = gear;
    }

    public GEAR getCurrentGear() {
        return this.currentGear;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getDistanceFromHome() {
        return this.distanceFromHome;
    }

    public void turnOn() {
        this.carOn = true;
        this.distanceFromHome = 0.0;
    }

    public void turnOff() {
        if (this.getSpeed() == 0.0)
            this.carOn = false;
    }

    public Boolean isCarOn() {
        return this.carOn;
    }

    public void gas(Integer time) {
        if (this.isCarOn()) {
            Double newSpeed = this.getSpeed() + (this.brakeEfficiency * time);
            if (newSpeed < 0)
                newSpeed = 0.0;
            if (newSpeed > 50)
                newSpeed = 50.0;
            this.setSpeed(this.getSpeed() + (this.accelaration * time));
        }
    }

    public void brake(Integer time) {
        if (this.isCarOn()) {
            Double newSpeed = this.getSpeed() + (this.brakeEfficiency * time);
            if (newSpeed < 0)
                newSpeed = 0.0;
            if (newSpeed > 50)
                newSpeed = 50.0;
            this.setSpeed(this.getSpeed() + (this.brakeEfficiency * time));
        }
    }

    public void drive(Integer time) {
        this.distance += time * speed;
        this.distanceFromHome += time * speed;
    }

    public void turnOnHeadlights() {
        this.headlights = Boolean.TRUE;
    }

    public void turnOffHeadlights() {
        this.headlights = Boolean.FALSE;
    }

    public Boolean getHeadlights() {
        return this.headlights;
    }

    public String checkDashboard() {
        this.dashboard.setDashboard(this.carOn, this.headlights, this.speed, this.distance, this.distanceFromHome,
                this.currentGear);
        return this.dashboard.checkDashboard();
    }
}