import java.util.Arrays;
import java.util.Comparator;

public class Manager {

    //This class contains code to implement memory allocation algorithms


    public int FirstFit(Partition [] P, Job j)
    {
        int size = P.length; //get number of partitions in memory


        for (int i = 0; i < size; i++)
        {
            //Find a free partition in memory that is large enough for Job j
            if (j.size <= P[i].capacity && !P[i].busy)
            {
                P[i].capacity -= j.size;    //Partition i's capacity has been reduced
                P[i].jobID = j.ID;          //Set the ID of the allocated job
                P[i].busy = true;           //Partition is busy and no other process can be loaded here

                return i;   //Return index of the chosen partition
            }
        }

        return -1; //This means that we failed to allocate the job to a free block


    }

    public void BestFit(Partition [] P, Job [] jobs)
    {
        // sort both arrays
        Arrays.sort(P, Comparator.comparingInt(partition -> partition.capacity ));
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.size));

        // loop through the jobs
        for (Job job : jobs) {

            // looping through partitions and assigning jobs to them
            for (Partition partition : P){

                // assigning a job to the specific partition
                if (partition.capacity >= job.size && !partition.busy){

                    partition.capacity -= job.size;
                    partition.jobID = job.ID;
                    partition.busy = true;

                    break;
                }
            }
        }

        // sort partition array back to normal
        Arrays.sort(P, Comparator.comparingInt(partition -> partition.address ));

    }


    public void viewJobs(Job [] Jobs, int size)
    {
        for (int i = 0; i < size ; i++) {
            System.out.println(Jobs[i].ID + "\t\t" + Jobs[i].size);
        }
    }



}
