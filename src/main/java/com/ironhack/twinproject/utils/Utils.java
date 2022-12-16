package com.ironhack.twinproject.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class Utils {
    public boolean isNumeric(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static void promptEnterKey() {
        System.out.println(ConsoleColors.BLUE+
                "Press ENTER to continue..."+
                ConsoleColors.RESET);
        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void clearScreen(){
        for (int i = 0; i < 20; i++) {
            System.out.println("\n");
        }
    }

    public static void pause(int milis){
        milis = Math.min(milis,5000);
        try{
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }

    public static void printWithColor(String text, String color){
        System.out.println(color + text + ConsoleColors.RESET);
    }
    public static void printLogo(){
        var logo = ("""
                    _______   _       _       _ _ _        \s
                   |__   __| (_)     (_)     | (_) |       \s
                      | |_ __ ___   ___  __ _| |_| |_ _   _\s
                      | | '__| \\ \\ / / |/ _` | | | __| | | |
                      | | |  | |\\ V /| | (_| | | | |_| |_| |
                      |_|_|  |_| \\_/ |_|\\__,_|_|_|\\__|\\__, |
                                                       __/ |
                                                      |___/\s
                """);

        printWithColor(logo, ConsoleColors.WHITE_BOLD_BRIGHT);

    }


}
