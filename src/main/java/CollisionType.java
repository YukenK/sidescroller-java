public enum CollisionType {
    PLAYER,
    TOP_DAMAGE, // Entity is killed by jumping on it.
    NO_DAMAGE,  // Entity can not be killed by collisions with the player.
    ENTITY_DAMAGES_PLAYER, // Entity will damage player.
}
