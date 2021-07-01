package com.yintju03.example.polygene.ms1.user.has;

import org.apache.polygene.api.property.Property;
import org.apache.polygene.library.constraints.annotation.NotEmpty;

public interface HasConfirmedPassword {
    @NotEmpty
    Property<String> confirmedPassword();
}
