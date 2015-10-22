package com.suplardo.tipper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Jacob
 * @since 8/4/2015
 * @todo support pre-post tax tip, other currencies, multi-languages, round up/down feature
 */
public class MainActivity extends AppCompatActivity {

    private static SharedPreferences preferences;
    private static EditText gratuityField;

    public static void setGratuity() {
        gratuityField.setText(Float.toString(preferences.getFloat("preferredGratuity", 18F)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("TipPreferences", MODE_MULTI_PROCESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gratuityField = ((EditText) findViewById(R.id.gratuityField));
        setGratuity();
        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double bill = Double.parseDouble(((EditText) findViewById(R.id.totalBillField)).getText().toString());
                    TextView partSizeView = ((TextView) findViewById(R.id.partySizeField));
                    String partySizeText = partSizeView.getText().toString();
                    int split = partySizeText.isEmpty() || partySizeText.equals("0") ? 1 : Integer.parseInt(partySizeText);
                    partSizeView.setText(Integer.toString(split));
                    bill /= split;
                    double tip = Double.parseDouble(gratuityField.getText().toString()) / 100 * bill;
                    ((TextView) findViewById(R.id.hiddenSplitBillText)).setText(String.format("$%.02f", bill));
                    ((TextView) findViewById(R.id.hiddenTipText)).setText(String.format("$%.02f", tip));
                    ((TextView) findViewById(R.id.hiddenTotalText)).setText(String.format("$%.02f", bill + tip));
                } catch (Exception ignored) {
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, PreferenceActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static SharedPreferences preferences() {
        return preferences;
    }
}