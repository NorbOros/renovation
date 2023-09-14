package org.example;

import org.example.domain.Room;
import org.example.exception.FileReadException;
import org.example.service.WallpaperService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws FileReadException {

        final String filePath = "C:\\Work\\tmp\\sample-input.txt"; // please change the file path

        WallpaperService wallpaperService = new WallpaperService();
        final List<Room> rooms = wallpaperService.parseInputFile(filePath);

        System.out.println("-------");
        System.out.println("Cubic shape rooms in descending order");
        System.out.println(wallpaperService.getCubicRoomsDesc(rooms));
        System.out.println("-------");
        System.out.println();
        System.out.println("-------");
        System.out.println("Number of total square feet of wallpaper the company should order");
        System.out.println(wallpaperService.getTotalWallpaperArea(rooms));
        System.out.println("-------");
        System.out.println();
        System.out.println("-------");
        System.out.println("Recurring rooms");
        System.out.println(wallpaperService.getMultipleOccurrences(rooms));
        System.out.println("-------");
    }
}
