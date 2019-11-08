package wsg.lol;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wsg.lol.common.enums.rank.MatchLaneEnum;
import wsg.lol.dao.mybatis.mapper.MatchReferenceMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LolApplicationTests {

    private MatchReferenceMapper referenceMapper;

    @Test
    public void contextLoads() {
        Assert.assertEquals(referenceMapper.selectByPrimaryKey(44251).getLane(), MatchLaneEnum.NONE);
    }

    @Autowired
    public void setReferenceMapper(MatchReferenceMapper referenceMapper) {
        this.referenceMapper = referenceMapper;
    }
}
