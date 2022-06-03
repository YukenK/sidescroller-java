public class Player extends Movable {
    int health;
    double next_damage;
    void Destroy(GameState game_state, Entity entity) {
        if (this.health == 1) {
            // Kill the player, reset map to beginning.
        }
        this.health--;
        this.next_damage = game_state.current_time + 1000;
    }
    void TryCollide(GameState game_state, Entity entity) {
        if (entity.collision_types.contains(CollisionType.TOP_DAMAGE)) {
            entity.Destroy(game_state, this);
        }
        // If we've not exited out at this point, entity will damage player.
        if (game_state.current_time >= this.next_damage && entity.collision_types.contains(CollisionType.ENTITY_DAMAGES_PLAYER) && !this.collision_types.contains(CollisionType.IGNORE_MOVABLE_COLLISION)) {
            this.Destroy(game_state, entity);
        }
    }
}
