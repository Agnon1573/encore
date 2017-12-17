package com.truelaurel.encore.click;

import com.truelaurel.encore.common.Link;

public class Click {
    private String timestamp;
    private Link source;
    private Link target;

    public Click() {
    }

    public Click(String timestamp, Link source, Link target) {
        this.timestamp = timestamp;
        this.source = source;
        this.target = target;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Link getSource() {
        return source;
    }

    public void setSource(Link source) {
        this.source = source;
    }

    public Link getTarget() {
        return target;
    }

    public void setTarget(Link target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Click{" +
                "timestamp='" + timestamp + '\'' +
                ", source=" + source +
                ", target=" + target +
                '}';
    }
}

