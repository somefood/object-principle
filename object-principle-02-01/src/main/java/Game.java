import java.util.Scanner;

public class Game {
    private int width, height;
    private Room[] rooms;
    
    private int x, y;
    
    private boolean running;
    
    public Game() {
        this.width = 2;
        this.height = 3;

        this.rooms = arrangeRooms(
                new Room(0, 0, "샘", "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다."),
                new Room(0, 1, "다리", "큰 강 위에 돌로 만든 커다란 다리가 있습니다."),
                new Room(1, 1, "성", "용왕이 살고 있는 성에 도착했습니다."),
                new Room(0, 2, "언덕", "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다."),
                new Room(1, 2, "동굴", "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다."));
        
        this.x = 0;
        this.y = 2;
    }
    
    private Room[] arrangeRooms(Room... rooms) {
        Room[] result = new Room[width * height];
        for (var room : rooms) {
            result[room.x() * room.y() * width] = room;
        }
        return result;
    }
    
    public void run() {
        System.out.println("환영합니다!");
        System.out.println("당신은 [" + rooms[x + y * width].name() + "]에 있습니다.");
        System.out.println(rooms[x + y * width].description());
        System.out.println("다음 명령어를 사용할 수 있습니다.");
        System.out.println("go {north|east|south|west} - 이동, quit - 게임 종료");

        Scanner scanner = new Scanner(System.in);
        
        running = true;
        while (running) {
            System.out.print("> ");
            String[] commands = scanner.nextLine().toLowerCase().trim().split("\\s+");
            switch (commands[0]) {
                case "go" -> {
                    switch (commands[1]) {
                        case "north" -> {
                            if (y - 1 < 0 || rooms[x + (y - 1) * width] == null) {
                                System.out.println("이동할 수 없습니다.");
                            } else {
                                y -= 1;
                                System.out.println("당신은 [" + rooms[x + y * width].name() + "]에 있습니다.");
                                System.out.println(rooms[x + y * width].description());
                            }
                        }
                        case "south" -> {
                            if (y + 1 >= height || rooms[x + (y + 1) * width] == null) {
                                System.out.println("이동할 수 없습니다.");
                            } else {
                                y += 1;
                                System.out.println("당신은 [" + rooms[x + y * width].name() + "]에 있습니다.");
                                System.out.println(rooms[x + y * width].description());
                            }
                        }
                        case "east" -> {
                            if (x + 1 >= width || rooms[(x + 1 ) + y * width] == null) {
                                System.out.println("이동할 수 없습니다.");
                            } else {
                                x += 1;
                                System.out.println("당신은 [" + rooms[x + y * width].name() + "]에 있습니다.");
                                System.out.println(rooms[x + y * width].description());
                            }
                        }
                        case "west" -> {
                            if (x - 1 < 0 || rooms[(x - 1) + y * width] == null) {
                                System.out.println("이동할 수 없습니다.");
                            } else {
                                x -= 1;
                                System.out.println("당신은 [" + rooms[x + y * width].name() + "]에 있습니다.");
                                System.out.println(rooms[x + y * width].description());
                            }
                        }
                        default -> System.out.println("이해할 수 없는 명령어입니다.");
                    }
                }

                case "quit" -> running = false;
                default -> System.out.println("이해할 수 없는 명령어입니다.");
            }
        }
        
        System.out.println("\n게임을 종료합니다.");
    }
}
