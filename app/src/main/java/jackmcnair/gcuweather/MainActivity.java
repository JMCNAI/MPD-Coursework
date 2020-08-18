package jackmcnair.gcuweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity{

    private ListView lvRss;
    private ArrayList<WeatherItem> items = new ArrayList<>();
    private String baseUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private String[] locationIDs = new String[]{"2648579", "2643743", "5128581", "287286", "934154", "1185241"};
    private String[] locationNames = new String[]{"Glasgow", "London", "New York", "Oman", "Mauritius", "Bangladesh"};
    private Button backButton;
    private ConstraintLayout detailLayout;
    private TextView locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvRss = findViewById(R.id.lvRss);
        backButton = findViewById(R.id.back);
        detailLayout = findViewById(R.id.layout);
        locationName = findViewById(R.id.locationName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvRss.setVisibility(View.VISIBLE);
                detailLayout.setVisibility(View.GONE);
            }
        });

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvRss.setVisibility(View.GONE);
                detailLayout.setVisibility(View.VISIBLE);

                locationName.setText(items.get(i).getLocationName());

                TextView day1 = findViewById(R.id.day1);
                day1.setText(items.get(i).getDay().get(0));
                TextView day2 = findViewById(R.id.day2);
                day2.setText(items.get(i).getDay().get(1));
                TextView day3 = findViewById(R.id.day3);
                day3.setText(items.get(i).getDay().get(2));

                TextView temp1 = findViewById(R.id.temp1);
                temp1.setText(items.get(i).getMintemp().get(0));
                TextView temp2 = findViewById(R.id.temp2);
                temp2.setText(items.get(i).getMintemp().get(1));
                TextView temp3 = findViewById(R.id.temp3);
                temp3.setText(items.get(i).getMintemp().get(2));


                TextView maxTemp2 = findViewById(R.id.maxTemp2);
                maxTemp2.setText(items.get(i).getMaxtemp().get(0));
                TextView maxTemp3 = findViewById(R.id.maxTemp3);
                maxTemp3.setText(items.get(i).getMaxtemp().get(1));

                TextView rain1 = findViewById(R.id.rain1);
                rain1.setText(items.get(i).getRain().get(0));
                TextView rain2 = findViewById(R.id.rain2);
                rain2.setText(items.get(i).getRain().get(1));
                TextView rain3 = findViewById(R.id.rain3);
                rain3.setText(items.get(i).getRain().get(2));

                TextView windspeed1 = findViewById(R.id.windspeed1);
                windspeed1.setText(items.get(i).getWindspeed().get(0));
                TextView windspeed2 = findViewById(R.id.windspeed2);
                windspeed2.setText(items.get(i).getWindspeed().get(1));
                TextView windspeed3 = findViewById(R.id.windspeed3);
                windspeed3.setText(items.get(i).getWindspeed().get(2));

                TextView winddir1 = findViewById(R.id.winddir1);
                winddir1.setText(items.get(i).getWinddir().get(0));
                TextView winddir2 = findViewById(R.id.winddir2);
                winddir2.setText(items.get(i).getWinddir().get(1));
                TextView winddir3 = findViewById(R.id.winddir3);
                winddir3.setText(items.get(i).getWinddir().get(2));

                TextView visibility1 = findViewById(R.id.visibility1);
                visibility1.setText(items.get(i).getVisibility().get(0));
                TextView visibility2 = findViewById(R.id.visibility2);
                visibility2.setText(items.get(i).getVisibility().get(1));
                TextView visibility3 = findViewById(R.id.visibility3);
                visibility3.setText(items.get(i).getVisibility().get(2));

                TextView humidity1 = findViewById(R.id.humidity1);
                humidity1.setText(items.get(i).getHumidity().get(0));
                TextView humidity2 = findViewById(R.id.humidity2);
                humidity2.setText(items.get(i).getHumidity().get(1));
                TextView humidity3 = findViewById(R.id.humidity3);
                humidity3.setText(items.get(i).getHumidity().get(2));

                TextView pressure1 = findViewById(R.id.pressure1);
                pressure1.setText(items.get(i).getPressure().get(0));
                TextView pressure2 = findViewById(R.id.pressure2);
                pressure2.setText(items.get(i).getPressure().get(1));
                TextView pressure3 = findViewById(R.id.pressure3);
                pressure3.setText(items.get(i).getPressure().get(2));

                TextView uvrisk1 = findViewById(R.id.uvrisk1);
                uvrisk1.setText(items.get(i).getUvrisk().get(0));
                TextView uvrisk2 = findViewById(R.id.uvrisk2);
                uvrisk2.setText(items.get(i).getUvrisk().get(1));
                TextView uvrisk3 = findViewById(R.id.uvrisk3);
                uvrisk3.setText(items.get(i).getUvrisk().get(2));

                TextView pollution1 = findViewById(R.id.pollution1);
                pollution1.setText(items.get(i).getPollution().get(0));
                TextView pollution2 = findViewById(R.id.pollution2);
                pollution2.setText(items.get(i).getPollution().get(1));
                TextView pollution3 = findViewById(R.id.pollution3);
                pollution3.setText(items.get(i).getPollution().get(2));

                TextView sunrise2 = findViewById(R.id.sunrise2);
                sunrise2.setText(items.get(i).getSunrise().get(0));
                TextView sunrise3 = findViewById(R.id.sunrise3);
                sunrise3.setText(items.get(i).getSunrise().get(1));

                TextView sunset1 = findViewById(R.id.sunset1);
                sunset1.setText(items.get(i).getSunset().get(0));
                TextView sunset2 = findViewById(R.id.sunset2);
                sunset2.setText(items.get(i).getSunset().get(1));
                TextView sunset3 = findViewById(R.id.sunset3);
                sunset3.setText(items.get(i).getSunset().get(2));
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
                    weatherItem.setLocationName(locationNames[i]);
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