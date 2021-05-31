public class Passenger {

    private String firstName;
    private String surname;
    private int secondsInQueue;
    private int seat;

    public String getName() {
        return firstName + " " + surname;
    }

    public void setName(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public int getSeconds() {
        return secondsInQueue;
    }

    public void setSecondsInQueue(int secondsInQueue) {
        this.secondsInQueue = secondsInQueue;
    }

    public void display() {

    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getSeat() {
        return seat;
    }
}

