package model.old;

import java.util.ArrayList;

public abstract class OperateurBoolean
{
    public abstract boolean invoke(Variable arg1, Variable arg2);
    public abstract String toCode();
}
