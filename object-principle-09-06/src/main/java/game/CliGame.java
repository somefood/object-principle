package game;

public class CliGame implements GameLoop {
    private Game game;
    private Input input;
    private Output output;
    private boolean running;

    public CliGame(Game game, Input input, Output output) {
        this.game = game;
        this.game.initialize(this);
        this.input = input;
        this.output = output;
    }

    public void run() {
        game.run();
    }

    @Override
    public void play() {
        start();
        while (isRunning()) {
            String input = inputCommand();
            game.executeCommand(input);
        }
    }

    @Override
    public void stop() {
        this.running = false;
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

    private String input() {
        return input.input();
    }

    private void showPrompt() {
        output.show("> ");
    }
}
