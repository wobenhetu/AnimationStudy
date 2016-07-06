package com.dvp.base.fenwu.propertystudy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

    @Bind(R.id.imageview)
    ImageView imageview;
    @Bind(R.id.activity_main)
    ConstraintLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageview)
    public void onClick()
    {
       /* ObjectAnimator
                .ofFloat(imageview, "rotationX", 0.0F, 360.0F)
                .setDuration(1000)
                .start();*/

//===============================================================
       /* ObjectAnimator anim = ObjectAnimator//
                .ofFloat(imageview, "xxx", 1.0F,  0.0F)//
                .setDuration(2000);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float cVal = (Float) animation.getAnimatedValue();
                imageview.setAlpha(3*cVal);
                imageview.setScaleX(2*cVal);
                imageview.setScaleY(2*cVal);
            }
        });*/
//===============================================================

        /*WrapperView wrapper = new WrapperView(imageview);
        ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(1000).start();*/

//===============================================================
        //多动画效果的另一种实现方法——propertyValuesHolder

        //ofFloat()方法的第一个参数表示动画操作的对象（可以是任意对象），
        // 第二个参数表示操作对象的属性名字（只要是对象有的属性都可以），
        // 第三个参数之后就是动画过渡值。当然过度值可以有一个到N个，
        // 如果是一个值的话默认这个值是动画过渡值的结束值。
        // 如果有N个值，动画就在这N个值之间过渡。
       /* PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.3f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.3f, 1f);
        PropertyValuesHolder pvhxz = PropertyValuesHolder.ofFloat("rotationX", 0.0F, 360.0F);

        PropertyValuesHolder pvhyz = PropertyValuesHolder.ofFloat("rotationY", 0.0F, 360.0F);
        ObjectAnimator.ofPropertyValuesHolder(imageview, pvhX, pvhY, pvhZ,pvhxz,pvhyz)
                .setDuration(1000).start();*/
        //===============================================================


      /*  ObjectAnimator animator = ObjectAnimator.ofFloat(imageview, "alpha", 1.0f, 0.3f, 1.0F);
        animator.setDuration(2000);//动画时间
        animator.setInterpolator(new BounceInterpolator());//动画插值
        animator.setRepeatCount(-1);//设置动画重复次数
        animator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        //animator.setStartDelay(1000);//动画延时执行
        animator.start();//启动动画*/


        //组合动画
        /*after(Animator anim) 将现有动画插入到传入的动画之后执行
        after(long delay) 将现有动画延迟指定毫秒后执行
        before(Animator anim) 将现有动画插入到传入的动画之前执行
        with(Animator anim) 将现有动画和传入的动画同时执行*/
        ObjectAnimator animator = ObjectAnimator.ofInt(activityMain, "backgroundColor", 0xFFFF0000, 0xFFFF00FF);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageview, "translationX", 0.0f, 200.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageview, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageview, "rotationX", 0.0f, 90.0f, 0.0F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageview, "alpha", 1.0f, 0.2f, 1.0F);

        //组合动画方式1
        AnimatorSet set = new AnimatorSet();
        ((set.play(animator).with(animator1).before(animator2)).before(animator3)).after(animator4);
        set.setDuration(5000);
        set.start();


    }


    private static class WrapperView {
        private View mTarget;

        public WrapperView(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
