package com.elevenfifty.www.indycivichack.Scene;

import android.content.Context;

import com.elevenfifty.www.indycivichack.FadeTransformer;
import com.elevenfifty.www.indycivichack.R;

import flow.Layout;

/**
 * Created by jeffhooton on 6/6/15.
 */
@Layout(R.layout.page_pager)
public class PagerScene extends IndexedScene {
    private final FadeTransformer transformer;

    public PagerScene(Context context) {
        super(PagerScene.class.getName());
        this.transformer = new FadeTransformer(context);
    }

    @Override
    public Transformer getTransformer() {
        return transformer;
    }
}
