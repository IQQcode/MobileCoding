package top.iqqcode.intentbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @Author: iqqcode
 * @Date: 2021/3/2
 * @Description:Extra属性在组件间传值
 */
public class MainActivity extends AppCompatActivity {

    private EditText met_emp_id, met_emp_name, met_emp_salary;
    private Button mbuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        met_emp_id = findViewById(R.id.et_emp_id);
        met_emp_name = findViewById(R.id.et_emp_name);
        met_emp_salary = findViewById(R.id.et_emp_salary);
        mbuButton = findViewById(R.id.btn_submit);

        mbuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(met_emp_id.getText().toString());
                String name = met_emp_name.getText().toString();
                float salary = Float.valueOf(met_emp_salary.getText().toString());

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("name", name);
                bundle.putFloat("salary", salary);
                intent.putExtras(bundle);

//                intent.putExtra("id", id);
//                intent.putExtra("name", name);
//                intent.putExtra("salary", salary);

                startActivity(intent);
            }
        });
    }
}