import com.raylib.Jaylib;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Optional;

public abstract class Entity {
    EnumSet<CollisionType> collision_types;
    Jaylib.Vector2 collider;
    Jaylib.Vector2 position;
    Integer id;
    String map;
    public Entity() {
        collider = new Jaylib.Vector2(32, 32); // Default collider is 1 tile.
        position = new Jaylib.Vector2(0, 0);
    }
    abstract void Destroy(GameState game_state, Entity entity);
    boolean DoesCollide(Entity entity) {
        return true;
    }
    public Optional<ArrayList<Entity>> SetPosition(GameState game_state, Jaylib.Vector2 position, GameMap map, boolean check_collision) {
        if (position == null) throw new NullPointerException();
        Jaylib.Vector2 old_position = check_collision ? new Jaylib.Vector2(this.position.x(), this.position.y()) : null;
        this.position.x(position.x());
        this.position.y(position.y());
        if (!check_collision) return Optional.empty();
        ArrayList<Entity> collisions = this.GetCollisions(game_state, map);
        boolean can_move_x = true;
        boolean can_move_y = true;
        for (Entity entity : collisions) {
            if (this.collision_types.contains(CollisionType.IGNORE_MOVABLE_COLLISION) && entity.collision_types.contains(CollisionType.ENEMY)) {
                continue;
            }
            if (!can_move_x && !can_move_y) {
                this.position.x(old_position.x());
                this.position.y(old_position.y());
                return Optional.empty();
            } else if(!this.DoesCollide(entity)) continue;
            can_move_x = !(this.position.x() < entity.position.x() + entity.collider.x() && this.position.x() + this.collider.x() > entity.position.x());
            can_move_y = !(this.position.y() < entity.position.y() + entity.collider.y() && this.position.y() + this.collider.y() > entity.position.y());
        }
        if (!can_move_x) this.position.x(old_position.x());
        if (!can_move_y) this.position.y(old_position.y());
        return Optional.of(collisions);
    }
    public Jaylib.Vector2 GetPosition() {
        return new Jaylib.Vector2(this.position.x(), this.position.y());
    }
    public Jaylib.Vector2 GetTilePosition() {
        return new Jaylib.Vector2((float)(Math.floor(this.position.x() / 32)), (float)(Math.floor(this.position.y() / 32)));
    }
    public ArrayList<Entity> GetCollisions(GameState game_state, GameMap map) {
        ArrayList<Entity> entities = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = - 1; y <= 1; y++) {
                Jaylib.Vector2 tile_pos = this.GetTilePosition();
                Jaylib.Vector2 check_pos = new Jaylib.Vector2(x + tile_pos.x(), y + tile_pos.y());
                ArrayList<Entity> e = map.GetEntities(game_state, check_pos);
                for (Entity entity : e) {
                    if (Entity.CollidesWith(this.position, this.collider, entity.position, entity.collider))
                        entities.add(entity);
                }
            }
        }
        return entities;
    }
    public static boolean CollidesWith(Jaylib.Vector2 p1, Jaylib.Vector2 c1, Jaylib.Vector2 p2, Jaylib.Vector2 c2) {
        return p1.x() < p2.x() + c2.x() &&
               p1.x() + c1.x() > p2.x()   &&
               p1.y() < p2.y() + c2.y() &&
               p1.y() + c1.y() > p2.y();
    }
}
