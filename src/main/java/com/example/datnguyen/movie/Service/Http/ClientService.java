package com.example.datnguyen.movie.Service.Http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apiMovie",url = "https://phimapi.com/")
public interface ClientService {
    @RequestMapping(method = RequestMethod.GET,value = "danh-sach/phim-moi-cap-nhat")
    Object getMovie(@RequestParam(defaultValue = "1",required = false) int page);

    @RequestMapping(method = RequestMethod.GET,value = "v1/api/tim-kiem")
    Object searchMovie(@RequestParam(defaultValue = "9",required = false) int limit,
                       @RequestParam String keyword);
}
