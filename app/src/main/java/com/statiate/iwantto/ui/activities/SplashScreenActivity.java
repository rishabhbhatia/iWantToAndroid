package com.statiate.iwantto.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.q42.android.scrollingimageview.ScrollingImageView;
import com.statiate.iwantto.R;
import com.statiate.iwantto.base.iWantToActivity;
import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 25/10/16.
 */

public class SplashScreenActivity extends iWantToActivity {

    @BindView(R.id.siv_splash_background)
    ScrollingImageView sivSplashBackground;
    @BindView(R.id.siv_splash_foreground)
    ScrollingImageView sivSplashForeground;
    @BindView(R.id.iv_splash_anim_character)
    ImageView ivSplashAnimCharacter;
    @BindView(R.id.frame_splash_scroll_animation_holder)
    FrameLayout frameSplashScrollAnimationHolder;
    @BindView(R.id.tiles_frame_splash)
    TilesFrameLayout tilesFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        sivSplashBackground.start();
        sivSplashForeground.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tilesFrameLayout.startAnimation();
                tilesFrameLayout.setOnAnimationFinishedListener(new TilesFrameLayoutListener() {
                    @Override
                    public void onAnimationFinished() {
                        startGoalSelectorScreen();
                    }
                });
            }
        }, 5000);
    }

    private void startGoalSelectorScreen() {
        Intent intent = new Intent(SplashScreenActivity.this, GoalSelectorActivity.class);
        startActivity(intent);
        finish();
    }
}
