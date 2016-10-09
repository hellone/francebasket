package com.mana.francebasket.model.ffbb;

public class Championnat {

	public String name;
	
    public String id ;
    public String type ;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return "Championnat [Name = "+name+" id= " + id + " type = " + type + "]";
	}
    
}
