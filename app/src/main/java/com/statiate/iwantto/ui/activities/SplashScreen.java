package com.statiate.iwantto.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.q42.android.scrollingimageview.ScrollingImageView;
import com.statiate.iwantto.R;
import com.statiate.iwantto.base.iWantToActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 25/10/16.
 */

public class SplashScreen extends iWantToActivity {

    @BindView(R.id.siv_splash_background)
    ScrollingImageView sivSplashBackground;
    @BindView(R.id.siv_splash_foreground)
    ScrollingImageView sivSplashForeground;
    @BindView(R.id.iv_splash_anim_character)
    ImageView ivSplashAnimCharacter;
    @BindView(R.id.frame_splash_scroll_animation_holder)
    FrameLayout frameSplashScrollAnimationHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        sivSplashBackground.start();
        sivSplashForeground.start();
    }
}
