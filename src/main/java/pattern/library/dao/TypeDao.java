package pattern.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pattern.library.entity.Type;

public interface TypeDao extends JpaRepository<Type, Long> {

}
