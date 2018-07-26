package grunt.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import grunt.Client;

public class CircularProgressBar extends JPanel
{
	private static final long serialVersionUID = 8657006248665406571L;
	private final static int MAX_PROGRESS_AMOUNT = 100;
	private double prgValue = 0;
	private double lvalue = 0;
	private double dist = 0;
	private double vv = 0;
	private double mdist = 0;
	private double tangle = 0;

	public CircularProgressBar()
	{
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 0));
		new Thread()
		{
			@Override
			public void run()
			{
				while(!Thread.interrupted())
				{
					if(!isVisible())
					{
						try
						{
							Thread.sleep(Client.config.getBoolean("throttle-launcher") ? 15 : 6);

							continue;
						}

						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}

					repaint();

					try
					{
						if(lvalue > prgValue)
						{
							lvalue -= (lvalue - prgValue) / 260;
							dist = Math.abs(lvalue - prgValue);
						}

						if(lvalue < prgValue)
						{
							lvalue += (prgValue - lvalue) / 240;
							dist = Math.abs(prgValue - lvalue);
						}

						vv = 0.1445 * (dist * 11) + 0.00;

						if(mdist < vv)
						{
							mdist = vv;
						}

						tangle += vv;

						if(tangle > 360)
						{
							tangle = 0;
						}

						Thread.sleep(Client.config.getBoolean("throttle-launcher") ? 15 : 6);
					}

					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 0));
		g2.setColor(new Color(0, 0, 0));
		g2.clearRect(0, 0, getWidth(), getHeight());
		g2.setColor(new Color(0, 0, 0));
		g2.drawRect(0, 0, getWidth(), getHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(0, 0, 0));

		try
		{
			g2.setStroke(new BasicStroke(1000f + (float) ((dist) * 3.8)));
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		g.drawArc(10, 10, getWidth() - 20, getHeight() - 20, 90, 360);

		if(prgValue <= MAX_PROGRESS_AMOUNT)
		{
			int angle = -(int) (((float) (lvalue) / MAX_PROGRESS_AMOUNT) * 360);
			UX.c(Color.getHSBColor((float) (0.25 + (float) (angle / (double) 180)), 1, 1));
			Color c = Color.getHSBColor((float) (vv / mdist), 1, 1).brighter().brighter().brighter().brighter();

			g.setColor(c);

			try
			{
				g2.setStroke(new BasicStroke(2f + (float) ((dist * 2.4))));
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

			g.drawArc(10, 10, getWidth() - 20, getHeight() - 20, (int) tangle, angle);
		}
	}

	public void setProgress(int pct)
	{
		prgValue = pct;
	}
}