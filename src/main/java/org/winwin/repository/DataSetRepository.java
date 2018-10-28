package org.winwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.winwin.model.DataSet;
import org.winwin.model.Revenue;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet, Long> {

}