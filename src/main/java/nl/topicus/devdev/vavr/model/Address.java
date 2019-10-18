package nl.topicus.devdev.vavr.model;

import java.util.Objects;

public class Address
{
	private final String streetname;

	private final int houseNumber;

	public Address(String streetname, int houseNumber)
	{
		this.streetname = streetname;
		this.houseNumber = houseNumber;
	}

	public String getStreetname()
	{
		return streetname;
	}

	public int getHouseNumber()
	{
		return houseNumber;
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return houseNumber == address.houseNumber && streetname.equals(address.streetname);
	}

	@Override public int hashCode()
	{
		return Objects.hash(streetname, houseNumber);
	}
}
