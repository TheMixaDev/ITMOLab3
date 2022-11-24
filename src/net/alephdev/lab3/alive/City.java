/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.alive;

import java.util.Objects;
import net.alephdev.lab3.stuff.Location;

/**
 *
 * @author MixaDev
 */
public class City {
    private String name;
    private Location location;

    public City(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.location);
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
        final City other = (City) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.location, other.location);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
