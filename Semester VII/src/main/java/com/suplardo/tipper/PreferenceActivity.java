package com.suplardo.tipper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * @author Jacob
 * @since 8/4/2015
 */
public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity);
    }

    private boolean isFloat(String string) {
        try {
            float f = Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        EditText view = ((EditText) findViewById(R.id.gratuityField2));
        view.setText(Float.toString(MainActivity.preferences().getFloat("preferredGratuity", 18F)));
        view.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (isFloat(text)) {
                    SharedPreferences.Editor editor = MainActivity.preferences().edit();
                    editor.putFloat("preferredGratuity", Float.parseFloat(text)).apply();
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_main) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
