package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var06SecondaryActivity extends ActionBarActivity {
    private EditText internetAddressEditText = null;
    private EditText internetAddressStatusEditText = null;
    private Button okButton = null;
    private Button cancelButton = null;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        internetAddressEditText = (EditText)findViewById(R.id.internet_address);
        internetAddressStatusEditText = (EditText)findViewById(R.id.internet_address_status);
        okButton = (Button)findViewById(R.id.ok_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);

        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("internet_address") && intent.getExtras().containsKey("internet_address_status")) {
            String int_addr = intent.getStringExtra("internet_address");
            String int_addr_stts = intent.getStringExtra("internet_address_status");
            internetAddressEditText.setText(int_addr);
            internetAddressStatusEditText.setText(int_addr_stts);
        }
    }
}
