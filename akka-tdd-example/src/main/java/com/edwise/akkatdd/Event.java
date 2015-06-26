package com.edwise.akkatdd;

public class Event {
    private Integer num;

    public Event(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;
        return !(num != null ? !num.equals(event.num) : event.num != null);

    }

    @Override
    public int hashCode() {
        return num != null ? num.hashCode() : 0;
    }
}
