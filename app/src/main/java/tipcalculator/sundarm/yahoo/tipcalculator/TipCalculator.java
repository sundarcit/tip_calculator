package tipcalculator.sundarm.yahoo.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TipCalculator extends Activity {

    private EditText billAmount;
    private TextView tip;
    public String TIP_SENTENCE = "Tip is : $";
    public Double tipSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        billAmount = (EditText) findViewById(R.id.BillAmount);
        tip = (TextView) findViewById(R.id.Tip);
        tipSelected = new Double(0);
        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                setTip();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCalculateTip10(View v) {
        tipSelected = .1;
        setTip();

    }

    public void onCalculateTip15(View v) {
        tipSelected = .15;
        setTip();

    }

    public void onCalculateTip20(View v) {
        tipSelected = .2;
        setTip();
    }

    public void setTip() {
        Double amount;
        try {

            amount = Double.parseDouble(billAmount.getText().toString());
        } catch (NumberFormatException ne) {
            tip.setText("");
            return;
        }
        if (tipSelected != 0) {
            DecimalFormat df = new DecimalFormat("#.##");
            Double tipAmount = amount * tipSelected;
            String tipStr = df.format(tipAmount).toString();
            tip.setText(TIP_SENTENCE + tipStr);
        }
    }
}
