package com.example.barath.jsoup;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.textView);
        click = (Button) findViewById(R.id.button);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new doit().execute();
            }
        });

    }

    public class doit extends AsyncTask<Void,Void,Void>{
        String content;
        int s,d;
        String temp;
        @Override
        protected Void doInBackground(Void... voids) {
             try {
                 Document doc = Jsoup.connect("https://www.icicibank.com/rural/loans/farmer-finance/index.page").get();
                 content = doc.text();

                s =  content.indexOf("What are the types of agriculture loans available for me?");
                 d =  content.indexOf("What are the benefits of availing the Kisan Card / Kisan Credit Card facility from ICICI Bank?");

                temp = String.valueOf(s);

                content= content.substring(s,d);


             } catch(Exception e){
                 e.printStackTrace();

             }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            text.setText(content);
        }
    }

}
