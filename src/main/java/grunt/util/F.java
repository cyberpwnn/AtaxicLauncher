package grunt.util;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class F
{
	private static NumberFormat NF;
	private static DecimalFormat DF;

	private static final String NAMES[] = new String[] {"Thousand", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Sextillion", "Septillion", "Octillion", "Nonillion", "Decillion", "Undecillion", "Duodecillion", "Tredecillion", "Quattuordecillion", "Quindecillion", "Sexdecillion", "Septendecillion", "Octodecillion", "Novemdecillion", "Vigintillion",};
	private static final BigInteger THOUSAND = BigInteger.valueOf(1000);
	private static final NavigableMap<BigInteger, String> MAP;

	static
	{
		MAP = new TreeMap<BigInteger, String>();
		for(int i = 0; i < NAMES.length; i++)
		{
			MAP.put(THOUSAND.pow(i + 1), NAMES[i]);
		}
	}

	public static String b(int i)
	{
		return b(new BigInteger(String.valueOf(i)));
	}

	public static String b(long i)
	{
		return b(new BigInteger(String.valueOf(i)));
	}

	public static String b(double i)
	{
		return b(new BigInteger(String.valueOf((long) i)));
	}

	public static String b(BigInteger number)
	{
		Entry<BigInteger, String> entry = MAP.floorEntry(number);
		if(entry == null)
		{
			return "Nearly nothing";
		}

		BigInteger key = entry.getKey();
		BigInteger d = key.divide(THOUSAND);
		BigInteger m = number.divide(d);
		float f = m.floatValue() / 1000.0f;
		float rounded = ((int) (f * 100.0)) / 100.0f;

		if(rounded % 1 == 0)
		{
			return ((int) rounded) + " " + entry.getValue();
		}

		return rounded + " " + entry.getValue();
	}

	private static void instantiate()
	{
		if(NF == null)
		{
			NF = NumberFormat.getInstance(Locale.US);
		}
	}

	/**
	 * Calculate a fancy string representation of a file size. Adds a suffix of B,
	 * KB, MB, GB, or TB
	 *
	 * @param s
	 *            the size (in bytes)
	 * @return the string
	 */
	public static String fileSize(long s)
	{
		return ofSize(s, 1000);
	}

	/**
	 * Calculate a fancy string representation of a file size. Adds a suffix of B,
	 * KB, MB, GB, or TB
	 *
	 * @param s
	 *            the size (in bytes)
	 * @return the string
	 */
	public static String memSize(long s)
	{
		return ofSize(s, 1024);
	}

	/**
	 * Get the timestamp of the time t (ms since 1970)
	 *
	 * @param t
	 *            the time
	 * @return the stamp
	 */
	@SuppressWarnings("deprecation")
	public static String stamp(long t)
	{
		Date d = new Date(t);
		return d.getMonth() + "-" + d.getDate() + "-" + (d.getYear() + 1900) + " " + d.getHours() + "h " + d.getMinutes() + "m " + d.getSeconds() + "s ";
	}

	/**
	 * Calculate a fancy string representation of a size in B, KB, MB, GB, or TB
	 * with a special divisor. The divisor decides how much goes up in the suffix
	 * chain.
	 *
	 * @param s
	 *            the size (in bytes)
	 * @param div
	 *            the divisor
	 * @return the string
	 */
	public static String ofSize(long s, int div)
	{
		Double d = (double) s;
		String sub = "Bytes";

		if(d > div - 1)
		{
			d /= div;
			sub = "KB";

			if(d > div - 1)
			{
				d /= div;
				sub = "MB";

				if(d > div - 1)
				{
					d /= div;
					sub = "GB";

					if(d > div - 1)
					{
						d /= div;
						sub = "TB";
					}
				}
			}
		}

		return F.f(d, 0) + " " + sub;
	}

	/**
	 * Trim a string to a length, then append ... at the end if it extends the limit
	 *
	 * @param s
	 *            the string
	 * @param l
	 *            the limit
	 * @return the modified string
	 */
	public static String trim(String s, int l)
	{
		if(s.length() <= l)
		{
			return s;
		}

		return s.substring(0, l) + "...";
	}

	/**
	 * Get a class name into a configuration/filename key For example,
	 * PhantomController.class is converted to phantom-controller
	 *
	 * @param clazz
	 *            the class
	 * @return the string representation
	 */
	public static String cname(String clazz)
	{
		String codeName = "";

		for(Character i : clazz.toCharArray())
		{
			if(Character.isUpperCase(i))
			{
				codeName = codeName + "-" + Character.toLowerCase(i);
			}

			else
			{
				codeName = codeName + i;
			}
		}

		if(codeName.startsWith("-"))
		{
			codeName = codeName.substring(1);
		}

		return codeName;
	}

	/**
	 * Repeat a string
	 *
	 * @param s
	 *            the string
	 * @param n
	 *            the amount of times to repeat
	 * @return the repeated string
	 */
	public static String repeat(String s, int n)
	{
		if(s == null)
		{
			return null;
		}

		final StringBuilder sb = new StringBuilder();

		for(int i = 0; i < n; i++)
		{
			sb.append(s);
		}

		return sb.toString();
	}

	/**
	 * Get a formatted representation of the memory given in megabytes
	 *
	 * @param mb
	 *            the megabytes
	 * @return the string representation with suffixes
	 */
	public static String mem(long mb)
	{
		if(mb < 1024)
		{
			return f(mb) + " MB";
		}

		else
		{
			return f(((double) mb / (double) 1024), 1) + " GB";
		}
	}

	/**
	 * Get a formatted representation of the memory given in kilobytes
	 *
	 * @param mb
	 *            the kilobytes
	 * @return the string representation with suffixes
	 */
	public static String memx(long kb)
	{
		if(kb < 1024)
		{
			return fd(kb, 2) + " KB";
		}

		else
		{
			double mb = kb / 1024.0;

			if(mb < 1024)
			{
				return fd(mb, 2) + " MB";
			}

			else
			{
				double gb = mb / 1024.0;

				return fd(gb, 2) + " GB";
			}
		}
	}

	/**
	 * Format a long. Changes -10334 into -10,334
	 *
	 * @param i
	 *            the number
	 * @return the string representation of the number
	 */
	public static String f(long i)
	{
		instantiate();
		return NF.format(i);
	}

	/**
	 * Format a number. Changes -10334 into -10,334
	 *
	 * @param i
	 *            the number
	 * @return the string representation of the number
	 */
	public static String f(int i)
	{
		instantiate();
		return NF.format(i);
	}

	/**
	 * Formats a double's decimals to a limit
	 *
	 * @param i
	 *            the double
	 * @param p
	 *            the number of decimal places to use
	 * @return the formated string
	 */
	public static String f(double i, int p)
	{
		String form = "#";

		if(p > 0)
		{
			form = form + "." + repeat("#", p);
		}

		DF = new DecimalFormat(form);

		return DF.format(i);
	}

	/**
	 * Formats a double's decimals to a limit, however, this will add zeros to the
	 * decimal places that dont need to be placed down. 2.4343 formatted with 6
	 * decimals gets returned as 2.434300
	 *
	 * @param i
	 *            the double
	 * @param p
	 *            the number of decimal places to use
	 * @return the formated string
	 */
	public static String fd(double i, int p)
	{
		String form = "0";

		if(p > 0)
		{
			form = form + "." + repeat("0", p);
		}

		DF = new DecimalFormat(form);

		return DF.format(i);
	}

	/**
	 * Formats a float's decimals to a limit
	 *
	 * @param i
	 *            the float
	 * @param p
	 *            the number of decimal places to use
	 * @return the formated string
	 */
	public static String f(float i, int p)
	{
		String form = "#";

		if(p > 0)
		{
			form = form + "." + repeat("#", p);
		}

		DF = new DecimalFormat(form);

		return DF.format(i);
	}

	/**
	 * Formats a double's decimals (one decimal point)
	 *
	 * @param i
	 *            the double
	 */
	public static String f(double i)
	{
		return f(i, 1);
	}

	/**
	 * Formats a float's decimals (one decimal point)
	 *
	 * @param i
	 *            the float
	 */
	public static String f(float i)
	{
		return f(i, 1);
	}

	/**
	 * Get a percent representation of a double and decimal places (0.53) would
	 * return 53%
	 *
	 * @param i
	 *            the double
	 * @param p
	 *            the number of decimal points
	 * @return a string
	 */
	public static String pc(double i, int p)
	{
		return f(i * 100.0, p) + "%";
	}

	/**
	 * Get a percent representation of a float and decimal places (0.53) would
	 * return 53%
	 *
	 * @param i
	 *            the float
	 * @param p
	 *            the number of decimal points
	 * @return a string
	 */
	public static String pc(float i, int p)
	{
		return f(i * 100, p) + "%";
	}

	/**
	 * Get a percent representation of a double and zero decimal places (0.53) would
	 * return 53%
	 *
	 * @param i
	 *            the double
	 * @return a string
	 */
	public static String pc(double i)
	{
		return f(i * 100, 0) + "%";
	}

	/**
	 * Get a percent representation of a float and zero decimal places (0.53) would
	 * return 53%
	 *
	 * @param i
	 *            the double
	 * @return a string
	 */
	public static String pc(float i)
	{
		return f(i * 100, 0) + "%";
	}

	/**
	 * Get a percent as the percent of i out of "of" with custom decimal places
	 *
	 * @param i
	 *            the percent out of
	 * @param of
	 *            of of
	 * @param p
	 *            the decimal places
	 * @return the string
	 */
	public static String pc(int i, int of, int p)
	{
		return f(100.0 * (((double) i) / ((double) of)), p) + "%";
	}

	/**
	 * Get a percent as the percent of i out of "of"
	 *
	 * @param i
	 *            the percent out of
	 * @param of
	 *            of of
	 * @return the string
	 */
	public static String pc(int i, int of)
	{
		return pc(i, of, 0);
	}

	/**
	 * Get a percent as the percent of i out of "of" with custom decimal places
	 *
	 * @param i
	 *            the percent out of
	 * @param of
	 *            of of
	 * @param p
	 *            the decimal places
	 * @return the string
	 */
	public static String pc(long i, long of, int p)
	{
		return f(100.0 * (((double) i) / ((double) of)), p) + "%";
	}

	/**
	 * Get a percent as the percent of i out of "of"
	 *
	 * @param i
	 *            the percent out of
	 * @param of
	 *            of of
	 * @return the string
	 */
	public static String pc(long i, long of)
	{
		return pc(i, of, 0);
	}

	/**
	 * Milliseconds to seconds (double)
	 *
	 * @param ms
	 *            the milliseconds
	 * @return a formatted string to milliseconds
	 */
	public static String msSeconds(long ms)
	{
		return f(ms / 1000.0);
	}

	/**
	 * Milliseconds to seconds (double) custom decimals
	 *
	 * @param ms
	 *            the milliseconds
	 * @param p
	 *            number of decimal points
	 * @return a formatted string to milliseconds
	 */
	public static String msSeconds(long ms, int p)
	{
		return f(ms / 1000.0, p);
	}

	/**
	 * nanoseconds to seconds (double)
	 *
	 * @param ms
	 *            the nanoseconds
	 * @return a formatted string to nanoseconds
	 */
	public static String nsMs(long ns)
	{
		return f(ns / 1000000.0);
	}

	/**
	 * nanoseconds to seconds (double) custom decimals
	 *
	 * @param ms
	 *            the nanoseconds
	 * @param p
	 *            number of decimal points
	 * @return a formatted string to nanoseconds
	 */
	public static String nsMs(long ns, int p)
	{
		return f(ns / 1000000.0, p);
	}

	/**
	 * nanoseconds to seconds (double) custom decimals
	 *
	 * @param ms
	 *            the nanoseconds
	 * @param p
	 *            number of decimal points
	 * @return a formatted string to nanoseconds
	 */
	public static String nsMsd(long ns, int p)
	{
		return fd(ns / 1000000.0, p);
	}
}
