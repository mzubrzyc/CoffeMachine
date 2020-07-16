public enum ActionAns {

    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit");

    final String stateName;

    ActionAns(String stateName) {
        this.stateName = stateName;

    }

    public static ActionAns getAns(String answer) {
        for (ActionAns actionAns : values()) {
            if (answer.equals(actionAns.stateName)) {
                return actionAns;
            }
        }
        return null;
    }
}
