import com.CalendarDateFormatter;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

// Subtask 2. Continued.
public class CalendarDateFormatTest
{
    @Test
    public void formatDateTest1() throws ParseException
    {
        Date actual = CalendarDateFormatter.formatToDate("2021.11.10 15:32");
        Date expected = new Date(2021 - 1900, Calendar.NOVEMBER, 10, 15, 32);
        assertEquals(expected, actual);
    }

    @Test
    public void formatDateTest2() throws ParseException
    {
        Date actual = CalendarDateFormatter.formatToDate("1970.1.11 02:05");
        Date expected = new Date(1970 - 1900, Calendar.JANUARY, 11, 2, 5);
        assertEquals(expected, actual);
    }

    @Test(expected = ParseException.class)
    public void formatDateTest3() throws ParseException
    {
        Date actual = CalendarDateFormatter.formatToDate("2021,11,10 15:32");
        Date expected = new Date(2021 - 1900, Calendar.DECEMBER, 10, 15, 32);
        assertEquals(expected, actual);
    }

    @Test
    public void formatCalendarTest1() throws ParseException
    {
        Calendar actual = CalendarDateFormatter.formatToCalendar("2021.11.10 15:32");
        Calendar expected = Calendar.getInstance();

        //Moment of time (2021.11.10 15:32:00.0) converted to milliseconds and placed below
        expected.setTimeInMillis(1636547520000L);
        assertEquals(expected, actual);
    }

    @Test
    public void formatCalendarTest2() throws ParseException
    {
        Calendar actual = CalendarDateFormatter.formatToCalendar("1970.1.11 02:05");
        Calendar expected = Calendar.getInstance();

        //Moment of time (100.01.11 02:05:00.0) converted to milliseconds and placed below
        expected.setTimeInMillis(860700000L);
        assertEquals(expected, actual);
    }

    @Test(expected = ParseException.class)
    public void formatCalendarTest3() throws ParseException
    {
        Calendar actual = CalendarDateFormatter.formatToCalendar("2021,11,10 15:32");
        Calendar expected = Calendar.getInstance();

        //Look up to formatCalendarTest1()
        expected.setTimeInMillis(1636547520000L);
        assertEquals(expected, actual);
    }
}