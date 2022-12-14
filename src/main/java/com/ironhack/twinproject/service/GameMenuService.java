package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.CategoryTypes;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@RequiredArgsConstructor
@Service
public class GameMenuService {
    private static final Scanner scanner = new Scanner(System.in);

    private final QuestionService questionService;

    public  void onePlayerGame() throws Exception {

        String input;
        String[] options;
        String inputLowerCase;
        do {
            Utils.clearScreen();
            var categoriesMenu = """                                
                            %s: Pick a category
                            ==================================== 
                            1- History
                            2- Music
                            3- Olympic Games
                            4- Cars
                            5- US cities
                            ====================================
                            Type your selection, and press enter:
                            """;
            Utils.printWithColor(categoriesMenu, ConsoleColors.WHITE_BOLD_BRIGHT);

            input = scanner.nextLine().trim();
            inputLowerCase = input.toLowerCase();

            options = inputLowerCase.split(" ");

            switch (options[0]) {
                case "1": {
                    playGame(CategoryTypes.HISTORY);
                    break;
                }

                case "2": {
                    playGame(CategoryTypes.MUSIC);
                    break;
                }

                case "3": {
                    playGame(CategoryTypes.OLYMPICS);
                    break;
                }

                case "4": {
                    playGame(CategoryTypes.CARS);
                    break;
                }

                case "5": {
                    playGame(CategoryTypes.CITIES);
                    break;
                }

                default: {
                    String message = "Error in command! Please write 'help' to see all commands...";
                    Utils.printWithColor(message, ConsoleColors.RED_BOLD);
                    Utils.promptEnterKey();
                    Utils.clearScreen();
                }
            }
        }while(!options[0].equals("exit"));

    }
    public  void playGame(CategoryTypes category) {
        System.out.println("You chose the " + category + " category");
        int categoryChosen = category.getValue();
        System.out.println(categoryChosen);
        var categoryQuestions= questionService.getQuestion(categoryChosen);
        System.out.println(categoryQuestions);
    }
}

