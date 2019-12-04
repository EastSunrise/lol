package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for types of events in the match.
 *
 * @author Kingen
 */
public enum MatchEventTypeEnum implements JSONSerializable<String> {
    CHAMPION_KILL,
    WARD_PLACED,
    WARD_KILL,
    BUILDING_KILL,
    ELITE_MONSTER_KILL,
    ITEM_PURCHASED,
    ITEM_SOLD,
    ITEM_DESTROYED,
    ITEM_UNDO,
    SKILL_LEVEL_UP,
    ASCENDED_EVENT,
    CAPTURE_POINT,
    PORO_KING_SUMMON;

    @Override
    public String serialize() {
        return name();
    }
}
