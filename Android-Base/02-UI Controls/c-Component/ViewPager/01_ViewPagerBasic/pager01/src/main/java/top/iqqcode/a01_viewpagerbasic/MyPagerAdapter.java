package top.iqqcode.a01_viewpagerbasic;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021-03-30 15:14
 * @Description:
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewLists;

    public MyPagerAdapter(List<View> viewLists) {
        this.viewLists = viewLists;
    }

    /**
     * 获得viewpager中有多少个view
     *
     * @return
     */
    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewLists.get(position));
    }
}
