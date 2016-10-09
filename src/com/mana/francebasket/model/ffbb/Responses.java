package com.mana.francebasket.model.ffbb;

public class Responses {

	private Values[] Values;

	public Values[] getValues ()
	{
		return Values;
	}

	public void setValues (Values[] Values)
	{
		this.Values = Values;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [Values = "+Values+"]";
	}
}
