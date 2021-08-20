package fr.eni.ENIEncheres.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
    
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3910741962296204885L;
	
	private List<Integer> listErrorCodes;

    public BLLException() {
        super();
        this.listErrorCodes=new ArrayList<>();
    }

    public void addError(int code)
    {
        if(!this.listErrorCodes.contains(code))
        {
            this.listErrorCodes.add(code);
        }
    }

    public boolean hasErrors()
    {
        return this.listErrorCodes.size()>0;
    }

    public List<Integer> getListErrorCodes()
    {
        return this.listErrorCodes;
    }

}