package com.my.crowd.crowd;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Leuteris on 14/6/2015.
 */
public class Prosfores extends Activity {

    private static final String[] time = { "Plaisio","Everest", "Super Market" };
    private static final String[] name = { "Δωρεάν επισκεύη", "Καφές","Κουπόνια προσφορών αξίας 20 ευρώ" };
    private static final String[] persons = { "100","50","200"};
    TableLayout table;
    Button btnsnd;
    TextView points;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prosfores);
        btnsnd = (Button) findViewById(R.id.button);

         points = (TextView)findViewById(R.id.points);
        fillTable();


        btnsnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int sum = 0;

                View view;

                TableRow row ;
                TextView tv ;
                CheckBox cb ;

                    for (int i = 0, j = table.getChildCount(); i < j; i++) {

                        view = table.getChildAt(i);

                        if (view instanceof TableRow) {

                            row = (TableRow) view;
                            tv = (TextView) row.getChildAt(2);
                            cb = (CheckBox) row.getChildAt(0);

                            if(cb.isChecked()) {
                                sum += Integer.parseInt(tv.getText().toString());
                                if((Integer.parseInt( MainActivity.tvName.getText().toString()))-sum<0) {
                                    sum -= Integer.parseInt(tv.getText().toString());

                                }
                                //else
                                  //  sum += Integer.parseInt(tv.getText().toString());

                                cb.setChecked(false);
                            }

                        }
                    }
                    Toast.makeText(v.getContext(), sum + "", Toast.LENGTH_LONG).show();
                MainActivity.tvName.setText(Integer.parseInt( MainActivity.tvName.getText().toString())-sum + "");

            }

        });

    }





    public void fillTable() {
        int rowCount = time.length;

        table = (TableLayout) this.findViewById(R.id.stationsTable);

        for (int i = 0; i < rowCount; i++) {

            TableRow row = new TableRow(this);

            table.addView(row);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(android.widget.TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT);
            row.setLayoutParams(lp);
           // if(i%2 == 0)
               // row.setBackgroundColor(this.getResources().getColor(R.color.tableRowColor));
          //  else
              //  row.setBackgroundColor(this.getResources().getColor(R.color.tableRowSecondColor));

            fillRow(row, i);
            // Create table from xml layout file using layout inflater:
            //fillRow(table, i);
        }
        table.setGravity(Gravity.CENTER);
    }

    public void fillRow(TableRow row, int noRow) {

        final int noTableRow = noRow;

        TextView tvNo                     = new CheckBox(this);
        TableRow.LayoutParams lptvNo = new TableRow.LayoutParams(0,android.widget.TableRow.LayoutParams.WRAP_CONTENT,10f);
        tvNo.setLayoutParams(lptvNo);
        tvNo.setText(time[noRow]);
        tvNo.setTextSize(20);
        tvNo.setGravity(Gravity.LEFT);
        tvNo.setTypeface(null, Typeface.BOLD);



        TextView tvName      = new TextView(this);
        TableRow.LayoutParams lptvStartDate = new TableRow.LayoutParams(0,android.widget.TableRow.LayoutParams.WRAP_CONTENT,10f);
        tvName.setLayoutParams(lptvStartDate);
        tvName.setText(name[noRow]);
        tvName.setTextSize(25);
        tvName.setGravity(Gravity.CENTER);
        tvName.setTypeface(null, Typeface.BOLD);

       // tvName.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View viewIn) {
       //         showSetPassengerDialog(noTableRow);
       //     }
       // });

        TextView tvEpivates      = new TextView(this);
        TableRow.LayoutParams lptvEpivates = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT,10f);
        tvEpivates.setLayoutParams(lptvEpivates);
        tvEpivates.setText(persons[noRow]);
        tvEpivates.setTextSize(25);
        tvEpivates.setGravity(Gravity.RIGHT);
        tvEpivates.setTypeface(null, Typeface.BOLD);

        //tvEpivates.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View viewIn) {
        //        showSetPassengerDialog(noTableRow);
        //    }
       // });

        row.addView(tvNo);
        row.addView(tvName);
        row.addView(tvEpivates);
        row.setPadding(17,17,17,17);
        row.setGravity(Gravity.CENTER);

    }

}
