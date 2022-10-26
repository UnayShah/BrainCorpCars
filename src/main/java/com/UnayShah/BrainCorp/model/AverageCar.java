package com.UnayShah.BrainCorp.model;

/**
 * Class to setup common functions for a car.
 */
public class AverageCar {
    protected Integer maxSpeed = 50;
    protected Integer accelaration = 5;
    protected Integer brakeEfficiency = -10;
    protected Integer speed = 0;
    protected Integer distance = 0;
    protected Integer distanceFromHome = 0;

    protected Boolean carOn = Boolean.FALSE;
    protected Boolean headlights = Boolean.FALSE;
    protected Dashboard dashboard;
    private GEAR currentGear = GEAR.PARK;

    public AverageCar() {
        this.maxSpeed = 50;
        this.accelaration = 5;
        this.brakeEfficiency = -10;
        this.speed = 0;
        this.distance = 0;
        this.distanceFromHome = 0;

        this.carOn = Boolean.FALSE;
        this.headlights = Boolean.FALSE;
        this.dashboard = new Dashboard();
        this.currentGear = GEAR.PARK;
    }

    protected void setSpeed(Integer speed) {
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

    public Integer getSpeed() {
        return this.speed;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Integer getDistanceFromHome() {
        return this.distanceFromHome;
    }

    public void turnOn() {
        this.carOn = true;
        this.distanceFromHome = 0;
    }

    public void turnOff() {
        if (this.getSpeed() == 0)
            this.carOn = false;
    }

    public Boolean isCarOn() {
        return this.carOn;
    }

    public void gas(Integer time) {
        if (this.isCarOn()) {
            Integer newSpeed = this.getSpeed() + (this.brakeEfficiency * time);
            if (newSpeed < 0)
                newSpeed = 0;
            if (newSpeed > 50)
                newSpeed = 50;
            this.setSpeed(this.getSpeed() + (this.accelaration * time));
        }
    }

    public void brake(Integer time) {
        if (this.isCarOn()) {
            Integer newSpeed = this.getSpeed() + (this.brakeEfficiency * time);
            if (newSpeed < 0)
                newSpeed = 0;
            if (newSpeed > 50)
                newSpeed = 50;
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