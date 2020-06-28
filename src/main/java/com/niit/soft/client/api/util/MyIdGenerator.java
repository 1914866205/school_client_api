package com.niit.soft.client.api.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-06-16 10:09
 */

public class MyIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return "order-" + UUID.randomUUID();   //生成格式：order-87cc2eae-c0dd-4c05-beef-69396391bab7
    }
}
