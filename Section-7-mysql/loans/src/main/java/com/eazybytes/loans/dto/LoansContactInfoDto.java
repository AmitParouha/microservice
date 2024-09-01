package com.eazybytes.loans.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
// this class will be final we can't change its value
@ConfigurationProperties(prefix = "account")
public class LoansContactInfoDto{
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
