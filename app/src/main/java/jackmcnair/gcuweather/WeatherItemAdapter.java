package jackmcnair.gcuweather;

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

        return convertView;
    }
}
