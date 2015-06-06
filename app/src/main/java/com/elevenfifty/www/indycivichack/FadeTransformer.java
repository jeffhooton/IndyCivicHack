package com.elevenfifty.www.indycivichack;

import android.content.Context;

import com.davidstemmer.screenplay.scene.transformer.TweenTransformer;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class FadeTransformer extends TweenTransformer {
    private static final Params params = new Params();

    static {
        params.forwardIn    = R.anim.fade_in;
        params.backIn       = -1;
        params.backOut      = R.anim.fade_out;
        params.forwardOut   = -1;
    }

    public FadeTransformer(Context context) {
        super(context, params);
    }

}