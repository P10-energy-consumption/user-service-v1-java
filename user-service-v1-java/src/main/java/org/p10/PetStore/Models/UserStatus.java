package org.p10.PetStore.Models;

import com.google.gson.annotations.SerializedName;

public enum UserStatus {
    @SerializedName("0")
    Active,
    @SerializedName("1")
    Inactive
}
