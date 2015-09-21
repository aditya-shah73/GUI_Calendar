import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * The frame that pops up when we create an event
 * @author Aditya Shah
 */
public class CreateEventPanel extends JPanel 
{
	private EventCalendar d;
	private ArrayList<String> input;
	private JTextField name;
	private JLabel j;
	private TimeInput input2;

	/**
	 * Parameterized constructor that helps to display the event
	 * @param data The data passed
	 */
	public CreateEventPanel(EventCalendar data) 
	{
		d = data;
		input = new ArrayList<String>();
		input2 = new TimeInput();
		j = new JLabel("Event Name: ");
		name = new JTextField("",14);
		drawFrame();
	}

	/**
	 * Draws the frame
	 */
	public void drawFrame()
	{
		add(j);
		add(name);
		add(input2);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	}

	/**
	 * Return an ArrayList of user inputs
	 * @return ArrayList<String> The user inputs
	 */
	public ArrayList<String> getInputs() 
	{
		input.add(name.getText());
		input.addAll(input2.getInputs());
		return input; 
	}

	/**
	 * Inner Class which draws the GUI components
	 *
	 */
	private class TimeInput extends JPanel 
	{
		ArrayList<String> inputs;
		private String today;
		private JTextField startTime;
		private JTextField endTime;
		private SimpleDateFormat format;
		private JTextField day;
		private JLabel start;
		private JLabel end;

		/**
		 * Default Constructor that initializes the GUI components
		 */
		public TimeInput() 
		{
			format = new SimpleDateFormat("MM/dd/YY");
			day = new JTextField(today);
			start = new JLabel("Start: ");
			startTime= new JTextField("",8);
			end = new JLabel("End: ");
			endTime= new JTextField("",8);
			drawPanel();	
		}

		/**
		 * Draws the panel
		 */
		public void drawPanel()
		{
			today = format.format(d.getTime());
			day.setEditable(false);
			start.setHorizontalAlignment(JLabel.RIGHT);
			end.setHorizontalAlignment(JLabel.RIGHT);
			add(day);
			add(start);
			add(startTime);
			add(end);
			add(endTime);
			setLayout(new FlowLayout());
			setAlignmentX(JLabel.LEFT_ALIGNMENT);
		}

		/**
		 * Return an ArrayList of user inputs
		 * @return ArrayList<String> The user inputs
		 */
		public ArrayList<String> getInputs() 
		{
			inputs = new ArrayList<String>();
			inputs.add(today);
			inputs.add(startTime.getText());
			inputs.add(endTime.getText());
			return inputs;
		}
	}
}