import java.util.Random;
import java.util.Scanner;


public class RockPaperScissors {

    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;

    private enum Move {
        ROCK, PAPER, SCISSORS;

        public int compareMoves(Move otherMove) {
            // Tie
            if (this == otherMove)
                return 0;

            switch (this) {
            case ROCK:
                return (otherMove == SCISSORS ? 1 : -1);
            case PAPER:
                return (otherMove == ROCK ? 1 : -1);
            case SCISSORS:
                return (otherMove == PAPER ? 1 : -1);
            }
            return 0;
        }
    }

    public void startGame() {
        System.out.println("\nWelcome to Rock, Paper, Scissors!");

        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nYou played " + userMove + ".");
        System.out.println("Computer played " + computerMove + ".\n");

        // Move Compare

        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
        case 0: // tie
            System.out.println("Tie!");
            break;
        case 1: // User Wins
            System.out.println(userMove + " beats " + computerMove);
            userScore++;
            break;
        case -1: // Computer Wins
            System.out.println(computerMove + " beats " + userMove);
            computerScore++;
            break;
        }
        numberOfGames++;

        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

    private void printGameStats(){
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins +((double)ties)/2)/numberOfGames;


        System.out.printf("\n\n|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
            "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");
        

            System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n\n",
            wins, losses, ties, numberOfGames, percentageWon * 100);

    }

    private class User {
        private Scanner inputScanner;
 
        public User() {
            inputScanner = new Scanner(System.in);
        }
 
        public Move getMove() {
            // Prompt 
            System.out.print("Rock, paper, or scissors? ");
 
            // Get the user input
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                switch (firstLetter) {
                case 'R':
                    return Move.ROCK;
                case 'P':
                    return Move.PAPER;
                case 'S':
                    return Move.SCISSORS;
                }
            }

            return getMove();
        }
 
        public boolean playAgain() {
            System.out.print("Do you want to play again? ");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }

    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }



    public RockPaperScissors() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGame();
        }
}
