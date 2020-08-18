package jackmcnair.gcuweather;

//Jack McNair S1630148

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WeatherItemAdapter extends ArrayAdapter<WeatherItem> {

    public WeatherItemAdapter(@NonNull Context context, @NonNull List<WeatherItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WeatherItem currentItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        }

        TextView locationName = convertView.findViewById(R.id.locationName);
        locationName.setText(currentItem.getLocationName());

        TextView day1 = convertView.findViewById(R.id.day1);
        day1.setText(currentItem.getDay().get(0));
        TextView day2 = convertView.findViewById(R.id.day2);
        day2.setText(currentItem.getDay().get(1));
        TextView day3 = convertView.findViewById(R.id.day3);
        day3.setText(currentItem.getDay().get(2));

        TextView temp1 = convertView.findViewById(R.id.temp1);
        temp1.setText(currentItem.getMintemp().get(0));
        TextView temp2 = convertView.findViewById(R.id.temp2);
        temp2.setText(currentItem.getMintemp().get(1));
        TextView temp3 = convertView.findViewById(R.id.temp3);
        temp3.setText(currentItem.getMintemp().get(2));

        TextView maxTemp2 = convertView.findViewById(R.id.maxTemp2);
        maxTemp2.setText(currentItem.getMaxtemp().get(0));
        TextView maxTemp3 = convertView.findViewById(R.id.maxTemp3);
        maxTemp3.setText(currentItem.getMaxtemp().get(1));

        TextView rain1 = convertView.findViewById(R.id.rain1);
        rain1.setText(currentItem.getRain().get(0));
        TextView rain2 = convertView.findViewById(R.id.rain2);
        rain2.setText(currentItem.getRain().get(1));
        TextView rain3 = convertView.findViewById(R.id.rain3);
        rain3.setText(currentItem.getRain().get(2));

        TextView windspeed1 = convertView.findViewById(R.id.windspeed1);
        windspeed1.setText(currentItem.getWindspeed().get(0));
        TextView windspeed2 = convertView.findViewById(R.id.windspeed2);
        windspeed2.setText(currentItem.getWindspeed().get(1));
        TextView windspeed3 = convertView.findViewById(R.id.windspeed3);
        windspeed3.setText(currentItem.getWindspeed().get(2));

        return convertView;
    }
}
