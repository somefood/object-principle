package game.command;

import game.InputOutput;
import game.world.worldmap.Direction;

public class CommandParser {
    private InputOutput io;

    public CommandParser(InputOutput io) {
        this.io = io;
    }

    public Command parseCommand(String input) {
        return parseCommand(split(input));
    }

    private Command parseCommand(String[] commands) {
        return switch (commands[0]) {
            case "go" ->
                    switch (commands[1]) {
                        case "north" -> new Command.Move(Direction.NORTH);
                        case "south" -> new Command.Move(Direction.SOUTH);
                        case "east" -> new Command.Move(Direction.EAST);
                        case "west" -> new Command.Move(Direction.WEST);
                        default -> new Command.Unknown();
                    };
            case "inventory" -> new Command.Inventory();
            case "take" -> new Command.Take(commands[1]);
            case "drop" -> new Command.Drop(commands[1]);
            case "destroy" -> new Command.Destroy(commands[1]);
            case "throw" -> new Command.Throw(commands[1]);
            case "look" -> new Command.Look();
            case "help" -> new Command.Help();
            case "quit" -> new Command.Quit();
            default -> new Command.Unknown();
        };
    }

    private String[] split(String input) {
        return input.toLowerCase().trim().split("\\s+");
    }

    public String help() {
        return "다음 명령어를 사용할 수 있습니다.\n" +
                "go {north|east|south|west} - 이동" +
                ", look - 보기" +
                ", inventory - 인벤토리" +
                ", take {item} - 줍기" +
                ", drop {item} - 버리기" +
                ", destroy {item} - 파괴하기" +
                ", throw {item} - 던지기" +
                ", help - 도움말" +
                ", quit - 게임 종료";
    }
}
