package com.acacia.common.dao.pojo;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.acacia.common.db.IData;


@MappedSuperclass
@SuppressWarnings("serial")
public class BaseData implements IData {
    /**
     * Constructor.
     */
    public BaseData() {
    }

    @Override
    public String toString() {
         return ReflectionToStringBuilder.toString(this,
         ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
