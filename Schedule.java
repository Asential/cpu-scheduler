import java.io.*;
import java.util.*;


class Schedule{
    public static void main(String args[]){

        Schedule scheduler = new Schedule();
        Scanner sc = new Scanner(System.in);
        int algoNum;
        int procNum;
        System.out.println("Hello! Welcome to the CPU Scheduler!");
        System.out.println("Select a Scheduling Algorithm from the given choices: ");
        System.out.printf(" 1. FCFS\n 2. RR\n 3. SJF\n 4. SRTN\n 5. HRRN\n 6. Lottery\n 7. EDF\n 8. Priority\n 9. All\n");
        while(true){
            try{algoNum = Integer.parseInt(sc.nextLine());
                if(algoNum >= 10 || algoNum <= 0){
                    System.out.println("Please enter a correct choice!");
                }else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please enter an Integer!");
            }
        }

        System.out.println("Enter the number of Processes(Less than 50 for Gaant Chart): ");
        while(true){
            try{
                procNum = Integer.parseInt(sc.nextLine());
                if(procNum <= 0){
                    System.out.println("Please enter a positive integer!");
                }else{
                    break;
                }
            }
            catch(Exception e){
                System.out.println("Please enter an Integer!");
            }
        }
        System.out.println("Process: " + procNum);
        Process[] procList = scheduler.genProcList(procNum);
        ScheduleAlgos algo = new ScheduleAlgos();
        
        switch(algoNum){
            case 1:
                algo.fcfs(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 2:
                algo.rr(procList, 2);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 3:
                algo.sjf_np(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 4:
                algo.sjf_p(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 5:
                algo.hrrn(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;  
            case 6:
                algo.lottery(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 7:
                algo.edf(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 8:
                algo.priority(procList);
                scheduler.getOutTex(procList, algo);
                scheduler.runPython(algo.name);
                break;
            case 9:
                Process[] copy = new Process[procNum];
                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.sjf_p(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);


                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.fcfs(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.rr(copy, 2);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.sjf_np(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.hrrn(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.lottery(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.edf(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);

                for (int i = copy.length - 1; i >= 0; --i) {
                    Process p = procList[i];
                    if (p != null) {
                        copy[i] = new Process(p);
                    }
                }
                algo.priority(copy);
                scheduler.getOutTex(copy, algo);
                scheduler.runPython(algo.name);
                break;
        }
        sc.close();
    }

    public void getOutTex(Process[] list, ScheduleAlgos currentAlgo){
        try{
            // Writer object, used with printf to create formatted columns in Output.tex from a list of processes.
            FileWriter writer = new FileWriter(currentAlgo.name+".txt");
            PrintWriter out = new PrintWriter(writer);
            out.printf("Algo: %s\n\n", currentAlgo.name);
            out.printf("%-20s %15s %15s %15s %15s %15s %15s %15s\n", 
                "Name", 
                "ArrivalTime", 
                "BurstTime", 
                "WaitingTime", 
                "TurnaroundTime", 
                "ResponseTime", 
                "CompletionTime", 
                "CPUUtil"
            );
            for(int i = 0; i < list.length; i++){
                if(currentAlgo.name == "Shortest Job First (Non-Premptive)"){
                    out.printf("%-20s %15s %15s %15s %15s %15s %15s %15s\n", 
                    list[i].name, 
                    0, 
                    list[i].burst, 
                    list[i].waitingTime, 
                    list[i].turnAroundTime, 
                    list[i].responseTime,
                    list[i].completionTime,
                    list[i].cpuUtilTime);
                }
                else if(currentAlgo.name == "Earliest Deadline First"){
                    out.printf("%-20s %15s %15s %15s %15s %15s %15s %15s %15s\n", 
                    list[i].name, 
                    list[i].arrival, 
                    list[i].burst, 
                    list[i].deadline, 
                    list[i].waitingTime, 
                    list[i].turnAroundTime, 
                    list[i].responseTime,
                    list[i].completionTime,
                    list[i].cpuUtilTime);
                }
                else{
                    out.printf("%-20s %15s %15s %15s %15s %15s %15s %15s\n", 
                    list[i].name, 
                    list[i].arrival, 
                    list[i].burst,  
                    list[i].waitingTime, 
                    list[i].turnAroundTime, 
                    list[i].responseTime,
                    list[i].completionTime,
                    list[i].cpuUtilTime);
                }
            }
            out.printf("\n");
            out.printf("Average Waiting TIme: %s\n", currentAlgo.averageWaitingTime);
            out.printf("Average Turnaround time: %s\n", currentAlgo.averageTurnAroundTime);
            out.printf("Average Response time: %s\n", currentAlgo.averageResponseTime);
            out.printf("CPUUtilTotal: %s\n", currentAlgo.CPUUtilTotal);
            out.printf("---------------------------");
            System.out.println(currentAlgo.name+".txt generated successfully!");
            writer.close();
        }
        catch(IOException error){
            System.out.println("Error occourred in adding data!");
            error.printStackTrace();
        }
    }

    public Process[] genProcList(int n){
        Process[] temp = new Process[n];
        for(int i = 0; i < n; i++){
            temp[i] = new Process();
            temp[i].name = "P"+(i+1);
        }
        return temp;
    }

    public void printProc(Process[] list){
        for(int i = 0; i < list.length; i++){
            System.out.printf("%-20s %15s %15s %15s\n", list[i].name, list[i].arrival, list[i].burst, list[i].deadline);
        }
        System.out.println("---------------------------------------------");
    }

    public void runPython(String GName){
        try{
            String [] cmd = new String[3];
            cmd[0] = "python";
            cmd[1] = "gaant.py";
            cmd[2] = GName;
            java.lang.Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        }
        catch(Exception e){
            System.out.println("Error occourred in Running program!");
            e.printStackTrace();
        }
}
}

class Process{
    Random generator = new Random();
    String name = "NULL";

    int arrival;
    int burst;
    int deadline;

    boolean responded=false;
    int waitingTime;
    int turnAroundTime;
    int responseTime;
    int completionTime;
    int remainingBurst;
    int newArrival;
    int period;
    
    float lottery;
    float priority;
    float cpuUtilTime;

    public Process(){
        arrival = generator.nextInt(10);
        burst = generator.nextInt(5) + 1;
        remainingBurst = burst;
        deadline = generator.nextInt(15) + 1;
        period = deadline;
        priority = generator.nextInt(15);
    }

    public Process(Process p){
        name = p.name;
        arrival = p.arrival;
        burst = p.burst;
        deadline = p.deadline;
        responded = p.responded;
        waitingTime = p.waitingTime;
        turnAroundTime = p.turnAroundTime;
        responseTime = p.responseTime;
        completionTime = p.completionTime;
        remainingBurst = p.remainingBurst;
        newArrival = p.newArrival;
        period = p.period;   
        lottery = p.lottery;
        priority = p.priority;
        cpuUtilTime = p.cpuUtilTime;
    }


    public Process(int arr, int bur){
        arrival = arr;
        burst = bur;
        remainingBurst = burst;
        deadline = generator.nextInt(20);
        period = deadline;
    }

    public Process(String s,int arr, int bur, int dead){
        name = s;
        arrival = arr;
        burst = bur;
        remainingBurst = burst;
        deadline = dead;
        period = deadline;
        period = deadline;
        priority = dead;
    }

    public Integer getBurst(){
        return burst;
    }
}

class ScheduleAlgos{
    String name="NULL";
    float averageWaitingTime;
    float averageResponseTime;
    float averageTurnAroundTime;
    float CPUUtilTotal;

    public void fcfs(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        name = "FirstComeFirstServe";
        sortArrival(list);
        clearGaantTex();
        list[0].waitingTime = 0;
        int idle = 0;
        int clock = list[0].burst + list[0].arrival;
        setGaantTex(list[0].name, list[0].arrival, clock);
        for(int i = 1; i < list.length; i++){
            if(clock >= list[i].arrival){
                list[i].waitingTime = clock - list[i].arrival;
                clock += list[i].burst;
            }
            else{
                clock += list[i].arrival - clock;
                clock += list[i].burst;
                list[i].waitingTime = 0;
            }
            setGaantTex(list[i].name, list[i].arrival + list[i].waitingTime, clock);

            
            list[i].responseTime = list[i].waitingTime;
            list[i].turnAroundTime = list[i].burst + list[i].waitingTime;
            list[i].completionTime = list[i].turnAroundTime + list[i].arrival;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageWaitingTime += list[i].waitingTime;

        }
        for(int i = 0; i < list.length; i++){
            list[i].cpuUtilTime = (float)list[i].burst/list[list.length-1].completionTime;
            list[i].cpuUtilTime *= 100;
        }        
        
        CPUUtilTotal = 100*(float)(list[list.length-1].completionTime - idle)/list[list.length-1].completionTime;
        averageWaitingTime = averageWaitingTime/list.length;
        averageTurnAroundTime = averageTurnAroundTime/list.length;
        averageResponseTime = averageWaitingTime;

    }

    public void rr(Process[] list, int quantum){

        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;
        
        name = "RoundRobin";
        sortArrival(list);
        clearGaantTex();
        
        boolean complete = true;
        boolean looping;
        int clock=0;
        int idle=0;
        while(true){
            complete = true;
            looping = false;
            for(int i = 0; i < list.length; i++){
                if(list[i].remainingBurst > 0){
                    complete = false;
                    if(list[i].remainingBurst > quantum && list[i].arrival <= clock){
                        if(list[i].responded == false){
                            list[i].responseTime = clock - list[i].arrival;
                            list[i].responded = true;
                        }
                        list[i].remainingBurst -= quantum;
                        clock += quantum;
                        setGaantTex(list[i].name, clock-quantum, clock);
                        looping = true;
                    }
                    else if(list[i].arrival <= clock){
                        if(list[i].responded == false){
                            list[i].responseTime = clock - list[i].arrival;
                            list[i].responded = true;
                        }
                        clock+= list[i].remainingBurst;
                        setGaantTex(list[i].name, clock-list[i].remainingBurst, clock);
                        list[i].remainingBurst = 0;
                        list[i].completionTime=clock;
                        looping = true;
                    }
                }
            }

            if(looping == false){
                idle++;
                clock++;
            }
            if (complete == true){
                break; 
            } 
        }

        for (int i = 0; i < list.length ; i++){            
            list[i].turnAroundTime = list[i].completionTime - list[i].arrival;
            list[i].waitingTime = list[i].turnAroundTime-list[i].burst;
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
            list[i].cpuUtilTime = (float)list[i].burst/list[list.length-1].completionTime;
            list[i].cpuUtilTime *= 100;
        }

        CPUUtilTotal = 100*(float)(list[list.length-1].completionTime - idle)/list[list.length-1].completionTime;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;
    }

    public void sjf_np(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        name = "Shortest Job First (Non-Premptive)";
        sortBurst(list);
        clearGaantTex();
        list[0].waitingTime = 0;
        int clock = 0;
        clock += list[0].burst;
        list[0].completionTime = clock;
        setGaantTex(list[0].name, clock-list[0].burst, clock);
        for(int i = 1; i < list.length; i++){
                list[i].waitingTime = clock;
                clock += list[i].burst;
                list[i].completionTime = clock;
                setGaantTex(list[i].name, clock-list[i].burst, clock);
        }

        for(int i = 0; i < list.length; i++){
            list[i].responseTime = list[i].waitingTime;
            list[i].turnAroundTime = list[i].burst + list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageWaitingTime += list[i].waitingTime;   
            list[i].cpuUtilTime = (float)list[i].burst/list[list.length-1].completionTime;
            list[i].cpuUtilTime *= 100;
        }       
        
        CPUUtilTotal = 100;
        averageWaitingTime = averageWaitingTime/list.length;
        averageTurnAroundTime = averageTurnAroundTime/list.length;
        averageResponseTime = averageWaitingTime;
    }

    public void sjf_p(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;
        
        name = "Shortest Job First (Premptive)";
        sortArrival(list);
        clearGaantTex();

        List<Process> waitingCopy = new ArrayList<Process>();
        List<Process> workingCopy = new ArrayList<Process>();
        for(int i = 0; i < list.length; i++){
            waitingCopy.add(list[i]);
        }

        int clock=0;
        int toRun = 0;
        int idle = 0;
        while(true){
            for(int i = waitingCopy.size()-1; i >= 0; i--){
                if(waitingCopy.get(i).arrival <= clock){
                    workingCopy.add(waitingCopy.get(i));
                    waitingCopy.remove(waitingCopy.get(i));
                }
            }
            if(workingCopy.size() == 0){
                clock++;
                idle++;
                continue;
            }
            toRun = 0;
            for(int i = 0; i < workingCopy.size(); i++){
                if(workingCopy.get(i).remainingBurst < workingCopy.get(toRun).remainingBurst){
                    toRun = i;
                }
            }
            
            if(workingCopy.get(toRun).responded == false){
                workingCopy.get(toRun).responseTime = clock - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).responded = true;
            }

            setGaantTex(workingCopy.get(toRun).name, clock, clock + 1);
            workingCopy.get(toRun).remainingBurst--;
            clock++;
            if(workingCopy.get(toRun).remainingBurst == 0){
                workingCopy.get(toRun).completionTime = clock;
                workingCopy.get(toRun).turnAroundTime = workingCopy.get(toRun).completionTime - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).waitingTime = workingCopy.get(toRun).turnAroundTime - workingCopy.get(toRun).burst;
                workingCopy.remove(workingCopy.get(toRun));
            }
            if(workingCopy.size() == 0 && waitingCopy.size() == 0){
                break;
            }
        }
        for (int i = 0; i < list.length ; i++){            
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
            list[i].cpuUtilTime = (float)list[i].burst/clock;
            list[i].cpuUtilTime *= 100;
        }

        CPUUtilTotal = 100*(float)(clock - idle)/clock;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;
    }

    public void edf(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        name = "Earliest Deadline First";
        sortDeadline(list);
        clearGaantTex();

        int[] periodList = new int[list.length];
        float checker=0;
        int clock = 0;
        List<Process> waitingCopy = new ArrayList<Process>();
        for(int i = 0; i < list.length; i++){
            waitingCopy.add(list[i]);
            periodList[i]=list[i].period;
            checker+=(float)list[i].burst/list[i].period;
            list[i].cpuUtilTime = (float)list[i].burst/list[i].period;

        }
        if(checker > 1){
            System.out.println("Not Schedulable Using EDF!");
            return;
        }
        List<Process> workingCopy = new ArrayList<Process>();
        
        int LCM = lcm_of_array_elements(periodList);
        int toRun;
        int idle = 0;
        while(clock < LCM){
            for(int i = waitingCopy.size()-1; i >= 0; i--){
                if(waitingCopy.get(i).newArrival <= clock){
                    workingCopy.add(waitingCopy.get(i));
                    waitingCopy.remove(waitingCopy.get(i));
                }
            }
            if(workingCopy.size() == 0){
                clock++;
                idle++;
                continue;
            }
            toRun = 0;
            for(int i = 0; i < workingCopy.size(); i++){
                if(workingCopy.get(i).period < workingCopy.get(toRun).period){
                    toRun = i;
                }
            }
            workingCopy.get(toRun).newArrival += workingCopy.get(toRun).deadline;
            workingCopy.get(toRun).period += workingCopy.get(toRun).deadline;

            if(workingCopy.get(toRun).responded == false){
                workingCopy.get(toRun).responseTime = clock - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).waitingTime = workingCopy.get(toRun).responseTime;
                workingCopy.get(toRun).turnAroundTime = workingCopy.get(toRun).waitingTime + workingCopy.get(toRun).burst;
                workingCopy.get(toRun).responded = true;
            }
            setGaantTex(workingCopy.get(toRun).name, clock, clock + workingCopy.get(toRun).burst);
            clock += workingCopy.get(toRun).burst;
            waitingCopy.add(workingCopy.get(toRun));
            workingCopy.remove(workingCopy.get(toRun));
        }

        for (int i = 0; i < list.length ; i++){            
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
        }
        CPUUtilTotal = 100*(float)(clock - idle)/clock;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;
    }

    public void hrrn(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        name = "Highest Response Ration Next";
        sortArrival(list);
        clearGaantTex();
        List<Process> waitingCopy = new ArrayList<Process>();
        List<Process> workingCopy = new ArrayList<Process>();
        for(int i = 0; i < list.length; i++){
            waitingCopy.add(list[i]);
        }
        int clock = 0;
        int idle = 0;
        int toRun;
        boolean hrrn=false;
        while(waitingCopy.size() > 0 || workingCopy.size() > 0){
            toRun = 0;
            for(int i = waitingCopy.size()-1; i >= 0; i--){
                if(waitingCopy.get(i).arrival <= clock){
                    workingCopy.add(waitingCopy.get(i));
                    waitingCopy.remove(waitingCopy.get(i));
                }
            }
            if(workingCopy.size() == 0){
                clock++;
                idle++;
                continue;
            }

            for(int i = 0; i < workingCopy.size(); i++){
                workingCopy.get(i).waitingTime = clock - workingCopy.get(i).arrival;
                workingCopy.get(i).priority = 1 + (float)(workingCopy.get(i).waitingTime/workingCopy.get(i).burst);
            }

            toRun = 0;
            if(hrrn == false){
                for(int i = 0; i < workingCopy.size(); i++){
                    if(workingCopy.get(i).arrival < workingCopy.get(toRun).arrival){
                        toRun = i;
                    }
                }
                hrrn = true;    
            }
            else{
                for(int i = 0; i < workingCopy.size(); i++){
                    if(workingCopy.get(i).priority > workingCopy.get(toRun).priority){
                        toRun = i;
                    }
                }
            }

            workingCopy.get(toRun).waitingTime = clock - workingCopy.get(toRun).arrival;
            setGaantTex(workingCopy.get(toRun).name, clock, clock + workingCopy.get(toRun).burst);
            if(workingCopy.get(toRun).responded == false){
                workingCopy.get(toRun).responseTime = clock - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).responded = true;
            }
            clock += workingCopy.get(toRun).burst;
            workingCopy.get(toRun).completionTime = clock;
            workingCopy.get(toRun).turnAroundTime = workingCopy.get(toRun).completionTime - workingCopy.get(toRun).arrival - workingCopy.get(toRun).responseTime;

            workingCopy.remove(workingCopy.get(toRun));    
            for(int i = 0; i < workingCopy.size(); i++){
                workingCopy.get(i).waitingTime = clock - workingCopy.get(i).arrival;
                workingCopy.get(i).priority = 1 + (float)(workingCopy.get(i).waitingTime/workingCopy.get(i).burst);
            }
        }

        

        for (int i = 0; i < list.length ; i++){            
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
            list[i].cpuUtilTime = (float)list[i].burst/clock;
            list[i].cpuUtilTime *= 100;
        }

        CPUUtilTotal = 100*(float)(clock-idle)/clock;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;
    }

    public void priority(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        name = "Priority";
        sortArrival(list);
        clearGaantTex();
        List<Process> waitingCopy = new ArrayList<Process>();
        List<Process> workingCopy = new ArrayList<Process>();
        for(int i = 0; i < list.length; i++){
            waitingCopy.add(list[i]);
        }
        int clock = 0;
        int toRun;
        int idle = 0;
        while(waitingCopy.size() > 0 || workingCopy.size() > 0){
            toRun = 0;
            for(int i = waitingCopy.size()-1; i >= 0; i--){
                if(waitingCopy.get(i).arrival <= clock){
                    workingCopy.add(waitingCopy.get(i));
                    waitingCopy.remove(waitingCopy.get(i));
                }
            }
            if(workingCopy.size() == 0){
                clock++;
                idle++;
                continue;
            }

            for(int i = 0; i < workingCopy.size(); i++){
                if(workingCopy.get(i).priority < workingCopy.get(toRun).priority){
                    toRun = i;
                }
            }

            workingCopy.get(toRun).waitingTime = clock - workingCopy.get(toRun).arrival;
            setGaantTex(workingCopy.get(toRun).name, clock, clock + workingCopy.get(toRun).burst);
            if(workingCopy.get(toRun).responded == false){
                workingCopy.get(toRun).responseTime = clock - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).responded = true;
            }
            clock += workingCopy.get(toRun).burst;
            workingCopy.get(toRun).completionTime = clock;
            workingCopy.get(toRun).turnAroundTime = workingCopy.get(toRun).completionTime - workingCopy.get(toRun).arrival - workingCopy.get(toRun).responseTime;
            workingCopy.remove(workingCopy.get(toRun));    
        }

        for (int i = 0; i < list.length ; i++){            
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
            list[i].cpuUtilTime = (float)list[i].burst/clock;
            list[i].cpuUtilTime *= 100;
        }
        CPUUtilTotal = 100*(float)(clock-idle)/clock;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;
    }

    public void lottery(Process[] list){
        averageWaitingTime = 0;
        averageTurnAroundTime = 0;
        averageResponseTime = 0;
        CPUUtilTotal = 0;

        Random generator = new Random();

        name = "Lottery";
        sortArrival(list);
        clearGaantTex();
        List<Process> waitingCopy = new ArrayList<Process>();
        List<Process> workingCopy = new ArrayList<Process>();
        
        for(int i = 0; i < list.length; i++){
            waitingCopy.add(list[i]);
        }

        int clock=0;
        int toRun = 0;
        int idle = 0;
        float chance = 0;
        float lotTotal=0;
        while(true){
            for(int i = waitingCopy.size()-1; i >= 0; i--){
                if(waitingCopy.get(i).arrival <= clock){
                    workingCopy.add(waitingCopy.get(i));
                    waitingCopy.remove(waitingCopy.get(i));
                }
            }
            if(workingCopy.size() == 0){
                clock++;
                idle++;
                continue;
            }

            toRun = 0;
            lotTotal = 0;

            for(int i = 0; i < workingCopy.size(); i++){
                lotTotal += workingCopy.get(i).remainingBurst;
            }

            for(int i = 0; i < workingCopy.size(); i++){
                workingCopy.get(i).lottery = (float)workingCopy.get(i).remainingBurst/lotTotal;
            }

            chance = generator.nextFloat();
            float temp = 0;
            for(int i = 0; i < workingCopy.size(); i++){
                if(temp <= chance && temp + workingCopy.get(i).lottery > chance){
                    toRun = i;
                    break;
                }
                else{
                    temp += workingCopy.get(i).lottery;
                }
            }
            

            if(workingCopy.get(toRun).responded == false){
                workingCopy.get(toRun).responseTime = clock - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).responded = true;
            }

            setGaantTex(workingCopy.get(toRun).name, clock, clock + 1);
            workingCopy.get(toRun).remainingBurst--;
            clock++;
            if(workingCopy.get(toRun).remainingBurst == 0){
                workingCopy.get(toRun).completionTime = clock;
                workingCopy.get(toRun).turnAroundTime = workingCopy.get(toRun).completionTime - workingCopy.get(toRun).arrival;
                workingCopy.get(toRun).waitingTime = workingCopy.get(toRun).turnAroundTime - workingCopy.get(toRun).burst;
                workingCopy.remove(workingCopy.get(toRun));
            }
            if(workingCopy.size() == 0 && waitingCopy.size() == 0){
                break;
            }
        }
        // Calculate Turnaorund time.
        for (int i = 0; i < list.length ; i++){            
            averageWaitingTime += list[i].waitingTime;
            averageTurnAroundTime += list[i].turnAroundTime;
            averageResponseTime += list[i].responseTime;
            list[i].cpuUtilTime = (float)list[i].burst/clock;
            list[i].cpuUtilTime *= 100;
        }
        CPUUtilTotal = 100*(float)(clock-idle)/clock;
        averageWaitingTime /= list.length;
        averageTurnAroundTime /= list.length;
        averageResponseTime /= list.length;

    }


    public void sortArrival(Process[] list){
        Arrays.sort(list, new Comparator<Process>() {
            public int compare(Process b1, Process b2) {
                return Integer.compare(b1.arrival, b2.arrival);
            }
         });
    }
    
    public void sortBurst(Process[] list){
        Arrays.sort(list, new Comparator<Process>() {
            public int compare(Process b1, Process b2) {
                return Integer.compare(b1.burst, b2.burst);
            }
         });
    }

    public void sortDeadline(Process[] list){
        Arrays.sort(list, new Comparator<Process>() {
            public int compare(Process b1, Process b2) {
                return Integer.compare(b1.deadline, b2.deadline);
            }
         });
    }

    public void setGaantTex(String name, int start, int end){
        try{
            // Writer object, used with printf to create formatted columns in Output.tex from a list of processes.
            FileWriter writer = new FileWriter("Gaant.tex", true);
            PrintWriter out = new PrintWriter(writer);
            out.printf("%s %s %s\n", name, start, end);
            writer.close();
        }
        catch(IOException error){
            System.out.println("Error occourred in exporting Gaant data!");
            error.printStackTrace();
        }
    }

    public void clearGaantTex(){
        try{
            // Writer object, used with printf to create formatted columns in Output.tex from a list of processes.
            FileWriter writer = new FileWriter("Gaant.tex");
            writer.close();
        }
        catch(IOException error){
            System.out.println("Error occourred in exporting Gaant data!");
            error.printStackTrace();
        }
    }

    public int lcm_of_array_elements(int[] element_array){
        int lcm_of_array_elements = 1;
        int divisor = 2;
        while (true) {
            int counter = 0;
            boolean divisible = false;
             
            for (int i = 0; i < element_array.length; i++) {
                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }  
}

