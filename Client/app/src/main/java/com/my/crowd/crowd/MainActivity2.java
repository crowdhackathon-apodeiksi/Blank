package com.my.crowd.crowd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.LinearLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
//https://bitbucket.org/danielnadeau/holographlibrary/wiki/Home

public class MainActivity2 extends Activity {

    Button btnsnd;
    Button btnscan;
    EditText txt1,txt2,txt3;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    LinearLayout linear;
    EditText text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnn);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btnsnd = (Button) findViewById(R.id.btnsnd);
        btnscan = (Button)findViewById(R.id.btnimage);
        txt1 = (EditText)findViewById(R.id.txt1);
        txt2 = (EditText)findViewById(R.id.txt2);
        txt3 = (EditText)findViewById(R.id.txt3);

        btnscan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Log.d("DATE", txt1.getText().toString());
                //Log.d("AFM", txt2.getText().toString());
               // Log.d("SUM", txt3.getText().toString());
               // new MyAsyncTask().execute(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());
                /*
                HttpClient httpClient = new DefaultHttpClient();
                     Log.d("Crashed?", "1");
                     HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/andro/server.php");
                     Log.d("Crashed?", "2");
                     List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
                     Log.d("Crashed?", "3");
                     nameValuePair.add(new BasicNameValuePair("DATE", txt1.getText().toString()));
                     nameValuePair.add(new BasicNameValuePair("AFM", txt2.getText().toString()));
                     nameValuePair.add(new BasicNameValuePair("SUM", txt3.getText().toString()));
                     Log.d("Crashed?", "4");
                     try {
                         httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                         Log.d("Crashed?", "4");
                     } catch (UnsupportedEncodingException e) {
                         Log.d("Crashed?", "4.5");
                         e.printStackTrace();
                         Log.d("Crashed?", "4.8");
                     }
                     try {
                         Log.d("Crashed?", "5");
                         HttpResponse response = httpClient.execute(httpPost);
                         Log.d("Crashed?", "6");
                         // write response to log
                         Log.d("Http Post Response:", response.toString());
                         Log.d("Crashed?", "7");

                     } catch (ClientProtocolException e) {
                         // Log exception
                         Log.d("Crashed?", "7.2");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.4");
                     } catch (IOException e) {
                         // Log exception
                         Log.d("Crashed?", "7.5");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.6");
                     } catch (Exception e) {
                         Log.d("Crashed?", "7.7");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.9");
                     }
                */

                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cam);
                 File imagesFolder = new File(Environment.getExternalStorageDirectory() + "/myscans/");
                File sdcard = Environment.getExternalStorageDirectory();
                 File dataDir = new File(sdcard, "MyApp");
                imagesFolder.mkdirs();
                Log.d("ImagesFolder:",Boolean.toString(imagesFolder.canWrite()));
                Log.d("ImagesFolder:",Boolean.toString(imagesFolder.isDirectory()));
                Log.d("ImagesFolder:",imagesFolder.getPath());

                boolean success = true;
                if (!imagesFolder.exists()) {
                    success = imagesFolder.mkdirs();
                }
                if (success)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Hello toast!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    // Do something else on failure
                    Context context = getApplicationContext();
                    CharSequence text = "Not hello!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                File image = new File(imagesFolder,"Scan1.png");
                Uri uriSavedImage = Uri.fromFile(image);
                cam.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage); // set the image file name
                startActivityForResult(cam, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                //startActivityForResult(imageIntent,0);
                //File imageFile = new File("/sdcard/myscans");
                //String imageInSD = Environment.getExternalStorageDirectory().getAbsolutePath() +"/myscans/"+"Scan1.jpg";
                //myImageView.setImageBitmap(bitmap);
               // DataProcess dp = new DataProcess(0,(text1.getText().toString()));
               // Log.d("DP:",dp.getInput());
            }
        });






        btnsnd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //Log.d("DATE", txt1.getText().toString());
                //Log.d("AFM", txt2.getText().toString());
                // Log.d("SUM", txt3.getText().toString());
                 new MyAsyncTask().execute(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

                HttpClient httpClient = new DefaultHttpClient();
                     Log.d("Crashed?", "1");
                     HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/andro/server.php");
                     Log.d("Crashed?", "2");
                     List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
                     Log.d("Crashed?", "3");
                     nameValuePair.add(new BasicNameValuePair("DATE", txt1.getText().toString()));
                     nameValuePair.add(new BasicNameValuePair("AFM", txt2.getText().toString()));
                     nameValuePair.add(new BasicNameValuePair("SUM", txt3.getText().toString()));
                     Log.d("Crashed?", "4");
                     try {
                         httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                         Log.d("Crashed?", "4");
                     } catch (UnsupportedEncodingException e) {
                         Log.d("Crashed?", "4.5");
                         e.printStackTrace();
                         Log.d("Crashed?", "4.8");
                     }
                     try {
                         Log.d("Crashed?", "5");
                         HttpResponse response = httpClient.execute(httpPost);
                         Log.d("Crashed?", "6");
                         // write response to log
                         Log.d("Http Post Response:", response.toString());
                         Log.d("Crashed?", "7");

                     } catch (ClientProtocolException e) {
                         // Log exception
                         Log.d("Crashed?", "7.2");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.4");
                     } catch (IOException e) {
                         // Log exception
                         Log.d("Crashed?", "7.5");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.6");
                     } catch (Exception e) {
                         Log.d("Crashed?", "7.7");
                         e.printStackTrace();
                         Log.d("Crashed?", "7.9");
                     }
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


    //http://mobiledevtuts.com/android/android-http-with-asynctask-example/
    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected Double doInBackground(String... params) {
// TODO Auto-generated method stub
            postData(params[0],params[1],params[2]);
            return null;
        }

        protected void onPostExecute(Double result){
            // pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
        }
        protected void onProgressUpdate(Integer... progress){
            // pb.setProgress(progress[0]);
        }

        public void postData(String text1,String text2,String text3) {
// Create a new HttpClient and Post Header
            Log.d("Here:","HERE");
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.mirobillis.host56.com/server.php");
            try {
// Add your data
                Log.d("Here","2");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("DATE", text1));
                nameValuePairs.add(new BasicNameValuePair("AFM", text2));
                nameValuePairs.add(new BasicNameValuePair("SUM", text3));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                Log.d("Here","3");
// Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                /*
                HttpEntity responseEntity =  response.getEntity();
                if (responseEntity != null) {
                   String res = EntityUtils.toString(responseEntity);
                } else {
                   String  res = "{\"NO DATA:\"NO DATA\"}";
                }
                */
                Log.d("Here","4");
                //Log.d("Http Post Response:", response.toString());
                String res;
                HttpEntity responseEntity =  response.getEntity();
                res = EntityUtils.toString(responseEntity);
                // if (responseEntity != null) {
                //     res = EntityUtils.toString(responseEntity);
                // } else {
                //     res = "{\"NO DATA:\"NO DATA\"}";
                // }
                Log.d("response is ",res);
                Log.d("Here","5");
            } catch (ClientProtocolException e) {
// TODO Auto-generated catch block
                Log.d("Here","6");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("Here","7");
                e.printStackTrace();
// TODO Auto-generated catch block
            }catch(Exception e)
            {Log.d("Here","8"); e.printStackTrace();}
        }
    }

}
