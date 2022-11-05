package com.example.view4_viewpager1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.Objects;

public class MyPagerAdapter extends PagerAdapter {

    Context context;
    int[] images;

    LayoutInflater layoutInflater;

    public MyPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
    }

    /**
     * 개수
     * @return images.length
     */
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    /**
     * 뷰 만들어서 return
     * @param container
     * @param position
     * @return itemView
     */
    @Override
    public Object instantiateItem( ViewGroup container, final int position) {
        //1. item.xml을 View로 객체화한다.
        View itemView = layoutInflater.inflate(R.layout.item,  container, false);
        //2. item.xml에 있는 ImageView를 가져온다. - activity_main.xml속의 itemView이므로 itemView.findViewById()로 처리
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //3. 배열의 이미지 id를  이미지뷰에 설정한다.
        imageView.setImageResource(images[position]);
        //4. 컨테이너(뷰그룹)에 뷰를 추가한다.
        //   container는 null이 되면 안된다는 메소드이다.
        Objects.requireNonNull(container).addView(itemView);
        //5. 만들어진 itemView를 반환한다.
        return itemView;
    }

    /**
     * item이 사라지면 삭제한다.
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
