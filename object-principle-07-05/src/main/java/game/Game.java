package game;

public class Game {
    private final Player player;
    private final CommandParser commandParser;
    private final InputOutput inputOutput;
    private boolean running;

    public Game(Player player, CommandParser commandParser, InputOutput io) {
        this.player = player;
        this.commandParser = commandParser;
        this.inputOutput = io;
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
        System.out.println("환영합니다!");
    }
    

    public void showHelp() {
        System.out.println("다음 명령어를 사용할 수 있습니다.");
        System.out.println("go {north|east|south|west} - 이동, look - 보기, help - 도움말, quit - 게임 종료");
    }

    private void farewell() {
        inputOutput.showLine("\n게임을 종료합니다.");
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
        switch (command) {
            case Command.Move move -> tryMove(move.direction());
            case Command.Look() -> showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> stop();
            case Command.Unknown() -> showUnknownCommand();
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

    private String inputCommand() {
        showPrompt();
        return input();
    }

    private void start() {
        running = true;
    }
    
    private boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    public void showUnknownCommand() {
        System.out.println("이해할 수 없는 명령어입니다.");
    }

    private String input() {
        return inputOutput.input();
    }

    private void showPrompt() {
        inputOutput.show("> ");
    }

    private void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }

    public void showRoom() {
        System.out.println("당신은 [" + player.currentRoom().name() + "]에 있습니다.");
        System.out.println(player.currentRoom().description());
    }

}
