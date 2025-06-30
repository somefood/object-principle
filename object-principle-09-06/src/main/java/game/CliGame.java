package game;

import game.command.Command;
import game.command.CommandParser;
import game.world.World;

public class CliGame {
    private World world;
    private CommandParser commandParser;
    private InputOutput io;
    private boolean running;

    public CliGame(World world, CommandParser commandParser, InputOutput io) {
        this.world = world;
        this.commandParser = commandParser;
        this.io = io;
    }

    public void run() {
        welcome();
        play();
        farewell();
    }

    private void welcome() {
        showGreetings();
        world.showRoom();
        showHelp();
    }

    private void showGreetings() {
        io.showLine("환영합니다!");
    }

    private void farewell() {
        io.showLine("\n게임을 종료합니다.");
    }

    private void play() {
        start();
        while (isRunning()) {
            String input = inputCommand();
            Command command = commandParser.parseCommand(input);
            executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch(command) {
            case Command.Move move -> world.tryMove(move.direction());
            case Command.Look() -> world.showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> stop();
            case Command.Unknown() -> showUnknownCommand();
            case Command.Inventory() -> world.showInventory();
            case Command.Take take -> world.takeItem(take.item());
            case Command.Drop drop -> world.dropItem(drop.item());
            case Command.Destroy destroy -> world.destroyItem(destroy.item());
            case Command.Throw aThrow -> world.throwItem(aThrow.item());
        }
    }

    private boolean isRunning() {
        return running == true;
    }

    private String inputCommand() {
        showPrompt();
        return input();
    }

    private void start() {
        running = true;
    }

    private void stop() {
        this.running = false;
    }

    private String input() {
        return io.input();
    }

    private void showPrompt() {
        io.show("> ");
    }

    private void showHelp() {
        io.showLine(commandParser.help());
    }

    private void showUnknownCommand() {
        io.showLine("이해할 수 없는 명령어입니다.");
    }
}
