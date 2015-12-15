package com.my.crowd.crowd;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Leuteris on 14/6/2015.
 */
public class IstorikoActivity extends Activity {

    private static final String[] time = { "Επιχείρηση","Plaisio","Everest", "Super Market" };
    private static final String[] name = { "Τιμή", "66 euro ", "30.5 euro","100 euro" };
    private static final String[] persons = {"Ημερ.","12-6-15","14-6-15","8-6-2015","10-5-15"};
    TableLayout table;
    Button btnsnd;
    TextView points;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.istoriko);

        fillTable();

    }





    public void fillTable() {
        int rowCount = time.length;

        table = (TableLayout) this.findViewById(R.id.stationsTable);

        for (int i = 0; i < rowCount; i++) {

            TableRow row = new TableRow(this);

            table.addView(row);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT);
            row.setLayoutParams(lp);
            //if(i%2 == 0)
             //   row.setBackgroundColor(this.getResources().getColor(R.color.tableRowColor));
             //   else
              //  row.setBackgroundColor(this.getResources().getColor(R.color.tableRowSecondColor));

            fillRow(row, i);
            // Create table from xml layout file using layout inflater:
            //fillRow(table, i);
            if(i==0)
            row.setBackgroundColor(Color.GRAY);
        }
        table.setGravity(Gravity.CENTER);
    }

    public void fillRow(TableRow row, int noRow) {

        final int noTableRow = noRow;

        TextView tvNo                     = new TextView(this);
        TableRow.LayoutParams lptvNo = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,10f);
        tvNo.setLayoutParams(lptvNo);
        tvNo.setText(time[noRow]);
        tvNo.setTextSize(20);
        tvNo.setGravity(Gravity.LEFT);
        tvNo.setTypeface(null, Typeface.BOLD);



        TextView tvName      = new TextView(this);
        TableRow.LayoutParams lptvStartDate = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,10f);
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
