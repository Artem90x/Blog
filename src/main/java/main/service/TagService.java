package main.service;


import main.api.response.TagsResponse;

public interface TagService {

    TagsResponse getTags(String query);
}
