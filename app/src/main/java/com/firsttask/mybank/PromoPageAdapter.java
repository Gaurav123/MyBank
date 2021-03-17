package com.firsttask.mybank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.firsttask.mybank.databinding.AdapterPagerItemBinding;

public class PromoPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] mResources;

    public PromoPageAdapter(Context context, int[] mResources) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        AdapterPagerItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.adapter_pager_item, container, false);
        Animation animationHeading  = AnimationUtils.loadAnimation(context, R.anim.anim_from_bottom);
        Animation animationSubText = AnimationUtils.loadAnimation(context, R.anim.anim_from_left);
        binding.textHeadingPromo.setAnimation(animationHeading);
        binding.textPromoDesc1.setAnimation(animationSubText);
        binding.textPromoDesc2.setAnimation(animationSubText);
        switch (position){
            case 0:
                Glide.with(context).load(context.getResources().getDrawable(R.drawable.userimg))
                        .into(binding.imageBackground);
                Glide.with(context).load(context.getResources().getDrawable(R.drawable.userimg2))
                        .into(binding.subImage);
                binding.textHeadingPromo.setText(context.getResources()
                        .getString(R.string.promo_heading_one));
                binding.textPromoDesc1.setText(context.getString(R.string.promo_desc_one));
                binding.textPromoDesc2.setText(context.getString(R.string.promo_desc_two));
                break;
            case 1:
                Glide.with(context).load(context.getResources().getDrawable(R.drawable.lockimg2))
                        .into(binding.imageBackground);
                Glide.with(context).load(context.getResources().getDrawable(R.drawable.lock))
                        .into(binding.subImage);
                binding.textHeadingPromo.setText(context.getResources()
                        .getString(R.string.promo_heading_two));
                binding.textPromoDesc1.setText(context.getString(R.string.promo_desc_three));
                binding.textPromoDesc2.setText(context.getString(R.string.promo_desc_four));
                break;

        }        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
