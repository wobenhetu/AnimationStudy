package com.dvp.base.fenwu.propertystudy;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.3f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.3f, 1f);
        PropertyValuesHolder pvhxz = PropertyValuesHolder.ofFloat("rotationX", 0.0F, 360.0F);

        PropertyValuesHolder pvhyz = PropertyValuesHolder.ofFloat("rotationY", 0.0F, 360.0F);
        ObjectAnimator.ofPropertyValuesHolder(imageview, pvhX, pvhY, pvhZ,pvhxz,pvhyz)
                .setDuration(1000).start();
        //===============================================================


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
