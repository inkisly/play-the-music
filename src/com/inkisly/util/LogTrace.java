package com.inkisly.util;

import android.util.Log;

public class LogTrace
{
	private final static String PREFIX = "[MyMusicPlayer]";
	private static boolean m_isEnabled = true;

	public static void enable( boolean isEnable )
	{
		m_isEnabled = isEnable;
	}

	public static void e( String strMsg )
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.e( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg );
		}
	}
	
	public static void e( String strMsg ,Throwable tr)
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.e( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg, tr );
		}
	}

	public static void w( String strMsg )
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.w( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg );
		}
	}

	public static void i( String strMsg )
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.i( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg );
		}
	}

	public static void d( String strMsg )
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.d( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg );
		}
	}
	
	public static void v( String strMsg )
	{
		if ( m_isEnabled )
		{
			Exception e = new Exception();
			StackTraceElement callerElement = e.getStackTrace()[1];
			Log.v( PREFIX, "[" + callerElement.getFileName() + " "
					+ callerElement.getLineNumber() + "] " + strMsg );
		}
	}
}
