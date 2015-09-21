import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The controller which handles events and handles the conflicts
 * @author Aditya Shah
 */
public class EventListener implements ActionListener 
{
	private EventCalendar d;
	private CreateEventPanel p;
	private ArrayList<String> input;

	/**
	 * Parameterized constructor which takes in the data
	 * @param data
	 */
	public EventListener(EventCalendar data) 
	{
		d = data;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		p = new CreateEventPanel(d);
		int i = JOptionPane.showOptionDialog(null, p, "Create Event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null); 
		if(i != 0) //Cancel Button
		{ 
			p = new CreateEventPanel(d);
		} 
		else //OK Button
		{ 
			boolean c = false;
			try 
			{
				input = p.getInputs(); 
				String name = input.get(0);
				String k = input.get(1);
				String startTime = input.get(2);
				String endTime = input.get(3);
				Event event = new Event(name, startTime, endTime);
				
				if(d.hasKey(k)) 
				{ 
					for(Event e1 : d.getEvents(k)) 
					{
						if(event.conflicts(e1) && !c) 
						{
							JOptionPane.showMessageDialog(null, "Error!! There is a time conflict" + " with an existing event, please create a different event" + " with a different time.");
							c = true;
						}
					}
				}
				if(!c) 
				{  
					d.addEvent(k, event);
				}
			} 
			catch (Exception exception) 
			{
				System.out.println("Error!! No event has been entered!!");
			}
		}
	} 
}