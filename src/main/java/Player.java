public class Player extends Movable {
    void TryCollide(Entity entity) {
        if (entity.collision_types.contains(CollisionType.TOP_DAMAGE)) {
            // Check if we're jumping on the entity.
        }
        // If we've not exited out at this point, entity will damage player.
        if (entity.collision_types.contains(CollisionType.ENTITY_DAMAGES_PLAYER)) {

        }
    }
}
