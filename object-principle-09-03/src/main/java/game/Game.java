package game;

import game.command.Command;
import game.command.CommandParser;
import game.item.Carrier;
import game.item.Item;
import game.player.Player;
import game.worldmap.Direction;

import java.util.stream.Collectors;

public class Game {
    private Player player;
    private CommandParser commandParser;
    private InputOutput io;
    private boolean running;

    public Game(Player player, CommandParser commandParser, InputOutput io) {
        this.player = player;
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
        showRoom();
        showHelp();
    }

    private void showGreetings() {
        io.showLine("환영합니다!");
    }

    private void showHelp() {
        io.showLine("다음 명령어를 사용할 수 있습니다.");
        io.showLine("go {north|east|south|west} - 이동, look - 보기, help - 도움말, quit - 게임 종료");
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
            case Command.Move move -> tryMove(move.direction());
            case Command.Look() -> showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> stop();
            case Command.Unknown() -> showUnknownCommand();
            case Command.Inventory() -> showInventory();
            case Command.Take take -> takeItem(take.item());
            case Command.Drop drop -> dropItem(drop.item());
            case Command.Destroy destroy -> destroyItem(destroy.item());
            case Command.Throw aThrow -> throwItem(aThrow.item());
        }
    }

    private void tryMove(Direction direction) {
        if (player.canMove(direction)) {
            player.move(direction);
            showRoom();
            return;
        }

        showBlocked();
    }

    private void showBlocked() {
        io.showLine("이동할 수 없습니다.");
    }

    private void showRoom() {
        io.showLine("당신은 [" + player.currentRoom().name() + "]에 있습니다.");
        io.showLine(player.currentRoom().description());
        if (!player.currentRoom().items().isEmpty()) {
            io.showLine(player.currentRoom().items().stream()
                    .map(Item::name)
                    .collect(Collectors.joining(", ", "아이템: [ ", " ]")));
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

    private void showUnknownCommand() {
        io.showLine("이해할 수 없는 명령어입니다.");
    }

    private String input() {
        return io.input();
    }

    private void showPrompt() {
        io.show("> ");
    }

    private void takeItem(String itemName) {
        transfer(player.currentRoom(), player, itemName,
                itemName + "을(를) 얻었습니다.",
                itemName + "을(를) 얻을 수 없습니다.");
    }

    private void dropItem(String itemName) {
        transfer(player, player.currentRoom(), itemName,
                itemName + "을(를) 버렸습니다.",
                itemName + "을(를) 버릴 수 없습니다.");
    }

    private void throwItem(String itemName) {
        transfer(player, player.worldMap(), itemName,
                itemName + "을(를) 어딘가로 던졌습니다.",
                itemName + "을(를) 던질 수 없습니다.");
    }

    private void transfer(Carrier source, Carrier target,
                          String itemName, String success, String fail) {
        Transfer transfer = new Transfer(source, target, itemName);

        if (transfer.isPossible()) {
            transfer.perform();
            io.showLine(success);
            return;
        }

        io.showLine(fail);
    }

    private void showInventory() {
        io.showLine(
                player.items().stream()
                        .map(Item::name)
                        .collect(Collectors.joining(", ", "인벤토리 목록: [ ", " ]")));
    }

    private void destroyItem(String itemName) {
        Destroy destroy = new Destroy(player, player.currentRoom(), itemName);
        if (destroy.isPossible()) {
            destroy.perform();
            io.showLine(itemName + "을(를) 파괴했습니다.");
            return;
        }

        io.showLine(itemName + "을(를) 파괴할 수 없습니다.");
    }
}
