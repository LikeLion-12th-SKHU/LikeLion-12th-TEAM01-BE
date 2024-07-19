package org.skhuton.fitpete.auth.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MemberInfo {

    private String id;

    private String nickname;
    @SerializedName("given_nickname")
    private Boolean givenNickname;

    private String name;
    @SerializedName("given_name")
    private Boolean givenName;

    private String email;
    @SerializedName("verified_email")
    private Boolean verifiedEmail;
}
