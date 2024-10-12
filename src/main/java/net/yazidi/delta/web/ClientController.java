package net.yazidi.delta.web;

import java.util.List;
import java.util.Optional;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.service.ClientService;

@Controller
@RequestMapping("/api/clients")
public class ClientController extends AbstractController<Client,Long>{

    @Autowired
    private ClientService clientService;


    @Override
    IService getService() {
        return this.clientService;
    }
}
