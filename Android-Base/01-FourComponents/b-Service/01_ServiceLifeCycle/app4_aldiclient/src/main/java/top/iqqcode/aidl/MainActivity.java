package top.iqqcode.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import top.iqqcode.aidl.IMyAidlInterface;
import top.iqqcode.aidl.Student;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "TAG";
    private ServiceConnection connection;
    private IMyAidlInterface mAIDL;
    private EditText et_aidl_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO 不识别布局文件
//        setContentView(R.layout.activity_client);
//
//        et_aidl_id = findViewById(R.id.et_aidl_id);
    }

    private ServiceConnection conn;
    private IMyAidlInterface studentService;

    public void bindRemoteService(View v) {

        Intent intent = new Intent(
                "top.iqqcode.intent.action.RemoteService.Action");
        if (conn == null) {
            conn = new ServiceConnection() {

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }

                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder service) {
                    Log.e("TAG", "onServiceConnected()");
                    studentService = IMyAidlInterface.Stub.asInterface(service);
                }
            };
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "绑定Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定Service", Toast.LENGTH_SHORT).show();
        }

    }

    public void invokeRemote(View v) throws RemoteException {
        if (studentService != null) {
            int id = Integer.parseInt(et_aidl_id.getText().toString());
            Student student = studentService.getStudentById(id);
            Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void unbindRemoteService(View v) {
        if (conn != null) {
            unbindService(conn);
            conn = null;
            studentService = null;
            Toast.makeText(this, "解绑Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还未绑定Service", Toast.LENGTH_SHORT).show();
        }
    }
}