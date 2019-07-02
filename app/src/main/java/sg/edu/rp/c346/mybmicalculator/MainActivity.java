package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText weight;
    EditText height;
    Button calc;
    Button reset;
    TextView date;
    TextView bmi;

    float wgt=0;
    float hgt=0;
    String datetime ="";
    float bmiCalc =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight=findViewById(R.id.editTextWeight);
        height=findViewById(R.id.editTextHeight);
        calc=findViewById(R.id.buttonCalculate);
        reset=findViewById(R.id.buttonRestData);
        date=findViewById(R.id.textViewDate);
        bmi=findViewById(R.id.textViewBMI);



         date.setText("Last Calculated Date: "+ datetime);
         bmi.setText("Last Calculated BMI: "+ bmiCalc);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wgt= Float.parseFloat(weight.getText().toString());
                hgt= Float.parseFloat(height.getText().toString());

                Calendar now = Calendar.getInstance();

                datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                bmiCalc= wgt/(hgt*hgt);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("date",datetime);
                prefEdit.putFloat("bmi",bmiCalc);

                date.setText("Last Calculated Date: "+ datetime);
                bmi.setText("Last Calculated BMI: "+ String.format("%.3f", bmiCalc));


                prefEdit.commit();

                weight.setText("");
                height.setText("");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weight.setText("");
                height.setText("");
                datetime="";
                bmiCalc=0;
                date.setText("Last Calculated Date: "+ datetime);
                bmi.setText("Last Calculated BMI: "+ bmiCalc);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.clear();

                date.setText("Last Calculated Date: "+ datetime);
                bmi.setText("Last Calculated BMI: "+ String.format("%.3f", bmiCalc));

                prefEdit.commit();
            }
        });
    }
}
