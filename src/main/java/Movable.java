import com.raylib.Jaylib;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Movable extends Entity {
    Jaylib.Vector2 move_direction;
    Jaylib.Vector2 velocity;
    double gravity = 9.8;
    int jump_velocity;
    float gravity_rate; // Rate of change of gravity.
    void gravity(double delta_time) {
        if (this.velocity.y() > this.gravity) {
            this.velocity.y((float)Math.max(this.velocity.y() - this.gravity_rate * delta_time, this.gravity));
        } else if (this.velocity.y() < this.gravity) {
            this.velocity.y((float)Math.min(this.velocity.y() + this.gravity_rate * delta_time, this.gravity));
        }
    }
    void movement(GameState game_state, double delta_time) {
        Jaylib.Vector2 new_position = new Jaylib.Vector2((float) (this.position.x() + this.velocity.x() * delta_time), (float) (this.position.y() + this.velocity.y() * delta_time));
        Optional<ArrayList<Entity>> collisions = this.SetPosition(game_state, new_position, game_state.GetMap(this.map), true);
        collisions.ifPresent(entities -> {
            for (Entity entity : entities) this.TryCollide(game_state, entity);
        });
    }
    abstract void TryCollide(GameState game_state, Entity entity); // Base entities can't move, and therefore will never need to try and collide with something.
    public void tick(GameState game_state) {
        this.gravity(game_state.delta_time);
        this.movement(game_state, game_state.delta_time);
    }
}
