package com.UnayShah.BrainCorp;

import com.UnayShah.BrainCorp.model.FancyCar;
import com.UnayShah.BrainCorp.model.FastCar;
import com.UnayShah.BrainCorp.model.GEAR;
import com.UnayShah.BrainCorp.model.SlowCar;

public class CarsSimulation {
    public static void main(String[] args) {
        FastCar fastCar = new FastCar();
        fastCar.turnOn();
        fastCar.turnOnHeadlights();
        fastCar.gas(11);
        fastCar.drive(30);
        fastCar.drive(30);
        fastCar.gas(20);
        fastCar.drive(60);
        fastCar.brake(3);
        fastCar.turnOff();
        fastCar.checkDashboard();

        FancyCar fancyCar = new FancyCar();
        fancyCar.turnOn();
        fancyCar.turnOnHeadlights();
        fancyCar.gas(11);
        fancyCar.drive(30);
        fancyCar.brake(5);
        fancyCar.drive(3);
        fancyCar.brake(1);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(20);
        fancyCar.drive(30);
        fancyCar.turnOffHeadlights();
        fancyCar.checkDashboard();
        fancyCar.horn();

        SlowCar slowCar = new SlowCar();
        slowCar.turnOn();
        slowCar.gas(11);
        slowCar.drive(30);
        slowCar.brake(6);
        slowCar.turnOff();
        slowCar.checkDashboard();
        slowCar.turnOn();
        slowCar.turnOnHeadlights();
        slowCar.gas(4);
        slowCar.drive(2000);
        slowCar.checkDashboard();

    }
}
