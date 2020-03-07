package dev.ksc.materialdesign.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import dev.ksc.materialdesign.R;

public class ScrollingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextView tv_largeText = findViewById(R.id.tv_largeText);
                                Log.d("TAG", tv_largeText.getText().toString());
                            }
                        }).show();
            }
        });

    }

    public void clickButton(View view) {
        Animator an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, view.getWidth());
        an.setInterpolator(new AccelerateDecelerateInterpolator());
        an.setDuration(4000);
        an.start();
        an.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(ScrollingActivity.this, NavigationDrawerActivity.class));
            }
        });
    }

    /**
     * 水波动画
     */
    // 经过抽象后可这样做
    public Animator initAnimator(View view, int style, int duration, Interpolator interpolator) {
        Animator an = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (style) {
                case 1: // 从控件的圆心扩散到控件的宽度
                    an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, view.getWidth());
                    break;
                case 2: // 从控件的宽度的一半为圆心扩散到控件的高度
                    an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, 0, 0, view.getHeight());
                    break;

            }
            an.setInterpolator(interpolator);
            an.setDuration(duration);
            return an;
        } else return null;
    }

    public void startAnimation(View view, int style, int duration, Interpolator interpolator) {
        Animator animator = initAnimator(view, style, duration, interpolator);
        if (animator != null) animator.start();
    }

    /**
     * 实现共享元素场景切换动画(转场动画)
     */
    public void transferActivity(Class cls, Pair<View, String>... sharedElements) {
        // 启动页面跳转
        Intent intent = new Intent();
        intent.setClass(ScrollingActivity.this, cls);
        Transition ts = new ChangeTransform();
        ts.setDuration(3000);
        getWindow().setExitTransition(ts);

        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, sharedElements);
        Bundle b = op.toBundle();
        startActivity(intent, b);

    }
    // 调用：
    // transferActivity(NavigationDrawerActivity.class, Pair.create((View) iv_image, "banner"), Pair.create((View) iv_image, "banner2"));
    // iv_image是本Activity内的控件ID，banner为目标跳转的Activity中布局中的transitionName,也就是说，目标cls中布局的共享元素需要加一条属性*android:transitionName="banner"*


}
