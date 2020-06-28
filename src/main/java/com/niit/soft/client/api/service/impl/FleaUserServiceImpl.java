package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.FleaUserDto;
import com.niit.soft.client.api.domain.dto.FleaUserIdDto;
import com.niit.soft.client.api.domain.dto.UpdateFleaUserDto;
import com.niit.soft.client.api.domain.model.FleaUser;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.repository.FleaUserRepository;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.FleaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaUserServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月09日 14:06:00
 */
@Slf4j
@Service
public class FleaUserServiceImpl implements FleaUserService {
    @Resource
    private FleaUserRepository fleaUserRepository;
    @Resource
    private UserAccountRepository userAccountRepository;

    @Override
    public ResponseResult saveFleaUser(FleaUserDto fleaUserDto) {
        FleaUser fleaUserVos = fleaUserRepository.findAllByJobNumber(fleaUserDto.getJobNumber());
        if (fleaUserVos == null) {
            UserAccount userAccount = userAccountRepository.findUserByJobNumber(fleaUserDto.getJobNumber());
            FleaUser fleaUser = FleaUser.builder()
                    .nickname(userAccount.getNickname()).username(userAccount.getUserName())
                    .sex(userAccount.getGender()).phoneNumber(userAccount.getPhoneNumber())
                    .jobNumber(userAccount.getJobNumber()).avatar(userAccount.getAvatar())
                    .isDeleted(false)
                    .build();
            fleaUserRepository.save(fleaUser);
            return ResponseResult.success("用户数据添加成功");
        } else {
            return ResponseResult.success(fleaUserVos);
        }
    }

    @Override
    public ResponseResult updateFleaUser(UpdateFleaUserDto updateFleaUserDto) {
        Optional<FleaUser> fleaUserOptional = fleaUserRepository.findById(updateFleaUserDto.getPkFleaUserId());
        FleaUser fleaUser = FleaUser.builder()
                .isDeleted(fleaUserOptional.get().getIsDeleted()).jobNumber(fleaUserOptional.get().getJobNumber())
                .avatar(updateFleaUserDto.getAvatar()).phoneNumber(updateFleaUserDto.getPhoneNumber())
                .username(fleaUserOptional.get().getUsername()).nickname(updateFleaUserDto.getNickname())
                .sex(updateFleaUserDto.getSex()).pkFleaUserId(updateFleaUserDto.getPkFleaUserId())
                .build();
        fleaUserRepository.saveAndFlush(fleaUser);
        return ResponseResult.success("用户数据修改成功");
    }

    @Override
    public ResponseResult findGoodsByUserId(FleaUserIdDto fleaUserIdDto) {
        Pageable pageable = PageRequest.of(fleaUserIdDto.getCurrentPage() - 1, fleaUserIdDto.getPageSize());
        return ResponseResult.success(fleaUserRepository.selectGoodsByUserId(fleaUserIdDto.getPkFleaUserId(), pageable));
    }

    @Override
    public ResponseResult findOrderByUserId(FleaUserIdDto fleaUserIdDto) {
        Pageable pageable = PageRequest.of(fleaUserIdDto.getCurrentPage() - 1, fleaUserIdDto.getPageSize());
        return ResponseResult.success(fleaUserRepository.selectOrOrdersByUserId(fleaUserIdDto.getPkFleaUserId(), pageable));
    }

    @Override
    public ResponseResult findById(FleaUserIdDto userIdDto) {
        return ResponseResult.success(fleaUserRepository.findById(userIdDto.getPkFleaUserId()));
    }
}
