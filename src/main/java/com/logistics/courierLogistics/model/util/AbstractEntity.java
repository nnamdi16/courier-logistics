package com.logistics.courierLogistics.model.util;

import java.io.Serializable;


public interface AbstractEntity<PK extends Serializable> {


    /**
     * @return the primary key as String
     */
    String getIdx();

    /**
     * @return the primary key as string
     */
    PK getPk();


    String getIde();

    /**
     * Helper method to know whether the primary key is set or not.
     *
     * @return true if the primary key is set, false otherwise
     */
    boolean isIdSet();




}
