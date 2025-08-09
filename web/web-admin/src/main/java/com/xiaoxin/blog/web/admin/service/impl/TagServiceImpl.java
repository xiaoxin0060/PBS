package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.blog.model.entity.Tag;
import com.xiaoxin.blog.web.admin.mapper.TagMapper;
import com.xiaoxin.blog.web.admin.service.TagService;
import com.xiaoxin.blog.web.admin.vo.PopularTagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author 小新
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2025-08-04 00:14:52
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    public static final String POPULAR_TAGS_KEY = "blog:popular:tag";

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<PopularTagsVo> getPopularTags(Integer limit) {
        if(Boolean.TRUE.equals(redisTemplate.hasKey(POPULAR_TAGS_KEY)))
        {
            //ZSetOperations.TypedTuple<Object>包含一个Vo和score,score就是vo里的count
            Set<ZSetOperations.TypedTuple<Object>> tagSet = redisTemplate.opsForZSet().reverseRangeWithScores(POPULAR_TAGS_KEY, 0, limit - 1);
            List<PopularTagsVo> result = new ArrayList<>();

            if (tagSet != null) {
                for (ZSetOperations.TypedTuple<Object> tuple : tagSet) {
                    result.add((PopularTagsVo) tuple.getValue());
                }
                return result;
            }
        }
        List<PopularTagsVo> popularTags = tagMapper.getPopularTags(limit);
        asyncUpdateRedisCache(popularTags);
        return popularTags;

    }

    @Override
    public void updatePopularTagsCache() {
        List<PopularTagsVo> popularTags = tagMapper.getPopularTags(10);
        //删除旧的缓存数据
        redisTemplate.delete(POPULAR_TAGS_KEY);
        asyncUpdateRedisCache(popularTags);
    }

    @Override
    public void restoreTag(Long id) {
        tagMapper.restoreTag(id);
    }

    @Async
    public void asyncUpdateRedisCache(List<PopularTagsVo> popularTags) {
        if(popularTags!=null&&!popularTags.isEmpty())
        {
            Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
            for (PopularTagsVo popularTag : popularTags) {
                tuples.add(new DefaultTypedTuple<>(popularTag, (double) popularTag.getCount()));
            }
            redisTemplate.opsForZSet().add(POPULAR_TAGS_KEY, tuples);
        }
    }
}




