package com.app.loginpagedemo

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.widget.NestedScrollView
import kotlinx.android.synthetic.main.some_test_layout.*
import java.util.concurrent.Executors


class TestActivity : AppCompatActivity() {

    private var animation: Animation? = null
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.some_test_layout)
        //startAnimationFromBackgroundThread()
        //rotatAnim()
        //iv_anim.animate().rotation(360f).start()
        button2.setOnClickListener {
            startScroller()
        }
        startScroller()
    }

    private fun startScroller() {
        /*image_scroller.postDelayed({
            //replace this line to scroll up or down
            image_scroller.fullScroll(ScrollView.FOCUS_DOWN)
        }, 10000L)*/
        /*image_scroller.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->  }*/
        image_scroller.setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> false
                MotionEvent.ACTION_UP -> false
                else -> true
            }
        }
        image_scroller.requestDisallowInterceptTouchEvent(true)
        image_scroller.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                image_scroller.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val objectAnimator = ObjectAnimator.ofInt(
                    image_scroller,
                    "scrollY",
                    image_scroller.getChildAt(0).height - image_scroller.height
                )
                objectAnimator.duration = 400000
                objectAnimator.interpolator = LinearInterpolator()
                objectAnimator.repeatCount = ValueAnimator.INFINITE
                objectAnimator.start()
                objectAnimator.doOnEnd {
                    it.start()
                }
            }
        })
    }

    private fun rotatAnim() {
        val anim = RotateAnimation(
            0.0f, 360.0f,
            Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f
        )
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 1000
        iv_anim.setAnimation(anim)
        iv_anim.startAnimation(anim)
    }

    private fun startAnimationFromBackgroundThread() {
        val executorService = Executors.newSingleThreadExecutor();
        executorService.submit {
            run {
                // this runs on a background thread
                animation = AnimationUtils.loadAnimation(this@TestActivity, R.anim.rotat_anim)
                //Log.v(TAG, "Animation thread id:" + Thread.currentThread().getId());
                iv_anim?.startAnimation(animation)
            }
        };
    }
}
