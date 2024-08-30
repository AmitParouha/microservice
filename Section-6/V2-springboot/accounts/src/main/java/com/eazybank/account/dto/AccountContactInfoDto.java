package com.eazybank.account.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// this class will be final we can't change its value
//@ConfigurationProperties(prefix = "account")
//public record AccountContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
//}

// record will create all the field as final. we can't change its value

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "account")
public class AccountContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}