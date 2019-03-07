package wsg.lol.dto.api.spectator;

/**
 * @author King
 * @date 2019/2/12
 */
public class Observer {

    /**
     * Key used to decrypt the spectator grid game data for playback
     */
    private String encryptionKey;

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
