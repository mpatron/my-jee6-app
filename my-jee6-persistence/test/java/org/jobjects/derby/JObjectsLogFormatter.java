package org.jobjects.derby;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class JObjectsLogFormatter extends Formatter {
	private static final DateFormat format = new SimpleDateFormat("h:mm:ss");
	
	@Override
	public String format(LogRecord record) {
		String loggerName = record.getLoggerName();
		if(loggerName == null) {
			loggerName = "root";
		}
		StringBuilder output = new StringBuilder()
			.append("[")
			.append(record.getLevel()).append('|')
			.append(Thread.currentThread().getName()).append('|')
			.append(format.format(new Date(record.getMillis())))
			.append("]: ")
			.append(loggerName)
			.append(" : ")
			.append(record.getMessage());
		
		if(record.getThrown()!=null) {
			output.append(SystemUtils.LINE_SEPARATOR);
			output.append(ExceptionUtils.getStackTrace(record.getThrown()));
		}
		
		output.append(SystemUtils.LINE_SEPARATOR);
		return output.toString();
	}

}
