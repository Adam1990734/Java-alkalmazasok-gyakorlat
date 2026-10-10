package com.example.javaalkalmazasokgyakorlat.repository;

import com.example.javaalkalmazasokgyakorlat.model.inventor.Inventor;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorCountDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorTimeLineDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventorRepository extends JpaRepository<Inventor, Long> {
    @Query("""
        select new com.example.javaalkalmazasokgyakorlat.model.inventor.InventorCountDto(
            i.name,
            count(inv)
        )
        from Inventor i
        left join i.inventions inv
        group by i.id, i.name
    """)
    List<InventorCountDto> getStatistics();

    @Query("""
        select new com.example.javaalkalmazasokgyakorlat.model.inventor.InventorTimeLineDto(
            i.name,
            i.bornat,
            i.diedat
        )
        from Inventor i
    """)
    List<InventorTimeLineDto> getTimeLineData();
}
