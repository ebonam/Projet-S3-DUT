package model.old;

import java.util.ArrayList;

public abstract class OperateurArithmetique
{
    public abstract Object invoke(Variable arg1, Variable arg2);
    public abstract String toCode();
}
