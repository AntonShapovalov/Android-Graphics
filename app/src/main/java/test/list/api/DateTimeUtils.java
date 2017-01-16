package test.list.api;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    public static void run() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

        /**
         * DataTimeZone.UTC return the same millis as initial date !!!
         */
        MutableDateTime mCurrent = new MutableDateTime(1484557500000L);
        MutableDateTime mCurrentUTC = new MutableDateTime(1484557500000L, DateTimeZone.UTC);
        Log.d("*** DATE_TIME val***", String.valueOf(mCurrent.getMillis()));
        Log.d("*** DATE_TIME cur ***", dateFormat.format(mCurrent.getMillis()));

        Log.d("*** DATE_TIME val***", String.valueOf(mCurrentUTC.getMillis()));
        Log.d("*** DATE_TIME utc ***", dateFormat.format(mCurrentUTC.getMillis()));

//        mCurrent = new MutableDateTime();
//        mCurrentUTC = new MutableDateTime(1445587200000L, DateTimeZone.UTC);
//        Log.d("*** DATE_TIME cur ***", dateFormat.format(mCurrent.getMillis()));
//        Log.d("*** DATE_TIME utc ***", dateFormat.format(mCurrentUTC.getMillis()));

        mCurrent = new MutableDateTime(DateTimeZone.UTC);
        Log.d("*** DATE_TIME *** ----", dateFormat.format(mCurrent.getMillis()));
        Log.d("*** DATE_TIME ***", String.valueOf(mCurrent.getMillis()));
        Log.d("*** DATE_TIME ***", fmt.print(mCurrent));

        DateTime startDateTime = mCurrent.toDateTime(DateTimeZone.getDefault());
        Log.d("*** DATE_TIME *** ----", dateFormat.format(startDateTime.getMillis()));
        Log.d("*** DATE_TIME ***", String.valueOf(startDateTime.getMillis()));
        Log.d("*** DATE_TIME ***", fmt.print(startDateTime));

        DateTimeZone timeZone = DateTimeZone.forID("Japan");
        MutableDateTime japan = new MutableDateTime(timeZone);
        Log.d("*** DATE_TIME *** ----", dateFormat.format(japan.getMillis()));
        Log.d("*** DATE_TIME ***", String.valueOf(japan.getMillis()));
        Log.d("*** DATE_TIME ***", fmt.print(japan));

        DateTime dateTime = new DateTime();
        DateTime dtLondon = dateTime.withZone(DateTimeZone.forID("Europe/London"));
        Log.d("*** DATE_TIME *** ----", dateFormat.format(dtLondon.getMillis()));
        Log.d("*** DATE_TIME ***", String.valueOf(dtLondon.getMillis()));
        Log.d("*** DATE_TIME ***", fmt.print(dtLondon));
//
//        DateTime dt = new DateTime();
//        // find the moment when London will have / had the same time
//        DateTime dtLondonSameTime = dt.withZoneRetainFields(DateTimeZone.forID("Europe/London"));
//        Log.d("*** DATE_TIME *** ----", dateFormat.format(dtLondonSameTime.getMillis()));
//        Log.d("*** DATE_TIME ***", String.valueOf(dtLondonSameTime.getMillis()));
//        Log.d("*** DATE_TIME ***", fmt.print(dtLondonSameTime));

        /**
         * ------------------------ UTC conservations ----------------------------------------
         */
        DateTime currentDate;
        long currentMillis;
        DateTime utcDate;
        long utcMillis;
        DateTimeZone defaultZone = DateTimeZone.getDefault();

        /**
         * Convert current millis to UTC
         */
        currentDate = new DateTime();
        currentMillis = currentDate.getMillis(); // currentMillis = System.currentTimeMillis();
        utcMillis = defaultZone.convertLocalToUTC(currentMillis, true);
        utcDate = new DateTime(utcMillis);

        Log.d("*** DATE_TIME ***", " ----------------- Current time to UTC ----------------- ");
        Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis);
        Log.d("*** DATE_TIME ***", "Current.date = " + dateFormat.format(currentMillis));
        Log.d("*** DATE_TIME ***", "Current.format = " + fmt.print(currentDate));
        Log.d("*** DATE_TIME ***", "UTC.millis = " + utcMillis);
        Log.d("*** DATE_TIME ***", "UTC.date = " + dateFormat.format(utcMillis));
        Log.d("*** DATE_TIME ***", "UTC.format = " + fmt.print(utcDate));

        /**
         * Convert some UTC millis to local
         */
