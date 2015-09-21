import java.text.*;
import java.util.*;

/**
 * Event class that has all the properties of an event
 * @author Aditya Shah
 */
public class Event 
{
	private String name;
	private SimpleDateFormat format;
	private Date startTime;
	private Date endTime;

	/**
	 * A Constructor that initializes the properties of an event
	 * @param title Name of the event
	 * @param start Start time of the event
	 * @param end End time of the event
	 */
	public Event(String title, String start, String end) throws ParseException 
	{
		name = title;
		this.format = new SimpleDateFormat("hh:mmaa");
		startTime = format.parse(start);
		endTime = format.parse(end);
	}

	/**
	 * Gets the name of the event
	 * @return String Name of the event
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Gets the start time of an event
	 * @return Date Start time of an event
	 */
	public Date getStartTime() 
	{
		return startTime;
	}

	/**
	 * Gets the end time of an event
	 * @return Date End time of an event
	 */
	public Date getEndTime() 
	{
		return endTime;
	}

	/**
	 * Gets all the information of the event
	 * @return String The event information
	 */
	public String getInfo() 
	{
		return name + " " + format.format(startTime) + " - " + format.format(endTime);
	}

	/**
	 * Checks if two events conflict
	 * @param e event to compare with
	 * @return Boolean A true value if two events conflict else a false value
	 */
	public boolean conflicts(Event e) 
	{
		Date start = e.getStartTime();
		Date end = e.getEndTime();
		for(long i = start.getTime(); i < end.getTime(); i+=300000)
		{
			if(i > this.startTime.getTime() && i < this.endTime.getTime())
			{ 
				return true;
			}
		}
		return false;
	}
}