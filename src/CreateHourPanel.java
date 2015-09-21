import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The controller which handles hour view and left and right clicks
 * @author Aditya Shah
 */
public class CreateHourPanel extends JPanel 
{
	private EventCalendar d;
	private JLabel dayDisplay;
	private JPanel hours;
	
	public CreateHourPanel(final EventCalendar data) 
	{
		d = data;

		//top display
		JPanel topDisplay = new JPanel();
		JButton prevDay = new JButton("<");
		prevDay.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int selectedDay = data.getDay();
				if(selectedDay == 1) 
				{
					data.add(Calendar.MONTH, -1);
					data.setDay(data.getActualMaximum(Calendar.DATE));
				} 
				else 
				{
					data.setDay(data.getDay()-1);
				}
			}
		});

		JButton nextDay = new JButton(">");
		nextDay.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int selectedDay = data.getDay();
				if(selectedDay == data.getActualMaximum(Calendar.DATE)) 
				{
					data.add(Calendar.MONTH, 1);
				} 
				else 
				{
					data.setDay(data.getDay()+1);
				}
			}
		});

		dayDisplay = new JLabel(data.getDayDisplay());
		dayDisplay.setHorizontalAlignment(JLabel.CENTER);
		dayDisplay.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		dayDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		topDisplay.add(dayDisplay);
		topDisplay.add(prevDay);
		topDisplay.add(nextDay);
		topDisplay.setOpaque(true);
		topDisplay.setBackground(Color.WHITE);

		add(topDisplay);

		//hour panel (left side)
		hours = new JPanel();
		hoursDisplay();

		HourLabel hourLabel = new HourLabel();
		final JPanel hourView = new JPanel();
		hourView.setLayout(new FlowLayout(0,0,0));
		hourView.add(hours);
		hourView.add(hourLabel);

		JScrollPane hourScrollPane = new JScrollPane(hourView);

		add(hourScrollPane);

		data.attach(new ChangeListener() 
		{
			@Override
			public void stateChanged(ChangeEvent e) 
			{
				changeContent();
				hourView.repaint();
			}
		});
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));  
		setOpaque(true);
		setBackground(Color.WHITE);
		this.setAlignmentY(JPanel.TOP_ALIGNMENT);
	}
	public void hoursDisplay()
	{
		hours.add(new OneHourPanel("12:00am"));
		hours.add(new OneHourPanel("1:00am"));
		hours.add(new OneHourPanel("2:00am"));
		hours.add(new OneHourPanel("3:00am"));
		hours.add(new OneHourPanel("4:00am"));
		hours.add(new OneHourPanel("5:00am"));
		hours.add(new OneHourPanel("6:00am"));
		hours.add(new OneHourPanel("7:00am"));
		hours.add(new OneHourPanel("8:00am"));
		hours.add(new OneHourPanel("9:00am"));
		hours.add(new OneHourPanel("10:00am"));
		hours.add(new OneHourPanel("11:00am"));
		hours.add(new OneHourPanel("12:00pm"));
		hours.add(new OneHourPanel("1:00pm"));
		hours.add(new OneHourPanel("2:00pm"));
		hours.add(new OneHourPanel("3:00pm"));
		hours.add(new OneHourPanel("4:00pm"));
		hours.add(new OneHourPanel("5:00pm"));
		hours.add(new OneHourPanel("6:00pm"));
		hours.add(new OneHourPanel("7:00pm"));
		hours.add(new OneHourPanel("8:00pm"));
		hours.add(new OneHourPanel("9:00pm"));
		hours.add(new OneHourPanel("10:00pm"));
		hours.add(new OneHourPanel("11:00pm")); 
		hours.setLayout(new BoxLayout(hours,BoxLayout.Y_AXIS));
		add(hours);
	}

	/**
	 * Change date display when a day is selected
	 */
	public void changeContent() 
	{
		dayDisplay.setText(d.getDayDisplay());
	}

	private class OneHourPanel extends JPanel 
	{
		private String time;
		public OneHourPanel(String time) 
		{
			this.time = time;
			JLabel hourLabel = new JLabel(this.time);
			hourLabel.setHorizontalAlignment(JLabel.RIGHT);
			hourLabel.setVerticalAlignment(JLabel.TOP);
			hourLabel.setPreferredSize(new Dimension(65,40));
			hourLabel.setMaximumSize(new Dimension(65,40));
			hourLabel.setMinimumSize(new Dimension(65,40));
			hourLabel.setOpaque(true);
			hourLabel.setBackground(Color.WHITE);
			hourLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));// .createLineBorder(Color.BLACK));

			add(hourLabel);
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			setAlignmentY(JPanel.TOP_ALIGNMENT);
			setPreferredSize(new Dimension(65,40));
		}
	}

	private class HourLabel extends JLabel 
	{
		public HourLabel() 
		{
			setAlignmentX(JPanel.LEFT_ALIGNMENT);
			setOpaque(true);
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setPreferredSize(new Dimension(200,960)); 
		}

		@Override
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			for(int i = 1; i < 24; i++ )
			{
				Line2D line = new Line2D.Double(0, 40*i, 400, 40*i);
				Rectangle2D rect = new Rectangle2D.Double(0, 100, 400,200);
				g2.setPaint(Color.BLACK);
				g2.draw(line);
			}
			ArrayList<Event> events = d.getEvents(d.getEventFormatedDate());
			if(events != null) 
			{
				for(Event e : events)
				{
					long offset = 28800000;
					long fullday = 86400000;
					long trueStartTime = e.getStartTime().getTime() - offset;
					long trueEndTime = e.getEndTime().getTime() - offset;
					double ratioStartTime = (double)trueStartTime/(double)fullday; 
					double ratioEndTime = (double)trueEndTime/(double)fullday; 
					Rectangle2D rect = new Rectangle2D.Double(0, ratioStartTime*960, 400,(ratioEndTime*960) - (ratioStartTime*960));
					g2.setPaint(Color.YELLOW);
					g2.draw(rect);
					g2.fill(rect);
					g2.setPaint(Color.BLACK);
					g2.drawString(e.getInfo(), 0 , (float)(ratioStartTime*960+15.0));
				}
			}
		}
	}
}