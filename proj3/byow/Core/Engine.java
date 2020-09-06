package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.WorldGenerator.RandomWorld;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    TETile[][] world;
    Avatar Aang;
    Door door;
    Key key;
    String save = "";

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        InputSource inputSource = new KeyboardInputSource();
        drawFrame();
        processInput(inputSource);
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().

        InputSource inputSource = new StringInputDevice(input);
        TETile[][] finalWorldFrame = processInput(inputSource);
        return finalWorldFrame;
    }

    private TETile[][] processInput(InputSource inputSource) {
        // Get string
        String seedString = "";
        while (inputSource.possibleNextInput()) {
            char c = inputSource.getNextKey();
            if (c == 'L') {
                String load = Save.loadString();
                world = interactWithInputString(load);
                break;
            }
            save = save + c;
            if (c == 'N') {
                drawSeed(0.5, 0.25, "");
            }
            else if (c == 'S') {
                // Initialize world
                ter.initialize(WIDTH, HEIGHT);
                world = new TETile[WIDTH][HEIGHT];
                long seed = Long.parseLong(seedString);
                RandomWorld.generateWorld(world, seed);
                ter.renderFrame(world);
                Aang = new Avatar(world, ter);
                door = new Door(world, ter);
                key = new Key(world, ter);
                break;
            }
            else {
                seedString = seedString + c;
                drawSeed(0.5, 0.25, seedString);
            }
        }

        while (inputSource.possibleNextInput()) {
            char c = inputSource.getNextKey();
            if (c == ':') {
                if (inputSource.getNextKey() == 'Q') {
                    Save.saveString(save);
                    System.exit(0);
                }
            }
            Aang.move(c);
            save = save + c;
        }
        return world;
    }

    private void drawFrame() {
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(0.5, 0.5, "(N)ew Game");
        StdDraw.text(0.5, 0.45, "(L)oad Game");
        StdDraw.text(0.5, 0.4, "(Q)uit");
        StdDraw.show();
    }

    private void drawSeed(double x, double y, String s) {
        drawFrame();
        StdDraw.text(0.5, 0.3, "Enter Seed, then press (S)tart:");
        StdDraw.text(x, y, s);
        StdDraw.show();
    }
}
