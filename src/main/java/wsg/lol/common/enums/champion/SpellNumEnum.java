package wsg.lol.common.enums.champion;

/**
 * Enums for order of the spell.
 *
 * @author Kingen
 */
public enum SpellNumEnum {
    P,
    Q,
    W,
    E,
    R,
    S;

    public boolean isChampionSpell() {
        return P.equals(this) || Q.equals(this) || W.equals(this) || E.equals(this) || R.equals(this);
    }
}
