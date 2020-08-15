package jackmcnair.gcuweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout list;
    private ArrayList<WeatherItem> items;
    private String baseUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private String[] locationIDs = new String[]{"2648579", "2643743", "5128581", "287286", "934154", "1185241"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);

        items = getData();

        for (int i = 0; i < items.size(); i++) {
            TextView item = new TextView(this);
            item.setTextSize(24);
            item.setText(items.get(i).getLocationName() + " - " + items.get(i).getTemperature());
            list.addView(item);
        }
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
}