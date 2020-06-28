package com.niit.soft.client.api.service;


import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.AddressBook;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
public interface AddressBookService {

    /**
     * 根据用户id获取通讯录信息
     *
     * @param userId
     * @return
     */
    List<AddressBook> getAddressBookByUserId(String userId);

    /**
     * 根据用户手机号获取通讯录信息
     *
     * @param phoneNumber
     * @return
     */
    List<AddressBook> findAddressBookByPhoneNumber(String phoneNumber);

    /**
     * 新增联系人
     *
     * @param addressBook
     */
    void insertAddressBook(AddressBook addressBook);

    /**
     * 根据id删除通讯录信息
     *
     * @param id
     */
    void deleteAddressBookById(long id);

    /**
     * 根据id更新通讯录信息
     *
     * @param addressBook
     */
    void updateAddressBookById(AddressBook addressBook);


    /**
     * 分页查询通讯里所有好友
     *
     * @param pageDto
     * @return
     */
    ResponseResult findAllByPage(PageDto pageDto);
}
