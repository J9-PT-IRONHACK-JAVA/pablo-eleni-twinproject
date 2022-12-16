package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.CategoryTypes;
import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.dto.Question;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    MainMenuService mainMenuService;

        int gamePoints = 0;

        //5 rounds of questions
        for (int i = 0; i < 5; i++) {
            boolean playNewGame = false;

            var category = chooseCategory();
            question = getRandomQuestion(category);
            System.out.println("For " + question.getValue() +
                               " points:\n " +
                                question.getQuestion() + "\n");
            if (getUserAnswer().equals(question.getAnswer())) {
                Utils.printWithColor("Congrats, you got the answer right!", ConsoleColors.GREEN_BOLD);
                Utils.printWithColor("You got " + question.getValue() + " Points", ConsoleColors.GREEN_BOLD);
                System.out.println("==========================================\n");
                stopBeforeContinue ();
                currentPlayerLogged.addPoints(question.getValue(), category);
                gamePoints = gamePoints + question.getValue();
            }
            else {
                Utils.printWithColor("You dumb! Answer is not correct", ConsoleColors.RED_BOLD);
                Utils.printWithColor("The correct answer is " + question.getAnswer(), ConsoleColors.BLUE);
                stopBeforeContinue ();
            }

            if  (i == 4) {
                askToContinue (gamePoints);
                if (getStringInput().equalsIgnoreCase("y")) {
                    i = 0; //counter reset to re-enter in the for to star the game
                }
                else {
                    System.out.println("Well played. See you next time");
                    mainMenuService.playerSelectionRoutine();
                    //System.exit(0);
                }
            }
        }

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


    public static void stopBeforeContinue() {
        System.out.println("Press any key to continue");
        var input = scanner.nextLine();
        }

    public static String getStringInput() {
        return scanner.nextLine();
    }

    public void askToContinue (int gamePoints) {
        System.out.println("Game is over, you got " + gamePoints + " points \n\n");
        funnyMessages(gamePoints);
        System.out.println("Do you want to play a new game?");
        System.out.println("[Y] Yes");
        System.out.println("[N] No, return main menu");
    }

    //print funny messages at the end of the game about player's score
    public void funnyMessages (int gamePoints) {
        if (gamePoints == 0) {
            System.out.println("Really? 0 points?.....Do you know what a book is? ");
        }
        else if (gamePoints < 100)  {
            System.out.println("You got some points but you should start to go to the library more often");
        } else if (gamePoints <= 800) {
            System.out.println("....so so, a tour around Wikipedia would not make you any damage");
        } else if ( gamePoints <= 1200) {
            System.out.println("Well done, you know more than a 8 years old kid ");
        } else if (gamePoints > 1200) {
            System.out.println("Congrats!! you are a genius. You could be working at Harvard University");
        }

    }
}

