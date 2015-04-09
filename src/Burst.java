/**
 * Created by Arman on 4/7/15.
 */
public class Burst implements Comparable {

    private boolean CPU;
    private int burstAmt;
    public boolean arrival = true;

    public Burst(boolean CI, int burstAmt) {
        CPU = CI;
        this.burstAmt = burstAmt;
    }

    public boolean isCPU() {
        return CPU;
    }

    public int getBurstAmt() {
        return burstAmt;
    }


    @Override
    public String toString() {
        return (CPU ? "CPU: " : "IO: ") + burstAmt;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
