import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandParserTest {
    
    @Test
    void go() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("go north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("go south")).isEqualTo(new Command.Move(Direction.SOUTH));
        assertThat(parser.parseCommand("go east")).isEqualTo(new Command.Move(Direction.EAST));
        assertThat(parser.parseCommand("go west")).isEqualTo(new Command.Move(Direction.WEST));
    }

    @Test
    void go_north() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("go north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("Go North")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("go     north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("  go     north  ")).isEqualTo(new Command.Move(Direction.NORTH));
    }

    @Test
    void look() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("look")).isEqualTo(new Command.Look());
    }

    @Test
    void stop() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("quit")).isEqualTo(new Command.Quit());
    }

    @Test
    void help() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("help")).isEqualTo(new Command.Help());
    }
}
