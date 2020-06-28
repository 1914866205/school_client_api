package com.niit.soft.client.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RoomDto
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    private String towerName;
    private String towerUnitName;
    private String roomName;


}