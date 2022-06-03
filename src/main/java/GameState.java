import java.util.HashMap;

public class GameState { // Multiple instances should be able to be instantiated, for multiple games on a server.
    // Avoid throwing a bunch of pointers around so GC doesn't get confused.
    HashMap<Integer, Entity> entities;
    HashMap<String, GameMap> game_maps;
    public GameState() {
        this.entities = new HashMap<>();
        this.game_maps = new HashMap<>();
    }
    public void TryRemoveEntityFromMap(Integer id) {
        if (!this.entities.containsKey(id)) return;
        Entity entity = this.entities.get(id);
        if (entity.map.isBlank()) return;
        GameMap map = this.game_maps.get(entity.map);
        map.entities.remove(id);
    }
    public void AddEntityToMap(Entity entity, String map_name) {
        // An entity can only ever be in one place.
        if (!this.entities.containsKey(entity.id)) this.entities.put(entity.id, entity);
        else this.TryRemoveEntityFromMap(entity.id);
        GameMap map = this.game_maps.get(map_name);
        map.entities.add(entity.id);
        entity.map = map_name;
    }
    public GameMap GetEntityMap(Integer id) {

    }
}
