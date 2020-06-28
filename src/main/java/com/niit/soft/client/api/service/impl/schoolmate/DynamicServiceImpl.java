package com.niit.soft.client.api.service.impl.schoolmate;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.common.ResultCode;
import com.niit.soft.client.api.config.RedisCacheKeyGeneratorConfig;
import com.niit.soft.client.api.domain.dto.schoolmate.*;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.domain.model.schoolmate.*;
import com.niit.soft.client.api.domain.vo.schoolmate.CommentVo;
import com.niit.soft.client.api.domain.vo.schoolmate.DynamicVo;
import com.niit.soft.client.api.domain.vo.schoolmate.ReplyCommentVo;
import com.niit.soft.client.api.mapper.schoolmate.CommentMapper;
import com.niit.soft.client.api.mapper.schoolmate.DynamicMapper;
import com.niit.soft.client.api.mapper.schoolmate.DynamicPhotoMapper;
import com.niit.soft.client.api.mapper.schoolmate.ThumbMapper;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.repository.schoolmate.DynamicRepository;
import com.niit.soft.client.api.service.schoolmate.DynamicPhotoService;
import com.niit.soft.client.api.service.schoolmate.DynamicService;
import com.niit.soft.client.api.service.schoolmate.ReplyCommentService;
import com.niit.soft.client.api.util.RedisUtil;
import com.niit.soft.client.api.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @ClassName DynamicServiceImpl
 * @Description 好友圈模块动态资讯
 * @Author xiaobinggan
 * @Date 2020/6/8 8:10 下午
 * @Version 1.0
 **/
@Service
@Transactional
@Slf4j
public class DynamicServiceImpl implements DynamicService {
    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private DynamicPhotoMapper dynamicPhotoMapper;

    @Resource
    private ReplyCommentService replyCommentService;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ThumbMapper thumbMapper;

    @Resource
    private DynamicRepository dynamicRepository;

    @Resource
    private DynamicPhotoService dynamicPhotoService;

    @Resource
    private UserAccountRepository userAccountRepository;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public DynamicVo findDynamicVoById(String id) {
        // 根据id查询动态信息以及，评论，点赞
        DynamicVo dynamicVo = dynamicMapper.findDynamicVoById(id);

        // 根据id查询动态图片信息
        dynamicVo.setDynamicPhotoList(dynamicPhotoMapper.selectList(new QueryWrapper<DynamicPhoto>().eq("dynamic_id", id)
                .orderByDesc("gmt_create")));

        // 查询动态用户信息
        String userId = dynamicVo.getUserId();
        if (userId != null) {
            UserAccount userAccountByInfo = userAccountRepository.findUserAccountByInfo(userId);
            dynamicVo.setUserAccount(userAccountByInfo);
        }


        // 获取评论列表
        List<Comment> commentList = dynamicVo.getCommentList();
        List<CommentVo> commentVoList = new ArrayList<>();
        if (commentList.size() != 0) {
            for (Comment comment : commentList) {
                String userId1 = comment.getUserId();
                // 根据用户id查询评论者的信息
                UserAccount userAccountByInfo = userAccountRepository.findUserAccountByInfo(userId1);
                // 设置评论者的用户头像、昵称
                String avatar = userAccountByInfo.getAvatar();
                String nickname = userAccountByInfo.getNickname();
                CommentVo commentVoById = commentMapper.findCommentVoById(comment.getPkCommentId());
                commentVoById.setAvatar(avatar);
                commentVoById.setNickname(nickname);
                commentVoList.add(commentVoById);

                List<ReplyCommentVo> replyCommentVos = new ArrayList<>(10);
                for (ReplyComment replyComment : commentVoById.getReplyComments()) {
                    // 查询到评论回复的信息
                    ReplyComment pk_reply_comment_id = replyCommentService.getOne(
                            new QueryWrapper<ReplyComment>().eq("pk_reply_comment_id", replyComment.getPkReplyCommentId()));
                    String userId2 = replyComment.getUserId();
                    UserAccount userAccountByInfo1 = userAccountRepository.findUserAccountByInfo(userId2);
                    String avatar1 = userAccountByInfo1.getAvatar();
                    String nickname1 = userAccountByInfo1.getNickname();
                    replyCommentVos.add(ReplyCommentVo.builder()
                            .avatar(avatar1)
                            .nickname(nickname1)
                            .pkReplyCommentId(pk_reply_comment_id.getPkReplyCommentId())
                            .userId(userId2)
                            .isDeleted(pk_reply_comment_id.getIsDeleted())
                            .commentId(pk_reply_comment_id.getCommentId())
                            .content(pk_reply_comment_id.getContent())
                            .gmtCreate(pk_reply_comment_id.getGmtCreate())
                            .gmtModified(pk_reply_comment_id.getGmtModified()).build());
                }
                commentVoById.setReplyCommentVos(replyCommentVos);
            }
            dynamicVo.setCommentVoList(commentVoList);
        }

        Map<String, Object> thumbMap = new HashMap<>(10);
        List<Thumb> thumbList = dynamicVo.getThumbList();
        if (thumbList.size() != 0 && thumbList.get(0).getPkThumbId() != null) {
            for (Thumb thumb : thumbList) {
                thumbMap.put(thumb.getPkThumbId(), thumb.getUserId());
            }
            // 将动态资讯的id存为键，将点赞id和用户id存为map
            boolean hmset = redisUtil.hmset(id, thumbMap);
            if (hmset) {
                log.info("成功存入redis");
            }
        } else {
            dynamicVo.setThumbList(null);
        }

        return dynamicVo;
    }

