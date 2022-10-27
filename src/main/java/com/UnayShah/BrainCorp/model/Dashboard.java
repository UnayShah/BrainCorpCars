package com.UnayShah.BrainCorp.model;

public class Dashboard {
    Boolean engine;
    Boolean headlights;
    Double speed;
    Double totalDistance;
    Double distanceFromHome;
    GEAR currentGear;

    public Dashboard(Boolean engine, Boolean headlights, Double speed, Double totalDistance, Double distanceFromHome,
            GEAR currentGear) {
        this.engine = engine;
        this.headlights = headlights;
        this.speed = speed;
        this.totalDistance = totalDistance;
        this.distanceFromHome = distanceFromHome;
        this.currentGear = currentGear;
    }

    public Dashboard() {
        this(Boolean.FALSE, Boolean.FALSE, 0.0, 0.0, 0.0, GEAR.PARK);
    }

    public void setDashboard(Boolean engine, Boolean headlights, Double speed, Double totalDistance,
            Double distanceFromHome,
            GEAR currentGear) {
        this.engine = engine;
        this.headlights = headlights;
        this.speed = speed;
        this.totalDistance = totalDistance;
        this.distanceFromHome = distanceFromHome;
        this.currentGear = currentGear;
    }

    public void setDashboard() {
        this.setDashboard(Boolean.FALSE, Boolean.FALSE, 0.0, 0.0, 0.0, GEAR.PARK);
    }

    public String checkDashboard() {
        return "Dashboard engine=" + engine + ", headlights=" + headlights + ", speed=" + speed + ", totalDistance="
                + totalDistance + ", distanceFromHome=" + distanceFromHome + ", currentGear=" + currentGear;
    }

}
