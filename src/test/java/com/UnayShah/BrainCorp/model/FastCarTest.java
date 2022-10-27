package com.UnayShah.BrainCorp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FastCarTest {
    @Mock
    private FastCar fastCar;
    Double accelaration = 10.0;
    Double maxSpeed = 150.0;
    Double brakeEfficiency = -10.0;

    @BeforeEach
    public void initializeEach() {
        fastCar = new FastCar();
        assertNotNull(fastCar);
    }

    @Test
    public void testCarOn() {
        assertFalse(fastCar.isCarOn());
        fastCar.turnOn();
        assertTrue(fastCar.isCarOn());
        fastCar.turnOn();
        assertTrue(fastCar.isCarOn());
    }

    @Test
    public void testCarOff() {
        assertFalse(fastCar.isCarOn());
        fastCar.turnOn();
        assertTrue(fastCar.isCarOn());
        fastCar.turnOff();
        assertFalse(fastCar.isCarOn());
    }

    @Test
    public void testSetSpeedCarOff() {
        assertEquals(0.0, fastCar.getSpeed());
        fastCar.setSpeed(10.0);
        assertEquals(0, fastCar.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        fastCar.turnOn();
        fastCar.setSpeed(100.0);
        assertEquals(100.0, fastCar.getSpeed());
    }

    @Test
    public void testSetSpeedExceed() {
        fastCar.turnOn();
        fastCar.setSpeed(200.0);
        assertEquals(maxSpeed, fastCar.getSpeed());
    }

    @Test
    public void testSetSpeedNegative() {
        fastCar.turnOn();
        fastCar.setSpeed(-10.0);
        assertEquals(0.0, fastCar.getSpeed());
    }

    @Test
    public void testGasCarOff() {
        fastCar.gas(100);
        assertEquals(0, fastCar.getSpeed());
    }

    @Test
    public void testGas() {
        fastCar.turnOn();
        fastCar.gas(5);
        assertEquals(5 * accelaration, fastCar.getSpeed());
    }

    @Test
    public void testGasExceed() {
        fastCar.turnOn();
        fastCar.gas(20);
        assertEquals(150, fastCar.getSpeed());
    }

    @Test
    public void testBrakeCarOff() {
        fastCar.brake(3);
        assertEquals(0, fastCar.getSpeed());
    }

    @Test
    public void testBrake() {
        fastCar.turnOn();
        fastCar.gas(8);
        assertEquals(8 * accelaration, fastCar.getSpeed());
        fastCar.brake(1);
        assertEquals((8 * accelaration) + brakeEfficiency, fastCar.getSpeed());
    }

    @Test
    public void testBrakeExceedSpeed() {
        fastCar.turnOn();
        fastCar.gas(20);
        fastCar.brake(10);
        assertEquals(150 + 10 * brakeEfficiency, fastCar.getSpeed());
    }

    @Test
    public void testBrakeExceedBrake() {
        fastCar.turnOn();
        fastCar.gas(20);
        fastCar.brake(20);
        assertEquals(0, fastCar.getSpeed());
    }

    @Test
    public void testGearInitial() {
        assertEquals(GEAR.PARK, fastCar.getCurrentGear());
    }

    @Test
    public void testGearForward() {
        fastCar.turnOn();
        fastCar.gas(4);
        assertEquals(GEAR.FORWARD, fastCar.getCurrentGear());
    }

    @Test
    public void testGearBrake() {
        fastCar.turnOn();
        fastCar.gas(4);
        assertEquals(GEAR.FORWARD, fastCar.getCurrentGear());
        fastCar.brake(2);
        assertEquals(GEAR.FORWARD, fastCar.getCurrentGear());
        fastCar.brake(10);
        assertEquals(GEAR.PARK, fastCar.getCurrentGear());
    }

    @Test
    public void testDistanceCarOff() {
        fastCar.drive(10);
        assertEquals(0, fastCar.getDistance());
    }

    @Test
    public void testDistanceNoGas() {
        fastCar.turnOn();
        fastCar.drive(10);
        assertEquals(0, fastCar.getDistance());
    }

    @Test
    public void testDistance() {
        fastCar.turnOn();
        fastCar.gas(5);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10, fastCar.getDistance());
    }

    @Test
    public void testDistanceExceedSpeed() {
        fastCar.turnOn();
        fastCar.gas(5);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10, fastCar.getDistance());
        fastCar.gas(11);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10 + maxSpeed * 10, fastCar.getDistance());
    }

    @Test
    public void testDistanceAfterBrake() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        assertEquals(10 * maxSpeed, fastCar.getDistance());
        fastCar.brake(1);
        fastCar.drive(10);
        assertEquals(10 * maxSpeed + 1400, fastCar.getDistance());
    }

    @Test
    public void testDistanceAfterFullBrake() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        assertEquals(10 * maxSpeed, fastCar.getDistance());
        fastCar.brake(60);
        fastCar.drive(10);
        assertEquals(10 * maxSpeed, fastCar.getDistance());
    }

    @Test
    public void testDistanceAfterTurningOff() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        fastCar.brake(60);
        fastCar.drive(10);
        fastCar.turnOff();
        fastCar.gas(2);
        fastCar.drive(5);
        assertEquals(10 * maxSpeed, fastCar.getDistance());
    }

    @Test
    public void testDistanceTurningOffOnAgain() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        fastCar.brake(1);
        fastCar.drive(10);
        fastCar.brake(20);
        fastCar.drive(5);
        fastCar.turnOff();
        fastCar.turnOn();
        fastCar.gas(2);
        fastCar.drive(5);
        assertEquals(maxSpeed * 10 + 140 * 10 + 2 * accelaration * 5, fastCar.getDistance());
    }

    @Test
    public void testDistanceFromHomeCarOff() {
        fastCar.drive(10);
        assertEquals(0, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeNoGas() {
        fastCar.turnOn();
        fastCar.drive(10);
        assertEquals(0, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHome() {
        fastCar.turnOn();
        fastCar.gas(5);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeExceedSpeed() {
        fastCar.turnOn();
        fastCar.gas(5);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10, fastCar.getDistanceFromHome());
        fastCar.gas(11);
        fastCar.drive(10);
        assertEquals(5 * accelaration * 10 + maxSpeed * 10, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterBrake() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        assertEquals(maxSpeed * 10, fastCar.getDistanceFromHome());
        fastCar.brake(3);
        fastCar.drive(10);
        assertEquals(maxSpeed * 10 + 120 * 10, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterFullBrake() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        assertEquals(maxSpeed * 10, fastCar.getDistanceFromHome());
        fastCar.brake(15);
        fastCar.drive(10);
        assertEquals(maxSpeed * 10, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterTurningOff() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        fastCar.brake(6);
        fastCar.drive(10);
        fastCar.brake(10);
        fastCar.drive(10);
        fastCar.turnOff();
        fastCar.gas(2);
        fastCar.drive(5);
        assertEquals(maxSpeed * 10 + 90 * 10, fastCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeTurningOffOnAgain() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        fastCar.brake(6);
        fastCar.drive(10);
        fastCar.brake(10);
        fastCar.drive(5);
        fastCar.turnOff();
        fastCar.turnOn();
        fastCar.gas(2);
        fastCar.drive(5);
        assertEquals(2 * accelaration * 5, fastCar.getDistanceFromHome());
    }

    @Test
    public void checkDashboardCarOff() {
        assertEquals("Fast Car Dashboard engine=" + Boolean.FALSE + ", headlights=" + Boolean.FALSE + ", speed=" + 0.0
                + ", totalDistance="
                + 0.0 + ", distanceFromHome=" + 0.0 + ", currentGear=" + GEAR.PARK, fastCar.checkDashboard());
    }

    @Test
    public void checkDashboard() {
        fastCar.turnOn();
        fastCar.gas(50);
        fastCar.drive(10);
        fastCar.brake(1);
        fastCar.drive(10);
        fastCar.brake(14);
        fastCar.drive(5);
        fastCar.turnOff();
        fastCar.turnOn();
        fastCar.gas(2);
        fastCar.drive(5);
        fastCar.turnOnHeadlights();
        assertEquals(
                "Fast Car Dashboard engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed="
                        + (2 * accelaration)
                        + ", totalDistance="
                        + (maxSpeed * 10 + 1400 + 2 * accelaration * 5) + ", distanceFromHome=" + (2 * accelaration * 5)
                        + ", currentGear=" + GEAR.FORWARD,
                fastCar.checkDashboard());
    }

    @Test
    public void testHeadlights() {
        assertFalse(fastCar.getHeadlights());
        fastCar.turnOffHeadlights();
        assertFalse(fastCar.getHeadlights());
        fastCar.turnOnHeadlights();
        assertTrue(fastCar.getHeadlights());
        fastCar.turnOnHeadlights();
        assertTrue(fastCar.getHeadlights());
        fastCar.turnOffHeadlights();
        assertFalse(fastCar.getHeadlights());
        fastCar.turnOn();
        assertFalse(fastCar.getHeadlights());
        fastCar.turnOffHeadlights();
        assertFalse(fastCar.getHeadlights());
        fastCar.turnOnHeadlights();
        assertTrue(fastCar.getHeadlights());
        fastCar.turnOnHeadlights();
        assertTrue(fastCar.getHeadlights());
        fastCar.turnOffHeadlights();
        assertFalse(fastCar.getHeadlights());
    }
}
