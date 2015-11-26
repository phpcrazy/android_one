package net.myanmarlinks.phoneinformation;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtCpuName; // to display cpu name
    Button btnOpenCamera; // to open camera
    Switch swtBluetooth; // to open bluetooth
    Switch swtWifi; // to open wifi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtCpuName = (TextView) findViewById(R.id.cpu_name);
        btnOpenCamera = (Button) findViewById(R.id.open_camera);
        swtBluetooth = (Switch) findViewById(R.id.swt_blue_tooth);
        swtWifi = (Switch) findViewById(R.id.swt_wifi);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });


        swtWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "WIFI ON", Toast.LENGTH_SHORT).show();
                    wifiManager.setWifiEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(), "WIFI OFF", Toast.LENGTH_SHORT).show();
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        swtBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (isChecked) {
                    mBluetoothAdapter.enable();
                } else {
                    mBluetoothAdapter.disable();
                }
        }
    });


        txtCpuName.setText("Intel Core i5");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
