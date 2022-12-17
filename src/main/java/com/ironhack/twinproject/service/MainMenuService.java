package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.repository.PlayerRepository;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;
@RequiredArgsConstructor
@Service
public class MainMenuService {
    private final Scanner scanner = new Scanner(System.in);
    static Long player1Id = null;
    static Player player1 = null;

    static Player player2 = null;
    static Player player2Id = null;

    private final PlayerService playerService;
    private final GameMenuService gameMenuService;
    private final PlayerRepository playerRepository;



    public void run() throws Exception {
        Utils.clearScreen();


        loggedUserRoutine();
    }
    public void loggedUserRoutine() throws Exception {

        String input;
        String[] options;
        String inputLowerCase;
        String[] optionsOriginalCase;
        do {
            Utils.clearScreen();
            Utils.printLogo();
            var gameMenu = """                                

                            \n \uD83D\uDCA1 Game on - Get your genius cap on \uD83D\uDC52
                             Choose an option to proceed
                            ====================s================ 
                            1- Play alone \uD83E\uDD3E
                            2- Two players \uD83E\uDD3C
                            3- Stats \uD83D\uDCC1
                            4- Help \u270B
                            5- Exit \uD83C\uDFC3
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
                    do {
                        player1 = playerSelectionRoutine(1);
                    } while (player1 == null);

                    gameMenuService.onePlayerGame(player1);
                    break;
                }

                case "2": {
                    System.out.println("You chose a game for 2 players");
                    do {
                        player1 = playerSelectionRoutine(1);
                    } while (player1 == null);

                    do {
                        player2 = playerSelectionRoutine(2);
                    } while (player2 == null);
                    gameMenuService.twoPlayerGame(player1,player2);
                    break;
                }

                case "3": {
                    Utils.printWithColor("SCORE OVERVIEW", ConsoleColors.YELLOW_BOLD);
                    var totalScore = playerRepository.listPlayersByScore();
                    var scoreInHistory = playerRepository.listPlayersByScoreInHistory();
                    var scoreInMusic = playerRepository.listPlayersByScoreInMusic();
                    var scoreInOlympics = playerRepository.listPlayersByScoreInOlympics();
                    var scoreInUSCities = playerRepository.listPlayersByScoreInUSCities();
                    var scoreInCars= playerRepository.listPlayersByScoreInCars();

                    System.out.println("Scores according to the total points:");
                    Utils.printReportResult(totalScore);

                    System.out.println("Scores in history questions:");
                    Utils.printReportResult(scoreInHistory);

                    System.out.println("Scores in music questions:");
                    Utils.printReportResult(scoreInMusic);

                    System.out.println("Scores in Olympics questions:" );
                    Utils.printReportResult(scoreInOlympics);

                    System.out.println("Scores in US cities questions:");
                    Utils.printReportResult(scoreInUSCities);

                    System.out.println("Scores in cars questions:");
                    Utils.printReportResult(scoreInCars);

                    break;
                }

                case "4": {
                    printHelp();
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

    public Player playerSelectionRoutine(int playerNumber) {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            Utils.printWithColor("\n## PLAYER " + playerNumber, ConsoleColors.YELLOW_BOLD);
            printAllPlayers();
            input = selectPlayer();

            //USER SELECT AN SPECIFIC PLAYER FROM THE LIST
            if (input.matches("\\d+")) {
                var selectedId = Long.parseLong(input);
                var playerFound = playerService.findById(selectedId);
                if (playerFound.isPresent()) {
                    //player1Id = selectedId;
                    return playerFound.get();
                } else {
                    Utils.printWithColor("Not a valid user selection", ConsoleColors.RED);
                    System.out.println();
                    return null;
                }

            //PLAYER CREATION
            }else if (input.equalsIgnoreCase("create")) {
                Utils.printWithColor("you want to create a player", ConsoleColors.WHITE_BOLD_BRIGHT);
                return createPlayerRoutine();
            }

            //COMMAND NOT RECOGNIZED
            else if (!input.equalsIgnoreCase("exit")) {
                Utils.printWithColor("Unrecognized command!", ConsoleColors.RED);
                return null;
            }

            //APPLICATION EXIT
            else {
                Utils.printWithColor("Goodbye!", ConsoleColors.WHITE_BOLD_BRIGHT);
                System.exit(0);
                return null;
            }
        }
        return null;
    }
    private Player createPlayerRoutine() {
        var input = "";
        boolean inputIsOK = true;
        do  {
            Utils.printWithColor("Enter your name:", ConsoleColors.WHITE_BOLD_BRIGHT);
            input = scanner.nextLine();
            if (input.isEmpty() || input.matches("\\d+")){
                Utils.printWithColor("Invalid input!", ConsoleColors.RED);
                inputIsOK = false;
            }
        } while ((!inputIsOK));
    //(!input.equalsIgnoreCase("BACK")) ||
        var player = new Player(input.trim().toLowerCase());
        playerService.save(player);
        System.out.printf(ConsoleColors.GREEN_BOLD+
                        "Congrats! new player created with name: %s\n\n"+
                        ConsoleColors.RESET,
                player.getName(), player.getId());
        return player;
    }

    public Player logIn () {
        var player = new Player();
        while ((player1 == null) || (player2 == null)) {
            player = playerSelectionRoutine(0);
            Utils.printWithColor("\nWelcome " + player.getName(), ConsoleColors.WHITE_BOLD_BRIGHT);
            Utils.pause(1500);
        }
        return player;
    }

    public void printAllPlayers () {
        Utils.printWithColor("Available users: ", ConsoleColors.WHITE_BOLD_BRIGHT);
        var player = playerService.findAll();
        for (Player p : player) {
            System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT+"%s - %s\n"+ConsoleColors.RESET
                    , p.getId(), p.getName());
        }

    }

    public String selectPlayer () {
        Utils.printWithColor("Pick your player, CREATE a new player, or EXIT",
                ConsoleColors.WHITE_BOLD_BRIGHT);
        return scanner.nextLine();
    }

    private void printHelp() throws IOException {
        Utils.clearScreen();
        Utils.printWithColor("""
                      ___       __ \s
                |__| |__  |    |__)\s
                |  | |___ |___ |   \s
                                  \s
                """, ConsoleColors.PURPLE_BOLD);

        System.out.println("Triviality\n");
        Utils.promptEnterKey();
        System.out.println("**************************************");
        System.out.println("\nAVAILABLE COMMANDS:");

        System.out.println("\n- 'Help'");
        System.out.println("    - 'help' : Show info and help.");
        System.out.println("\n- 'Play alone'");
        System.out.println("    - the player creates his persona or chooses one already created");
        System.out.println("    - launches a game of five rounds where each time the player gets to choose a category");
        System.out.println("\n- 'Two players'");
        System.out.println("    - both players create their personas or choose one already created");
        System.out.println("    - launches a game of three rounds where each time the each player gets to choose a category");
        System.out.println("\n- 'Stats'");
        System.out.println("    - shows an overview of the players' scores in total and per category");
        System.out.println("\n- 'Exit'");
        System.out.println("    - the player can exit the application");
        Utils.promptEnterKey();
        System.out.println(ConsoleColors.BLUE + "\nFor support contact: admin@triviality.com");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "**************************************" + ConsoleColors.RESET);
        Utils.clearScreen();
    }
}
