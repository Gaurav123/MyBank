package com.firsttask.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firsttask.mybank.databinding.ActivityPromotionBinding;

public class PromotionActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener  {

    private ActivityPromotionBinding binding;
    private int imagesLength;
    private ImageView[] imageDotsArray;
    private PromoPageAdapter mAdapter;
    private ImageChangeHandler imageChangeHandler = new ImageChangeHandler();
    private static int currentPage = -1;
    private int[] mImageResources = {
            R.drawable.userimg,
            R.drawable.lock,
    };
    private Animation animation, animationDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.PromoPage);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_promotion);
        initView();
        setAdapterData();
        setUiPageViewController();
    }

    private void initView(){
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        animationDots = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        binding.buttonGetStarted.setOnClickListener(this);
    }

    private void setButtonAnimation(){
        binding.buttonGetStarted.setAnimation(animation);
        binding.layoutDots.setAnimation(animationDots);
    }


    private void setAdapterData(){
        mAdapter = new PromoPageAdapter(this, mImageResources);
        binding.viewPagerImage.setAdapter(mAdapter);
        binding.viewPagerImage.setCurrentItem(0);
        binding.viewPagerImage.setOnPageChangeListener(this);
        binding.viewPagerImage.setPageTransformer(true, new DepthPageTransformer());
    }

    private void setUiPageViewController() {
        imagesLength = mAdapter.getCount();
        imageDotsArray = new ImageView[imagesLength];
        for (int i = 0; i < imagesLength; i++) {
            imageDotsArray[i] = new ImageView(this);
            imageDotsArray[i].setImageDrawable(getResources()
                    .getDrawable(R.drawable.ad_item_not_selected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);
            binding.layoutDots.addView(imageDotsArray[i], params);
        }
        imageDotsArray[0].setImageDrawable(getResources()
                .getDrawable(R.drawable.ad_item_selected));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonGetStarted) {
            openActivity();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imagesLength; i++) {
            imageDotsArray[i].setImageDrawable(getResources()
                    .getDrawable(R.drawable.ad_item_not_selected));
        }
        imageDotsArray[position].setImageDrawable(getResources()
                .getDrawable(R.drawable.ad_item_selected));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void openActivity(){
        startActivity(new Intent(this, lockScreen.class));
        finish();
    }

    @Override
    protected void onResume() {
        setButtonAnimation();
        startAutoImageChange();
        super.onResume();
    }

    private void startAutoImageChange(){
        imageChangeHandler.sleep();
        if(mImageResources.length>0){
            if (currentPage==mImageResources.length){
                currentPage=0;
            } else {
                currentPage++;
            }
            binding.viewPagerImage.setCurrentItem(currentPage);
        } else {imageChangeHandler.removeCallbacksAndMessages(null);}
    }

    @SuppressLint("HandlerLeak")
    private class ImageChangeHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            startAutoImageChange();
        }

        void sleep(){
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), 4500);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        imageChangeHandler.removeCallbacksAndMessages(null);
    }
}