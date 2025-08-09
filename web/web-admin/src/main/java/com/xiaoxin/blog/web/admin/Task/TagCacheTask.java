package com.xiaoxin.blog.web.admin.Task;

import com.xiaoxin.blog.web.admin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class TagCacheTask {
    @Autowired
    private TagService tagService;

    /**
     * 每天凌晨0点更新热门标签缓存
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updatePopularTagsCache() {
        tagService.updatePopularTagsCache();
    }
}
