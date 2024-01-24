package com.gilbut.gilbut.repository;

import com.gilbut.gilbut.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
