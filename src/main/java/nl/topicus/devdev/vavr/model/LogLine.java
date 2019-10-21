package nl.topicus.devdev.vavr.model;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Objects;

public class LogLine
{
	private final String system;

	private final String username;

	private final LocalDateTime date;

	private final String message;

	public LogLine(@Nonnull String system, @Nonnull String username, @Nonnull LocalDateTime date, @Nonnull String message)
	{
		this.system = system;
		this.username = username;
		this.date = date;
		this.message = message;
	}

	public String getSystem()
	{
		return system;
	}

	public String getUsername()
	{
		return username;
	}

	public LocalDateTime getDate()
	{
		return date;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LogLine logLine = (LogLine) o;
		return system.equals(logLine.system) && username.equals(logLine.username) && date.equals(
				logLine.date) && message.equals(logLine.message);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(system, username, date, message);
	}
}
