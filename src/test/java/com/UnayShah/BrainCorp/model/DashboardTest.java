package com.UnayShah.BrainCorp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DashboardTest {
    Dashboard dashboard;

    Boolean engine = Boolean.TRUE;
    Boolean headlights = Boolean.FALSE;
    Double speed = 100.0;
    Double totalDistance = 100.0;
    Double distanceFromHome = 100.0;
    GEAR currentGear = GEAR.FORWARD;

    @BeforeEach
    public void initializeDashboard() {
        dashboard = new Dashboard(engine, headlights, speed, totalDistance, distanceFromHome, currentGear);
    }

    @Test
    public void testDashboardWithValues() {
        assertEquals(dashboard.checkDashboard(),
                "Dashboard [engine=" + engine + ", headlights=" + headlights + ", speed=" + speed + ", totalDistance="
                        + totalDistance + ", distanceFromHome=" + distanceFromHome + ", currentGear=" + currentGear);
        assertNotEquals(dashboard.checkDashboard(),
                "Dashboard [engine=" + !engine + ", headlights=" + headlights + ", speed=" + (speed + 1)
                        + ", totalDistance="
                        + totalDistance + ", distanceFromHome=" + distanceFromHome + ", currentGear=" + currentGear);
    }
}
