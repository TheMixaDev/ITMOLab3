/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.inanimate.rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.alephdev.lab3.Utils;
import net.alephdev.lab3.alive.City;
import net.alephdev.lab3.inanimate.Planet;
import net.alephdev.lab3.stuff.Location;

/**
 *
 * @author MixaDev
 */
public class Rocket {
    private String name;
    private int weight;
    private City city;
    private List<RocketModule> modules = new ArrayList<>();
    private boolean destroyed = false;

    public Rocket(String name, int weight, City city, List<RocketModule> modules) {
        this.name = name;
        this.weight = weight;
        this.city = city;
        this.modules = modules;
    }
    public Rocket(String name, int weight, City city) {
        this.name = name;
        this.weight = weight;
        this.city = city;
    }
    public Rocket(String name, int weight, Location location) {
        this.name = name;
        this.weight = weight;
        this.city = new City(location.getName(), location);
    }
    public Rocket(String name, int weight, Planet planet) {
        this.name = name;
        this.weight = weight;
        this.city = new City(planet.getName(), new Location(planet));
    }
    public Rocket(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public List<RocketModule> getModules() {
        return modules;
    }
    public void clearModules() {
        this.modules.clear();
    }
    public void addModule(RocketModule module) {
        this.modules.add(module);
    }
    public void removeModule(RocketModule module) {
        this.modules.remove(module);
    }
    public void removeModule(int index) {
        this.modules.remove(index);
    }
    public boolean IsDestroyed() {
        return destroyed;
    }
    public void describe() {
        Utils.print(name+" находилась в "+city.getName());
        for(RocketModule module : modules) {
            module.describe();
        }
    }
    public boolean beforeLaunchCheck() {
        if(city.getLocation().getPlanet().getGravity() < 0.1) {
            Utils.print("Ракета уже находится в зоне низкой гравитации, и не требует запуска");
            return false;
        }
        if(destroyed) {
            Utils.print("Разрушенная ракета не может улететь");
            return false;
        }
        if(weight * city.getLocation().getPlanet().getGravity() < 1000000) {
            Utils.print("Проектировка ракеты позволяет ей улететь с планеты "+city.getLocation().getPlanet().getName()+" в открытый космос");
            return true;
        }
        Utils.print("К сожалению, слишком тяжелая, что бы покинуть планету "+city.getLocation().getPlanet().getName());
        return false;
    }
    public void launch(Location target) {
        if(beforeLaunchCheck()) {
            if(Math.random() < 0.01) {
                explode();
                return;
            }
            city = new City("Космос", new Location("Космическое пространство"));
            Utils.print("Ракета готова к запуску, и улетает в открытый космос!");
        } else {
            Utils.print("Запуск ракеты отменен");
        }
    }
    public void explode() {
        destroyed = true;
        weight = 0;
        Utils.print("Ракета взорвалась!");
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.weight;
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.modules);
        hash = 67 * hash + Objects.hashCode(this.destroyed);
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
        final Rocket other = (Rocket) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.destroyed, other.destroyed)) {
            return false;
        }
        return Objects.equals(this.modules, other.modules);
    }
    @Override
    public String toString() {
        return Objects.isNull(this.name) ? "ракета" : name;
    }
}
