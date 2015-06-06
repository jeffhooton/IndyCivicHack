package com.elevenfifty.www.indycivichack.Scene;

import com.davidstemmer.screenplay.scene.StandardScene;

/**
 * Created by jeffhooton on 6/6/15.
 */
public abstract class IndexedScene extends StandardScene {

    public final String id;

    protected IndexedScene(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexedScene that = (IndexedScene) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}