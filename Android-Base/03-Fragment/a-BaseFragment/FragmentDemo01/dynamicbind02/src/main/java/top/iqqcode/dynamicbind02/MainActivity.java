package top.iqqcode.dynamicbind02;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Java动态添加Fragement到Activity中,涉及到FragmentManager 和 FragmentTransaction两个对象
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fm_fragment_view01, new FragmentView01());
        fragmentTransaction.add(R.id.fm_fragment_view02, new FragmentView02());
        fragmentTransaction.add(R.id.fm_fragment_view03, new FragmentView03());
        fragmentTransaction.add(R.id.fm_fragment_view04, new FragmentView04());
        fragmentTransaction.commit();
    }
}