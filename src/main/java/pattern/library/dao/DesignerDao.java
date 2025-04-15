package pattern.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pattern.library.entity.Designer;

public interface DesignerDao extends JpaRepository<Designer, Long> {

}
