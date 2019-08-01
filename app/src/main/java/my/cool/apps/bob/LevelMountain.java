package my.cool.apps.bob;

import android.util.Log;

import java.util.ArrayList;

public class LevelMountain extends LevelData {
    public LevelMountain() {
        tiles = new ArrayList<>();
        tiles.add("pECO.............................................................................................2222222222222..........");
        tiles.add("........................................................................................................................");
        tiles.add("................................2...2....2.....................................................2........................");
        tiles.add(".........................2..............................................................................................");
        tiles.add(".............................................2..........................................2....2......................t...");
        tiles.add("............................22....................................................2......................................");
        tiles.add(".................................................2......................................................................");
        tiles.add(".......2...................2....................................................2.......................................");
        tiles.add("........................................................................................................................");
        tiles.add("..2.......................2..........................2.......................2..........................................");
        tiles.add("........................................................................................................................");
        tiles.add(".......2.....2...2..2...2...............................2.................2.............................................");
        tiles.add("........................................................................................................................");
        tiles.add(".....2.2....................................................2..2..222222................................................");
        tiles.add("........................................................................................................................");
        tiles.add(".2....x....2x.2....x.........x..........x.................x..............x..........x.........x........x................");
        tiles.add("........................................................................................................................");
        tiles.add("...2....2............ecu............t...................................................................................");
        tiles.add("........................................................................................................................");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        tiles.add("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        // declare the values for the teleports in order of appearance
        locations = new ArrayList<>();
        this.locations.add(new Location("LevelCave", 5f, 16f));
        Log.d("location","location added Cave");

        backgroundDataList = new ArrayList<>();
        this.backgroundDataList.add(new BackgroundData("mountain", true, -2, -10, 6, 4));
        this.backgroundDataList.add(new BackgroundData("mountainside", true, -1, 6, 31, 8));
        this.backgroundDataList.add(new BackgroundData("grass", true, 1,28, 34, 16));
    }
}
