package com.isa.zajavieni.service;

import com.isa.zajavieni.dto.EventDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SearchService {

    @EJB
    private EventDtoService eventDtoService;

    public List<EventDto> searchEvents(String phrase) {
        return eventDtoService.searchEvents(phrase);
    }
}