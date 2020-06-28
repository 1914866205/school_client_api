package com.niit.soft.client.api.repository;

import com.niit.soft.client.api.domain.model.AddressBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    /**
     * 根据userId查询通讯录
     *
     * @param userId
     * @return
     */
    @Query("SELECT u FROM AddressBook u " +
            "WHERE u.userId=?1 AND u.isDeleted= false ")
    List<AddressBook> getAddressBookByUserId(String userId);

    /***
     * 根据手机号查询通讯录
     * @param phoneNumber
     * @return
     */
    @Query("SELECT u FROM AddressBook u WHERE u.phoneNumber=?1 AND u.isDeleted= false ")
    List<AddressBook> findAddressBookByPhoneNumber(String phoneNumber);

    /**
     * 根据id修改联系人信息
     *
     * @param addressBook
     */
    @Modifying
    @Transactional
    @Query("UPDATE AddressBook SET remark=:#{#addressBook.remark} WHERE pkAddressBookId=:#{#addressBook.pkAddressBookId}")
    void updateAddressBookById(@Param("addressBook") AddressBook addressBook);

    /**
     * 根据备注模糊查询
     *
     * @return
     */
    List<AddressBook> findAddressBookByRemarkContaining(String keywords);

    /**
     * 逻辑删除
     *
     * @param id
     */
    @Modifying
    @Transactional(timeout = 10, rollbackFor = RuntimeException.class)
    @Query("update AddressBook v set v.isDeleted = true where v.pkAddressBookId in ?1")
    void deleteAddressBookById(Long id);

    /**
     * 自定义分页查询
     *
     * @param pageable
     * @return
     */
    @Query("select u from AddressBook u")
    Page<AddressBook> getAll(Pageable pageable);


}
