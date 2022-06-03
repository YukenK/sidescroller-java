public enum CollisionType {
    PLAYER,
    ENEMY,
    TOP_DAMAGE, // Entity is killed by jumping on it.
    NO_DAMAGE,  // Entity can not be killed by collisions with the player.
    ENTITY_DAMAGES_PLAYER, // Entity will damage player.
    IGNORE_MOVABLE_COLLISION, // Used when an entity is temporarily invulnerable.
}
