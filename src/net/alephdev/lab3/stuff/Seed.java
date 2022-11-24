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
public class Seed implements Takeable {
    private final int weight;

    public Seed(int weight) {
        this.weight = weight;
    }
    @Override
    public void take(Human human) {
        if(human.getRightArmContent() == null) {
            human.setUnsafeRightArmContent(this);
            Utils.print(human.getName()+" взял в правую руку "+this.toString());
        } else if(human.getLeftArmContent() == null) {
            human.setUnsafeLeftArmContent(this);
            Utils.print(human.getName()+" взял в левую руку "+this.toString());
        } else {
            Utils.print("У "+human.getName()+" заняты руки, и потому он не может взять "+this.toString());
        }
    }
    @Override
    public void drop(Human human) {
        if(this.equals(human.getRightArmContent())) {
            human.setUnsafeRightArmContent(null);
            Utils.print(human.getName()+" отпустил из правой руки "+this.toString());
        } else if(this.equals(human.getLeftArmContent())) {
            human.setUnsafeLeftArmContent(null);
            Utils.print(human.getName()+" отпустил из левой руки "+this.toString());
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
        hash = 17 * hash + this.weight;
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
        final Seed other = (Seed) obj;
        return this.weight == other.weight;
    }
    @Override
    public String toString() {
        return "семя";
    }
}
