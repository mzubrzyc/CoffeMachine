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


        // Defining new Coffee Types
        CoffeeType espresso = new CoffeeType("espresso", 250, 0, 16, 4);
        CoffeeType latte = new CoffeeType("latte", 350, 75, 20, 7);
        CoffeeType cappuccino = new CoffeeType("cappuccino", 200, 100, 12, 6);

        ArrayList<CoffeeType> availableCoffeeTypes = new ArrayList<>(Arrays.asList(espresso, latte, cappuccino));

        // Defining a new CoffeeMachine
        CoffeeMachineDevice coffeeMachine1 = new CoffeeMachineDevice(
                400, 540, 120, 9, 550, availableCoffeeTypes);

        coffeeMachineInterface(coffeeMachine1);

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

    private static void coffeeMachineInterface(CoffeeMachineDevice coffeeMachine) {

        String[] coffeeResourcesQueries = new String[]{
                "\nWrite how many ml of water do you want to add:",
                "Write how many ml of milk do you want to add:",
                "Write how many grams of coffee beans do you want to add:",
                "Write how many disposable cups of coffee do you want to add:"
        };

        ArrayList<CoffeeType> availableCoffeeTypes = coffeeMachine.getCoffeeTypes();

        CoffeeMachineState coffeeMachineState = coffeeMachine.getState();

        switch (coffeeMachineState) {
            case ACTION:
                System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
                String answer = scanner.nextLine();
                interfaceAction(coffeeMachine, availableCoffeeTypes, answer, coffeeResourcesQueries);
                break;
            case CHOOSING_COFFEE:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                answer = scanner.nextLine();
                for (CoffeeTypeAns type : CoffeeTypeAns.values()) {
                    if (answer.equals(type.getAnswer())) {
                        int coffeeIndex = Integer.parseInt(answer) - 1;
                        coffeeMachineAnswer(coffeeMachine.coffeeRequest(availableCoffeeTypes.get(coffeeIndex)));
                    }
                }
                break;
            case REPLENISHING:
                int[] resources = resources(coffeeResourcesQueries);
                coffeeMachine.fillResources(resources);
                break;
            case TAKING_MONEY:
                System.out.printf("%nI gave you $%d%n", coffeeMachine.takeMoney());
                break;
            case CHECKING_RESOURCES:
                coffeeMachine.getResources();
                break;
            case EXITING:
                break;
        }

        if (!coffeeMachine.getState().equals(CoffeeMachineState.EXITING)) {
            coffeeMachine.setState(CoffeeMachineState.ACTION);
            coffeeMachineInterface(coffeeMachine);
        }
    }

    private static void interfaceAction(CoffeeMachineDevice coffeeMachine, ArrayList<CoffeeType> availableCoffeeTypes,
                                        String answer, String[] coffeeResourcesQueries) {

        ActionAns actionAnswer = ActionAns.getAns(answer);

        if (actionAnswer != null) {
            switch (actionAnswer) {
                case BUY:
                    coffeeMachine.setState(CoffeeMachineState.CHOOSING_COFFEE);
                    coffeeMachineInterface(coffeeMachine);
                    break;
                case FILL:
                    coffeeMachine.setState(CoffeeMachineState.REPLENISHING);
                    coffeeMachineInterface(coffeeMachine);
                    break;
                case TAKE:
                    coffeeMachine.setState(CoffeeMachineState.TAKING_MONEY);
                    coffeeMachineInterface(coffeeMachine);
                    break;
                case REMAINING:
                    coffeeMachine.setState(CoffeeMachineState.CHECKING_RESOURCES);
                    coffeeMachineInterface(coffeeMachine);
                    break;
                case EXIT:
                    coffeeMachine.setState(CoffeeMachineState.EXITING);
                    coffeeMachineInterface(coffeeMachine);
            }
        }
    }
}