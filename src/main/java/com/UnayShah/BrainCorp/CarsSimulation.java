package com.UnayShah.BrainCorp;

import com.UnayShah.BrainCorp.model.FastCar;
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
