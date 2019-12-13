package wsg.lol.service.common;

import tk.mybatis.mapper.entity.Example;
import wsg.lol.common.pojo.domain.summoner.SummonerDo;

/**
 * Util to calculate the priority while selecting part of a large amount of data.
 *
 * @author Kingen
 */
public class PriorityUtils {

    private static final int REVISION_WEIGHT = 1;

    private static final int LAST_UPDATE_WEIGHT = 1;

    private static final int LAST_MATCH_WEIGHT = 1;

    public static Example updateSummoners() {
        Example example = new Example(SummonerDo.class);
        example.setOrderByClause("TIMESTAMPDIFF( SECOND, last_update, SYSDATE( ) ) * " + LAST_UPDATE_WEIGHT + "+ TIMESTAMPDIFF( SECOND, SYSDATE( ), revision_date ) * " + REVISION_WEIGHT);
        return example;
    }

    // todo: tier
    public static Example updateMatches() {
        Example example = new Example(SummonerDo.class);
        example.setOrderByClause("TIMESTAMPDIFF( SECOND, last_match, SYSDATE( ) ) * " + LAST_MATCH_WEIGHT + "+ TIMESTAMPDIFF( SECOND, SYSDATE( ), revision_date ) * " + REVISION_WEIGHT);
        return example;
    }
}
