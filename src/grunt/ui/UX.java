package grunt.ui;

import java.awt.Color;

public class UX
{
	public static Color c = Color.ORANGE;
	public static boolean rgb;
	public static double rq = 0;

	public static void c(Color hsbColor)
	{
		if(rgb)
		{
			rq += 0.005;
			c = Color.getHSBColor((float) ((Math.sin(rq / 10D) + 1D) / 2), 1, 1).brighter().brighter().brighter().brighter();
			return;
		}

		c = new Color(244, 66, 66);
	}
}
