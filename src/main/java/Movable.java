import com.raylib.Jaylib;

public class Movable extends Entity {
    Jaylib.Vector2 velocity;
    double gravity = 9.8;
    int jump_velocity;
    float gravity_rate; // Rate of change of gravity.
    void tick(GameState game_state, double delta_time) {
        if (this.velocity.y() > this.gravity) {
            this.velocity.y((float)Math.max(this.velocity.y() - this.gravity_rate, this.gravity));
        } else if (this.velocity.y() < this.gravity) {
            this.velocity.y((float)Math.min(this.velocity.y() + this.gravity_rate, this.gravity));
        }
    }
}
