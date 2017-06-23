package model.old;

import java.util.ArrayList;

	
public interface Element
{
    public Object invoke(Object... args);

	public String toCode();

}
