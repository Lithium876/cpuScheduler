import java.util.Random;

//Process Control Block


public class PCB {

     public String processName;
     public int priority;
     public int expBurstTime;
     public int actBurstTime;
     public String state;
     
     public PCB(){
     
         processName = "";
         priority = 0;
         expBurstTime = 0;
         actBurstTime = 0;
         state = "created";
     }
     
     public PCB(String name){
         processName = name;
         priority = randInt(1,5);
         expBurstTime = randInt(1, 50);
         actBurstTime = 0;
         state = "created";
     }
     
     public PCB(PCB pcb){
         processName = pcb.getProcessName();
         priority = pcb.getPriority();
         expBurstTime = pcb.getExpBurstTime();
         actBurstTime = pcb.getActBurstTime();
         state = pcb.getState();
     }
     
     public PCB(String name, int priority,
                 int burstTime){
     
         processName = name;
         priority = priority;
         expBurstTime = burstTime;
         actBurstTime = 0;
         state = "created";
     
     } 
     
     public String getProcessName(){
         return processName;
     }
     
     public int getPriority(){
         return priority;
     }
     
     public int getExpBurstTime(){
         return expBurstTime;
     }
     
     public int getActBurstTime(){
         return actBurstTime;
     }
     
     public String getState(){
         return state;
     }
     
     public void setProcessName(String processName){
         processName = processName;
     }
     
     public void setPriority(int priority){
         priority = priority;
     }
     
     public void setExpBurstTime(int bTime){
         expBurstTime = bTime;
     }
     
     public void reduceExpBurstTime(int time){
         expBurstTime = expBurstTime - time;
         if (expBurstTime < 0){
            expBurstTime = 0;
         }
     }
     
     public void setActBurstTime(int bTime){
         actBurstTime = bTime;
     }
     
     public void setState(String state1){
         state = state1;
     }
     
     public void printPCB(){
         System.out.print("Process Name: " + processName + "\n");
         System.out.print("Process Priority: " + priority + "\n");
         System.out.print("Expected Burst Time: " + expBurstTime + "\n");
         System.out.print("Actual Burst Time: " + actBurstTime + "\n");
         System.out.print("Process State: " + state + "\n\n");
     }
     
     public static int randInt(int min, int max){

        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
     }
     
     
      
}

