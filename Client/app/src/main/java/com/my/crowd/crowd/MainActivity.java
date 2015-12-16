package com.my.crowd.crowd;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends TabActivity {

    Button firstTabBtn;
    Button secondTabBtn;
    TabHost tabHost;
    int firstTabFlag = 0;
    int secondTabFlag = 0;
   // DatabaseHandler db;

    public static TextView tvName;
    TextView tvecoDrive;
    TextView tvKm;
    TextView tvMin;
    TextView tvTemp;
    TextView tvVolt;

    final int UPDATE_READ_DATA = 1;
    final int UPDATE_READ_DATA_DONE = 4;
    final int UPDATE_SEND_DATA = 2;
    final int UPDATE_SEND_DATA_DONE = 3;

    private static final Object SendSynchronizationObject=new Object();


    /*graphical objects*/

    Button button2;





    byte numBytes;
    byte count;
    byte status;
    byte writeIndex = 0;
    byte readIndex =  0;

    int baudRate; /*baud rate*/
    byte stopBit; /*1:1stop bits, 2:2 stop bits*/
    byte dataBit; /*8:8bit, 7: 7bit*/
    byte parity;  /* 0: none, 1: odd, 2: even, 3: mark, 4: space*/
    byte flowControl; /*0:none, 1: flow control(CTS,RTS)*/

    private static final String FILE_NAME = "SavedFile.txt";
    private static final String ACCESS_FILE = android.os.Environment.getExternalStorageDirectory()
            + java.io.File.separator + FILE_NAME;



    public long start_time, end_time;
    long cal_time_1, cal_time_2;
    int totalDataCount = 0;

    //public save_data_thread save_data_Thread;
    //public send_file_thread send_file_Thread;

    public Context global_context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);

        tvName = (TextView)findViewById(R.id.points);
       // tvecoDrive = (TextView)findViewById(R.id.tveco_drive);
        tvKm = (TextView)findViewById(R.id.tvkm);
        tvTemp = (TextView)findViewById(R.id.tvtemp);


        tabHost = getTabHost();

        Intent messageIntent,bulletinIntent, istorikointent;

        messageIntent = new Intent().setClass(this, MainActivity2.class);
        bulletinIntent = new Intent().setClass(this, Prosfores.class);
        istorikointent = new Intent().setClass(this, IstorikoActivity.class);

        this.setNewTab(this, tabHost, "tab2", R.string.textTabTitle2, R.drawable.button_plus_green, messageIntent);
        this.setNewTab(this, tabHost, "tab1", R.string.textTabTitle3, R.drawable.offers, bulletinIntent);
        this.setNewTab(this, tabHost, "tab3", R.string.textTabTitle1, R.drawable.his, istorikointent);

     //   db = new DatabaseHandler(this);

        //add a route
        //  db.addRoute(new Route("ΑΓΙΟΣ ΝΙΚΟΛΑΟΣ-ΑΕΡΟΔΡΟΜΙΟ ΗΡΑΚΛΕΙΟΥ","11 ΑΠΡΙΛΙΟΥ,08:20","TUI","...",54));

        //select olwn twn routes
      //  List<Route> routes = db.getAllRoutes();


      //  for(Route rt: routes){
       //     String log = "Route: " + rt.getRoute() +" ,Date: " + rt.getDate() + " ,Customer: "+ rt.getCustomer() +",Comments:" +rt.getComments()+ " ,PAX: "+ rt.getPax()  ;
            // Writing messages to log
       //     Log.d("Egrafh:", log);

       // }

        // firstTabBtn = (Button) findViewById(R.id.btn1);
        //  secondTabBtn= (Button) findViewById(R.id.btn2);
/*
        View.OnClickListener myOnlyhandler = new View.OnClickListener() {

            public void onClick(View v) {

                View tabView = tabHost.getCurrentTabView();
                ImageView iv = (ImageView) tabView.findViewById(R.id.imageView);

                switch (v.getId()) {
                    case R.id.btn1:

                        if (firstTabFlag == 1) {
                            firstTabBtn.setText("Unset icon");

                            iv.setVisibility(View.VISIBLE);
                            firstTabFlag = 0;
                        } else {

                            firstTabBtn.setText("Set icon");
                            iv.setVisibility(View.INVISIBLE);
                            firstTabFlag = 1;
                        }
                        break;
                    case R.id.btn2:

                        if (secondTabFlag == 1) {
                            secondTabBtn.setText("Unset icon");

                            iv.setVisibility(View.VISIBLE);
                            secondTabFlag = 0;
                        } else {

                            secondTabBtn.setText("Set icon");
                            iv.setVisibility(View.INVISIBLE);
                            secondTabFlag = 1;
                        }
                        break;
                }
            }
        };

//        firstTabBtn.setOnClickListener(myOnlyhandler);
        secondTabBtn.setOnClickListener(myOnlyhandler);


*/
        baudRate = 9600;
        stopBit = 1;
        dataBit = 8;
        parity = 0;
        flowControl = 0;


    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, Intent contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon));
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }


    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        tv.setTextSize(25);
        return view;
    }

    @Override
    protected void onResume() {
        // Ideally should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();

    }

    @Override
    protected void onPause() {
        // Ideally should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
    }



    /*********************helper routines*************************************************/


    private void UpdateText(String type, final double speedOverGroundInKnots, final double ecoDriverIndicator, final String buttonID, final double batteryVoltageInVolts, boolean validGPSSignal, final double temperature , int uniqueID )
    {
        // final CharSequence message=s;
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                tvName.setText(buttonID);
                tvecoDrive.setText(ecoDriverIndicator+"%");
                tvKm.setText(speedOverGroundInKnots+" km/h");
                tvTemp.setText(temperature+" C");
                tvVolt.setText(batteryVoltageInVolts+"V");
            }
        });
    }






}