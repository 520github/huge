package me.power.speed.test.springmodule.cache;

public interface IStudentService {
	public String getName() ;
	public String getName(String salution) ;
    
    public void setName(String name) ;
    
    public void changeNameAndNotTellCache(String name);
}
