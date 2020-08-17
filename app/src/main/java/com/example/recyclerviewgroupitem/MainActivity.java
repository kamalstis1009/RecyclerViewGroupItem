package com.example.recyclerviewgroupitem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.recyclerviewgroupitem.models.EventModel;
import com.example.recyclerviewgroupitem.models.EventItem;
import com.example.recyclerviewgroupitem.models.HeaderItem;
import com.example.recyclerviewgroupitem.models.ListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ListItem> mItems = new ArrayList<>();
        Map<String, ArrayList<EventModel>> map = toMap(loadEvents());
        for (String key : map.keySet()) {
            HeaderItem header = new HeaderItem(key);
            mItems.add(header);
            for (EventModel model : map.get(key)) {
                EventItem item = new EventItem(model);
                mItems.add(item);
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(mItems));
    }

    @NonNull
    private ArrayList<EventModel> loadEvents() {
        ArrayList<EventModel> arrayList = new ArrayList<>();
        arrayList.add(new EventModel("Jan1", "01-01-2020"));
        arrayList.add(new EventModel("Feb1", "01-02-2020"));
        arrayList.add(new EventModel("Jan2", "31-01-2020"));
        arrayList.add(new EventModel("Dec3", "21-12-2020"));
        arrayList.add(new EventModel("Dec1", "11-12-2020"));
        arrayList.add(new EventModel("Dec2", "13-12-2020"));
        arrayList.add(new EventModel("Mar2", "13-03-2020"));
        arrayList.add(new EventModel("Mar1", "10-03-2020"));
        return arrayList;
    }

    @NonNull
    private Map<String, ArrayList<EventModel>> toMap(@NonNull ArrayList<EventModel> arrayList) {
        Map<String, ArrayList<EventModel>> map = new TreeMap<>();
        ArrayList<EventModel> janArr = new ArrayList<>();
        ArrayList<EventModel> febArr = new ArrayList<>();
        ArrayList<EventModel> marArr = new ArrayList<>();
        ArrayList<EventModel> decArr = new ArrayList<>();
        for (EventModel model : arrayList) {
            Date[] jan = getFirstAndLastDate(1);
            Date[] feb = getFirstAndLastDate(2);
            Date[] mar = getFirstAndLastDate(3);
            Date[] dec = getFirstAndLastDate(12);
            Date date = getDateFromStringDate(model.getDate());

            if (jan != null && date.after(jan[0]) && date.before(jan[1])) {
                janArr.add(model);
                map.put("January", janArr);
            } else if (feb != null && date.after(feb[0]) && date.before(feb[1])) {
                febArr.add(model);
                map.put("February", febArr);
            } else if (mar != null && date.after(mar[0]) && date.before(mar[1])) {
                marArr.add(model);
                map.put("March", marArr);
            } else if (dec != null && date.after(dec[0]) && date.before(dec[1])) {
                decArr.add(model);
                map.put("December", decArr);
            }

            Log.d(TAG, jan[0] + "" + date);
            Log.d(TAG, "" + date);


            /*ArrayList<EventModel> value = map.get(model.getDate());
            if (value == null) {
                value = new ArrayList<>();
                map.put(model.getDate(), value);
            }
            value.add(model);*/
        }
        return map;
    }

    //=================================================| Get start date and end date from current month
    private Date[] getFirstAndLastDate(int dayOfMonth) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //https://stackoverflow.com/questions/22223786/get-first-and-last-day-of-month-using-threeten-localdate
            LocalDate initial = LocalDate.of(LocalDate.now().getYear() /*year*/, dayOfMonth /*month*/, 13 /*day*/);
            LocalDate start = initial.withDayOfMonth(1);
            LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
            return new Date[] {java.sql.Date.valueOf(start+""), java.sql.Date.valueOf(end+"")};
        }
        return null;
    }

    public String getFormatFromStringDate(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date); //java.text.DateFormat.getDateTimeInstance().format(ts.getTime())
    }

    public Date getDateFromStringDate(String input) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }


}