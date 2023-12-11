package com.muhammadrifkipratamajbusio.jbus_android;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.muhammadrifkipratamajbusio.jbus_android.model.Bus;

import java.util.List;

/**
 * The type Bus array adapter.
 */
public class BusArrayAdapter extends ArrayAdapter<Bus> {

        private Context context;

    /**
     * Instantiates a new Bus array adapter.
     *
     * @param context the context
     * @param busList the bus list
     */
    public BusArrayAdapter(Context context, List<Bus> busList) {
            super(context, 0, busList);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.bus_view, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.busNameTextView = convertView.findViewById(R.id.textViewBusName);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Bus currentBus = getItem(position);
            if (currentBus != null) {
                viewHolder.busNameTextView.setText(currentBus.toString());
            }

            return convertView;
        }

    /**
     * The type View holder.
     */
    static class ViewHolder {
        /**
         * The Bus name text view.
         */
        TextView busNameTextView;
        }
    }
