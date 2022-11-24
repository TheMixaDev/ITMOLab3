/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.alive;

import net.alephdev.lab3.Utils;
import net.alephdev.lab3.stuff.Location;
import net.alephdev.lab3.stuff.Takeable;

/**
 *
 * @author MixaDev
 */
public class Human {
    private final String name;
    private int karma = 50;
    private int mood = 50;
    private int saturation = 70;
    private int hydration = 70;
    private Location location;
    private int strength = 10;
    private Takeable rightArmContent;
    private Takeable leftArmContent;

    public Human(String name, Location location) {
        this.name = name;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void checkCriticalStats() {
        if(mood < 1)
            Utils.print(this.name+" впал в депрессию");
        if(saturation < 10) {
            Utils.print(this.name+" невероятно голоден");
            eat((int)Math.round(Math.random()*20)+20);
        }
        if(hydration < 10) {
            Utils.print(this.name+" очень хочет попить воды");
            drink((int)Math.round(Math.random()*20)+20);
        }
    }
    public void passiveStatsReduce() {
        if(Math.random() < 0.5)
            mood -= 1;
        if(Math.random() < 0.5)
            saturation -= 1;
        if(Math.random() < 0.5)
            hydration -= 1;
        checkCriticalStats();
    }
    public void eat(int effectiveness) {
        this.saturation += effectiveness;
        Utils.print(this.name+" поел и сыт теперь на дополнительные +"+effectiveness+"%");
    }
    public void drink(int effectiveness) {
        this.hydration += effectiveness;
        Utils.print(this.name+" попил и насыщен теперь на дополнительные +"+effectiveness+"%");
    }
    public void printStats() {
        Utils.print(this.name+" в хорошем настроении на "+this.mood+"%, сыт на "+saturation+"% и насыщен на "+hydration+"%");
    }
    public int getKarma() {
        return karma;
    }
    public void addKarma(int karma) {
        this.karma += karma;
    }
    public void reduceKarma(int karma) {
        this.addKarma(-karma);
    }
    public int getMood() {
        return mood;
    }
    public void addMood(int mood) {
        this.mood += mood;
    }
    public void reduceMood(int mood) {
        this.addMood(-mood);
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public Takeable getRightArmContent() {
        return rightArmContent;
    }
    public Takeable getLeftArmContent() {
        return leftArmContent;
    }
    public boolean isDepressed() {
        if(mood < 1) {
            Utils.print(this.name+" не имеет мотивации что-либо делать в депрессии.");
            return true;
        }
        return false;
    }
    public void setInArmContent(Takeable item) {
        if(isDepressed()) return;
        if(item.getWeight() > strength) {
            Utils.print(getName()+" не может взять "+item.toString()+" поскольку он слишком тяжелый");
            return;
        }
        item.take(this);
        passiveStatsReduce();
    }
    public void dropRightArmContent() {
        if(isDepressed()) return;
        rightArmContent.drop(this);
        passiveStatsReduce();
    }
    public void dropLeftArmContent() {
        if(isDepressed()) return;
        leftArmContent.drop(this);
        passiveStatsReduce();
    }
    public void setUnsafeRightArmContent(Takeable rightArmContent) {
        this.rightArmContent = rightArmContent;
    }
    public void setUnsafeLeftArmContent(Takeable leftArmContent) {
        this.leftArmContent = leftArmContent;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void look(Object observing, boolean positive) {
        if(isDepressed()) return;
        Utils.print(this.name+" смотрит на "+observing+" из "+location.getName());
        if(positive)
            addMood(7);
        else
            reduceMood(7);
        passiveStatsReduce();
    }
    public void thinkProcess(int time) {
        if(isDepressed()) return;
        addMood(time);
        addKarma((int)(time/2));
        Utils.print(this.name+" поразмылшлял в течении "+time+" минут");
        passiveStatsReduce();
    }
    public void notPermitTo(Location location, SocialGroup group) {
        notPermitTo(location, group, null);
    }
    public void notPermitTo(Location location, SocialGroup group, Object additional) {
        if(isDepressed()) return;
        group.reduceRating(2);
        group.addBannedLocation(location);
        reduceMood(2);
        Utils.print(this.name+" не пустит на "+location+" "+group.toString(this)+(additional == null ? "" : " с "+additional));
        passiveStatsReduce();
    }
    public void callCommunity(Community community) {
        if(isDepressed()) return;
        if(!community.isActive()) {
            Utils.print(this.name+" не может обратиться к исчезновшому "+community.toString());
            return;
        }
        community.setLocation(getLocation());
        Utils.print(this.name+" призвал "+community.toString());
        passiveStatsReduce();
    }
}
