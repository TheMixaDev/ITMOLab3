/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.stuff;

import net.alephdev.lab3.Utils;
import net.alephdev.lab3.alive.Human;

/**
 *
 * @author MixaDev
 */
public class AstronomicalInstruments implements Takeable {
    private final int weight;

    public AstronomicalInstruments(int weight) {
        this.weight = weight;
    }
    @Override
    public void take(Human human) {
        if(human.getRightArmContent() == null &&
                human.getLeftArmContent() == null) {
            Utils.print(human.getName()+" взял "+this.toString());
            human.setUnsafeLeftArmContent(this);
        } else {
            Utils.print("У "+human.getName()+" заняты руки, и потому он не может взять "+this.toString());
        }
    }
    @Override
    public void drop(Human human) {
        if(this.equals(human.getRightArmContent()) &&
                this.equals(human.getLeftArmContent())) {
            human.setUnsafeRightArmContent(null);
            human.setUnsafeLeftArmContent(null);
            Utils.print(human.getName()+" отпустил "+this.toString());
        } else {
            Utils.print(human.getName()+" не держит "+this.toString());
        }
    }
    @Override
    public int getWeight() {
        return weight;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.weight;
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
        final AstronomicalInstruments other = (AstronomicalInstruments) obj;
        return this.weight == other.weight;
    }
    @Override
    public String toString() {
        return "астрономические инструменты";
    }
}
