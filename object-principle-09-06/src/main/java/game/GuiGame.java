package game;

import game.command.Command;
import game.command.CommandParser;
import game.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class GuiGame extends JFrame implements ActionListener, InputOutput {
    private JTextArea display;
    private JTextField input;
    private JButton enter;

    private World world;
    private CommandParser commandParser;

    public GuiGame() {
        super("텍스트 어드벤처 게임");
        buildComponents();
    }

    private void buildComponents() {
        buildFrame();
        buildDisplay();
        buildInput();
    }

    private void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());
    }

    private void buildDisplay() {
        display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        add(new JScrollPane(display), BorderLayout.CENTER);
    }

    private void buildInput() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        input = new JTextField();
        inputPanel.add(input, BorderLayout.CENTER);

        enter = new JButton("입력");
        inputPanel.add(enter, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        enter.addActionListener(this::actionPerformed);
        input.addActionListener(this::actionPerformed);
    }

    public void initialize(World world, CommandParser parser) {
        this.world = world;
        this.commandParser = parser;
    }

    public void run() {
        welcome();
        play();
    }

    private void welcome() {
        showGreetings();
        world.showRoom();
        showHelp();
    }

    private void showGreetings() {
        showLine("환영합니다!");
    }

    private void farewell() {
        showLine("\n게임을 종료합니다.");
    }

    private void play() {
        showLine("");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = commandParser.parseCommand(input.getText());
        executeCommand(command);
        showLine("");
        display.setCaretPosition(display.getDocument().getLength());
        input.setText("");
    }

    private void executeCommand(Command command) {
        switch(command) {
            case Command.Move move -> world.tryMove(move.direction());
            case Command.Look() -> world.showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> stop();
            case Command.Unknown() -> showUnknownCommand();
            case Command.Inventory() -> world.showInventory();
            case Command.Take take -> world.takeItem(take.item());
            case Command.Drop drop -> world.dropItem(drop.item());
            case Command.Destroy destroy -> world.destroyItem(destroy.item());
            case Command.Throw aThrow -> world.throwItem(aThrow.item());
        }
    }

    private void stop() {
        farewell();
        new Timer(1000,
                event -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING))).start();
    }

    private void showHelp() {
        showLine(commandParser.help());
    }

    private void showUnknownCommand() {
        showLine("이해할 수 없는 명령어입니다.");
    }

    @Override
    public void showLine(String text) {
        display.append(text + "\n");
    }

    @Override
    public void show(String text) {
        display.append(text);
    }

    // 불필요한 메서드
    @Override
    public String input() {
        return "";
    }
}