//        utcMillis = 1459325827594L;
//        utcDate = new DateTime(utcMillis);
//        currentMillis = defaultZone.convertUTCToLocal(utcMillis);
//        currentDate = new DateTime(currentMillis);
//
//        Log.d("*** DATE_TIME ***", "----------------- UTC time to Local -----------------");
//        Log.d("*** DATE_TIME ***", "UTC.millis = " + utcMillis);
//        Log.d("*** DATE_TIME ***", "UTC.date = " + dateFormat.format(utcMillis));
//        Log.d("*** DATE_TIME ***", "UTC.format = " + fmt.print(utcDate));
//        Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis);
//        Log.d("*** DATE_TIME ***", "Current.date = " + dateFormat.format(currentMillis));
//        Log.d("*** DATE_TIME ***", "Current.format = " + fmt.print(currentDate));


        /**
         * Convert some text date value to UTC millis and local millis
         */
        String someTextDate = "2017-01-16T11:05:00.000Z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        try {
            Date valueDate = simpleDateFormat.parse(someTextDate);
            Long valueMillis = valueDate.getTime();
            utcMillis = defaultZone.convertLocalToUTC(valueMillis, true);
            utcDate = new DateTime(utcMillis);

            Log.d("*** DATE_TIME ***", " ----------------- Some text value to UTC ----------------- ");
            Log.d("*** DATE_TIME ***", "Value.millis = " + valueMillis);
            Log.d("*** DATE_TIME ***", "Value.date = " + simpleDateFormat.format(valueMillis));
            Log.d("*** DATE_TIME ***", "UTC.millis = " + utcMillis);
            Log.d("*** DATE_TIME ***", "UTC.date = " + dateFormat.format(utcMillis));
            Log.d("*** DATE_TIME ***", "UTC.format = " + fmt.print(utcDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Log.d("*** DATE_TIME_ZONES ***", "-----------------");
//        Set<String> zones = DateTimeZone.getAvailableIDs();
//        for (String s : zones) {
//            Log.d("DATE_TIME_ZONE", s);
//        }
//        Log.d("*** DATE_TIME_ZONES ***", "-----------------");

//        TextView textView = (TextView) findViewById(android.R.id.text1);
//        textView.append(dateFormat.format(mCurrent.getMillis()));
//        textView.append("\n");
//        textView.append(dateFormat.format(startDateTime.getMillis()));

//        Date date = new Date();
//        currentMillis = date.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
//
//        Log.d("*** DATE_TIME ***", " ----------------- 8.00 UTC ----------------- ");
//        Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis);
//        Log.d("*** DATE_TIME ***", "Current.date = " + dateFormat.format(date));
//        Log.d("*** DATE_TIME ***", "Current.date = " + simpleDateFormat.format(date));


//        long[] timestamps = new long[]{
//                1446019200000L,
//                1445932800000L,
//                1445846400000L,
//                1445760000000L,
//                1445670000000L,
//                1445583600000L
//        };
//        int size = timestamps.length;
//        for (int i = 0; i < size; i++) {
//            currentDate = new DateTime(timestamps[i]);
//            currentMillis = currentDate.getMillis();
//            Log.d("*** DATE_TIME ***", "timestamps[" + i + "]=" + timestamps[i] + "----------");
//            Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis);
//            Log.d("*** DATE_TIME ***", "Current.date = " + dateFormat.format(currentMillis));
//            Log.d("*** DATE_TIME ***", "Current.format = " + fmt.print(currentDate));
//        }


//        Log.d("*** DATE_TIME ***", "-------- Timestamp change for DST (daily change time) --------");
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        String time = "2016-10-29 11:00:00";
//        LocalDateTime localDateTime = formatter.parseLocalDateTime(time);
//        currentMillis = localDateTime.toDateTime().getMillis();
//        Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis);
//        Log.d("*** DATE_TIME ***", "Current.date = " + formatter.print(currentMillis));
//
//        time = "2016-10-30 11:00:00";
//        localDateTime = formatter.parseLocalDateTime(time);
//        long currentMillis1 = localDateTime.toDateTime().getMillis();
//        Log.d("*** DATE_TIME ***", "Current.millis = " + currentMillis1);
//        Log.d("*** DATE_TIME ***", "Current.date = " + formatter.print(currentMillis1));
//        Log.d("*** DATE_TIME ***", "Difference hours = " + ((currentMillis1 - currentMillis) / DateTimeConstants.MILLIS_PER_HOUR));
//
//        time = "2016-04-15T16:18:33.613";
//        //DateTimeFormatter isoFormatter = ISODateTimeFormat.dateTime();
//        String date;
//        SimpleDateFormat simpleDateFormatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
//        SimpleDateFormat simpleDateFormatOut = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
//        try {
//            date = simpleDateFormatOut.format(simpleDateFormatIn.parse(time));
//        } catch (Exception e) {
//            e.printStackTrace();
//            date = "20160501_102511";
//        }
//        Log.d("*** DATE_TIME ***", "date = " + date);


//        Log.d("*** PARSE DATE WITH SIMPLE FORMATTER AND JODA FORMATTER ***");
//        String str = "2016-04-26 15:11:00";
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        Date date = null;
//        try {
//            date = SERVER_DATE_TIME_FORMAT.parse(str);
//            calendar.setTime(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        int dayOfWeek = 0;
//        String dayOfWeekText = null;
//        if (date != null) {
//            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//            dayOfWeekText = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        }
//        System.out.println("millis=" + calendar.getTimeInMillis() + "; dayOfWeek=" + dayOfWeek + "; dayOfWeekText=" + dayOfWeekText);
//
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime localDateTime = formatter.parseLocalDateTime(str);
//        System.out.print("millis=" + localDateTime.toDateTime().getMillis() + "dayOfWeek=" + localDateTime.getDayOfWeek() + "; dayOfWeekText=" + localDateTime.dayOfWeek().getAsText(Locale.getDefault()));

    }

    public static void truncDate() {
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        Date date = new Date();
        String header = dateFormat.format(date);
        pln(fullDateFormat.format(date));
        pln(header);
        pln("headerId=" + date.getTime());

        try {
            Date newDate = dateFormat.parse(header);
            pln(fullDateFormat.format(newDate));
            pln(dateFormat.format(newDate));
            pln("headerId=" + newDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] arr = new String[0];
        pln(String.valueOf(arr.length));
    }

    public static void pln(String s) {
        System.out.println(s);
    }
}
