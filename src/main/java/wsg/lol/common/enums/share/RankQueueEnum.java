package wsg.lol.common.enums.share;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for rank queues.
 *
 * @author Kingen
 */
public enum RankQueueEnum implements JSONSerializable<String> {
    RANKED_SOLO_5x5,
    RANKED_FLEX_SR,
    RANKED_FLEX_TT,
    RANKED_TFT;

    @Override
    public String serialize() {
        return name();
    }
}
