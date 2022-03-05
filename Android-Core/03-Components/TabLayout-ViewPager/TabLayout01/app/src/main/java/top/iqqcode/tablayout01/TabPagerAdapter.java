package top.iqqcode.tablayout01;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Author: jiazihui
 * @Date: 2021-08-18 00:07
 * @Description:
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    private List<TabFragment> container;

    public TabPagerAdapter(@NonNull FragmentManager fm, List<TabFragment> mContainer) {
        super(fm);
        this.container = mContainer;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return container.get(position);
    }

    @Override
    public int getCount() {
        return container.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // 不用在主Activity添加tab标题，这里直接设置
        return container.get(position).getTitle();
    }

    /**
     * 防止viewpager在滑动切换的时候，里面的fragment被销毁，导致数据需要重新加载
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
