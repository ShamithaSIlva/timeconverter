package com.example.formatter;

import java.util.regex.Pattern;

public class Formatter
{
	public String convertString(String timeString) 
	{		
		StringBuilder processed = new StringBuilder();
		processed.append( "" );
		if(isValidString( timeString ))
		{
			String spaceSplit [] = timeString.split( " " );
			String matchTag = spaceSplit[0];
			String [] timeSplits = spaceSplit[1].split( ":" );
			String [] secondsNmills = timeSplits[1].split( "\\." );
			int minutes = Integer.valueOf(timeSplits[0]);
			int seconds = Integer.valueOf(secondsNmills[0]);
			int milliseconds = Integer.valueOf(secondsNmills[1]);
			if(MatchTimeTag.PRE_MATCH.getTag().equals( matchTag ))
			{
				processed.append( "00:00 – " ).append( MatchTimeTag.PRE_MATCH.toString() );
			}
			else if(MatchTimeTag.FIRST_HALF.getTag().equals( matchTag ))
			{
				if(minutes >= 45 && (seconds > 0 | milliseconds > 0))
				{
					// first half additional time
					processed.append( "45:00 +" ).append( String.format("%02d", minutes-45) ).append( ":" ).append( String.format("%02d", milliseconds >= 500? seconds+1:seconds) ).append( " – FIRST_HALF" );
				}
				else if(minutes <= 45)
				{
					processed.append( String.format("%02d", minutes) ).append( ":" ).append( String.format("%02d", milliseconds >= 500? seconds+1:seconds) ).append( " – FIRST_HALF" );
				}				
			}
			else if(MatchTimeTag.HALF_TIME.getTag().equals( matchTag ))
			{
				processed.append( "45:00 – HALF_TIME" );
			}
			else if(MatchTimeTag.SECOND_HALF.getTag().equals( matchTag ))
			{				
				if(minutes >= 90 && (seconds > 0 | milliseconds > 0))
				{
					// second half additional time
					processed.append( "90:00 +" ).append( String.format("%02d", minutes-90) ).append( ":" ).append( String.format("%02d", milliseconds >= 500? seconds+1:seconds) ).append( " – SECOND_HALF" );
				}
				else if(minutes <= 90)
				{
					processed.append( String.format("%02d", minutes) ).append( ":" ).append( String.format("%02d", milliseconds >= 500? seconds+1:seconds) ).append( " – SECOND_HALF" );
				}				
			}
			else if(MatchTimeTag.FULL_TIME.getTag().equals( matchTag ))
			{
				processed.append( "90:00 +00:00 – FULL_TIME" );
			}
		}
		return processed.toString();
	}
	
	public boolean isValidString(String timeString)
	{
		Pattern p = Pattern.compile("^\\[(PM|H1|HT|H2|FT)\\]\\s(\\d{1}|[1-8][1-9]|9[0]):[0-5][0-9].\\d{3}$");
		return p.matcher(timeString).matches();  
	}
}
