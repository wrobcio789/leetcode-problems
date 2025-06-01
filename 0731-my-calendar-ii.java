import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;

class MyCalendarTwo {
    private static final Comparator<BookingEvent> EVENT_COMPARATOR = (BookingEvent s1, BookingEvent s2) -> {
        if (!Objects.equals(s1.getPoint(), s2.getPoint())) {
            return Integer.compare(s1.getPoint(), s2.getPoint());
        }
        if(!Objects.equals(s1.isStart(), s2.isStart())) {
            return Boolean.compare(s1.isStart(), s2.isStart());
        }
        return Integer.compare(s1.hashCode(), s2.hashCode());
    };

    private final Set<BookingEvent> bookingEvents = new TreeSet<>(EVENT_COMPARATOR);

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        final var startEvent = new BookingEvent(start, true);
        final var endEvent = new BookingEvent(end, false);
        bookingEvents.add(startEvent);
        bookingEvents.add(endEvent);

        if (verify()) {
            return true;
        }

        bookingEvents.remove(startEvent);
        bookingEvents.remove(endEvent);
        return false;
    }

    private boolean verify() {
        int index = 0;
        for (final var event : bookingEvents) {
            index = index + (event.isStart() ? 1 : -1);
            if (index > 2) {
                return false;
            }
        }
        return true;
    }

    private static class BookingEvent {
        private final int point;
        private final boolean isStart;

        public BookingEvent(int point, boolean isStart) {
            this.point = point;
            this.isStart = isStart;
        }

        public final int getPoint() {
            return point;
        }

        public final boolean isStart() {
            return isStart;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public static void main(String[] args) {
        var calendar = new MyCalendarTwo();
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();

        System.out.println(myCalendarTwo.book(10, 20));
        System.out.println(myCalendarTwo.book(50, 60));
        System.out.println(myCalendarTwo.book(10, 40));
        System.out.println(myCalendarTwo.book(5, 15));
        System.out.println(myCalendarTwo.book(5, 10));
        System.out.println(myCalendarTwo.book(25, 55));
    }
}