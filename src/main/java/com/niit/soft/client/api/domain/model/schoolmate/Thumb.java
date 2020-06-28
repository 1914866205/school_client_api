package com.niit.soft.client.api.domain.model.schoolmate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName Thumb
 * @Description 校友圈动态点赞
 * @Date 2020/6/8  14:01
 * @Version 1.0
 **/

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "thumb")
@TableName("thumb")
public class Thumb extends Model<Thumb> {
    /**
     * 主键，策略为自增
     */
    @Id
    @TableId(value = "pk_thumb_id", type = IdType.INPUT)
    private String pkThumbId;

    /**
     * 动态id
     */
    @Column(name = "dynamic_id", nullable = false)
    private String dynamicId;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create")
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified")
    private Timestamp gmtModified;

    /**
     * 删除标志（0 逻辑删除， 1 未删除）
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
