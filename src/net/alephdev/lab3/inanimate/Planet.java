/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.inanimate;

import java.util.Objects;

/**
 *
 * @author MixaDev
 */
public class Planet {
    private String name;
    private double gravity;
    private int weight;
    private int radius;

    public Planet(String name, double gravity, int weight, int radius) {
        this.name = name;
        this.gravity = gravity;
        this.weight = weight;
        this.radius = radius;
    }

    public Planet(String name, double gravity, int weight) {
        this.name = name;
        this.gravity = gravity;
        this.weight = weight;
    }

    public Planet(String name, double gravity) {
        this.name = name;
        this.gravity = gravity;
    }

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.gravity) ^ (Double.doubleToLongBits(this.gravity) >>> 32));
        hash = 83 * hash + this.weight;
        hash = 83 * hash + this.radius;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planet other = (Planet) obj;
        if (Double.doubleToLongBits(this.gravity) != Double.doubleToLongBits(other.gravity)) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.radius != other.radius) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
