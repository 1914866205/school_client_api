package com.niit.soft.client.api.repository.schoolmate;

import com.niit.soft.client.api.domain.model.schoolmate.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName CommentRepository
 * @Description 评论
 * @Date 2020/6/8  16:20
 * @Version 1.0
 **/
public interface CommentRepository extends JpaRepository<Comment, String> {

    /**
     * @param id
     * @return
     */
    Comment findCommentByPkCommentId(String id);

    /**
     * 根据动态id查询评论
     *
     * @param dynamicId
     * @return
     */
    List<Comment> findCommentByDynamicId(String dynamicId);

    /**
     * 根据动态id查找存在的评论
     *
     * @return
     */
    List<Comment> findCommentByIsDeletedAndDynamicId(Boolean isDel, String dynamicId);

    /**
     * 查找动态id
     *
     * @param id
     * @return
     */
    @Query(value = "select dynamic_id from first_smart_campus.comment c where c.pk_comment_id=?1", nativeQuery = true)
    Long findDynamicIdByPkCommentId(Long id);

    /**
     * 更具用户id，评论id删除
     *
     * @param commentId
     * @param userId
     */
    @Transactional
    @Modifying
    @Query("delete from Comment where pkCommentId = ?1 and userId = ?2 ")
    void deleteByDynamicIdAndDynamicId(String commentId, String userId);


}
