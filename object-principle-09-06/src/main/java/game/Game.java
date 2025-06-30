package game;

import game.command.Command;
import game.command.CommandParser;
import game.world.World;

public class Game {
    private World world;
    private CommandParser commandParser;
    private Output output;
    private GameLoop gameLoop;

    public Game(World world, CommandParser commandParser, Output output) {
        this.world = world;
        this.commandParser = commandParser;
        this.output = output;
    }

    public void initialize(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void run() {
        welcome();
        gameLoop.play();
    }

    private void welcome() {
        showGreetings();
        world.showRoom();
        showHelp();
    }

    private void showGreetings() {
        output.showLine("환영합니다!");
    }

    private void farewell() {
        output.showLine("\n게임을 종료합니다.");
    }

    public void executeCommand(String input) {
        Command command = commandParser.parseCommand(input);
        executeCommand(command);
    }

    private void executeCommand(Command command) {
        switch(command) {
            case Command.Move move -> world.tryMove(move.direction());
            case Command.Look() -> world.showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> quit();
            case Command.Unknown() -> showUnknownCommand();
            case Command.Inventory() -> world.showInventory();
            case Command.Take take -> world.takeItem(take.item());
            case Command.Drop drop -> world.dropItem(drop.item());
            case Command.Destroy destroy -> world.destroyItem(destroy.item());
            case Command.Throw aThrow -> world.throwItem(aThrow.item());
        }
    }

    private void quit() {
        farewell();
        gameLoop.stop();
    }

    private void showHelp() {
        output.showLine(commandParser.help());
    }

    private void showUnknownCommand() {
        output.showLine("이해할 수 없는 명령어입니다.");
    }
}

