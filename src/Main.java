import java.sql.Array;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Main{

    public static void ViewMemory(Partition [] P, int size)
    {
        //Print out all memory partitions and the jobs that occupy them
        System.out.println("Address\t\tAvailable Mem.\t\tBusy\tJob");
        System.out.println(" ____________________________________________________________________");
        for (int i = 0; i < size; i++)
        {
            System.out.println(P[i].address + "\t\t\t" + P[i].capacity + "\t\t\t\t" + P[i].busy + "\t\t" + P[i].jobID);
        }
    }
    public static void InitializeMemory(Partition [] P)
    {
        for(int i = 0; i < P.length; i++)
            P[i] = new Partition();
    }

    public static void CreatePartitions(Partition [] P, int size)
    {
        //Reconfigure partitions
        int a = 0;

        for(int i = 0; i < size; i++)
        {

            P[i].capacity = ThreadLocalRandom.current().nextInt(20, 201);
            P[i].address = a;
            a += P[i].capacity;
            P[i].busy = false;
            P[i].jobID = 0;

        }
    }
    public static void main(String []args){
        int jobCounter = 0;
        Manager M = new Manager();
        Scanner sc = new Scanner(System.in);
        Partition [] p = new Partition[5];
        InitializeMemory(p);
        CreatePartitions(p, 5); //Create main memory with five partitions
        int choice;
        int jobSize;
        Job j;
        do
        {
            System.out.println("Fixed Partition Memory Allocation simulator");
            System.out.println("1) View memory");
            System.out.println("2) Add 5 jobs to memory (Best Fit Allocation)");
            System.out.println("3) Add 5 jobs to memory (First Fit Allocation)");
            System.out.println("4) Add 5 jobs to memory (Next Fit Allocation)");
            System.out.println("5) Reset system");
            System.out.println("6) Quit");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    ViewMemory(p, 5); //Show memory and occupied jobs;
                    break;
                case 2:
                    Job [] allJobs = new Job[5];

                    // create jobs and place in job array
                    for (int i = 0; i < 5; i++)
                    {

                        System.out.println("How large is job " + (i+1) + "?");
                        jobSize = sc.nextInt();

                        if(jobSize < 0)
                        {

                            System.out.println("INVALID SIZE ENTERED. TRY AGAIN");
                            i--;
                        }
                        else
                        {
                            j = new Job();
                            j.ID = i+1;
                            j.size = jobSize;
                            allJobs[i] = j;
                        }
                    }

                    M.BestFit(p, allJobs);

                    break;

                case 3:

                    for(int i = 1; i <= 5; i++)        //Add at most 5 jobs to memory
                    {

                        System.out.println("How large is job " + i + "?");
                        jobSize = sc.nextInt();

                        if(jobSize < 0)
                        {
                            System.out.println("INVALID SIZE ENTERED. TRY AGAIN.");
                            i--;
                        }
                        else
                        {
                            j = new Job();
                            j.ID = ++jobCounter;
                            j.size = jobSize;
                            M.FirstFit(p, j); //Perform First Fit allocation
                        }

                    }

                    break;

                case 4:
                    break;

                case 5:
                    jobCounter = 0;
                    CreatePartitions(p, 5);
                    break;
            }
        } while(choice != 6);

    }
}
