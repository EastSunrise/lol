package wsg.lol.common.pojo.dto.spectator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wsg.lol.common.base.BaseDto;

/**
 * DTO for observers of the game.
 *
 * @author Kingen
 */
@EqualsAndHashCode(callSuper = true)
@Data
class Observer extends BaseDto {

    /**
     * Key used to decrypt the spectator grid game data for playback
     */
    private String encryptionKey;
}
