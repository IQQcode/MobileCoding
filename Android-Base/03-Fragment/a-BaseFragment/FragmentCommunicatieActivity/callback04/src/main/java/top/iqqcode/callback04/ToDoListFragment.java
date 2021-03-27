package top.iqqcode.callback04;


import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


/**
 * @Author: iqqcode
 * @Date: 2021-03-27 08:37
 * @Description:
 */
public class ToDoListFragment extends ListFragment {
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
    }
}
