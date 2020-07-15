public class CoffeeMachineDevice {
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;

    public CoffeeMachineDevice() {
        this.water = 0;
        this.milk = 0;
        this.coffee = 0;
        this.cups = 0;
        this.money = 0;
    }

    public CoffeeMachineDevice(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
    }

    public int enoughResources(CoffeeType coffeeType) {
        int resources;

        resources = water - coffeeType.getAmountOfWater() <= 0 ? -1 : milk - coffeeType.getAmountOfMilk() <= 0 ?
                -1 : coffee - coffeeType.getAmountOfCoffee() <= 0 ? -1 : 1;

        return resources;
    }

    public void coffeeCupsRequest(CoffeeType coffeeType, int numCups) {
        int cupsAvailable = enoughResources(coffeeType);
        if (cupsAvailable < numCups) {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", cupsAvailable);
        } else if (cupsAvailable == numCups) {
            System.out.printf("Yes, I can make that amount of coffee%n");
        } else {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n", cupsAvailable - numCups);
        }
    }

    public void getResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water%n%d of milk%n%d of coffee beans%n%d of disposable cups%n$%d of money%n",
                water, milk, coffee, cups, money);
    }

    public boolean coffeeRequest(CoffeeType coffeeType) {
        if (enoughResources(coffeeType) > 0) {
            water -= coffeeType.getAmountOfWater();
            milk -= coffeeType.getAmountOfMilk();
            coffee -= coffeeType.getAmountOfCoffee();
            cups -= 1;
            money += coffeeType.getPrice();
            return true;
        }
        return false;
    }

    public void fillResources(int[] resources) {
            water += resources[0];
            milk += resources[1];
            coffee += resources[2];
            cups += resources[3];
    }

    public int takeMoney() {
        int preAmount = this.money;
        this.money = 0;
        return preAmount;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }
}