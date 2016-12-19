package com.example.yandi.maudaftar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ViewPagerAdapter extends PagerAdapter
{
	// Declare Variables
	Context context;
	
	String[] flag;
	LayoutInflater inflater;

	public ViewPagerAdapter(Context context, String[] flag)
	{
		this.context = context;		
		this.flag = flag;		
	}
	
	@Override
	public int getCount() 
	{
		return flag.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		return view == ((RelativeLayout) object);
	}
	
	public float getPageWidth(int position)
    {
		return 1;
    }	
	
	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		
		ImageView imgflag;

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.pager_item, container,false);
		
		// Locate the ImageView in viewpager_item.xml
		imgflag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set to the ImageView
		String i = flag[position];
		try {
			InputStream ims = context.getAssets().open(i);
			Bitmap original = BitmapFactory.decodeStream(ims);
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			original.compress(Bitmap.CompressFormat.JPEG, 100, bOut);
			Bitmap dec = BitmapFactory.decodeStream(new ByteArrayInputStream(bOut.toByteArray()));
//			Drawable d = Drawable.createFromStream(ims, null);
//			imgflag.setImageDrawable(d);
			imgflag.setImageBitmap(dec);
		}
		catch (Exception e){

		}
		// imgflag.setImageResource(flag[position]);
		
		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);
	}
}
