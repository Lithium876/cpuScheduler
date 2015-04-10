import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler{
 
    int timer;
    
    public Scheduler(){
       timer = 0;
    }
    
    public void increaseTime(int add){
       timer = timer + add;
    }
    
    public int getTime(){
        return timer;
    }
    
    public void setTime(int time){
        timer = time;
    }

    public static ArrayList pcbGenerator(){
    
        ArrayList<PCB> pcbList = new ArrayList<PCB>();
        String processName = "";
    
        for (int i = 1; i < 6; i++){
             processName = "process" + i;
             PCB processCtrlBlk = new PCB(processName);
             pcbList.add(processCtrlBlk);
        }
        return pcbList;
    }
    
    public PCB schedule(PCB pcb){
        String string = "scheduled";
        pcb.setState(string);
        //System.out.print(pcb.getProcessName() + " has been scheduled.\n");
        return pcb;
     }
     
     public PCB wait(PCB pcb){
        String string = "waiting";
        pcb.setState(string);
        //System.out.print(pcb.getProcessName() + " is waiting to execute.\n");
        return pcb;
     }
     
     public PCB execute(PCB pcb){
        int expected = pcb.getExpBurstTime();
        int actual = 0;
        String string = "executing";
        pcb.setState(string);
        //System.out.print(pcb.getProcessName() + " is executing.\n");
        while (expected != 0){
            actual = actual + 1;
            expected = expected - 1;
        }
        timer = timer + actual;
        pcb.setActBurstTime(timer);
        //System.out.print(pcb.getProcessName() + " has executed successfully.\n");
        //string = "terminated";
        //pcb.setState(string);
        
        return pcb;
        
     }
     
     public PCB execute(PCB pcb, int quantum){
        int expected = pcb.getExpBurstTime();
        int quan = quantum;
        String string = "executing";
        pcb.setState(string);
        if (expected > quantum){
           while (quan != 0){
              expected = expected - 1;
              quan = quan - 1;
              timer = timer + 1;
           }
        }else{
           while (expected != 0){
              expected = expected - 1;
              quan = quan - 1;
              timer = timer + 1;
           }
        }
        pcb.setExpBurstTime(expected);
        pcb.setActBurstTime(timer);
        return pcb;
     }
     
     public PCB terminate(PCB pcb){
        String string = "terminated";
        pcb.setState(string);
        return pcb;
     }
     
     //FIFO algorithm
     public static ProcessQueue FIFO(ProcessQueue processQueue){
       Scheduler scheduler = new Scheduler();
       PCB temp;
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.schedule(temp);
           processQueue.setProcessSlot(i, temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           
       }
       
       System.out.println("Queue after processes placed in schedule order:\n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.wait(temp);
           processQueue.setProcessSlot(i, temp);
           //System.out.println("Updated Queue: \n");
           //processQueue.print();
       }
       
       System.out.println("Queue after all processes move into waiting state: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.execute(temp);
           processQueue.setProcessSlot(i, temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           temp = scheduler.terminate(temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           processQueue.setProcessSlot(i, temp);

       }
      
       System.out.println("Queue after each process has executed: \n");
       processQueue.print();
       return processQueue;
    }
    
    //SJF algorithm
    public static ProcessQueue SJF(ProcessQueue processQueue){
       PCB temp;
       int iMin;
       int iTemp;
       ArrayList<PCB> array;
       Scheduler scheduler = new Scheduler();
       
       for(int j = 0; j < 5; j++){
          iMin = j;
          iTemp = j;
          iMin = processQueue.compareBurst(iTemp,iMin);
          for(int i = j+1; i < 4; i++){
             iTemp = i;
             iMin = processQueue.compareBurst(iTemp,iMin);
          }
          if (iMin != j){
             processQueue.swap(iMin,j);
          }
       }
       
       System.out.print("Process queue in scheduled order: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
          temp = processQueue.getProcess(i);
          temp = scheduler.schedule(temp);
          processQueue.setProcessSlot(i, temp);
          System.out.println("Updated Queue: \n");
          processQueue.print();
       }
       
       System.out.println("Queue after scheduling: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.wait(temp);
           processQueue.setProcessSlot(i, temp);
           //System.out.println("Updated Queue: \n");
           //processQueue.print();
       }
       
       System.out.println("Queue after all processes move into waiting state: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.execute(temp);
           processQueue.setProcessSlot(i, temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           temp = scheduler.terminate(temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           processQueue.setProcessSlot(i, temp);

       }
      
       System.out.println("Queue after each process has executed: \n");
       processQueue.print();
      
       return processQueue;
   }
   
   public static ProcessQueue prioritySched(ProcessQueue processQueue){
       PCB temp;
       int iMax;
       int iTemp;
       ArrayList<PCB> array;
       Scheduler scheduler = new Scheduler();
       
       for(int j = 0; j < 5; j++){
          iMax = j;
          iTemp = j;
          iMax = processQueue.comparePriority(iTemp,iMax);
          for(int i = j+1; i < 4; i++){
             iTemp = i;
             iMax = processQueue.comparePriority(iTemp,iMax);
          }
          if (iMax != j){
             processQueue.swap(iMax,j);
          }
       }
       
       System.out.print("Process queue in scheduled order: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
          temp = processQueue.getProcess(i);
          temp = scheduler.schedule(temp);
          processQueue.setProcessSlot(i, temp);
          System.out.println("Updated Queue: \n");
          processQueue.print();
       }
       
       System.out.println("Queue after scheduling: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.wait(temp);
           processQueue.setProcessSlot(i, temp);
           //System.out.println("Updated Queue: \n");
           //processQueue.print();
       }
       
       System.out.println("Queue after all processes move into waiting state: \n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.execute(temp);
           processQueue.setProcessSlot(i, temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           temp = scheduler.terminate(temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           processQueue.setProcessSlot(i, temp);

       }
      
       System.out.println("Queue after each process has executed: \n");
       processQueue.print();
       
       return processQueue;
   }
   
   public static ProcessQueue roundRobin(ProcessQueue processQueue){
       Scanner in = new Scanner(System.in);
       Scheduler scheduler = new Scheduler();
       int quantum;
       PCB temp;
       int done = 0;
       
       System.out.println("Choose quantum (between 1 and 25): ");
       quantum = in.nextInt();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.schedule(temp);
           processQueue.setProcessSlot(i, temp);
           System.out.println("Updated Queue: \n");
           processQueue.print();
           
       }
       
       System.out.println("Queue after processes placed in schedule order:\n");
       processQueue.print();
       
       for(int i = 0; i < 5; i++){
           temp = processQueue.getProcess(i);
           temp = scheduler.wait(temp);
           processQueue.setProcessSlot(i, temp);
           //System.out.println("Updated Queue: \n");
           //processQueue.print();
       }
       
       System.out.println("Queue after all processes move into waiting state: \n");
       processQueue.print();
       

       while(processQueue.hasWorkLeft() == true){
          for(int i = 0; i < 5; i++){
              temp = processQueue.getProcess(i);
              if (temp.getState() != "terminated"){
                  temp = scheduler.execute(temp, quantum);
                  processQueue.setProcessSlot(i, temp);
                  System.out.println("Updated Queue: \n");
                  processQueue.print();
                  if (temp.getExpBurstTime() == 0){
                      temp = scheduler.terminate(temp);
                      System.out.println("Updated Queue: \n");
                      processQueue.print();
                      processQueue.setProcessSlot(i, temp);
                  }
                  if (temp.getExpBurstTime() > 0){
                      temp = scheduler.wait(temp);
                      processQueue.setProcessSlot(i, temp);
                      System.out.println("Updated Queue: \n");
                      processQueue.print();
                  }
               }
            }
         }
         
         return processQueue;
      } 

             
   
   
    public static void main(String[] args){
     
          //Generates 5 process control blocks (processes)
          ProcessQueue processQueue = new ProcessQueue();
          PCB firstPCB = new PCB();
        
          //Print initial queue
          System.out.print("Initial process queue:\n");
          for(int i = 0; i < processQueue.getSize(); i++){
              PCB pcb = processQueue.getProcess(i);
              pcb.printPCB();
          }
          
          //ask user's input on which algorithm to use
          Scanner in = new Scanner(System.in);
          int input;
          String algorithm = "";
          
          
          System.out.println("Choose an algorithm (type corresponding number and hit enter):");
          System.out.println("1. FIFO | 2. SJF  | 3. Priority Scheduling | 4. Round-Robin");
          input = in.nextInt();
          if (input == 1){
             algorithm = "FIFO";
          }if (input == 2){
             algorithm = "SJF";
          }if (input == 3){
             algorithm = "Priority Scheduling";
          }if (input == 4){
             algorithm = "Round-Robin";
          }

          System.out.println("You chose " + algorithm);
          
          
          switch (input) {
             case 1: processQueue = FIFO(processQueue);
                     break;
             case 2: processQueue = SJF(processQueue);
                     break;
             case 3: processQueue = prioritySched(processQueue);
                     break;
             case 4: processQueue = roundRobin(processQueue);
                     break;
          }
          System.out.println("Final process queue: \n");
          processQueue.print();
         
   }
          
    
}