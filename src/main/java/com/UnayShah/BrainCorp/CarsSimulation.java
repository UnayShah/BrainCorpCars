package com.UnayShah.BrainCorp;

import com.UnayShah.BrainCorp.model.FastCar;

public class CarsSimulation {
    public static void main(String[] args) {
        FastCar fastCar = new FastCar();
        fastCar.turnOn();
        fastCar.gas(1);
        fastCar.drive(10);
        fastCar.brake(3);
        fastCar.turnOff();
        System.out.println(fastCar.checkDashboard());
    }
}
