import java.text.*;
import java.util.*;
import javax.swing.event.*;

/**
 * The model class which handles the calendar
 * @author Aditya Shah
 */
public class EventCalendar extends GregorianCalendar
{
    private ArrayList<ChangeListener> listener;
    private HashMap<String,ArrayList<Event>> event;
    private SimpleDateFormat month;
    private SimpleDateFormat day;
    private SimpleDateFormat date;
    private int Day; 
    private int previousDay;
    
    /**
     * Default constructor which initializes the data structure
     */
    public EventCalendar() 
    {
    	month = new SimpleDateFormat("MMMM");
        day = new SimpleDateFormat("EEEE M/d");
        date = new SimpleDateFormat("MM/dd/YY");
    	listener = new ArrayList();
        event = new HashMap<>();
        Day = super.get(Calendar.DATE);
        previousDay = Day;
    }
           
    /**
     * Returns the selected day
     * @return int selected day
     */
    public int getDay() 
    {
        return Day;
    }
    
    /**
     * Returns the previously selected day
     * @return int previous day
     */
    public int getpreviousDay() 
    {
        return previousDay;
    }
    
    /**
     * Returns the current month 
     * @return String Current month
     */
    public String getMonthName() 
    {
        return month.format(super.getTime());
    }
    
    /**
     * Returns the first day of the week in the current month
     * @return int First day of the week
     */
    public int getFirstDayOfWeekInMonth() 
    {
        Date original = super.getTime();
        super.set(Calendar.DATE, 1);
        int f = super.get(Calendar.DAY_OF_WEEK);
        super.setTime(original);
        return f;
    }
    
    /**
     * Returns the last day of the week in the current month
     * @return int Last day of the week
     */
    public int getLastDayOfWeekInMonth() 
    {
        Date original = super.getTime();
        super.set(Calendar.DATE, super.getActualMaximum(Calendar.DATE));
        int l = super.get(Calendar.DAY_OF_WEEK);
        super.setTime(original);
        return l;
    }
    
    /**
     * Returns ArrayList of event
     * @param key Formated date of the event
     * @return ArrayList<> The list of events
     */
    public ArrayList<Event> getEvents(String key) 
    {
        return event.get(key);
    }
    
    /**
     * Returns the formatted date of the event
     * @return String Date of the event
     */
    public String getEventFormatedDate() 
    {
        Date temp = super.getTime();
        return date.format(temp);
    }
    
    /**
     * The display for hour view 
     * @return String hour view
     */ 
    public String getDayDisplay() 
    {
        Date original = super.getTime();
        super.set(Calendar.DAY_OF_MONTH,Day);
        Date date = super.getTime();
        super.setTime(original);
        return day.format(date);
    }
    
    @Override
    public void add(int field, int amount) 
    {
        if(field == Calendar.MONTH) 
        {
            super.add(field,amount);
            setDay(1);
        } 
        else 
        {
            super.add(field, amount);
            stateChanged(new ChangeEvent(this));
        }
    }
    
    public void attach(ChangeListener c) 
    {
        listener.add(c);
        stateChanged(new ChangeEvent(this));
    }
    
    /**
     * Checks if the key exist
     * @param key Formated date of the event 
     * @return boolean Ture if key exists else false
     */
    public boolean hasKey(String key) 
    {
        return event.containsKey(key);
    }
    
    /**
     * Set the selected day 
     * @param i Day to be changed
     */
    public void setDay(int i) 
    {
        previousDay = Day;
        Day = i;
        super.set(Calendar.DAY_OF_MONTH, i);
        stateChanged(new ChangeEvent(this));
    }
    
    /**
     * Add an event corresponding to a key
     * @param key The date
     * @param e The event
     */
    public void addEvent(String key,Event e) 
    {
        ArrayList<Event> temp = (event.get(key) != null)? event.get(key) : new ArrayList();
        temp.add(e);
        event.put(key,temp);
        stateChanged(new ChangeEvent(this));
    }
    
    private void stateChanged(ChangeEvent e) 
    {
        for(ChangeListener c : listener ) 
        {
            c.stateChanged(e);
        }
    }
}