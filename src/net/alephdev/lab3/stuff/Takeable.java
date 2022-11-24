/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab3.stuff;

import net.alephdev.lab3.alive.Human;

/**
 *
 * @author MixaDev
 */
public interface Takeable {
    public void take(Human human);
    public void drop(Human human);
    public int getWeight();
}
