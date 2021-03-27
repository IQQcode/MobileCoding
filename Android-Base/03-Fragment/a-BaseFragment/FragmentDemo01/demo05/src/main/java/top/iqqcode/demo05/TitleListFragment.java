package top.iqqcode.demo05;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

/**
 * @Author: iqqcode
 * @Date: 2021-03-25 14:55
 * @Description:用来显示标题的列表
 */
public class TitleListFragment extends ListFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 给listView设置adapter显示列表
        // public ArrayAdapter(Context context,  int resource,  T[] objects) {
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, DataUtils.TITLES));

        //默认选中第一个item
        getListView().setItemChecked(0, true);
        //显示第一个详情
        showDetail(0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetail(position);
    }

    /**
     * 显示指定下标的详情
     *
     * @param position
     */
    private void showDetail(int position) {
        DetailFragment fragment = new DetailFragment();
        //将对应的详情数据携带过去
        Bundle args = new Bundle();
        args.putString("DETAIL", DataUtils.DETAILS[position]);
        fragment.setArguments(args);
        //将其替换到id为fl_main_container的容器布局中
        getFragmentManager().beginTransaction().replace(R.id.fl_main_container, fragment).commitAllowingStateLoss();
    }
}
