
package com.resolveit.repository;

import com.resolveit.model.User;
import com.resolveit.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
}
