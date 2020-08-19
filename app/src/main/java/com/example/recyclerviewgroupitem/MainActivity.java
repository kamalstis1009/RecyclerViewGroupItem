package com.example.recyclerviewgroupitem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.recyclerviewgroupitem.adapters.MyAdapter;
import com.example.recyclerviewgroupitem.models.MyModel;
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
        Map<String, ArrayList<MyModel>> map = toMap(loadEvents());
        for (String key : map.keySet()) {
            HeaderItem header = new HeaderItem(key);
            mItems.add(header);
            for (MyModel model : map.get(key)) {
                EventItem item = new EventItem(model);
                mItems.add(item);
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(mItems));
    }

    @NonNull
    private ArrayList<MyModel> loadEvents() {
        ArrayList<MyModel> arrayList = new ArrayList<>();
        arrayList.add(new MyModel("Jan1", "2020-01-21 01:57:33.435"));
        arrayList.add(new MyModel("Feb1", "2020-02-01 01:57:33.435"));
        arrayList.add(new MyModel("Jan2", "2020-01-31 01:57:33.435"));
        arrayList.add(new MyModel("Dec3", "2020-12-21 01:57:33.435"));
        arrayList.add(new MyModel("Dec1", "2020-12-11 01:57:33.435"));
        arrayList.add(new MyModel("Dec2", "2020-12-12 01:57:33.435"));
        arrayList.add(new MyModel("Mar2", "2020-03-17 01:57:33.435"));
        arrayList.add(new MyModel("Mar1", "2020-03-01 01:57:33.435"));
        return arrayList;
    }

    @NonNull
    private Map<String, ArrayList<MyModel>> toMap(@NonNull ArrayList<MyModel> arrayList) {
        Map<String, ArrayList<MyModel>> map = new TreeMap<>(); //Sorted A-Z : TreeMap
        ArrayList<MyModel> janArr = null, febArr = null, marArr = null, appArr = null, mayArr = null, junArr = null, julArr = null, augArr = null, sepArr = null, octArr = null, novArr = null, decArr = null;
        for (MyModel model : arrayList) {
            Date[] jan = getFirstAndLastDate(1), feb = getFirstAndLastDate(2), mar = getFirstAndLastDate(3), apr = getFirstAndLastDate(4), may = getFirstAndLastDate(5), jun = getFirstAndLastDate(6), jul = getFirstAndLastDate(7), aug = getFirstAndLastDate(8), sep = getFirstAndLastDate(9), oct = getFirstAndLastDate(10), nov = getFirstAndLastDate(11), dec = getFirstAndLastDate(12);
            Date date = getDateFromCSharpStringDateTime(model.getDate());
            if (jan != null && jan[0].before(date) && jan[1].after(date)) {
                if (janArr == null) { janArr = new ArrayList<>(); }
                janArr.add(model);
            } else if (feb != null && date.after(feb[0]) && date.before(feb[1])) {
                if (febArr == null) { febArr = new ArrayList<>(); }
                febArr.add(model);
            } else if (mar != null && date.after(mar[0]) && date.before(mar[1])) {
                if (marArr == null) { marArr = new ArrayList<>(); }
                marArr.add(model);
            } else if (apr != null && date.after(apr[0]) && date.before(apr[1])) {
                if (appArr == null) { appArr = new ArrayList<>(); }
                appArr.add(model);
            } else if (may != null && date.after(may[0]) && date.before(may[1])) {
                if (mayArr == null) { mayArr = new ArrayList<>(); }
                mayArr.add(model);
            } else if (jun != null && date.after(jun[0]) && date.before(jun[1])) {
                if (junArr == null) { junArr = new ArrayList<>(); }
                junArr.add(model);
            } else if (jul != null && date.after(jul[0]) && date.before(jul[1])) {
                if (julArr == null) { julArr = new ArrayList<>(); }
                julArr.add(model);
            } else if (aug != null && date.after(aug[0]) && date.before(aug[1])) {
                if (augArr == null) { augArr = new ArrayList<>(); }
                augArr.add(model);
            } else if (sep != null && date.after(sep[0]) && date.before(sep[1])) {
                if (sepArr == null) { sepArr = new ArrayList<>(); }
                sepArr.add(model);
            } else if (oct != null && date.after(oct[0]) && date.before(oct[1])) {
                if (octArr == null) { octArr = new ArrayList<>(); }
                octArr.add(model);
            } else if (nov != null && date.after(nov[0]) && date.before(nov[1])) {
                if (novArr == null) { novArr = new ArrayList<>(); }
                novArr.add(model);
            } else if (dec != null && date.after(dec[0]) && date.before(dec[1])) {
                if (decArr == null) { decArr = new ArrayList<>(); }
                decArr.add(model);
            }
        }
        if (janArr != null) { map.put("January", janArr); }
        if (febArr != null) { map.put("February", febArr); }
        if (marArr != null) { map.put("March", marArr); }
        if (appArr != null) { map.put("April", appArr); }
        if (mayArr != null) { map.put("May", mayArr); }
        if (junArr != null) { map.put("Jun", junArr); }
        if (julArr != null) { map.put("July", julArr); }
        if (augArr != null) { map.put("August", augArr); }
        if (sepArr != null) { map.put("September", sepArr); }
        if (octArr != null) { map.put("October", octArr); }
        if (novArr != null) { map.put("November", novArr); }
        if (decArr != null) { map.put("December", decArr); }
        return map;

        /*ArrayList<EventModel> value = map.get(model.getDate());
        if (value == null) {
            value = new ArrayList<>();
            map.put(model.getDate(), value);
        }
        value.add(model);*/
    }

    //=================================================| Get start date and end date from current month
    private Date[] getFirstAndLastDate(int month) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //https://stackoverflow.com/questions/22223786/get-first-and-last-day-of-month-using-threeten-localdate
            LocalDate initial = LocalDate.of(LocalDate.now().getYear() /*year*/, month /*month*/, 13 /*day*/);
            LocalDate start = initial.withDayOfMonth(1);
            LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
            return new Date[] {java.sql.Date.valueOf(start+""), java.sql.Date.valueOf(end+"")};
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public Date getDateFromCSharpStringDateTime(String input) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
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