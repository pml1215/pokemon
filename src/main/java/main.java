import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Pokemon pokemon1 = new Pokemon(1, 100, 50, 30, "Pikachu");
        Pokemon pokemon2 = new Pokemon(2, 100, 40, 20, "Charmander");

        while (pokemon1.checkAlive() && pokemon2.checkAlive()) {
            // reset the defend status for each round
            pokemon1.resetDefendStatus();
            pokemon2.resetDefendStatus();

            try{
                System.out.println("Player 1 choose action for " + pokemon1.getName() + ":");
                System.out.println("1. Attack 2. Defense 3. Heal");
                int player1_action = scan.nextInt();

                Random random = new Random();
                int comp_action = random.nextInt(3) + 1;

                // To make sure the order is correct, we need to check the action of players and assign the sequence of the actions

                // If player 1 choose attack, player 2 choose defense, then we will call the player 2 defense method first
                // and then call the player 1 attack method, vice verse.
                // It is because we need to call the defense method first to generate the defense result first.
                // Otherwise, the side who chooses to defense could never defense the attack from the rival.
                if (player1_action == 1 && comp_action == 2) {
                    pokemon2.defense();
                    pokemon1.attack(pokemon2);
                } else if (player1_action == 2 && comp_action == 1) {
                    pokemon1.defense();
                    pokemon2.attack(pokemon1);
                }

                // Since the player 1 always has the priority to action first,
                // the player 1's actions will be called first.(Except the situation of one chooses attack and the other chooses defense.)
                else if (player1_action == 1 && comp_action == 1) {
                    // We need to check whether the rival is alive after the attack, if the rival is dead,
                    // we don't need to call the rival's method.
                    pokemon1.attack(pokemon2);
                    if (pokemon2.checkAlive()) {
                        pokemon2.attack(pokemon1);
                    }
                } else if (player1_action == 1 && comp_action == 3) {
                    // Same as above
                    pokemon1.attack(pokemon2);
                    if (pokemon2.checkAlive()) {
                        pokemon2.heal();
                    }
                } else if (player1_action == 2 && comp_action == 2) {
                    pokemon1.defense();
                    pokemon2.defense();
                } else if (player1_action == 2 && comp_action == 3) {
                    pokemon1.defense();
                    pokemon2.heal();
                } else if (player1_action == 3 && comp_action == 1) {
                    pokemon1.heal();
                    pokemon2.attack(pokemon1);
                } else if (player1_action == 3 && comp_action == 2) {
                    pokemon1.heal();
                    pokemon2.defense();
                } else if (player1_action == 3 && comp_action == 3) {
                    pokemon1.heal();
                    pokemon2.heal();
                } else {
                    System.out.println("Wrong number! Please enter again!");
                    continue;
                }

            System.out.println(pokemon1.getName() + " remaining HP: " + pokemon1.getBlood());
            System.out.println(pokemon2.getName() + " remaining HP: " + pokemon2.getBlood()+"\n");

            if (pokemon1.checkAlive() && !pokemon2.checkAlive()) {
                System.out.println("Player 1 wins!");
            }
            else if (!pokemon1.checkAlive() && pokemon2.checkAlive()){
                System.out.println("Computer wins!");
            }
        }
        catch (Exception e){
            System.out.println("Wrong Input! Please enter again!");
            scan.next();
        }
        }
    }
}