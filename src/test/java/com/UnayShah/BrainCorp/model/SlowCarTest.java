package com.UnayShah.BrainCorp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class SlowCarTest {
    @Mock
    private SlowCar slowCar;

    @BeforeEach
    public void initializeEach() {
        slowCar = new SlowCar();
        assertNotNull(slowCar);
    }

    @Test
    public void testCarOn() {
        assertFalse(slowCar.isCarOn());
        slowCar.turnOn();
        assertTrue(slowCar.isCarOn());
        slowCar.turnOn();
        assertTrue(slowCar.isCarOn());
    }

    @Test
    public void testCarOff() {
        assertFalse(slowCar.isCarOn());
        slowCar.turnOn();
        assertTrue(slowCar.isCarOn());
        slowCar.turnOff();
        assertFalse(slowCar.isCarOn());
    }

    @Test
    public void testSetSpeedCarOff() {
        assertEquals(0.0, slowCar.getSpeed());
        slowCar.setSpeed(10.0);
        assertEquals(0, slowCar.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        slowCar.turnOn();
        slowCar.setSpeed(10.0);
        assertEquals(10.0, slowCar.getSpeed());
    }

    @Test
    public void testSetSpeedExceed() {
        slowCar.turnOn();
        slowCar.setSpeed(100.0);
        assertEquals(37.5, slowCar.getSpeed());
    }

    @Test
    public void testSetSpeedNegative() {
        slowCar.turnOn();
        slowCar.setSpeed(-10.0);
        assertEquals(0.0, slowCar.getSpeed());
    }

    @Test
    public void testGasCarOff() {
        slowCar.gas(100);
        assertEquals(0, slowCar.getSpeed());
    }

    @Test
    public void testGas() {
        slowCar.turnOn();
        slowCar.gas(5);
        assertEquals(5 * 3.75, slowCar.getSpeed());
    }

    @Test
    public void testGasExceed() {
        slowCar.turnOn();
        slowCar.gas(20);
        assertEquals(37.5, slowCar.getSpeed());
    }

    @Test
    public void testBreakCarOff() {
        slowCar.brake(3);
        assertEquals(0, slowCar.getSpeed());
    }

    @Test
    public void testBreak() {
        slowCar.turnOn();
        slowCar.gas(8);
        assertEquals(8 * 3.75, slowCar.getSpeed());
        slowCar.brake(1);
        assertEquals(10, slowCar.getSpeed());
    }

    @Test
    public void testBreakExceedSpeed() {
        slowCar.turnOn();
        slowCar.gas(20);
        slowCar.brake(1);
        assertEquals(17.5, slowCar.getSpeed());
    }

    @Test
    public void testBreakExceedBrake() {
        slowCar.turnOn();
        slowCar.gas(20);
        slowCar.brake(10);
        assertEquals(0, slowCar.getSpeed());
    }

    @Test
    public void testGearInitial() {
        assertEquals(GEAR.PARK, slowCar.getCurrentGear());
    }

    @Test
    public void testGearForward() {
        slowCar.turnOn();
        slowCar.gas(4);
        assertEquals(GEAR.FORWARD, slowCar.getCurrentGear());
    }

    @Test
    public void testGearBrake() {
        slowCar.turnOn();
        slowCar.gas(4);
        assertEquals(GEAR.FORWARD, slowCar.getCurrentGear());
        slowCar.brake(2);
        assertEquals(GEAR.PARK, slowCar.getCurrentGear());
        slowCar.brake(1);
        assertEquals(GEAR.PARK, slowCar.getCurrentGear());
    }

    @Test
    public void testDistanceCarOff() {
        slowCar.drive(10);
        assertEquals(0, slowCar.getDistance());
    }

    @Test
    public void testDistanceNoGas() {
        slowCar.turnOn();
        slowCar.drive(10);
        assertEquals(0, slowCar.getDistance());
    }

    @Test
    public void testDistance() {
        slowCar.turnOn();
        slowCar.gas(5);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10, slowCar.getDistance());
    }

    @Test
    public void testDistanceExceedSpeed() {
        slowCar.turnOn();
        slowCar.gas(5);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10, slowCar.getDistance());
        slowCar.gas(6);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10 + 37.5 * 10, slowCar.getDistance());
    }

    @Test
    public void testDistanceAfterBrake() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistance());
        slowCar.brake(1);
        slowCar.drive(10);
        assertEquals(375 + 175, slowCar.getDistance());
    }

    @Test
    public void testDistanceAfterFullBrake() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistance());
        slowCar.brake(6);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistance());
    }

    @Test
    public void testDistanceAfterTurningOff() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        slowCar.brake(6);
        slowCar.drive(10);
        slowCar.turnOff();
        slowCar.gas(2);
        slowCar.drive(5);
        assertEquals(375, slowCar.getDistance());
    }

    @Test
    public void testDistanceTurningOffOnAgain() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        slowCar.brake(1);
        slowCar.drive(10);
        slowCar.brake(1);
        slowCar.drive(5);
        slowCar.turnOff();
        slowCar.turnOn();
        slowCar.gas(2);
        slowCar.drive(5);
        assertEquals(375 + 175 + 3.75 * 2 * 5, slowCar.getDistance());
    }

    @Test
    public void testDistanceFromHomeCarOff() {
        slowCar.drive(10);
        assertEquals(0, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeNoGas() {
        slowCar.turnOn();
        slowCar.drive(10);
        assertEquals(0, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHome() {
        slowCar.turnOn();
        slowCar.gas(5);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeExceedSpeed() {
        slowCar.turnOn();
        slowCar.gas(5);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10, slowCar.getDistanceFromHome());
        slowCar.gas(6);
        slowCar.drive(10);
        assertEquals(5 * 3.75 * 10 + 10 * 37.5, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterBrake() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistanceFromHome());
        slowCar.brake(1);
        slowCar.drive(10);
        assertEquals(375 + 175, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterFullBrake() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistanceFromHome());
        slowCar.brake(6);
        slowCar.drive(10);
        assertEquals(375, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterTurningOff() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        slowCar.brake(6);
        slowCar.drive(10);
        slowCar.turnOff();
        slowCar.gas(2);
        slowCar.drive(5);
        assertEquals(375, slowCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeTurningOffOnAgain() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        slowCar.brake(4);
        slowCar.drive(10);
        slowCar.brake(1);
        slowCar.drive(5);
        slowCar.turnOff();
        slowCar.turnOn();
        slowCar.gas(2);
        slowCar.drive(5);
        assertEquals(2 * 3.75 * 5, slowCar.getDistanceFromHome());
    }

    @Test
    public void checkDashboardCarOff() {
        assertEquals("Slow Car Dashboard [engine=" + Boolean.FALSE + ", headlights=" + Boolean.FALSE + ", speed=" + 0.0
                + ", totalDistance="
                + 0.0 + ", distanceFromHome=" + 0.0 + ", currentGear=" + GEAR.PARK, slowCar.checkDashboard());
    }

    @Test
    public void checkDashboard() {
        slowCar.turnOn();
        slowCar.gas(50);
        slowCar.drive(10);
        slowCar.brake(1);
        slowCar.drive(10);
        slowCar.brake(1);
        slowCar.drive(5);
        slowCar.turnOff();
        slowCar.turnOn();
        slowCar.gas(2);
        slowCar.drive(5);
        slowCar.turnOnHeadlights();
        assertEquals(
                "Slow Car Dashboard [engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed=" + (2 * 3.75)
                        + ", totalDistance="
                        + (375 + 175 + 2 * 3.75 * 5) + ", distanceFromHome=" + (2 * 3.75 * 5) + ", currentGear="
                        + GEAR.FORWARD,
                slowCar.checkDashboard());
    }

    @Test
    public void testHeadlights() {
        assertFalse(slowCar.getHeadlights());
        slowCar.turnOffHeadlights();
        assertFalse(slowCar.getHeadlights());
        slowCar.turnOnHeadlights();
        assertTrue(slowCar.getHeadlights());
        slowCar.turnOnHeadlights();
        assertTrue(slowCar.getHeadlights());
        slowCar.turnOffHeadlights();
        assertFalse(slowCar.getHeadlights());
        slowCar.turnOn();
        assertFalse(slowCar.getHeadlights());
        slowCar.turnOffHeadlights();
        assertFalse(slowCar.getHeadlights());
        slowCar.turnOnHeadlights();
        assertTrue(slowCar.getHeadlights());
        slowCar.turnOnHeadlights();
        assertTrue(slowCar.getHeadlights());
        slowCar.turnOffHeadlights();
        assertFalse(slowCar.getHeadlights());
    }
}
