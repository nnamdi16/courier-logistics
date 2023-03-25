package com.logistics.courierLogistics.model.util;

import com.logistics.courierLogistics.utils.AppUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;


@MappedSuperclass
@Data
public class AbstractBaseEntity implements Serializable, AbstractEntity<String> {
    private static  final Logger log = LoggerFactory.getLogger(AbstractEntity.class);
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 40)
    protected String id;

    @LastModifiedDate
    @Column(name = "updated_date")
    private ZonedDateTime lastModifiedDate;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 40)
    private String createdBy;


    @Column(name = "updated_by", length = 40)
    @LastModifiedBy
    private String lastModifiedBy;

    @PreUpdate
    public void abstractPreUpdate() {
        lastModifiedDate = ZonedDateTime.now();
    }

    @PrePersist
    public void abstractPrePersist() {
        log.debug("about to run abstractPrePersist method");
        createdDate = ZonedDateTime.now();
        lastModifiedDate = ZonedDateTime.now();
        if (AppUtil.stringIsNullOrEmpty(id)) {
            id = ID.generateUUIDString();
        }
        log.debug("finished running abstractPrePersist method");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 71 * hash + Objects.hashCode(this.createdDate);
        hash = 71 * hash + Objects.hashCode(this.createdBy);
        hash = 71 * hash + Objects.hashCode(this.lastModifiedBy);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        if (!Objects.equals(lastModifiedDate, that.lastModifiedDate)) return false;
        if (!Objects.equals(createdDate, that.createdDate)) return false;
        if (!Objects.equals(createdBy, that.createdBy)) return false;
        return Objects.equals(lastModifiedBy, that.lastModifiedBy);

    }


    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id='" + id + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }





    @Transient
    @Override
    public String getIdx() {
        return id;
    }

    @Override
    public String getPk() {
        return id;
    }

    @Override
    public String getIde() {
        String idd = (id == null) ? "" : id;
        return EncDec.INSTANCE.encryptAndEncode(idd);
    }

    @Override
    public boolean isIdSet() {
        return id != null && !id.isEmpty();
    }



}
