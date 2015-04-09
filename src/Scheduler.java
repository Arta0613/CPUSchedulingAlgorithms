import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Arman on 4/7/15.
 */
public class Scheduler {

    private static MyPriorityQueue eventQueue = new MyPriorityQueue();
    private static String processes;
    private static Process currentlyRunning;
    private static Burst event;
    private static MyArrayList readyQueue = new MyArrayList();

    public static void main(String [] args) {
        // TODO: test args
        processes = args[0];
        parse();
        for(Comparable p : eventQueue.toArray()) {
            System.out.println(((Process)p).getID() + " " + ((Process)p).getArrival());
            ((Process)p).bursts.print();
        }


    }

    public static void FCFS() {
        currentlyRunning = null;

        while (!eventQueue.isEmpty()) {
            event = ((Process) eventQueue.peek(0)).next();

            if(event.arrival) {
                if(readyQueue.size <= 20)
                    readyQueue.pushBack(eventQueue.poll());

            }
        }
    }

    private static void parse() {
        String line = "";
        String idArr;
        String rest;
//        int i = 0;
        Scanner s  = null;
        try {
            s = new Scanner(new File(processes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNextLine()) {
            line = s.nextLine();
            idArr = line.substring(0, line.indexOf('C'));
//            System.out.println(idArr);
            rest = line.substring(line.indexOf('C'));
//            System.out.println(rest);
            eventQueue.add(new Process(idArr, rest));
        }
    }
}
