package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName friendDto
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/24 10:01 上午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDto {
    private String userId;
    private String tag;
}
