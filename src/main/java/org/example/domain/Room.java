package org.example.domain;

import java.util.Objects;

public record Room(int length, int width, int height) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return length == room.length && width == room.width && height == room.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }

}
