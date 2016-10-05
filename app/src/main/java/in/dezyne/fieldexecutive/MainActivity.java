package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Dezyne 2 on 9/8/2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Maps" ;
    Button submit, logout;
    FloatingActionButton fab;
    boolean isInFront;
    ImageButton maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        submit = (Button) findViewById(R.id.submittions);
        logout = (Button) findViewById(R.id.logout);
        fab = (FloatingActionButton) findViewById(R.id.fabadd);
        maps = (ImageButton) findViewById(R.id.maps);

        startService(new Intent(this, MyService.class));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubmittionActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNewActivity.class);
                startActivity(intent);


            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               new postdata().execute("String");

                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);


            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;

    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }



    public class postdata extends AsyncTask<String, Void, String>
    {
       /* HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://asso.esy.es/test/inst.php");
        try {
            // create a list to store HTTP variables and their values
            List nameValuePairs = new ArrayList();
            // add an HTTP variable and value pair
            nameValuePairs.add(new BasicNameValuePair("myHttpData", id));
            nameValuePairs.add(new BasicNameValuePair("myHttpData", time));
            nameValuePairs.add(new BasicNameValuePair("myHttpData", loc));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // send the variable and value, in other words post, to the URL
            HttpResponse response = httpclient.execute(httppost);
        } catch (UnsupportedEncodingException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL("http://asso.esy.es:80/test/inst.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setReadTimeout(10000);
                //conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("firstParam", "1")
                        .appendQueryParameter("secondParam", "12:10")
                        .appendQueryParameter("thirdParam", "Delhi");
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                Log.e(TAG, "Data Sent");
            } catch (IOException e) {

                e.printStackTrace();
                Log.e(TAG, "Data Not Sent");
            }
            return null;
        }

        /*@Override
        protected void onPostExecute(String results)
        {
            int i;
            HttpGet httpRequest = new HttpGet("http://asso.esy.es:80/test/inst.php");
            HttpEntity httpEntity = null;
            HttpClient httpclient = new DefaultHttpClient();
            try {
                HttpResponse response = httpclient.execute(httpRequest);
               i=response.getStatusLine().getStatusCode();
                Log.i("TASK Result", String.valueOf(i));
            } catch (IOException e) {
                e.printStackTrace();
            }



        }*/
    }

}





