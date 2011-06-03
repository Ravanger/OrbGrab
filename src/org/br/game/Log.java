package org.br.game;

import java.util.Calendar;

/**
 * Simple logger TODO - use some logging framework !
 * 
 * @author Boris
 */
public class Log {

	private static final Calendar c = Calendar.getInstance();

	public static void info(Class<?> clazz, String mess) {
		System.out.println("INFO:" + format(clazz.getName(), mess));
	}

	public static void warn(Class<?> clazz, String mess) {
		System.out.println("WARNING:" + format(clazz.getName(), mess));
	}

	public static void error(Class<?> clazz, Throwable ex) {
		ex.printStackTrace();
		System.out.println("ERROR:" + format(clazz.getName(), ex.toString()));
	}

	private static String format(String className, String mess) {
		return String.format("%1$tm %1$te,%1$tY-[%2$s]: %3$s", c.getTime(), className, mess);

	}
}
