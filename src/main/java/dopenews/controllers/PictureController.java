package dopenews.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import dopenews.repository.PictureRepository;

@Controller
public class PictureController {
    @Autowired
    private PictureRepository pictureRepository;

    @ResponseBody
    @GetMapping(path = "/image/{id}", produces = "image/png")
    public byte[] getImage(@PathVariable long id) {
        return pictureRepository.findOne(id).getData();
    }
}
