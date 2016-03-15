package test.lin.com.testdemos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/*
１、setDuration(long durationMills)
　　设置动画持续时间（单位：毫秒）
　　２、setFillAfter(Boolean fillAfter)
　　如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
注意：AnimationSet使用则fillAfter和fillBefore都没有效果
　　３、setFillBefore(Boolean fillBefore)
　　如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
　　４、setStartOffSet(long startOffSet)
　　设置动画执行之前的等待时间
　　５、setRepeatCount(int repeatCount)
　　设置动画重复执行的次数


LayoutAnimationsController的使用，可以用于实现使多个控件按顺序一个一个的显示
Interpolator的使用，定义动画变化的速率
AnimationListener的使用，动画监听器
LayoutAnimationsController可以用于实现使多个控件按顺序一个一个的显示。
 */

public class MainActivity extends Activity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private Button xmlButton = null;
    private ImageView image = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animition);

        rotateButton = (Button) findViewById(R.id.rotateButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        alphaButton = (Button) findViewById(R.id.alphaButton);
        translateButton = (Button) findViewById(R.id.translateButton);
        xmlButton =(Button)findViewById(R.id.xmlButton);
        image = (ImageView) findViewById(R.id.image);

        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(
                new TranslateButtonListener());

        xmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(
                        MainActivity.this, R.anim.ani);
                // 启动动画
                image.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Toast.makeText(MainActivity.this,"动画开始",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(MainActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    class AlphaButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            //创建一个AnimationSet对象，参数为Boolean型，
            //true表示使用Animation的interpolator，false则是使用自己的
            AnimationSet animationSet = new AnimationSet(true);
            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);

            //设置动画执行的时间
            alphaAnimation.setDuration(2000);
            //将alphaAnimation对象添加到AnimationSet当中
            animationSet.addAnimation(alphaAnimation);
            //使用ImageView的startAnimation方法执行动画
            image.startAnimation(animationSet);
        }
    }

    class RotateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：从哪个旋转角度开始
            //参数2：转到什么角度
            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数5：确定y轴坐标的类型
            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);

//            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
//                    Animation.ABSOLUTE,400,
//                    Animation.ABSOLUTE,400);

            rotateAnimation.setDuration(1000);

            rotateAnimation.setRepeatCount(1);

            animationSet.addAnimation(rotateAnimation);
//            image.startAnimation(animationSet);
            image.startAnimation(rotateAnimation);
        }
    }

    class ScaleButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：x轴的初始值
            //参数2：x轴收缩后的值
            //参数3：y轴的初始值
            //参数4：y轴收缩后的值
            //参数5：确定x轴坐标的类型
            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数7：确定y轴坐标的类型
            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0, 0.5f, 0, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(1000);
            animationSet.addAnimation(scaleAnimation);
            image.startAnimation(animationSet);
        }
    }

    class TranslateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1～2：x轴的开始位置
            //参数3～4：y轴的开始位置
            //参数5～6：x轴的结束位置
            //参数7～8：x轴的结束位置
//            TranslateAnimation translateAnimation =
//                    new TranslateAnimation(
//                            Animation.RELATIVE_TO_SELF,0f,
//                            Animation.RELATIVE_TO_SELF,0.5f,
//                            Animation.RELATIVE_TO_SELF,0f,
//                            Animation.RELATIVE_TO_SELF,0.5f);

            TranslateAnimation translateAnimation =
                    new TranslateAnimation(
                            Animation.ABSOLUTE, 0,
                            Animation.ABSOLUTE, 500,
                            Animation.ABSOLUTE, 0,
                            Animation.ABSOLUTE, 500);

            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            animationSet.addAnimation(translateAnimation);
//            image.startAnimation(animationSet);

            image.startAnimation(translateAnimation);
        }
    }
}
