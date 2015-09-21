import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The controller which initiates the days and highlights the day
 * @author Aditya Shah 
 */
public final class DayPanel extends JPanel 
{
	private EventCalendar data;
	
	public DayPanel(final EventCalendar data) 
	{
		this.data = data;
		for(int i = 1; i < data.getFirstDayOfWeekInMonth(); i++)  
		{
			JLabel temp = new JLabel(" ");
			temp.setPreferredSize(new Dimension(23,20));
			add(temp);
		}
		for(int i = 1; i < data.getActualMaximum(Calendar.DATE)+1 ; i++) 
		{
			JLabel day = new JLabel();
			day.setMaximumSize(new Dimension(23,20));
			day.setText(String.valueOf(i));
			day.setHorizontalAlignment(JLabel.CENTER);
			day.setCursor(new Cursor(Cursor.HAND_CURSOR));
			day.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					JLabel s = (JLabel) e.getSource();
					data.setDay(Integer.parseInt(s.getText()));
				}
			});
			add(day);
		}
		for(int i = data.getLastDayOfWeekInMonth(); i < 7; i++) 
		{
			JLabel temp = new JLabel(" ");
			temp.setPreferredSize(new Dimension(23,20));
			add(temp);
		}

		highlightDay();
		setOpaque(true);
		setBackground(Color.WHITE);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setPreferredSize(new Dimension(161, data.getActualMaximum(Calendar.WEEK_OF_MONTH) *20 +10));
		setMaximumSize(new Dimension(161, data.getActualMaximum(Calendar.WEEK_OF_MONTH) *20 +10));
		setMinimumSize(new Dimension(161, data.getActualMaximum(Calendar.WEEK_OF_MONTH) *20 +10));
		setLayout(new GridLayout(data.getActualMaximum(Calendar.WEEK_OF_MONTH), 7 ,0,0));
	}
	
	/**
	 * Highlights the selected day 
	 */
	public void highlightDay() 
	{
		Object[] obj = getComponents();
		int pd = data.getpreviousDay();
		if(data.getDay() != 1) 
		{
			JLabel label = (JLabel) obj[pd + data.getFirstDayOfWeekInMonth()-2];
			label.setOpaque(false);
			label.setBackground(null);
			label.setBorder(null);
		}
		int selected = data.getDay();
		int i = selected + data.getFirstDayOfWeekInMonth()-2;
		JLabel temp = (JLabel) obj[i];
		temp.setOpaque(true);
		temp.setBackground(Color.LIGHT_GRAY);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}