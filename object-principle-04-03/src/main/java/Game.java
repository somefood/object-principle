import java.util.Scanner;

public class Game {
    private int width, height;
    private Room[] rooms;
    private int x, y;
    private boolean running;

    public Game() {
        this.x = 0;
        this.y = 2;
        this.width = 2;
        this.height = 3;
        this.rooms = arrangeRooms(
                new Room(0, 0, "샘", "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다."),
                new Room(0, 1, "다리", "큰 강 위에 돌로 만든 커다란 다리가 있습니다."),
                new Room(1, 1, "성", "용왕이 살고 있는 성에 도착했습니다."),
                new Room(0, 2, "언덕", "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다."),
                new Room(1, 2, "동굴", "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다."));
    }

    private Room[] arrangeRooms(Room ... rooms) {
        Room[] result = new Room[width * height];
        for(var room : rooms) {
            result[room.x() + room.y() * width] = room;
        }
        return result;
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

    private void showRoom() {
        System.out.println("당신은 [" + roomAt(x, y).name() + "]에 있습니다.");
        System.out.println(roomAt(x, y).description());
    }

    private void showHelp() {
        System.out.println("다음 명령어를 사용할 수 있습니다.");
        System.out.println("go {north|east|south|west} - 이동, look - 보기, help - 도움말, quit - 게임 종료");
    }

    private void farewell() {
        System.out.println("\n게임을 종료합니다.");
    }

    private void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);

        start();
        while (isRunning()) {
            String input = inputCommand(scanner);
            parseCommand(input);
        }
    }

    private String inputCommand(Scanner scanner) {
        showPrompt();
        return input(scanner);
    }

    private void start() {
        running = true;
    }
    
    private boolean isRunning() {
        return running;
    }

    private void stop() {
        this.running = false;
    }

    private void parseCommand(String input) {
        String[] commands = input.toLowerCase().trim().split("\\s+");
        switch (commands[0]) {
            case "go" -> {
                switch (commands[1]) {
                    case "north" -> moveNorth();
                    case "south" -> moveSouth();
                    case "east" -> moveEast();
                    case "west" -> moveWest();
                    default -> showUnknownCommand();
                }
            }
            case "look" -> showRoom();
            case "help" -> showHelp();
            case "quit" -> stop();
            default -> showUnknownCommand();
        }
    }

    private void moveWest() {
        tryMove( -1, 0);
    }

    private void moveEast() {
        tryMove(1, 0);
    }

    private void moveSouth() {
        tryMove(0, 1);
    }

    private void moveNorth() {
        tryMove(0, -1);
    }

    private void showUnknownCommand() {
        System.out.println("이해할 수 없는 명령어입니다.");
    }

    private String input(Scanner scanner) {
        return scanner.nextLine();
    }

    private void showPrompt() {
        System.out.print("> ");
    }

    private void tryMove(int incX, int incY) {
        if (isBlocked(x + incX, y + incY)) {
            showBlocked();
        } else {
            this.x += incX;
            this.y += incY;
            showRoom();
        }
    }

    private boolean isBlocked(int x, int y) {
        return isExcluded(x, y) || roomAt(x, y) == null;
    }

    private boolean isExcluded(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    private Room roomAt(int x, int y) {
        return rooms[x + y * width];
    }
}
