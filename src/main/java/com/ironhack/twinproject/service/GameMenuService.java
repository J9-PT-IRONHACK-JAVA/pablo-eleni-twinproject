package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.CategoryTypes;
import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.dto.Question;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@RequiredArgsConstructor
@Service
public class GameMenuService {
    private static final Scanner scanner = new Scanner(System.in);

    private final QuestionService questionService;
    private final PlayerService playerService;

    private Question question;

    public  void onePlayerGame(Player currentPlayerLogged) {
        int gamePoints = 0;

        //5 rounds of questions
        for (int i = 0; i < 5; i++) {
            var category = chooseCategory();
            question = getRandomQuestion(category);
            System.out.println("For " + question.getValue() +
                               " points:\n " +
                                question.getQuestion() + "\n");
            if (getUserAnswer().equals(question.getAnswer())) {
                Utils.printWithColor("Congrats, you got the answer right!", ConsoleColors.GREEN_BOLD);
                Utils.printWithColor("You got " + question.getValue() + " Points", ConsoleColors.GREEN_BOLD);
                System.out.println("==========================================\n");
                currentPlayerLogged.addPoints(question.getValue(), category);
                gamePoints = gamePoints + question.getValue();
            }
            else {
                Utils.printWithColor("You dumb! Answer is not correct", ConsoleColors.RED_BOLD);
                Utils.printWithColor("The correct answer is " + question.getAnswer(), ConsoleColors.BLUE);
            }
        }
        System.out.println("Game is over, you got " + gamePoints + " points");
        Utils.printWithColor("You dumb! Answer is not correct", ConsoleColors.RED_BOLD);
    }

    private String getUserAnswer() {
        System.out.println("Your answer is:");
        return scanner.nextLine();
    }
/*
    private void addPoints(Player player, CategoryTypes category, int value) {
        player.addPoints(value, category);
    }*/

    public CategoryTypes chooseCategory () {
        String input;
        String[] options;
        String inputLowerCase;
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
                return CategoryTypes.HISTORY;
            }

            case "2": {
                return CategoryTypes.MUSIC;
            }

            case "3": {
                return CategoryTypes.OLYMPICS;
            }

            case "4": {
                return CategoryTypes.CARS;
            }

            case "5": {
                return CategoryTypes.CITIES;
            }

            default: {
                String message = "Error in command! Please write 'help' to see all commands...";
                Utils.printWithColor(message, ConsoleColors.RED_BOLD);
                Utils.promptEnterKey();
                Utils.clearScreen();
                return null;
            }
        }
    }

    public Question getRandomQuestion(CategoryTypes category) {
        System.out.println("You chose the " + category + " category");
        var categoryQuestions= questionService.getQuestion(category.getValue());
        int min = 1;
        int max = categoryQuestions.size()-1;
        Scanner scanner = new Scanner(System.in);
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return categoryQuestions.get(random_int);
    }
}