    @Cacheable(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public List<DynamicPhoto> findDynamicPhotoById(String id) {
        return dynamicPhotoMapper.selectList(new QueryWrapper<DynamicPhoto>().eq("dynamic_id", Long.valueOf(id)));
    }

    @Override
    public ResponseResult thumbsUp(ThumbDto thumbDto) {
        Map<Object, Object> map = redisUtil.hmget(thumbDto.getDynamicId());
        List<Thumb> thumbList = dynamicMapper.findDynamicVoById(thumbDto.getDynamicId()).getThumbList();
        if (thumbList.size() == 0 || thumbList.get(0).getPkThumbId() == null) {
            log.info("键存redis");
            Map<String, Object> thumbMap = new HashMap<>(10);
            Long id = new SnowFlake(1, 3).nextId();
            thumbMap.put(String.valueOf(id), thumbDto.getUserId());
            redisUtil.hmset(thumbDto.getDynamicId(), thumbMap);
            thumbMapper.insert(Thumb.builder().pkThumbId(String.valueOf(id)).userId(thumbDto.getUserId())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .dynamicId(thumbDto.getDynamicId())
                    .build());
        } else {
            Boolean flag = false;
            if (redisUtil.hasKey(thumbDto.getDynamicId())) {
                for (Entry<Object, Object> entry : map.entrySet()) {
                    log.info("foreachEntry : key :" + entry.getKey() + "---> value :" + entry.getValue());
                    if (entry.getKey().equals(thumbDto.getPkThumbId()) || entry.getValue().equals(thumbDto.getUserId())) {
                        flag = false;
                        log.info("从redis删除");
                        redisUtil.hdel(thumbDto.getDynamicId(), thumbDto.getPkThumbId(), thumbDto.getUserId());
                        return ResponseResult.success(ResultCode.SCHOOL_MATE_THUMBS_DOWN);
                    }
                    flag = true;
                }
                if (flag) {
                    log.info("值存redis");
                    Map<String, Object> thumbMap = new HashMap<>(10);
                    thumbMap.put(String.valueOf(new SnowFlake(1, 3).nextId()), thumbDto.getUserId());
                    redisUtil.hmset(thumbDto.getDynamicId(), thumbMap);
                }
            } else {
                return ResponseResult.success(ResultCode.SCHOOL_MATE_THUMBS_UP_REDIS);
            }
        }
        Map<Object, Object> mapAfter = redisUtil.hmget(thumbDto.getDynamicId());
        for (Entry<Object, Object> entry : mapAfter.entrySet()) {
            if (entry.getValue().equals(String.valueOf(thumbDto.getUserId()))) {
                return ResponseResult.success(String.valueOf(entry.getKey()));
            }
        }
        return ResponseResult.success(ResultCode.SCHOOL_MATE_THUMBS_UP);
    }


    @Cacheable(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public List<Dynamic> findDynamicByPage(SchoolmatePageDto schoolmatePageDto) {
        return dynamicMapper.selectPage(new Page(schoolmatePageDto.getCurrentPage(), schoolmatePageDto.getPageSize()),
                new QueryWrapper<Dynamic>().orderByDesc("gmt_create")).getRecords();
    }


    @Cacheable(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public List<Dynamic> findDynamicVoByUserId(SchoolmateUserPageDto schoolmateUserPageDto) {
        return dynamicMapper.selectPage(new Page(schoolmateUserPageDto.getCurrentPage(), schoolmateUserPageDto.getPageSize()),
                new QueryWrapper<Dynamic>().orderByDesc("gmt_create").eq("user_id", schoolmateUserPageDto.getId())).getRecords();
    }

    @CachePut(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public Dynamic addOne(DynamicDto dynamicDto) {
        return dynamicRepository.save(Dynamic.builder()
                .pkDynamicId(String.valueOf(new SnowFlake(1, 3).nextId()))
                .title(dynamicDto.getTitle())
                .content(dynamicDto.getContent())
                .type(dynamicDto.getType())
                .comments(0)
                .thumbs(0)
                .userId(dynamicDto.getUserId())
                .isDeleted(false).build());
    }

    @CachePut(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public ResponseResult addPhoto(List<DynamicPhotoDto> dynamicPhotoDtos) {
        ArrayList<DynamicPhoto> dynamicPhotos = new ArrayList<>();
        for (DynamicPhotoDto dynamicPhotoDto : dynamicPhotoDtos) {
            dynamicPhotos.add(DynamicPhoto.builder().pkDynamicPhotoId(String.valueOf(new SnowFlake(1, 3).nextId()))
                    .dynamicId(dynamicPhotoDto.getDynamicId())
                    .picture(dynamicPhotoDto.getPicture())
                    .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                    .gmtModified(Timestamp.valueOf(LocalDateTime.now()))
                    .isDeleted(false)
                    .build());
        }
        if (dynamicPhotoService.saveBatch(dynamicPhotos)) {
            return ResponseResult.success("添加成功");
        }
        return ResponseResult.failure(ResultCode.SCHOOLMATE_ADD_PHOTO_FAILING);
    }

    @Override
    public CommentVo findCommentVoById(String id) {
        CommentVo commentVoById = commentMapper.findCommentVoById(id);
        // 查询动态用户信息
        String userId = commentVoById.getUserId();
        if (userId != null) {
            UserAccount userAccountByInfo = userAccountRepository.findUserAccountByInfo(userId);
            commentVoById.setAvatar(userAccountByInfo.getAvatar());
            commentVoById.setNickname(userAccountByInfo.getNickname());
        }
        List<ReplyCommentVo> replyCommentVos = new ArrayList<>(10);
        for (ReplyComment replyComment : commentVoById.getReplyComments()) {
            ReplyComment pk_reply_comment_id = replyCommentService.getOne(
                    new QueryWrapper<ReplyComment>().eq("pk_reply_comment_id", replyComment.getPkReplyCommentId()));
            String userId2 = replyComment.getUserId();
            UserAccount userAccountByInfo1 = userAccountRepository.findUserAccountByInfo(userId2);
            String avatar1 = userAccountByInfo1.getAvatar();
            String nickname1 = userAccountByInfo1.getNickname();
            replyCommentVos.add(ReplyCommentVo.builder()
                    .avatar(avatar1)
                    .nickname(nickname1)
                    .pkReplyCommentId(pk_reply_comment_id.getPkReplyCommentId())
                    .userId(userId2)
                    .isDeleted(pk_reply_comment_id.getIsDeleted())
                    .commentId(pk_reply_comment_id.getCommentId())
                    .content(pk_reply_comment_id.getContent())
                    .gmtCreate(pk_reply_comment_id.getGmtCreate())
                    .gmtModified(pk_reply_comment_id.getGmtModified()).build());
        }
        commentVoById.setReplyCommentVos(replyCommentVos);
        return commentVoById;
    }

    @CachePut(value = RedisCacheKeyGeneratorConfig.COMMON, keyGenerator = RedisCacheKeyGeneratorConfig.DEFAULT_KEY_GENERATOR)
    @Override
    public int deleteDynamicById(Dynamic dynamic) {
        UpdateWrapper<Dynamic> wrapper = new UpdateWrapper<>();
        wrapper.set("is_deleted", 0);
        return dynamicMapper.update(dynamic, wrapper);
    }
}
