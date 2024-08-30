package com.eazybytes.loans.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// this class will be final we can't change its value
@ConfigurationProperties(prefix = "account")
public record LoansContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
