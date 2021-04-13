package com.omelnur.roya.royaServiceApp.auth.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author michael wagih
 */

@Data
public class RolesRequest {

    @NotNull(message = "name not null")
    @Column(length = 20)
    private String name;

    @NotNull(message = "name not null")
    private Set<Long> permissionIdsLongs;
}
