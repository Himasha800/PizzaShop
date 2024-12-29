
class CommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }

    public void undoCommand() {
        if (command != null) {
            command.undo();
        } else {
            System.out.println("No command set.");
        }
    }
}
