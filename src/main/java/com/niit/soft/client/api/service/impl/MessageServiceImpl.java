package com.niit.soft.client.api.service.impl;

import com.niit.soft.client.api.common.ResponseResult;
import com.niit.soft.client.api.domain.dto.PageDto;
import com.niit.soft.client.api.domain.model.Message;
import com.niit.soft.client.api.repository.MessageRepository;
import com.niit.soft.client.api.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Tao
 * @version 1.0
 * @ClassName MessageServiceImpl
 * @Description TODO
 * @date 2020-05-26 15:46
 **/
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageRepository messageRepository;

    @Override
    public ResponseResult findAllByPage(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getCurrentPage(),
                pageDto.getPageSize(),
                Sort.Direction.DESC,
                "pkMessageId");
        Page<Message> messagePage = messageRepository.findAll(pageable);
        return ResponseResult.success(messagePage.getContent());
    }

}
