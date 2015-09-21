import java.awt.*;
import javax.swing.*;

/**
 * The View which displays the name of the days of week
 * @author Aditya Shah
 */
public class WeekView extends JPanel
{
	public WeekView() 
	{ 
		add(new Box("S")); //Sunday
		add(new Box("M")); //Monday
		add(new Box("T")); //Tuesday
		add(new Box("W")); //Wednesday
		add(new Box("T")); //Thursday
		add(new Box("F")); //Friday
		add(new Box("Sa")); //Saturday

		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
		setPreferredSize(new Dimension(161, 20));
		setMaximumSize(new Dimension(161, 20));
		setMinimumSize(new Dimension(161, 20));
	}

	/**
	 * An inner class box with a default size
	 */
	private class Box extends JLabel 
	{
		public Box(String text) 
		{
			setText(text);
			setHorizontalAlignment(JLabel.CENTER);
			setPreferredSize(new Dimension(23, 20));
			setBackground(Color.YELLOW);
			setOpaque(true);
		}
	}
}