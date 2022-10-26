package com.UnayShah.BrainCorp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AverageCarTest {
    @Mock
    private AverageCar averageCar;

    @BeforeEach
    public void initializeEach() {
        averageCar = new AverageCar();
        assertNotNull(averageCar);
    }

    @Test
    public void testCarOn() {
        assertFalse(averageCar.isCarOn());
        averageCar.turnOn();
        assertTrue(averageCar.isCarOn());
        averageCar.turnOn();
        assertTrue(averageCar.isCarOn());
    }

    @Test
    public void testCarOff() {
        assertFalse(averageCar.isCarOn());
        averageCar.turnOn();
        assertTrue(averageCar.isCarOn());
        averageCar.turnOff();
        assertFalse(averageCar.isCarOn());
    }

    @Test
    public void testSetSpeedCarOff() {
        assertEquals(0, averageCar.getSpeed());
        averageCar.setSpeed(10);
        assertEquals(0, averageCar.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        averageCar.turnOn();
        averageCar.setSpeed(10);
        assertEquals(10, averageCar.getSpeed());
    }

    @Test
    public void testSetSpeedExceed() {
        averageCar.turnOn();
        averageCar.setSpeed(100);
        assertEquals(50, averageCar.getSpeed());
    }

    @Test
    public void testSetSpeedNegative() {
        averageCar.turnOn();
        averageCar.setSpeed(-10);
        assertEquals(0, averageCar.getSpeed());
    }

    @Test
    public void testGasCarOff() {
        averageCar.gas(100);
        assertEquals(0, averageCar.getSpeed());
    }

    @Test
    public void testGas() {
        averageCar.turnOn();
        averageCar.gas(5);
        assertEquals(5 * 5, averageCar.getSpeed());
    }

    @Test
    public void testGasExceed() {
        averageCar.turnOn();
        averageCar.gas(20);
        assertEquals(50, averageCar.getSpeed());
    }

    @Test
    public void testBreakCarOff() {
        averageCar.brake(3);
        assertEquals(0, averageCar.getSpeed());
    }

    @Test
    public void testBreak() {
        averageCar.turnOn();
        averageCar.gas(5);
        assertEquals(5 * 5, averageCar.getSpeed());
        averageCar.brake(2);
        assertEquals(5, averageCar.getSpeed());
    }

    @Test
    public void testBreakExceedSpeed() {
        averageCar.turnOn();
        averageCar.gas(20);
        averageCar.brake(3);
        assertEquals(20, averageCar.getSpeed());
    }

    @Test
    public void testBreakExceedBrake() {
        averageCar.turnOn();
        averageCar.gas(20);
        averageCar.brake(10);
        assertEquals(0, averageCar.getSpeed());
    }

    @Test
    public void testGearInitial() {
        assertEquals(GEAR.PARK, averageCar.getCurrentGear());
    }

    @Test
    public void testGearForward() {
        averageCar.turnOn();
        averageCar.gas(4);
        assertEquals(GEAR.FORWARD, averageCar.getCurrentGear());
    }

    @Test
    public void testGearBrake() {
        averageCar.turnOn();
        averageCar.gas(4);
        assertEquals(GEAR.FORWARD, averageCar.getCurrentGear());
        averageCar.brake(2);
        assertEquals(GEAR.PARK, averageCar.getCurrentGear());
        averageCar.brake(1);
        assertEquals(GEAR.PARK, averageCar.getCurrentGear());
    }

    @Test
    public void testDistanceCarOff() {
        averageCar.drive(10);
        assertEquals(0, averageCar.getDistance());
    }

    @Test
    public void testDistanceNoGas() {
        averageCar.turnOn();
        averageCar.drive(10);
        assertEquals(0, averageCar.getDistance());
    }

    @Test
    public void testDistance() {
        averageCar.turnOn();
        averageCar.gas(5);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10, averageCar.getDistance());
    }

    @Test
    public void testDistanceExceedSpeed() {
        averageCar.turnOn();
        averageCar.gas(5);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10, averageCar.getDistance());
        averageCar.gas(6);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10 + 10 * 50, averageCar.getDistance());
    }

    @Test
    public void testDistanceAfterBrake() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistance());
        averageCar.brake(4);
        averageCar.drive(10);
        assertEquals(500 + 100, averageCar.getDistance());
    }

    @Test
    public void testDistanceAfterFullBrake() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistance());
        averageCar.brake(6);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistance());
    }

    @Test
    public void testDistanceAfterTurningOff() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        averageCar.brake(6);
        averageCar.drive(10);
        averageCar.turnOff();
        averageCar.gas(2);
        averageCar.drive(5);
        assertEquals(500, averageCar.getDistance());
    }

    @Test
    public void testDistanceTurningOffOnAgain() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        averageCar.brake(4);
        averageCar.drive(10);
        averageCar.brake(1);
        averageCar.drive(5);
        averageCar.turnOff();
        averageCar.turnOn();
        averageCar.gas(2);
        averageCar.drive(5);
        assertEquals(650, averageCar.getDistance());
    }

    @Test
    public void testDistanceFromHomeCarOff() {
        averageCar.drive(10);
        assertEquals(0, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeNoGas() {
        averageCar.turnOn();
        averageCar.drive(10);
        assertEquals(0, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHome() {
        averageCar.turnOn();
        averageCar.gas(5);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeExceedSpeed() {
        averageCar.turnOn();
        averageCar.gas(5);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10, averageCar.getDistanceFromHome());
        averageCar.gas(6);
        averageCar.drive(10);
        assertEquals(5 * 5 * 10 + 10 * 50, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterBrake() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistanceFromHome());
        averageCar.brake(4);
        averageCar.drive(10);
        assertEquals(500 + 100, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterFullBrake() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistanceFromHome());
        averageCar.brake(6);
        averageCar.drive(10);
        assertEquals(500, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeAfterTurningOff() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        averageCar.brake(6);
        averageCar.drive(10);
        averageCar.turnOff();
        averageCar.gas(2);
        averageCar.drive(5);
        assertEquals(500, averageCar.getDistanceFromHome());
    }

    @Test
    public void testDistanceFromHomeTurningOffOnAgain() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        averageCar.brake(4);
        averageCar.drive(10);
        averageCar.brake(1);
        averageCar.drive(5);
        averageCar.turnOff();
        averageCar.turnOn();
        averageCar.gas(2);
        averageCar.drive(5);
        assertEquals(50, averageCar.getDistanceFromHome());
    }

    @Test
    public void checkDashboardCarOff() {
        assertEquals("Dashboard [engine=" + Boolean.FALSE + ", headlights=" + Boolean.FALSE + ", speed=" + 0
                + ", totalDistance="
                + 0 + ", distanceFromHome=" + 0 + ", currentGear=" + GEAR.PARK, averageCar.checkDashboard());
    }

    @Test
    public void checkDashboard() {
        averageCar.turnOn();
        averageCar.gas(50);
        averageCar.drive(10);
        averageCar.brake(4);
        averageCar.drive(10);
        averageCar.brake(1);
        averageCar.drive(5);
        averageCar.turnOff();
        averageCar.turnOn();
        averageCar.gas(2);
        averageCar.drive(5);
        averageCar.turnOnHeadlights();
        System.out.println(averageCar.checkDashboard());
        System.out.println("Dashboard [engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed=" + 10
                + ", totalDistance="
                + 650 + ", distanceFromHome=" + 50 + ", currentGear=" + GEAR.FORWARD);
        assertEquals("Dashboard [engine=" + Boolean.TRUE + ", headlights=" + Boolean.TRUE + ", speed=" + 10
                + ", totalDistance="
                + 650 + ", distanceFromHome=" + 50 + ", currentGear=" + GEAR.FORWARD, averageCar.checkDashboard());
    }

    @Test
    public void testHeadlights() {
        assertFalse(averageCar.getHeadlights());
        averageCar.turnOffHeadlights();
        assertFalse(averageCar.getHeadlights());
        averageCar.turnOnHeadlights();
        assertTrue(averageCar.getHeadlights());
        averageCar.turnOnHeadlights();
        assertTrue(averageCar.getHeadlights());
        averageCar.turnOffHeadlights();
        assertFalse(averageCar.getHeadlights());
        averageCar.turnOn();
        assertFalse(averageCar.getHeadlights());
        averageCar.turnOffHeadlights();
        assertFalse(averageCar.getHeadlights());
        averageCar.turnOnHeadlights();
        assertTrue(averageCar.getHeadlights());
        averageCar.turnOnHeadlights();
        assertTrue(averageCar.getHeadlights());
        averageCar.turnOffHeadlights();
        assertFalse(averageCar.getHeadlights());
    }
}
