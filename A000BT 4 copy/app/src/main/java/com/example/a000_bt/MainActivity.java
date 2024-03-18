package com.example.a000_bt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

//// HERE
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    // BT Variables
    private final String CV_ROBOTNAME = "HUAHUA";
    private BluetoothAdapter cv_btInterface = null;
    private Set<BluetoothDevice> cv_pairedDevices = null;
    private BluetoothDevice cv_btDevice = null;
    private BluetoothSocket cv_btSocket = null;

    // Data stream to/from NXT bluetooth
    private InputStream cv_is = null;
    private OutputStream cv_os = null;

    private int[] notes = {
            262, 262, 294, 262, 349, 330, // "Happy"
            262, 262, 294, 262, 392, 349, 330, // "Birthday"
            262, 494, 523, 494, 470, 440, 392 // "to you"
    };

    private int[] durations = {
            500, 500, 250, 250, 500, 500, // "Happy"
            500, 500, 250, 250, 500, 500, 500, // "Birthday"
            500, 250, 500, 250, 500, 500, 500 // "to you"
    };

    TextView cv_label01;
    TextView cv_label02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cv_button = (Button) findViewById(R.id.vv_button);
        Button cv_soundFile = (Button) findViewById(R.id.vv_soundFile);
        cv_label01 = (TextView) findViewById(R.id.vv_tvOut1);
        cv_label02 = (TextView) findViewById(R.id.vv_tvOut2);

        // Initialize buttons for each key
        ImageButton whiteKey1 = findViewById(R.id.whiteKey1);
        ImageButton whiteKey2 = findViewById(R.id.whiteKey2);
        ImageButton whiteKey3 = findViewById(R.id.whiteKey3);
        ImageButton whiteKey4 = findViewById(R.id.whiteKey4);
        ImageButton whiteKey5 = findViewById(R.id.whiteKey5);
        ImageButton whiteKey6 = findViewById(R.id.whiteKey6);
        ImageButton whiteKey7 = findViewById(R.id.whiteKey7);
        ImageButton whiteKey8 = findViewById(R.id.whiteKey8);
        ImageButton whiteKey9 = findViewById(R.id.whiteKey9);
        ImageButton whiteKey10 = findViewById(R.id.whiteKey10);
        ImageButton whiteKey11 = findViewById(R.id.whiteKey11);
        ImageButton whiteKey12 = findViewById(R.id.whiteKey12);
        ImageButton whiteKey13 = findViewById(R.id.whiteKey13);
        ImageButton whiteKey14 = findViewById(R.id.whiteKey14);

        ImageButton blackKey1 = findViewById(R.id.blackKey1);
        ImageButton blackKey2 = findViewById(R.id.blackKey2);
        ImageButton blackKey3 = findViewById(R.id.blackKey3);
        ImageButton blackKey4 = findViewById(R.id.blackKey4);
        ImageButton blackKey5 = findViewById(R.id.blackKey5);
        ImageButton blackKey6 = findViewById(R.id.blackKey6);
        ImageButton blackKey7 = findViewById(R.id.blackKey7);
        ImageButton blackKey8 = findViewById(R.id.blackKey8);
        ImageButton blackKey9 = findViewById(R.id.blackKey9);
        ImageButton blackKey10 = findViewById(R.id.blackKey10);
        ImageButton blackKey11 = findViewById(R.id.blackKey11);

        // @+id/whiteKey1  (B3): 246.94 Hz
        whiteKey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0xf7);
            }
        });

        // @+id/whiteKey2:(C4 - Middle C): 0x106
        whiteKey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x106);
            }
        });

        // @+id/whiteKey3 (D4): 0x126
        whiteKey3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x126);
            }
        });

        // @+id/whiteKey4 (E4): 0x14a
        whiteKey4.setOnClickListener(v -> cpf_EV3PlayTone(0x14a));

        // @+id/whiteKey5 (F4): 0x15d
        whiteKey5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x15d);
            }
        });

        // @+id/whiteKey6  (G4): 0x188
        whiteKey6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x188);
            }
        });

        // @+id/whiteKey7  (A4): 0x1b8
        whiteKey7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x1b8);
            }
        });

        // @+id/whiteKey8 B4: 0x1ee
        whiteKey8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x1ee);
            }
        });

        // @+id/whiteKey9 C5: 0x20b
        whiteKey9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x20b);
            }
        });

        // @+id/whiteKey10 (D5): 0x24b
        whiteKey10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x24b);
            }
        });

        // @+id/whiteKey11 (E5): 0x293
        whiteKey11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x293);
            }
        });

        // @+id/whiteKey12 F5: 2DB
        whiteKey12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x2DB);
            }
        });

        // @+id/whiteKey13 (G5): 0x32b
        whiteKey13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x32B);
            }
        });

        // @+id/whiteKey14 (A5): 0x383
        whiteKey14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x383);
            }
        });

        // @+id/blackKey1 (A#3): 0xe9
        blackKey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0xe9);
            }
        });
        // @+id/blackKey2 (C#4 or Db4): 0x115
        blackKey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x115);
            }
        });
        // @+id/blackKey3 (D#4 or Eb4): 0x137
        blackKey3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x137);
            }
        });
        // @+id/blackKey4 (F#4 or Gb4): 0x172
        blackKey4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x172);
            }
        });
        // @+id/blackKey5 (G#4 or Ab4): 0x19f
        blackKey5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x19f);
            }
        });
        // @+id/blackKey6 (A#4 or Bb4): 0x1d2
        blackKey6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x1d2);
            }
        });
        // @+id/blackKey7 (C#5 or Db5): 0x22a
        blackKey7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x22a);
            }
        });
        // @+id/blackKey8 (D#5 or Eb5): 0x26e
        blackKey8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x26e);
            }
        });
        // @+id/blackKey9 (F#5): 0x2FF
        blackKey9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x2ff);
            }
        });
        // @+id/blackKey10 (G#5): 0x353
        blackKey10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x353);
            }
        });

        // @+id/blackKey11 (A#5): 0x37F
        blackKey11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpf_EV3PlayTone(0x37F);
            }
        });


        // Need grant permission once per install
        cpf_checkBTPermissions();
        cv_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playHappyBirthday();
                    }
                });

        cv_soundFile.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        cpf_EV3Test();
                    }

                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_first: cpf_requestBTPermissions();
                return true;
            case R.id.menu_second: cv_btDevice = cpf_locateInPairedBTList(CV_ROBOTNAME);
                return true;
            case R.id.menu_third: cpf_connectToEV3(cv_btDevice);
                return true;
            case R.id.menu_fourth: cpf_EV3MoveMotor();
                return true;
            case R.id.menu_fifth: cpf_EV3PlayTone();
                return true;
            case R.id.menu_sixth: cpf_disconnFromEV3(cv_btDevice);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void cpf_checkBTPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            cv_label01.setText("BLUETOOTH_SCAN already granted.\n");
        } else {
            cv_label01.setText("BLUETOOTH_SCAN NOT granted.\n");
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            cv_label02.setText("BLUETOOTH_CONNECT NOT granted.\n");
        } else {
            cv_label02.setText("BLUETOOTH_CONNECT already granted.\n");
        }
    }

    // https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    // https://stackoverflow.com/questions/67722950/android-12-new-bluetooth-permissions
    private void cpf_requestBTPermissions() {
        // We can give any value but unique for each permission.
        final int BLUETOOTH_SCAN_CODE = 100;
        final int BLUETOOTH_CONNECT_CODE = 101;

        // Android version < 12, "android.permission.BLUETOOTH" just fine
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            Toast.makeText(MainActivity.this,
                    "BLUETOOTH granted for earlier Android", Toast.LENGTH_SHORT).show();
            return;
        }

        // Android 12+ has to go through the process
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    BLUETOOTH_SCAN_CODE);
        } else {
            Toast.makeText(MainActivity.this,
                    "BLUETOOTH_SCAN already granted", Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    BLUETOOTH_CONNECT_CODE);
        } else {
            Toast.makeText(MainActivity.this,
                    "BLUETOOTH_CONNECT already granted", Toast.LENGTH_SHORT).show();
        }
    }

    // Modify from chap14, pp390 findRobot()
    private BluetoothDevice cpf_locateInPairedBTList(String name) {
        BluetoothDevice lv_bd = null;
        try {
            cv_btInterface = BluetoothAdapter.getDefaultAdapter();
            cv_pairedDevices = cv_btInterface.getBondedDevices();
            Iterator<BluetoothDevice> lv_it = cv_pairedDevices.iterator();
            while (lv_it.hasNext()) {
                lv_bd = lv_it.next();
                if (lv_bd.getName().equalsIgnoreCase(name)) {
                    cv_label01.setText(name + " is in paired list");
                    return lv_bd;
                }
            }
            cv_label01.setText(name + " is NOT in paired list");
        } catch (Exception e) {
            cv_label01.setText("Failed in findRobot() " + e.getMessage());
        }
        return null;
    }

    // Modify frmo chap14, pp391 connectToRobot()
    private void cpf_connectToEV3(BluetoothDevice bd) {
        try {
            cv_btSocket = bd.createRfcommSocketToServiceRecord
                    (UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            cv_btSocket.connect();

            cv_is = cv_btSocket.getInputStream();
            cv_os = cv_btSocket.getOutputStream();
            cv_label02.setText("Connect to " + bd.getName() + " at " + bd.getAddress());
        } catch (Exception e) {
            cv_label02.setText("Error interacting with remote device [" +
                    e.getMessage() + "]");
        }
    }

    private void cpf_disconnFromEV3(BluetoothDevice bd) {
        try {
            cv_btSocket.close();
            cv_is.close();
            cv_os.close();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            cv_label02.setText(bd.getName() + " is disconnect ");
        } catch (Exception e) {
            cv_label02.setText("Error in disconnect -> " + e.getMessage());
        }
    }

    // Communication Developer Kit Page 27
    // 4.2.2 Start motor B & C forward at power 50 for 3 rotation and braking at destination
    private void cpf_EV3MoveMotor() {
        try {
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            cv_label01.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    // 4.2.5 Play a 1Kz tone at level 2 for 1 sec.
    private void cpf_EV3PlayTone() {
        try {
            byte[] buffer = new byte[17];       // 0x0f command length

            buffer[0] = (byte) (17-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;
            buffer[8] = 1;

            buffer[9] = (byte) 0x81;
            buffer[10] = (byte) 0x02;

            buffer[11] = (byte) 0x82;
            buffer[12] = (byte) 0xe8;
            buffer[13] = (byte) 0x03;

            buffer[14] = (byte) 0x82;
            buffer[15] = (byte) 0xe8;
            buffer[16] = (byte) 0x03;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            cv_label02.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    private void playHappyBirthday() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < notes.length; i++) {
                    cpf_EV3PlayTone(notes[i]);
                    try {
                        Thread.sleep(durations[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    protected void cpf_EV3PlayTone(int frequency) {
        try {
            byte[] buffer = new byte[16];

            buffer[0] = (byte) (16 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;
            buffer[8] = 1;

            buffer[9] = (byte) 0x01; // VOLUME=1

            buffer[10] = (byte) 0x82;

            buffer[11] = (byte) (frequency & 0xFF);
            buffer[12] = (byte) ((frequency >> 8) & 0xFF);

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x23;
            buffer[15] = (byte) 0x01;

            cv_os.write(buffer);
            cv_os.flush();
        } catch (Exception e) {
            cv_label02.setText("Error in PlayTone(" + e.getMessage() + ")");
        }
    }

    private void cpf_EV3Test() {
        try {
            byte[] buffer = new byte[32];

            buffer[0] = (byte) (32 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;
            buffer[8] = 2;

            buffer[9] = (byte) 0x81;
            buffer[10] = (byte) 0x64;

            buffer[11] = (byte) 0x84;
            buffer[12] = (byte) 0x2E;
            buffer[13] = (byte) 0x2F;
            buffer[14] = (byte) 0x75;
            buffer[15] = (byte) 0x69;
            buffer[16] = (byte) 0x2F; //  "/"
            
            // file name 
            buffer[17] = (byte) 0x4F; // "O"
            buffer[18] = (byte) 0x76; // "v"
            buffer[19] = (byte) 0x65; // "e"
            buffer[20] = (byte) 0x72; // "r"
            buffer[21] = (byte) 0x70; // "p"
            buffer[22] = (byte) 0x6F; // "o"
            buffer[23] = (byte) 0x77; // "w"
            buffer[24] = (byte) 0x65; // "e"
            buffer[25] = (byte) 0x72; // "r"
            buffer[26] = (byte) 0x41; // "A"
            buffer[27] = (byte) 0x6C; // "l"
            buffer[28] = (byte) 0x65; // "e"
            buffer[29] = (byte) 0x72; // "r"
            buffer[30] = (byte) 0x74; // "t"
            buffer[31] = (byte) 0x00;

            cv_os.write(buffer);
            cv_os.flush();
        } catch (Exception e) {
            cv_label02.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }



    
}