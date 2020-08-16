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
    private ArrayList<WeatherItem> items = new ArrayList<>();
    private String baseUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private String[] locationIDs = new String[]{"2648579", "2643743", "5128581", "287286", "934154", "1185241"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvRss = findViewById(R.id.lvRss);

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {
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
            try {
                for (int i = 0; i < locationIDs.length; i++) {

                    WeatherItem weatherItem = new WeatherItem();

                    URL url = new URL(baseUrl + locationIDs[i]);
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(getInputStream(url), "UTF_8");
                    boolean insideItem = false;
                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            if (xpp.getName().equalsIgnoreCase("item")) {
                                insideItem = true;
                            } else if (xpp.getName().equalsIgnoreCase("title")) {
                                if (insideItem) {
                                    String title = xpp.nextText();
                                    String[] titleParts = title.split(", ");
                                    weatherItem.getDay().add(titleParts[0].split(": ")[0]);
                                    weatherItem.getRain().add(titleParts[0].split(": ")[1]);
                                }
                            } else if (xpp.getName().equalsIgnoreCase("description")) {
                                if (insideItem) {
                                    String description = xpp.nextText();
                                    String[] descriptionParts = description.split(", ");
                                    weatherItem.getRain().add(descriptionParts[0].split(": ")[1]);

                                    String[] partTitles = {"Maximum Temperature",
                                            "Minimum Temperature", "Wind Direction",
                                            "Wind Speed", "Visibility", "Pressure",
                                            "Humidity", "UV Risk", "Pollution",
                                            "Sunrise", "Sunset"
                                    };

                                    for (int j = 0; j < descriptionParts.length; j++) {
                                        if(descriptionParts[j].startsWith(partTitles[0])){
                                            weatherItem.getMaxtemp().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[1])){
                                            weatherItem.getMintemp().add(descriptionParts[j].split(": ")[1]);
                                        }  else if(descriptionParts[j].startsWith(partTitles[2])){
                                            weatherItem.getWinddir().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[3])){
                                            weatherItem.getWindspeed().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[4])){
                                            weatherItem.getVisibility().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[5])){
                                            weatherItem.getPressure().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[6])){
                                            weatherItem.getHumidity().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[7])){
                                            weatherItem.getUvrisk().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[8])){
                                            weatherItem.getPollution().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[9])){
                                            weatherItem.getSunrise().add(descriptionParts[j].split(": ")[1]);
                                        } else if(descriptionParts[j].startsWith(partTitles[10])){
                                            weatherItem.getSunset().add(descriptionParts[j].split(": ")[1]);
                                        }
                                    }
                                }
                            }
                        } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        }
                        eventType = xpp.next();
                    }

                    items.add(weatherItem);

                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            WeatherItemAdapter adapter = new WeatherItemAdapter(MainActivity.this, items);
            lvRss.setAdapter(adapter);
            progressDialog.dismiss();
        }
    }
}