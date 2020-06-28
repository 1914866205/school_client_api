package com.niit.soft.client.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName DormVo
 * @Description TODO
 * @date 2020-06-10 8:11
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormVo {
    private String towerName;
    private String towerUnitName;
    private String roomName;
    private Long roomId;

}
