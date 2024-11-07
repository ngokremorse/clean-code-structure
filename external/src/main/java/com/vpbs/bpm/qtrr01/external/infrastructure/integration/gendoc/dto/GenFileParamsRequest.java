package com.vpbs.bpm.bond.external.infrastructure.integration.gendoc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenFileParamsRequest {
    private String contractNo;
    private String referenceInfo;
    private String supplierCode;
    private String staffId;
    private String staffName;
    private String staffDepartment;
    private String staffUnit;
    private String debitBankAccountNo;
    private String debitBankName;
    private String debitBankAccountName;
    private String creditBankName;
    private String creditBankAccountNo;
    private String creditBankAccountName;
    private String amountInWord;
    private String transferAmount;
    private String advancedAmount;
    private String additionalPayAmount;
    private String additionalRequestAmount;
    private String detailOfPayment;
    private String paymentDate;
    private String accountingUnit;
    private String report;
    private String receipt;
    private String contract;
    private String acceptanceReport;
    private String otherDocuments;
    private String requestPayAmount;
    private String otherAdvancedAmount;
    private String totalPayAmount;
    private String leaderStaffName;
    private String authorStaffName;
    private String approvedPayAmount;
    private String approvedPayNote;
    private String accountant;
    private String headAccountant;
    private String ceoName;
    private String createDay;
    private String createMonth;
    private String createYear;
    private String approveDay;
    private String approveMonth;
    private String approveYear;
    private boolean paymentCreditCard;
}
