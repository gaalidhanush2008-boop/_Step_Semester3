/**
 * Week 7 - Problem 4: The Traffic Light
 * The color can only move forward via next(): RED -> GREEN -> YELLOW -> RED ...
 */
public class P4_TrafficLight {

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println(t.getColor()); // RED
        System.out.println(t.next());     // GREEN
        System.out.println(t.next());     // YELLOW
        System.out.println(t.next());     // RED
        System.out.println(t.next());     // GREEN
    }
}

class TrafficLight {
    private static final String[] CYCLE = {"RED", "GREEN", "YELLOW"};

    private final String id;
    private int position = 0;             // index into CYCLE; starts on RED

    TrafficLight(String id) {
        this.id = id;
    }

    String next() {                       // the only way to change the color
        position = (position + 1) % CYCLE.length;
        return CYCLE[position];
    }

    String getColor() {
        return CYCLE[position];
    }

    String getId() {
        return id;
    }
}
