package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

public class IconMapping {
    List<Tuple<Class, Integer>> map;

    public IconMapping() {
        this.map = new ArrayList<Tuple<Class, Integer>>();
    }

    public void addMapping(Class act, Integer draw) {
        map.add(new Tuple(act, draw));
    }

    public Class getActivity(int pos) {
        return this.map.get(pos).i;
    }

    public Integer getDrawable(int pos) {
        return this.map.get(pos).j;
    }

    public int size() {
        return this.map.size();
    }

    private class Tuple<I, J> {
        I i;
        J j;

        public Tuple (I i, J j) {
            this.i = i;
            this.j = j;
        }

    }
}
