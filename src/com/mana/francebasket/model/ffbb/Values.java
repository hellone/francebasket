package com.mana.francebasket.model.ffbb;

public class Values
{
    private String id;

    private String groupField;

    private String name;
    
    private String type;

    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getGroupField ()
    {
        return groupField;
    }

    public void setGroupField (String groupField)
    {
        this.groupField = groupField;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return " CHAMPIONNAT  ::   [id = "+id+", groupField = "+groupField+", name = "+name +", type = "+type + " ]";
    }
}
