package com.isa.zajavieni.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.zajavieni.jsonclasses.Category;
import com.isa.zajavieni.jsonclasses.Event;
import com.isa.zajavieni.jsonclasses.Organizer;
import com.isa.zajavieni.jsonclasses.Place;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DataParseService {
  private ObjectMapper objectMapper = new ObjectMapper();

  public List<Event> parseEventsFromApi(String events) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(events);

    return objectMapper.readValue(jsonNode.toString(),
        new TypeReference<List<Event>>() {
        });
  }

  public List<Category> parseCategoriesFromApi(String categories) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(categories);

    return objectMapper.readValue(jsonNode.toString(),
        new TypeReference<List<Category>>() {
        });
  }

  public List<Place> parsePlacesFromApi(String places) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(places);

    return objectMapper.readValue(jsonNode.toString(),
        new TypeReference<List<Place>>() {
        });
  }

  public List<Organizer> parseOrganizersFromApi(String organizers) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(organizers);

    return objectMapper.readValue(jsonNode.toString(),
        new TypeReference<List<Organizer>>() {
        });
  }
}
