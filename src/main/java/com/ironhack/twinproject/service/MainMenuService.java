package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Scanner;
@RequiredArgsConstructor
@Service
public class MainMenuService {
    private final Scanner scanner = new Scanner(System.in);
    static Long currentPlayerId = null;
    static Player currentPlayerLogged = null;

    private final PlayerService playerService;
    private final GameMenuService gameMenuService;


    public void run() throws Exception {
        Utils.clearScreen();
        //PRINT LOGO

        while (currentPlayerId == null) {
            playerSelectionRoutine();
            Utils.printWithColor("\nWelcome " + currentPlayerLogged.getName(), ConsoleColors.WHITE_BOLD_BRIGHT);
            Utils.pause(1500);
            loggedUserRoutine();
        }
    }
    private void loggedUserRoutine() throws Exception {

        String input;
        String[] options;
        String inputLowerCase;
        String[] optionsOriginalCase;
        do {
            Utils.clearScreen();
            var gameMenu = """                                
                            \n** Game on - Get your genius cap on **
                            %s: Choose an option to proceed
                            ==================================== 
                            1- Play alone
                            2- Two players
                            3- Stats
                            4- Help
                            5- Exit
                            ====================================
                            Type your selection, and press enter:
                            """;
            Utils.printWithColor(gameMenu, ConsoleColors.WHITE_BOLD_BRIGHT);

            input = scanner.nextLine().trim();
            inputLowerCase = input.toLowerCase();

            options = inputLowerCase.split(" ");
            optionsOriginalCase = input.split(" ");

            switch (options[0]) {
                case "1": {
                    System.out.println("You chose a game for one player");
                    gameMenuService.onePlayerGame();
                    break;
                }

                case "2": {
                    System.out.println("You chose a game for 2 players");
                    //show 2 people game
                    break;
                }

                case "3": {
                    System.out.println("Show stats");
                    //show stats table
                    break;
                }

                case "4": {
                    System.out.println("Help");
                    //show help instructions
                    break;
                }

                case "5": {
                    Utils.printWithColor("Goodbye!", ConsoleColors.WHITE_BOLD_BRIGHT);
                    System.exit(0);
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
    public void playerSelectionRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            Utils.printWithColor("Available users: ", ConsoleColors.WHITE_BOLD_BRIGHT);
            var player = playerService.findAll();
            for (Player p : player) {
                System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT+"%s - %s\n"+ConsoleColors.RESET
                        , p.getId(), p.getName());
            }
            Utils.printWithColor("Pick your player, CREATE a new player, or EXIT",
                    ConsoleColors.WHITE_BOLD_BRIGHT);
            input = scanner.nextLine();
            if (input.matches("\\d+")) {
                var selectedId = Long.parseLong(input);
                var playerFound = playerService.findById(selectedId);
                if (playerFound.isPresent()) {
                    currentPlayerId = selectedId;
                    currentPlayerLogged = playerFound.get();
                    break;
                } else {
                    Utils.printWithColor("Not a valid user selection", ConsoleColors.RED);
                    System.out.println();
                }
            }else if (input.equalsIgnoreCase("create")) {
                Utils.printWithColor("you want to create a player", ConsoleColors.WHITE_BOLD_BRIGHT);
                createPlayerRoutine();
            }
            else if (!input.equalsIgnoreCase("exit")) {
                Utils.printWithColor("Unrecognized command!", ConsoleColors.RED);
            } else {
                Utils.printWithColor("Goodbye!", ConsoleColors.WHITE_BOLD_BRIGHT);
                System.exit(0);
            }
        }
    }
    private void createPlayerRoutine() {
        var input = "";
        while (!input.equalsIgnoreCase("BACK")) {
            Utils.printWithColor("Enter your name:", ConsoleColors.WHITE_BOLD_BRIGHT);
            input = scanner.nextLine();
            if (input.isEmpty() || input.matches("\\d+")){
                Utils.printWithColor("Invalid input!", ConsoleColors.RED);
            } else if(!input.equalsIgnoreCase("BACK")) {

                var player = playerService.save(new Player(input.trim().toLowerCase()));
                System.out.printf(ConsoleColors.GREEN_BOLD+
                                "Congrats! new player created with name: %s and id: %s\n\n"+
                                ConsoleColors.RESET,
                        player.getName(), player.getId());
                break;
            }
        }
    }
}
