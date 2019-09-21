package com.isa.zajavieni.mapper;

import com.isa.zajavieni.dto.OrganizerDto;
import com.isa.zajavieni.entity.Organizer;
import com.isa.zajavieni.servlet.LoggerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class OrganizerDtoMapper {

    private Logger logger = LoggerFactory.getLogger(LoggerServlet.class.getName());

    public OrganizerDto mapOrganizerToDto(Organizer organizer){
        OrganizerDto organizerDto = new OrganizerDto();
        organizerDto.setId(organizer.getId());
        organizerDto.setName(organizer.getDesignation());

        logger.info("Map event entity id: {} to dto", organizer.getId());
        return organizerDto;
    }
}
