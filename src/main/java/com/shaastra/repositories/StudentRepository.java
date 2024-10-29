package com.shaastra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long>
{

//	private final EntityManager entityManager;
//	
//	public StudentRepository (EntityManager entityManager)
//	{
//		this.entityManager = entityManager;
//	}
	
	/*public List<Student> findStudentsWithPartition(int partitionValue) {
        String sql = "SELECT * FROM students_partitioned WHERE partition_column = ?";
        Query query = entityManager.createNativeQuery(sql, Student.class);
        query.setParameter(1, partitionValue);
      */ 
}
