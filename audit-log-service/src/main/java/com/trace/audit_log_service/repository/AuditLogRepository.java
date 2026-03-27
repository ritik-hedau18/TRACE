package com.trace.audit_log_service.repository;

import com.trace.audit_log_service.document.AuditLog;
import com.trace.audit_log_service.enums.TransactionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    List<AuditLog> findBySenderEmail(String senderEmail);

    List<AuditLog> findByFinalStatus(TransactionStatus status);

}
