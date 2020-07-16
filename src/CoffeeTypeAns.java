public enum CoffeeTypeAns {

    ESPRESSO("1"),
    LATTE("2"),
    CAPPUCCINO("3");

    String answer;

    CoffeeTypeAns(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }


}
