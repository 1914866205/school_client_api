package com.niit.soft.client.api.repository.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName ReplyCommentRepository
 * @Description TODO
 * @Date 2020/6/9  8:33
 * @Version 1.0
 **/
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, String> {

    /**
     * 更具用户id，删除
     *
     * @param commentId
     * @param userId
     */
    @Transactional
    @Modifying
    @Query("delete from ReplyComment where pkReplyCommentId = ?1 and userId = ?2 ")
    void deleteByDynamicIdAndDynamicId(String commentId, String userId);

    /**
     * 更具评论id查找
     *
     * @return
     */
    List<ReplyComment> findByCommentId(String commentId);

    /**
     * 12
     *
     * @param commentId
     */
    @Transactional
    @Modifying
    @Query("delete from ReplyComment where commentId = ?1  ")
    void deleteByCommentId(String commentId);

    /**
     * 查询所有CommentId
     *
     * @param commentId
     * @return
     */
    @Query(value = "select v.pk_reply_comment_id from first_smart_campus.reply_comment as v where comment_id = ?1", nativeQuery = true)
    List<String> selectAllCommentId(String commentId);

    /**
     * 批量修改
     *
     * @param ids
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update ReplyComment v set v.isDeleted = true where v.commentId in (?1)")
    int updateIsDelete(List<String> ids);

    /**
     * 根据id查找
     *
     * @return
     */
    ReplyComment findReplyCommentByPkReplyCommentId(String id);
}
