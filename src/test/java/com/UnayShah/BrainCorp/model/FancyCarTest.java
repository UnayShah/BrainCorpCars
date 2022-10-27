package com.UnayShah.BrainCorp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FancyCarTest {
    @Mock
    private FancyCar fancyCar;
    Double accelaration = 5.0;
    Double maxSpeed = 100.0;
    Double brakeEfficiency = -10.0;

    @BeforeEach
    public void initializeEach() {
        fancyCar = new FancyCar();
        assertNotNull(fancyCar);
    }

    @Test
    public void testCarOn() {
        assertFalse(fancyCar.isCarOn());
        fancyCar.turnOn();
        assertTrue(fancyCar.isCarOn());
        fancyCar.turnOn();
        assertTrue(fancyCar.isCarOn());
    }

    @Test
    public void testCarOff() {
        assertFalse(fancyCar.isCarOn());
        fancyCar.turnOn();
        assertTrue(fancyCar.isCarOn());
        fancyCar.turnOff();
        assertFalse(fancyCar.isCarOn());
    }

    @Test
    public void testSetSpeedCarOff() {
        assertEquals(0.0, fancyCar.getSpeed());
        fancyCar.setSpeed(10.0);
        assertEquals(0, fancyCar.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        fancyCar.turnOn();
        fancyCar.setSpeed(100.0);
        assertEquals(100.0, fancyCar.getSpeed());
    }

    @Test
    public void testSetSpeedExceed() {
        fancyCar.turnOn();
        fancyCar.setSpeed(200.0);
        assertEquals(maxSpeed, fancyCar.getSpeed());
    }

    @Test
    public void testSetSpeedNegative() {
        fancyCar.turnOn();
        fancyCar.setSpeed(-10.0);
        assertEquals(0.0, fancyCar.getSpeed());
    }

    @Test
    public void testGasCarOff() {
        fancyCar.gas(100);
        assertEquals(0, fancyCar.getSpeed());
    }

    @Test
    public void testGas() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        assertEquals(5 * accelaration, fancyCar.getSpeed());
    }

    @Test
    public void testGasExceed() {
        fancyCar.turnOn();
        fancyCar.gas(20);
        assertEquals(100, fancyCar.getSpeed());
    }

    @Test
    public void testBrakeCarOff() {
        fancyCar.brake(3);
        assertEquals(0, fancyCar.getSpeed());
    }

    @Test
    public void testBrake() {
        fancyCar.turnOn();
        fancyCar.gas(8);
        assertEquals(8 * accelaration, fancyCar.getSpeed());
        fancyCar.brake(1);
        assertEquals((8 * accelaration) + brakeEfficiency, fancyCar.getSpeed());
    }

    @Test
    public void testBrakeExceedSpeed() {
        fancyCar.turnOn();
        fancyCar.gas(20);
        fancyCar.brake(10);
        assertEquals(100 + 10 * brakeEfficiency, fancyCar.getSpeed());
    }

    @Test
    public void testBrakeExceedBrake() {
        fancyCar.turnOn();
        fancyCar.gas(20);
        fancyCar.brake(20);
        assertEquals(0, fancyCar.getSpeed());
    }

    @Test
    public void testGearInitial() {
        assertEquals(GEAR.PARK, fancyCar.getCurrentGear());
    }

    @Test
    public void testGearForward() {
        fancyCar.turnOn();
        fancyCar.gas(4);
        assertEquals(GEAR.FORWARD, fancyCar.getCurrentGear());
    }

    @Test
    public void testGearReverse() {
        fancyCar.turnOn();
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(4);
        assertEquals(GEAR.REVERSE, fancyCar.getCurrentGear());
    }

    @Test
    public void testGearForwardRunningCar() {
        fancyCar.turnOn();
        fancyCar.setCurrentGear(GEAR.FORWARD);
        fancyCar.gas(4);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        assertEquals(GEAR.FORWARD, fancyCar.getCurrentGear());
    }

    @Test
    public void testGearReverseRunningCar() {
        fancyCar.turnOn();
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(4);
        fancyCar.setCurrentGear(GEAR.FORWARD);
        assertEquals(GEAR.REVERSE, fancyCar.getCurrentGear());
    }

    @Test
    public void testGearBrake() {
        fancyCar.turnOn();
        fancyCar.gas(4);
        assertEquals(GEAR.FORWARD, fancyCar.getCurrentGear());
        fancyCar.brake(1);
        assertEquals(GEAR.FORWARD, fancyCar.getCurrentGear());
        fancyCar.brake(10);
        assertEquals(GEAR.PARK, fancyCar.getCurrentGear());
    }

    @Test
    public void testDistanceCarOff() {
        fancyCar.drive(10);
        assertEquals(0, fancyCar.getDistance());
    }

    @Test
    public void testDistanceNoGas() {
        fancyCar.turnOn();
        fancyCar.drive(10);
        assertEquals(0, fancyCar.getDistance());
    }

    @Test
    public void testDistance() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10, fancyCar.getDistance());
    }

    @Test
    public void testDistanceExceedSpeed() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10, fancyCar.getDistance());
        fancyCar.gas(20);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10 + maxSpeed * 10, fancyCar.getDistance());
    }

    @Test
    public void testDistanceAfterBrake() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        assertEquals(10 * maxSpeed, fancyCar.getDistance());
        fancyCar.brake(1);
        fancyCar.drive(10);
        assertEquals(10 * maxSpeed + 900, fancyCar.getDistance());
    }

    @Test
    public void testDistanceAfterFullBrake() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        assertEquals(10 * maxSpeed, fancyCar.getDistance());
        fancyCar.brake(60);
        fancyCar.drive(10);
        assertEquals(10 * maxSpeed, fancyCar.getDistance());
    }

    @Test
    public void testDistanceAfterTurningOff() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(60);
        fancyCar.drive(10);
        fancyCar.turnOff();
        fancyCar.gas(2);
        fancyCar.drive(5);
        assertEquals(10 * maxSpeed, fancyCar.getDistance());
    }

    @Test
    public void testDistanceTurningOffOnAgain() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(1);
        fancyCar.drive(10);
        fancyCar.brake(20);
        fancyCar.drive(5);
        fancyCar.turnOff();
        fancyCar.turnOn();
        fancyCar.gas(2);
        fancyCar.drive(5);
        assertEquals(maxSpeed * 10 + 90 * 10 + 2 * accelaration * 5, fancyCar.getDistance());
    }

    @Test
    public void testDistanceFromHomeCarOff() {
        fancyCar.drive(10);
        assertEquals(0, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeNoGas() {
        fancyCar.turnOn();
        fancyCar.drive(10);
        assertEquals(0, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHome() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeReverse() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        fancyCar.brake(10);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(5);
        fancyCar.drive(9);
        assertEquals(5 * accelaration * 1, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeReverseForward() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        fancyCar.brake(10);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(5);
        fancyCar.drive(9);
        fancyCar.brake(10);
        fancyCar.setCurrentGear(GEAR.FORWARD);
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 11, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeReverseExceedHome() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        fancyCar.brake(10);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(5);
        fancyCar.drive(9);
        fancyCar.brake(10);
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(-5 * accelaration * 9, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeExceedSpeed() {
        fancyCar.turnOn();
        fancyCar.gas(5);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10, fancyCar.getDistanceFromHome());
        fancyCar.gas(20);
        fancyCar.drive(10);
        assertEquals(5 * accelaration * 10 + maxSpeed * 10, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterBrake() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        assertEquals(maxSpeed * 10, fancyCar.getDistanceFromHome());
        fancyCar.brake(3);
        fancyCar.drive(10);
        assertEquals(maxSpeed * 10 + 70 * 10, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterFullBrake() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        assertEquals(maxSpeed * 10, fancyCar.getDistanceFromHome());
        fancyCar.brake(15);
        fancyCar.drive(10);
        assertEquals(maxSpeed * 10, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterTurningOff() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(6);
        fancyCar.drive(10);
        fancyCar.brake(10);
        fancyCar.drive(10);
        fancyCar.turnOff();
        fancyCar.gas(2);
        fancyCar.drive(5);
        assertEquals(maxSpeed * 10 + 40 * 10, fancyCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeTurningOffOnAgain() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(6);
        fancyCar.drive(10);
        fancyCar.brake(10);
        fancyCar.drive(5);
        fancyCar.turnOff();
        fancyCar.turnOn();
        fancyCar.gas(2);
        fancyCar.drive(5);
        assertEquals(2 * accelaration * 5, fancyCar.getDistanceFromHome());
    }

    @Test
    public void checkDashboardCarOff() {
        assertEquals("Fancy Car Dashboard engine=" + Boolean.FALSE + ", headlights=" + Boolean.FALSE + ", speed=" + 0.0
                + ", totalDistance="
                + 0.0 + ", distanceFromHome=" + 0.0 + ", currentGear=" + GEAR.PARK, fancyCar.checkDashboard());
    }

    @Test
    public void checkDashboard() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(1);
        fancyCar.drive(10);
        fancyCar.brake(14);
        fancyCar.drive(5);
        fancyCar.turnOff();
        fancyCar.turnOn();
        fancyCar.gas(2);
        fancyCar.drive(5);
        fancyCar.turnOnHeadlights();
        assertEquals(
                "Fancy Car Dashboard engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed="
                        + (2 * accelaration)
                        + ", totalDistance="
                        + (maxSpeed * 10 + 900 + 2 * accelaration * 5) + ", distanceFromHome=" + (2 * accelaration * 5)
                        + ", currentGear=" + GEAR.FORWARD,
                fancyCar.checkDashboard());
    }

    @Test
    public void checkDashboardWithReverse() {
        fancyCar.turnOn();
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.brake(1);
        fancyCar.drive(10);
        fancyCar.brake(14);
        fancyCar.drive(5);
        fancyCar.setCurrentGear(GEAR.REVERSE);
        fancyCar.gas(50);
        fancyCar.drive(10);
        fancyCar.turnOnHeadlights();
        assertEquals(
                "Fancy Car Dashboard engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed="
                        + (maxSpeed)
                        + ", totalDistance="
                        + (2 * maxSpeed * 10 + 900) + ", distanceFromHome=" + (900.0)
                        + ", currentGear=" + GEAR.REVERSE,
                fancyCar.checkDashboard());
    }

    @Test
    public void testHeadlights() {
        assertFalse(fancyCar.getHeadlights());
        fancyCar.turnOffHeadlights();
        assertFalse(fancyCar.getHeadlights());
        fancyCar.turnOnHeadlights();
        assertTrue(fancyCar.getHeadlights());
        fancyCar.turnOnHeadlights();
        assertTrue(fancyCar.getHeadlights());
        fancyCar.turnOffHeadlights();
        assertFalse(fancyCar.getHeadlights());
        fancyCar.turnOn();
        assertFalse(fancyCar.getHeadlights());
        fancyCar.turnOffHeadlights();
        assertFalse(fancyCar.getHeadlights());
        fancyCar.turnOnHeadlights();
        assertTrue(fancyCar.getHeadlights());
        fancyCar.turnOnHeadlights();
        assertTrue(fancyCar.getHeadlights());
        fancyCar.turnOffHeadlights();
        assertFalse(fancyCar.getHeadlights());
    }

    @Test
    public void testHorn() {
        assertEquals("beep beep", fancyCar.horn());
    }
}
