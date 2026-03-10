package io.github.ninobomba.utils.spring.data;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "STATUS", length = 10, nullable = false)
    @NotBlank(message = "Status cannot be blank")
    private String status = "ENABLED";

    @CreatedBy
    @Column(name = "CREATED_BY", length = 50, nullable = false, updatable = false)
    @NotBlank(message = "Created by cannot be blank")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @NotNull(message = "Created date cannot be null")
    private Instant createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", length = 50, nullable = false)
    @NotBlank(message = "Last modified by cannot be blank")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    @NotNull(message = "Last modified date cannot be null")
    private Instant lastModifiedDate;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = "ENABLED";
        }
        if (this.createdBy == null) {
            this.createdBy = "SYSTEM";
        }
        if (this.lastModifiedBy == null) {
            this.lastModifiedBy = "SYSTEM";
        }
        if (this.createdDate == null) {
            this.createdDate = Instant.now();
        }
        if (this.lastModifiedDate == null) {
            this.lastModifiedDate = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (this.lastModifiedBy == null) {
            this.lastModifiedBy = "SYSTEM";
        }
        this.lastModifiedDate = Instant.now();
    }

}
