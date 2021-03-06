public class CoffeeType {
    String name;
    int amountOfWater;
    int amountOfMilk;
    int amountOfCoffee;
    int price;

    public CoffeeType(String name, int amountOfWater, int amountOfMilk, int amountOfCoffee, int price) {
        this.name = name;
        this.amountOfWater = amountOfWater;
        this.amountOfMilk = amountOfMilk;
        this.amountOfCoffee = amountOfCoffee;
        this.price = price;
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public int getAmountOfMilk() {
        return amountOfMilk;
    }

    public void setAmountOfMilk(int amountOfMilk) {
        this.amountOfMilk = amountOfMilk;
    }

    public int getAmountOfCoffee() {
        return amountOfCoffee;
    }

    public void setAmountOfCoffee(int amountOfCoffee) {
        this.amountOfCoffee = amountOfCoffee;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}