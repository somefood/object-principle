package game.command;

import game.worldmap.Direction;

public sealed interface Command {
    record Move(Direction direction) implements Command {}
    record Unknown() implements Command {}
    record Look() implements Command {}
    record Help() implements Command {}
    record Quit() implements Command {}
    record Inventory() implements Command {}
    record Take(String item) implements Command {}
    record Drop(String item) implements Command {}
}
