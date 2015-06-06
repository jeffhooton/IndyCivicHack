package com.elevenfifty.www.indycivichack.Scene;

import android.content.Context;

import com.elevenfifty.www.indycivichack.FadeTransformer;
import com.elevenfifty.www.indycivichack.R;

import flow.Layout;

/**
 * Created by jeffhooton on 6/6/15.
 */
@Layout(R.layout.page_profile)
public class ProfileScene extends IndexedScene {
    private final FadeTransformer transformer;

    public ProfileScene(Context context) {
        super(ProfileScene.class.getName());
        this.transformer = new FadeTransformer(context);
    }

    @Override
    public Transformer getTransformer() {
        return transformer;
    }
}
