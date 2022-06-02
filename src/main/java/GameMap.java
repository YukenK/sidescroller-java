import com.raylib.Jaylib;
import java.util.ArrayList;

public class GameMap {
    ArrayList<Integer> entities;
    int[] tiles;
    Jaylib.Vector2 dimensions;
    String map_name;
    public GameMap(String map_name, int x, int y) {
        this.dimensions = new Jaylib.Vector2(x, y);
        this.entities = new ArrayList<>();
        this.map_name = map_name;
        this.tiles = new int[x * y];
    }
    public ArrayList<Entity> GetEntities(GameState game_state, Jaylib.Vector2 position) {
        // Ideal performance could be obtained by keeping track of entities on tiles
        // and instead referencing an array with the proper offsets.
        // Not necessary for this demo though.
        ArrayList<Entity> entities = new ArrayList<>();
        for (Integer id : this.entities) {
            Entity entity = game_state.entities.get(id);
            Jaylib.Vector2 entity_pos = entity.GetTilePosition();
            if (entity_pos.x() == position.x() && entity_pos.y() == position.y()) {
                entities.add(entity);
            }
        }
        return entities;
    }
}
