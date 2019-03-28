package wsg.lol.pojo.dto.api.spectator;

/**
 * @author King
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
