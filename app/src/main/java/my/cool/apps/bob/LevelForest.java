package my.cool.apps.bob;

import android.util.Log;

import java.util.ArrayList;

public class LevelForest extends LevelData {
    public LevelForest() {
        tiles = new ArrayList<>();
        tiles.add("pECO.......................g.........................................................................................uuu");
        tiles.add("...........................................................1111111....111............................................555");
        tiles.add("11111111111111111111111111111111111111111111111111111.........................11....................................1...");
        tiles.add(".......w.w.....w.w..w..........w.............................................1111111111.................................");
        tiles.add("...................................................................11.11111.......................................1.....");
        tiles.add("................................................................11......................................................");
        tiles.add("............................................1111111....11111111.....................w..w..w.....................1.......");
        tiles.add("....11111111111111111111......11111111111...............................................................................");
        tiles.add("5.............................................................................................................1.........");
        tiles.add("55............................................................................c.c.c.....................................");
        tiles.add("..55..........................................................................11111111111111111111..........1...........");
        tiles.add(".....55..............................................55...55.....55555..................................................");
        tiles.add("........55..55....55...55....55.....55...55....55..................................................1.......1............");
        tiles.add("7.............................d........................................................................................7");
        tiles.add("7...............................................................................z....................1...1............7");
        tiles.add("7....................................................................d.........z.z.....................................7");
        tiles.add("7w..........................................1...............z.................z.z.z....................1..............w7");
        tiles.add("7........1111111111.......111111111111.....111111111111111111111111..111111111111111111111111111111111111111111111111117");
        tiles.add("7......................t.....................d.........g.......................d............d.......d......c.c.c.c.e...7");
        tiles.add("7....1....z........1..........zzz....z........................................z...z....................................7");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        tiles.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

        // declare the values for the teleports in order of appearance
        locations = new ArrayList<>();
        this.locations.add(new Location("LevelMountain", 18f, 4f));
        if(!locations.isEmpty())
        Log.d("location","location added Mt.");

        backgroundDataList = new ArrayList<>();
        // note that speeds less than 2 cause problems
        this.backgroundDataList.add(new BackgroundData("forest", true, -1, 10, 31, 4));
        this.backgroundDataList.add(new BackgroundData("grass", true, 1, 28, 34, 24));
    }
}
