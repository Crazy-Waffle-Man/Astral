package io.cwm.engine.logic.math;

/**
 * Holds 2 numbers. This can be used to represent 2d pos, movement, acceleration, etc.
 * Dev note: I plan to update this with methods as needed, so this class may see changes for a while.
 */
public record Vec2(double x, double y) {
    public int xi() {return (int) x; }
    public int yi() {return (int) y;}
}
