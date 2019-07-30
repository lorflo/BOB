package my.cool.apps.bob;

import java.util.ArrayList;

public class LevelCave extends LevelData {
        public LevelCave() {
                tiles = new ArrayList<>();
                tiles.add("p..........................1111................");
                tiles.add(".....d.........................................");
                tiles.add("..g.............11.............................");
                tiles.add("..........................................d....");
                tiles.add("..1111.......1111..1111....11111...............");
                tiles.add("................................1........d.....");
                tiles.add(".................................1.............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");
                tiles.add("................ffffff............4............");


                // declare the values for the teleports in order of appearance
                locations = new ArrayList<>();
                locations.add(new Location("LevelCity", 118f, 18f));

                backgroundDataList = new ArrayList<>();
                // note that speeds less than 2 cause problems
                backgroundDataList.add(new BackgroundData("underground", true, -1, -10, 25, 4, 35 ));
        }
}
