import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * The view which displays the month and the year
 * @author Aditya Shah
 */
public final class MonthView extends JLabel
{
	private String year;
	private String month;
	private EventCalendar d;
	private JLabel l;

	/**
	 * Parameterized constructor that initializes the values
	 * @param data Data required
	 */
	public MonthView(EventCalendar data) 
	{
		d = data;
		month = data.getMonthName();
		year = String.valueOf(data.get(Calendar.YEAR));
		l = new JLabel();
		drawView();
	}

	/**
	 * Draws the components of this view
	 */
	public void drawView()
	{
		update();
		l.setHorizontalAlignment(JLabel.LEFT);
		add(l);
		setOpaque(true);
		setBackground(Color.WHITE);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(161,20));
		setMaximumSize(new Dimension(161,20));
		setMinimumSize(new Dimension(161,20));
	}

	/**
	 * Update the month and the year when required
	 */
	public void update() 
	{
		String text = " " + d.getMonthName() + " " + String.valueOf(d.get(Calendar.YEAR));
		this.setText(text);
	}
}