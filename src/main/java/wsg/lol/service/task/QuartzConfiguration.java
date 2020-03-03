package wsg.lol.service.task;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import wsg.lol.common.enums.system.EventTypeEnum;
import wsg.lol.common.enums.system.RegionEnum;
import wsg.lol.common.util.PageUtils;
import wsg.lol.config.RegionIdentifier;
import wsg.lol.dao.mybatis.config.DatasourceHolder;
import wsg.lol.service.intf.EventService;
import wsg.lol.service.intf.SharedService;

import javax.sql.DataSource;

/**
 * Configuration for quartz.
 *
 * @author Kingen
 */
@Configuration
public class QuartzConfiguration {

    public static final String JOB_GROUP_SHARED = "SHARED";

    private SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

    private SharedService sharedService;

    private EventService eventService;

    private SchedulerFactoryBean factoryBean;

    @Bean
    public Scheduler featuredGames() throws SchedulerException {
        return getSharedScheduler("FEATURED_GAMES", () -> sharedService.updateFeaturedGames());
    }

    @Bean
    public Scheduler sharedStatus() throws SchedulerException {
        return getSharedScheduler("SHARD_STATUS", () -> sharedService.updateShardStatus());
    }

    @Bean
    public Scheduler championRotation() throws SchedulerException {
        return getSharedScheduler("CHAMPION_ROTATION", () -> sharedService.updateChampionRotation());
    }

    @Bean
    public Scheduler handleSummoners() throws SchedulerException {
        return getRegionScheduler("EVENT_SUMMONER", region -> {
            RegionIdentifier.setPlatform(region);
            eventService.handle(EventTypeEnum.Summoner, PageUtils.DEFAULT_PAGE);
            RegionIdentifier.setPlatform(null);
        });
    }

    @Bean
    public Scheduler handleMatches() throws SchedulerException {
        return getRegionScheduler("EVENT_MATCH", region -> {
            RegionIdentifier.setPlatform(region);
            eventService.handle(EventTypeEnum.Match, PageUtils.DEFAULT_PAGE);
            RegionIdentifier.setPlatform(null);
        });
    }

    private Scheduler getRegionScheduler(String jobGroup, RegionTask regionTask) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();
        RegionEnum[] regions = RegionEnum.REGIONS;
        for (RegionEnum region : regions) {
            scheduler.scheduleJob(
                    JobBuilder.newJob(new QuartzJobBean() {
                        @Override
                        protected void executeInternal(@NonNull JobExecutionContext context) {
                            regionTask.doTask(region);
                        }
                    }.getClass()).withIdentity(region.name(), jobGroup).storeDurably().build(),
                    TriggerBuilder.newTrigger().forJob(region.name(), jobGroup).withSchedule(builder).build()
            );
        }
        return scheduler;
    }

    private Scheduler getSharedScheduler(String jobName, SharedTask sharedTask) throws SchedulerException {
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.scheduleJob(
                JobBuilder.newJob(new QuartzJobBean() {
                    @Override
                    protected void executeInternal(@NonNull JobExecutionContext context) {
                        sharedTask.update();
                    }
                }.getClass()).withIdentity(jobName, JOB_GROUP_SHARED).storeDurably().build(),
                TriggerBuilder.newTrigger().forJob(jobName, JOB_GROUP_SHARED).withSchedule(builder).build()
        );
        return scheduler;
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        return DatasourceHolder.getInstance().getDatasource(RegionEnum.LOL);
    }

    @Autowired
    public void setFactoryBean(SchedulerFactoryBean factoryBean) {
        this.factoryBean = factoryBean;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSharedService(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    private interface SharedTask {
        void update();
    }

    private interface RegionTask {
        void doTask(RegionEnum region);
    }
}
