import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The view which adds all the GUI components
 * @author Aditya Shah
 */
public class CalendarView extends JFrame 
{
	private EventCalendar d;
	private JPanel panel;
	private MonthView mv;
	private WeekView wv;
	private DayPanel dp;
	private JButton add;

	/**
	 * Default constructor that initializes the components
	 */
	public CalendarView() 
	{
		d = new EventCalendar();
		add= new JButton("ADD EVENT");	
		panel = new JPanel();
		mv = new MonthView(d);
		wv = new WeekView();
		dp = new DayPanel(d);
		create();
	}
	
	/**
	 * Adds all the components to the 
	 * panel and then draws them.
	 */
	public void create()
	{
		add.addActionListener(new EventListener(d));
		CreateHourPanel hp = new CreateHourPanel(d);
		hp.setPreferredSize(new Dimension(290,200));
		panel.add(add);
		panel.add(mv);
		panel.add(wv);
		panel.add(dp);
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setAlignmentY(JPanel.TOP_ALIGNMENT);

		d.attach(new AddListener(panel));
		add(panel);
		add(hp);

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/**
	 * Inner class to add listeners
	 */
	private class AddListener implements ChangeListener 
	{
		private JPanel p;
		
		/**
		 * Default constructor which initializes the panel
		 * @param p Required Panel
		 */
		public AddListener(JPanel p) 
		{
			this.p = p;
		}

		@Override
		public void stateChanged(ChangeEvent e) 
		{
			p.remove(dp);
			dp = new DayPanel(d);
			mv.update();
			p.add(dp);
			p.revalidate();
		}
	}
}