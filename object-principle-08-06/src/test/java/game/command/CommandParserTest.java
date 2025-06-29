package game.command;

import game.worldmap.Direction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandParserTest {
    @Test
    public void go() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("go north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("go south")).isEqualTo(new Command.Move(Direction.SOUTH));
        assertThat(parser.parseCommand("go east")).isEqualTo(new Command.Move(Direction.EAST));
        assertThat(parser.parseCommand("go west")).isEqualTo(new Command.Move(Direction.WEST));
    }

    @Test
    public void go_north() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("go north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("Go North")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("go     north")).isEqualTo(new Command.Move(Direction.NORTH));
        assertThat(parser.parseCommand("  go     north  ")).isEqualTo(new Command.Move(Direction.NORTH));
    }

    @Test
    public void look() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("look")).isEqualTo(new Command.Look());
    }

    @Test
    public void stop() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("quit")).isEqualTo(new Command.Quit());
    }

    @Test
    public void help() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("help")).isEqualTo(new Command.Help());
    }

    @Test
    public void take() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("take sword")).isEqualTo(new Command.Take("sword"));
    }

    @Test
    public void drop() {
        CommandParser parser = new CommandParser();
        assertThat(parser.parseCommand("drop sword")).isEqualTo(new Command.Drop("sword"));
    }
}
