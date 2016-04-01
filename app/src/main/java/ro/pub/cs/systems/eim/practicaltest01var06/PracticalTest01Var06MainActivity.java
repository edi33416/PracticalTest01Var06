package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.TooManyListenersException;

public class PracticalTest01Var06MainActivity extends ActionBarActivity {
    private EditText addressEditText = null;
    private EditText studentNameEditText = null;
    private Button detailsToggleButton = null;
    private Button addressStatusButton = null;
    private Button navigateToSecondaryActivityButton = null;
    private ButtonClickListenter buttonClickListenter = new ButtonClickListenter();
    private LinearLayout moreDetailsContainerLayout = null;
    private boolean isMoreDetailsContainerVisible = true;

    private static final int SECONDARY_ACTIVITY_REQ_CODE = 1;

    private class ButtonClickListenter implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.details_toggle_button:
                    if (isMoreDetailsContainerVisible) {
                        detailsToggleButton.setText(getResources().getString(R.string.more_details));
                        moreDetailsContainerLayout.setVisibility(View.INVISIBLE);
                        isMoreDetailsContainerVisible = false;
                    } else {
                        detailsToggleButton.setText(getResources().getString(R.string.less_details));
                        moreDetailsContainerLayout.setVisibility(View.VISIBLE);
                        isMoreDetailsContainerVisible = true;
                    }
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                    String int_addr = addressEditText.getText().toString();
                    String int_addr_stts = addressStatusButton.getText().toString();

                    intent.putExtra("internet_address", int_addr);
                    intent.putExtra("internet_address_status", int_addr_stts);

                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQ_CODE);
                    break;
            }
        }
    }

    private final TextWatcher addressWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String addressText = addressEditText.getText().toString();
            if (addressText.startsWith("http")) {
                addressStatusButton.setText(getResources().getString(R.string.addr_passed));
                addressStatusButton.setBackground(getResources().getDrawable(R.color.green));
            } else {
                addressStatusButton.setText(getResources().getString(R.string.addr_failed));
                addressStatusButton.setBackground(getResources().getDrawable(R.color.red));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        addressEditText = (EditText)findViewById(R.id.address_edit_text);
        studentNameEditText = (EditText)findViewById(R.id.student_name_edit_text);
        detailsToggleButton = (Button)findViewById(R.id.details_toggle_button);
        addressStatusButton = (Button)findViewById(R.id.address_status_button);
        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        moreDetailsContainerLayout = (LinearLayout)findViewById(R.id.more_details_container);

        isMoreDetailsContainerVisible = true;

        /* Set text watcher */
        addressEditText.addTextChangedListener(addressWatcher);

        /* Add button listener */
        detailsToggleButton.setOnClickListener(buttonClickListenter);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var06_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("address_text", addressEditText.getText().toString());
        outState.putString("student_name", studentNameEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("address_text")) {
            addressEditText.setText(savedInstanceState.getString("address_text"));
        }
        if (savedInstanceState.containsKey("student_name")) {
            studentNameEditText.setText(savedInstanceState.getString("student_name"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Address " + addressEditText.getText().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Student " + studentNameEditText.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECONDARY_ACTIVITY_REQ_CODE) {
            Toast.makeText(this, "The activity returned with result" + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
