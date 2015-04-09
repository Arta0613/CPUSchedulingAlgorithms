import java.util.Scanner;

/**
 * Created by Arman on 4/7/15.
 */
public class Process implements Comparable {

    private int ID;
    private int arrival;
    private int waitTot;
    private int waitStart;
    public MyArrayList bursts = new MyArrayList();
    private int burstsTotTime;
    private int contextSwitchTime;

    public Process(String line, String bursts) {

        // Get ID and Arrival
        StringBuilder string = new StringBuilder(line);
        ID = Integer.parseInt(string.substring(1, string.indexOf(",")));
        string.delete(0, string.indexOf(",") + 1);
        arrival = Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(",")));

        // Parse CPU & IO Bursts ; put into Burst class which resides in MyArrayList
        Scanner sC = new Scanner(bursts);
        String burstParse;
        int i = 0;
        while (sC.hasNext()) {
            burstParse = sC.next();
            if(burstParse.contains(",")) {
                i = Integer.parseInt(burstParse.substring(burstParse.indexOf(":") + 1, burstParse.indexOf(",")));
                burstsTotTime += i;
            } else {
                i = Integer.parseInt(burstParse.substring(burstParse.indexOf(":") + 1));
                burstsTotTime += i;
            }
            if (burstParse.startsWith("C")) {
                this.bursts.pushBack(new Burst(true, i));
            } else {
                this.bursts.pushBack(new Burst(false, i));
            }
        }
    }

    public Burst next() {
        return (Burst) bursts.popFront();
    }

    public void addTwo() {
        contextSwitchTime += 2;
    }
    public int getID() {
        return ID;
    }

    public int getArrival() {
        return arrival;
    }

    @Override
    public int compareTo(Object o) {
        if(this.arrival == ((Process) o).arrival) {
            return new Integer(this.ID).compareTo(((Process) o).ID);
        } else
            return new Integer(this.arrival).compareTo(((Process) o).arrival);
    }
}
