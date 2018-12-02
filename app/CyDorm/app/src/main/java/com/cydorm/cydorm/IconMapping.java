package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage the mapping of an icon to an activity
 * @author Malcolm Boyd
 */
public class IconMapping {
    List<Tuple<Class, Integer>> map;

    /**
     * New mapping class
     */
    public IconMapping() {
        this.map = new ArrayList<Tuple<Class, Integer>>();
    }

    /**
     * Add a new mapping between an icon and the activity
     * @param Class act The activity associated with a drawable
     * @param Int Drawable, the icon
     **/
    public void addMapping(Class act, Integer draw) {
        map.add(new Tuple(act, draw));
    }

    /** Get the activity based on the position of the icon
     * @param int position of icon
     * @return Class Activity associated with position
     */
    public Class getActivity(int pos) {
        return this.map.get(pos).i;
    }

    /** Get drawable based on position
     *
     * @param int position of icon
     * @return Interer the drawable at that position
     */
    public Integer getDrawable(int pos) {
        return this.map.get(pos).j;
    }

    /**
     * @return int the number of mappings
     */
    public int size() {
        return this.map.size();
    }

    /** THe internal mapping tuple class*/
    private class Tuple<I, J> {
        I i;
        J j;

        public Tuple (I i, J j) {
            this.i = i;
            this.j = j;
        }

    }
}
