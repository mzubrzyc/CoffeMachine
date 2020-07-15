import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
// a limited supply of water, milk, coffee beans, and disposable cups
// it counts how much money it gets for selling coffee.
// It can make different varieties of coffee: espresso, latte, and cappuccino (different ingredients but one cup)
// the coffee machine should be able to get replenished by a special worker.
// Third, another special worker should be able to take money from the coffee machine.
// standard input, which can be "buy", "fill", "take".

// Coffee type:
// For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
// For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
// And for the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.

// At the moment, the coffee machine has $550, 400 ml of water, 540 ml of milk, 120 g of coffee beans, and 9 disposable cups.

// Write a program that offers to buy one cup of coffee or to fill the ingredients or to take it's money.
// At the same time, the program should calculate how many ingredients it has left.
// And also display the number of ingredients before and after purchase.

        String[] coffeeResourcesQueries = new String[]{
                "\nWrite how many ml of water do you want to add:",
                "Write how many ml of milk do you want to add:",
                "Write how many grams of coffee beans do you want to add:",
                "Write how many disposable cups of coffee do you want to add:"
        };

        CoffeeMachineDevice coffeeMachine1 = new CoffeeMachineDevice(400, 540, 120, 9, 550);

        CoffeeType espresso = new CoffeeType("espresso", 250, 0, 16, 4);
        CoffeeType latte = new CoffeeType("latte", 350, 75, 20, 7);
        CoffeeType cappuccino = new CoffeeType("cappuccino", 200, 100, 12, 6);

        ArrayList<CoffeeType> availableCoffeeTypes = new ArrayList<>(Arrays.asList(espresso, latte, cappuccino));

        boolean end = false;

        while (!end) {

            System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
            String answer = scanner.nextLine();

            switch (answer) {
                case "buy":

                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    answer = scanner.nextLine();
                    switch (answer) {
                        case "1":
                            coffeeMachineAnswer(coffeeMachine1.coffeeRequest(availableCoffeeTypes.get(0)));
                            break;
                        case "2":
                            coffeeMachineAnswer(coffeeMachine1.coffeeRequest(availableCoffeeTypes.get(1)));
                            break;
                        case "3":
                            coffeeMachineAnswer(coffeeMachine1.coffeeRequest(availableCoffeeTypes.get(2)));
                            break;
                        case "back":
                            break;
                    }
                    break;
                case "fill":
                    int[] resources = resources(coffeeResourcesQueries);
                    coffeeMachine1.fillResources(resources);
                    break;
                case "take":
                    System.out.printf("%nI gave you $%d%n", coffeeMachine1.takeMoney());
                    break;
                case "remaining":
                    coffeeMachine1.getResources();
                    break;
                case "exit":
                    end = true;
            }
        }
    }

    private static int[] resources(String[] questions) {
        int[] ingredients = new int[questions.length];

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            ingredients[i] = Math.max(CoffeeMachine.scanner.nextInt(), 0);
        }
        scanner.nextLine();
        return ingredients;
    }

    private static void coffeeMachineAnswer(Boolean bool) {
        if (bool) {
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough water!");
        }
    }
}