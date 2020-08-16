package jackmcnair.gcuweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.*;
import java.net.*;

import android.os.*;
import java.net.MalformedURLException;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvRss;
    private ArrayList<WeatherItem> items;
    private String baseUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private String[] locationIDs = new String[]{"2648579", "2643743", "5128581", "287286", "934154", "1185241"};
    private ArrayList<String> titles;
    private ArrayList<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvRss = findViewById(R.id.lvRss);

        items = getData();
        titles = new ArrayList<String>();
        descriptions = new ArrayList<String>();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        for (int i = 0; i < items.size(); i++) {
            TextView item = new TextView(this);
            item.setTextSize(24);
            item.setText(items.get(i).getLocationName() + " - " + items.get(i).getTemperature());
            //list.addView(item);
        }

        new ProccessInBackground().execute();
    }

    public ArrayList<WeatherItem> getData() {
        ArrayList<WeatherItem> newItems = new ArrayList<>();
        newItems.add(new WeatherItem("Glasgow", 50));
        newItems.add(new WeatherItem("London", 50));
        newItems.add(new WeatherItem("NewYork", 50));
        newItems.add(new WeatherItem("Oman", 50));
        newItems.add(new WeatherItem("Mauritius", 50));
        newItems.add(new WeatherItem("Bangladesh", 50));
        return newItems;
    }

    public InputStream getInputStream(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }

    }

    public class ProccessInBackground extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading RSS feed...");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {

            try
            {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579");

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(getInputStream(url),"UTF_8");

                boolean insideItem = false;

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                           if (insideItem)
                           {
                                titles.add(xpp.nextText());
                           }
                        }
                        else if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            if (insideItem)
                            {
                                descriptions.add(xpp.nextText());
                            }
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem = false;
                    }

                    eventType = xpp.next();
                }
            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch(XmlPullParserException e){
                exception = e;
            }
            catch (IOException e){
                exception = e;
            }

            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);

            lvRss.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }
}