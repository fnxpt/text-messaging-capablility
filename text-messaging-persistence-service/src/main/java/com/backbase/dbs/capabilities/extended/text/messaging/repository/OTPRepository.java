package com.backbase.dbs.capabilities.extended.text.messaging.repository;

import com.backbase.dbs.capabilities.extended.text.messaging.domain.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Extends the JpaRepository class to add custom find methods
 */
@Repository
public interface OTPRepository extends JpaRepository<OTP, String> {
    /**
     * Find an OTP by id
     *
     * @param id OTP id
     * @return OTP
     */
    OTP findById(String id);
}
