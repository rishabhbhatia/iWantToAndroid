package com.statiate.iwantto.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.q42.android.scrollingimageview.ScrollingImageView;
import com.statiate.iwantto.R;
import com.statiate.iwantto.base.iWantToActivity;
import com.statiate.iwantto.utils.iWantConstants;

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
    @BindView(R.id.rl_splash_scroll_animation_holder)
    RelativeLayout relativeSplashScrollAnimationHolder;

    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        sivSplashBackground.start();
        sivSplashForeground.start();
    }

    private void setupSplashHandler()
    {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startGoalSelectorScreen();
            }
        }, 5000);
    }

    private void startGoalSelectorScreen()
    {
        Intent intent = new Intent(SplashScreenActivity.this, GoalSelectorActivity.class);
        startActivity(intent);
        finish();
    }


    private void hideStatusBar()
    {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void showStatusBar()
    {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onPause() {
        if(handler != null)
        {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupSplashHandler();
    }

    @Override
    protected void onDestroy() {
        if(handler != null)
        {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        super.onDestroy();
    }
}
