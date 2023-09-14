package org.example.service;

import org.example.domain.Room;
import org.example.exception.FileReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WallpaperService {
    final String divider = "x";

    public List<Room> parseInputFile(final String filePath) throws FileReadException {
        try {
            return Files.readAllLines(Path.of(filePath))
                    .stream()
                    .map(this::getRoom)
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            throw new FileReadException(e.getMessage());
        }
    }

    public List<Room> getCubicRoomsDesc(final List<Room> rooms) {
        return rooms.stream()
                .filter(this::isCubic)
                .sorted(Comparator.comparingInt(this::getWallpaperArea).reversed())
                .toList();
    }

    public int getTotalWallpaperArea(final List<Room> rooms) {
        int totalWallPaperArea = 0;

        for (final Room room : rooms) {
            totalWallPaperArea += getWallpaperArea(room);
        }
        return totalWallPaperArea;
    }

    public List<Room> getMultipleOccurrences(final List<Room> rooms) {
        final Set<Room> seenRooms = new HashSet<>();
        final List<Room> duplicateRooms = new ArrayList<>();

        for (final Room room : rooms) {
            if (!seenRooms.add(room)) {
                duplicateRooms.add(room);
            }
        }

        return duplicateRooms;
    }

    private int getSideLength(final String[] dimensions, final int index) {
        return Integer.parseInt(dimensions[index]);
    }

    private Room getRoom(final String line) {
        final String[] dimensions = line.split(divider);
        return new Room(getSideLength(dimensions, 0), getSideLength(dimensions, 1), getSideLength(dimensions, 2));
    }

    private int getWallpaperArea(final Room room) {
        return 2 * (room.length() * room.width() + room.width() * room.height() + room.height() * room.length()) +
                Math.min(Math.min(room.length() * room.width(), room.width() * room.height()), room.height() * room.length());
    }

    private boolean isCubic(final Room room) {
        return room.length() == room.width() &&
                room.width() == room.height();
    }

}
