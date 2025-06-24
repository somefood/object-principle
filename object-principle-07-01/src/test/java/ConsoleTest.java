import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleTest {

    @Test
    void input() {
        System.setIn(new ByteArrayInputStream("input\n".getBytes()));

        String command = new Console().input();

        assertThat(command).isEqualTo("input");
    }

    @Test
    void showLine() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        new Console().showLine("showLine");

        assertThat(output.toString()).isEqualToIgnoringNewLines("showLine");
    }
}