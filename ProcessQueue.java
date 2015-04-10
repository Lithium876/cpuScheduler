import java.util.ArrayList;

public class ProcessQueue{

    ArrayList<PCB> processQueue = new ArrayList<PCB>();
    
    public ProcessQueue(){
    
        String processName = "";
    
        for (int i = 1; i < 6; i++){
             processName = "process" + i;
             PCB processCtrlBlk = new PCB(processName);
             processQueue.add(processCtrlBlk);
        }

    }
    
    public ProcessQueue(ArrayList<PCB> array){
       PCB temp;
       processQueue.clear();
       for (int i = 0; i < array.size(); i++){
           temp = array.get(i);
           processQueue.add(temp);
       }
    }
       
    
    public void add(PCB pcb){
        processQueue.add(pcb);
    }
    
    public void add(int index, PCB pcb){
        processQueue.add(index, pcb);
    }
    
    public void remove(int index){
        processQueue.remove(index);
    }
    
    public PCB getProcess(int index){
        PCB pcb = new PCB();
        pcb = processQueue.get(index);
        return pcb;
    }
    
    public PCB setProcessSlot(int index, PCB pcb){
        PCB pcb1 = new PCB();
        pcb1 = processQueue.get(index);
        processQueue.set(index, pcb1);
        return pcb1; //old PCB that has been replaced (so PCB isn't lost)
    }
    
    public int getSize(){
        int size = processQueue.size();
        return size;
    }
    
    public void print(){
        for(int i = 0; i < 5; i++){
            PCB pcb = new PCB(processQueue.get(i));
            pcb.printPCB();
        }
    }
    
    public boolean test(){
        boolean success = true;
        PCB temp;
        while (success == true){
            for(int i = 0; i < 5; i++){
               temp = processQueue.get(i);
               if (temp.getState() == "terminated"){
                   success = true;
               }else{
                  success = false;
               }
            }
        }
        return success;
     }
     
     public void newArray(ArrayList<PCB> array){
        PCB temp;
        for(int i = 0; i < processQueue.size(); i++){
            processQueue.remove(i);
            temp = array.get(i);
            processQueue.add(i,temp);
        }
     }
     
     public void swap(int i, int j){
        PCB one;
        PCB two;
        PCB temp;
        one = getProcess(i);
        two = getProcess(j);
        processQueue.remove(i);
        processQueue.remove(j);
        temp = one;
        one = two;
        two = temp;
        processQueue.add(i, one);
        processQueue.add(j, two);
     }
     
     public int compareBurst(int index1, int index2){
        PCB pcb1;
        PCB pcb2;
        int burst1;
        int burst2;
        pcb1 = processQueue.get(index1);
        burst1 = pcb1.getExpBurstTime();
        pcb2 = processQueue.get(index2);
        burst2 = pcb2.getExpBurstTime();
        if (burst1 < burst2){
           return index1;
        }
        else{
           return index2;
        }
     }
     
     public int comparePriority(int index1, int index2){
        PCB pcb1;
        PCB pcb2;
        int priority1;
        int priority2;
        int burst1;
        int burst2;
        int index;
        pcb1 = processQueue.get(index1);
        priority1 = pcb1.getPriority();
        burst1 = pcb1.getExpBurstTime();
        pcb2 = processQueue.get(index2);
        priority2 = pcb2.getPriority();
        burst2 = pcb2.getExpBurstTime();
        if (priority1 > priority2){
           return index1;
        }
        if (priority1 < priority2){
           return index2;
        }
        else{
           index = compareBurst(index1, index2);
           return index;
        }
     }
     
     public boolean hasWorkLeft(){
        int work = 0;
        PCB pcb;
        for(int i = 0; i < 5; i++){
           pcb = processQueue.get(i);
           if(pcb.getExpBurstTime() != 0){
              work = work + 1;
           }
        }
        if (work == 0){
           return false;
        }else{
           return true;
        }
     }
   
    
}