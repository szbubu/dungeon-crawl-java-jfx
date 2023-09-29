package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Door;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Golem;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.actors.Vampire;
import com.codecool.dungeoncrawl.data.items.*;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    if (isDigit(line.charAt(x))) {
                        int symbol = Character.getNumericValue(line.charAt(x));
                        if (symbol % 2 == 0) {
                          Cell newCell = new Door(map, x, y, CellType.CLOSED_DOOR, symbol);
                            map.setCell(newCell);
                        } else if (symbol % 2 != 0) {
                            cell.setType(CellType.FLOOR);
                            new Key(cell, "key", symbol + 1);
                        }
                    } else {
                        switch (line.charAt(x)) {
                            case ' ':
                                cell.setType(CellType.EMPTY);
                                break;
                            case '#':
                                cell.setType(CellType.WALL);
                                break;
                            case '.':
                                cell.setType(CellType.FLOOR);
                                break;
                            case 's':
                                cell.setType(CellType.FLOOR);
                                new Skeleton(cell);
                                break;
                            case 'v':
                                cell.setType(CellType.FLOOR);
                                new Vampire(cell);
                                break;
                            case 'g':
                                cell.setType(CellType.FLOOR);
                                new Golem(cell);
                                break;
                            case '@':
                                cell.setType(CellType.FLOOR);
                                map.setPlayer(new Player(cell));
                                break;
                            case '&':
                                cell.setType(CellType.FLOOR);
                                new Key(cell, "key", 1);
                                break;
                            case 'k':
                                cell.setType(CellType.FLOOR);
                                new Sword(cell, "sword", 6);
                                break;
                            case 'h':
                                cell.setType(CellType.FLOOR);
                                new HealingPotion(cell, "healing-potion", 10);
                                break;
                            case 'b':
                                cell.setType(CellType.FLOOR);
                                new Hammer(cell, "hammer", 12);
                                break;
                            default:
                                throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                        }
                    }
                }
            }
        }
        return map;
    }

}
