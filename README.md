# cpu-scheduler

This is a Java program to simulate the following CPU Scheduling algorithms.

1. First Come First Serve
2. Round Robin
3. Shoftest Job First (Premptive)
4. Shortest Job First (Non-Premptive)
5. HRRN
6. Lottery
7. Earliest Deadline First
8. Priority

I calculate the following values for each algorithm, generated in a .txt file with algorithm's name.

- Arrival Time       
- Burst Time
- Waiting Time
- Turnaround Time    
- Response Time
- Completion Time
- CPU Utilisation
- Average Waiting Time
- Average Turnaround Time 
- Average Response Time

I also generate a Gaant chart using python in form of a SVG for the algorithms.

Sample data can be found in /samples

![main](images\All.jpg)


**Gaant Charts**

*First Come First Serve*

![main](images\FirstComeFirstServe.jpg)

---

*Highest Response Ratio Next*

![main](images\HighestResponseRationNext.jpg)

---


*Priority*

![main](images\Priority.jpg)

---

*Round Robin*

![main](images\RoundRobin.jpg)

---

*Shortest Job First (Non-Preemptive)*

![main](images\ShortestJobFirst(NonPreEmptive).jpg)

---

*Shortest Job First (Preemptive)*

![main](images\ShortestJobFirst(PreEmptive).jpg)

---