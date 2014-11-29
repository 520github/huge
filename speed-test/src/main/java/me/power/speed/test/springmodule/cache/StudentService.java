package me.power.speed.test.springmodule.cache;

public class StudentService {
     private String name = "matthew";
     
     public String getName() {
         return name;
     }
     
     public String getName(String salution) {
         return salution + " " + name;
     }
     
     public void setName(String name) {
         this.name = name;
     }
     
     public void changeNameAndNotTellCache(String name) {
         this.name = name;
     }
}
