package ro.pub.cs.systems.eim.practicaltest01var06;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var06MainActivity extends ActionBarActivity {
    private EditText addressEditText = null;
    private Button detailsToggleButton = null;
    private Button addressStatusButton = null;
    private Button navigateToSecondaryActivityButton = null;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        addressEditText = (EditText)findViewById(R.id.address_edit_text);
        detailsToggleButton = (Button)findViewById(R.id.details_toggle_button);
        addressStatusButton = (Button)findViewById(R.id.address_status_button);
        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);

        /* Set text watcher */
        addressEditText.addTextChangedListener(addressWatcher);
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
}
