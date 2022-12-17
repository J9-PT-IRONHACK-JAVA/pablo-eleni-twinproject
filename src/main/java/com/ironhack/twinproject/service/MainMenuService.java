package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.repository.PlayerRepository;
import com.ironhack.twinproject.utils.ConsoleColors;
import com.ironhack.twinproject.utils.Utils;
import lombok.NoArgsConstructor;
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
    private final PlayerRepository playerRepository;



    public void run() throws Exception {
        Utils.clearScreen();
        Utils.printLogo();

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
                    gameMenuService.onePlayerGame(currentPlayerLogged);
                    break;
                }

                case "2": {
                    System.out.println("You chose a game for 2 players");
                    //show 2 people game
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

            //USER SELECT AN SPECIFIC PLAYER
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

            //PLAYER CREATION
            }else if (input.equalsIgnoreCase("create")) {
                Utils.printWithColor("you want to create a player", ConsoleColors.WHITE_BOLD_BRIGHT);
                createPlayerRoutine();
            }

            //COMMAND NOT RECOGNIZED
            else if (!input.equalsIgnoreCase("exit")) {
                Utils.printWithColor("Unrecognized command!", ConsoleColors.RED);
            }

            //APPLICATION EXIT
            else {
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

                var player = new Player(input.trim().toLowerCase());
                playerService.save(player);
                System.out.printf(ConsoleColors.GREEN_BOLD+
                                "Congrats! new player created with name: %s\n\n"+
                                ConsoleColors.RESET,
                        player.getName(), player.getId());
                break;
            }
        }
    }
}
