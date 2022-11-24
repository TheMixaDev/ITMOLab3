/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3;

import net.alephdev.lab3.alive.City;
import net.alephdev.lab3.alive.Community;
import net.alephdev.lab3.alive.Human;
import net.alephdev.lab3.alive.groups.Citizens;
import net.alephdev.lab3.alive.groups.PoorStratum;
import net.alephdev.lab3.alive.groups.RichStratum;
import net.alephdev.lab3.inanimate.Planet;
import net.alephdev.lab3.inanimate.rocket.Rocket;
import net.alephdev.lab3.inanimate.rocket.RocketModule;
import net.alephdev.lab3.inanimate.rocket.RocketRoom;
import net.alephdev.lab3.stuff.AstronomicalInstruments;
import net.alephdev.lab3.stuff.Feeling;
import net.alephdev.lab3.stuff.Location;
import net.alephdev.lab3.stuff.Material;
import net.alephdev.lab3.stuff.Origin;
import net.alephdev.lab3.stuff.Seed;
import net.alephdev.lab3.stuff.shapes.Cone;
import net.alephdev.lab3.stuff.shapes.Cylinder;

/**
 *
 * @author MixaDev
 */
public class Main {
    public static void main(String[] args) {
        Material steel = new Material("сталь");
        Cylinder basicRocketCylinder = new Cylinder(10, 10);
        Cone rocketLivingModule = new Cone(10, 0, 10);
        
        Planet earth = new Planet("Земля", 9.8, (int)5.972E24);
        Location spaceCityLocation = new Location(earth);
        City spaceCity = new City("Космический городок", spaceCityLocation);
        Rocket rocket = new Rocket("ракета", 100000, spaceCity);
        rocket.addModule(new RocketModule(1, steel, basicRocketCylinder));
        rocket.addModule(new RocketModule(2, steel, basicRocketCylinder));
        rocket.addModule(new RocketModule(3, steel, basicRocketCylinder));
        RocketModule livingModule = new RocketModule(4, steel, rocketLivingModule);
        livingModule.addRoom(new RocketRoom("кабина для космонавтов"));
        livingModule.addRoom(new RocketRoom("запасы продовольствия"));
        livingModule.addRoom(new RocketRoom("приборы управления"));
        rocket.addModule(livingModule);
        Human znayka = new Human("Знайка", new Location("комната", earth));
        
        znayka.look(rocket, true);
        rocket.describe();
        rocket.beforeLaunchCheck();
        
        Utils.print("");
        // SECOND PART
        Planet moon = new Planet("Луна", 1.6, (int)7.36E22);
        Location moonLocation = new Location(moon);
        Human spruts = new Human("господин Спрутс", moonLocation);
        spruts.reduceKarma(30);
        Community hugePlants = new Community("гиганстких растений", 5);
        PoorStratum poorStratum = new PoorStratum("бедняки", moon, 100000);
        RichStratum richStratum = new RichStratum("богачи", moon, 100);
        Citizens earthCitizens = new Citizens("жители", earth, Origin.RESIDENT);
        Community astronoms = new Community("лучшие лунные астрономы", 20, 70);
        Seed seeds = new Seed(1);
        
        hugePlants.destroy(spruts);
        (new Feeling("облегчение", 5)).apply(spruts);
        (new Feeling("уверенность", 7)).apply(spruts);
        poorStratum.setDependence(richStratum);
        poorStratum.addBan(seeds, moon);
        spruts.thinkProcess(5);
        earthCitizens.canSendSpaceship(rocket, moon);
        spruts.notPermitTo(moonLocation, earthCitizens, seeds);
        spruts.callCommunity(astronoms);
        astronoms.analyze(rocket, new AstronomicalInstruments(30));
    }
}
