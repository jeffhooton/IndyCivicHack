package com.elevenfifty.www.indycivichack.Scene;

import android.content.Context;

import com.elevenfifty.www.indycivichack.FadeTransformer;
import com.elevenfifty.www.indycivichack.R;

import flow.Layout;

/**
 * Created by jeffhooton on 6/6/15.
 */
@Layout(R.layout.page_check)
public class CheckScene extends IndexedScene {
    private final FadeTransformer transformer;

    public CheckScene(Context context) {
        super(CheckScene.class.getName());
        this.transformer = new FadeTransformer(context);
    }

    @Override
    public Transformer getTransformer() {
        return transformer;
    }
}
