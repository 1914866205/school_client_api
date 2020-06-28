package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.AddressBook;
import com.niit.soft.client.api.domain.model.UserAccount;
import com.niit.soft.client.api.repository.AddressBookRepository;
import com.niit.soft.client.api.repository.UserAccountRepository;
import com.niit.soft.client.api.service.AddressBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Resource
    private AddressBookRepository addressBookRepository;
    @Resource
    private UserAccountRepository userAccountRepository;

    @Override
    public List<AddressBook> getAddressBookByUserId(String userId) {
        return addressBookRepository.getAddressBookByUserId(userId);
    }

    @Override
    public List<AddressBook> findAddressBookByPhoneNumber(String phoneNumber) {
        return addressBookRepository.findAddressBookByPhoneNumber(phoneNumber);
    }

    @Override
    public void insertAddressBook(AddressBook addressBook) {
        UserAccount user = userAccountRepository.findSysUserAccountByPkUserAccountId(addressBook.getUserId());
        System.out.println(user);
        addressBook.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
        addressBook.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
        addressBook.setIsDeleted(false);
        addressBookRepository.save(addressBook);
    }

    @Override
    public void deleteAddressBookById(long id) {
        addressBookRepository.deleteAddressBookById(id);
    }

    @Override
    public void updateAddressBookById(AddressBook addressBook) {
        addressBookRepository.updateAddressBookById(addressBook);
    }

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.ASC,
                "pkAddressBookId");
        Page<AddressBook> addressBooks = addressBookRepository.getAll(pageable);
        return ResponseResult.success(addressBooks.getContent());
    }
}
