package com.solthnull.android.trafficcontrol;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class TrafficControl extends AppCompatActivity {

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_control);

        Intent newInt = getIntent();
        address = newInt.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device
        new ConnectBT().execute(); //Call the class to connect
    }

    public void colorClick (View view){
        int clicked = view.getId();
        switch (clicked){
            case (R.id.oneRed):
                sendMessage("OR");
                break;
            case (R.id.oneYellow):
                sendMessage("OY");
                break;
            case (R.id.oneGreen):
                sendMessage("OG");
                break;
            case (R.id.twoRed):
                sendMessage("TR");
                break;
            case (R.id.twoYellow):
                sendMessage("TY");
                break;
            case (R.id.twoGreen):
                sendMessage("TG");
                break;
            default:
                break;
        }
    }

    public void stop(View view) {
        sendMessage("TF");
    }

    public void turnAuto(View view) {
        sendMessage("TA");
    }

    private void sendMessage(String rMsg){
        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write(rMsg.getBytes());
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error Sending Message!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //helper class for handling Bluetooth Connection
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(TrafficControl.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices){ //while the progress dialog is shown, the connection is done in background
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){ //after the doInBackground, it checks if everything went fine
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }

        // fast way to call Toast
        private void msg(String s) {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }
}
