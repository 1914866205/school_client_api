package com.niit.soft.client.api.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/4/24 9:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeNode {
    private Long pkFleaTypeId;
    private Long parentId;
    private String typeName;
    private String typeCoverUrl;
    private String typeUrl;
    private List<TreeNode> subTypes;


    public TreeNode(Long pkFleaTypeId, Long parentId, String typeName, String typeCoverUrl, String typeUrl) {
        this.pkFleaTypeId = pkFleaTypeId;
        this.parentId = parentId;
        this.typeName = typeName;
        this.typeCoverUrl = typeCoverUrl;
        this.typeUrl = typeUrl;
    }

}
