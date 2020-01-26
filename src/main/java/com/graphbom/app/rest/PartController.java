package com.graphbom.app.rest;

import com.graphbom.app.model.Part;
import com.graphbom.app.output.Result;
import com.graphbom.app.service.PartBOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services/partservice")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PartController {

    @Autowired
    private PartBOMService partBOMService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping(value = "/findparts")
    public Result<String, Iterable<Part>> findParts() {
        Iterable<Part> parts = partBOMService.findParts();
        Result<String,Iterable<Part>> result = new Result<>();
        result.setMessage("OK");
        result.setData(parts);
        return result;
    }

    @GetMapping(value="/loadbom")
    public Map<String, List<Part>> loadBom(@RequestParam(name ="type") String type,
                                           @RequestParam(name="name") String name,
                                           @RequestParam(name="rev") Long rev){
        return partBOMService.loadBOM(type,name,rev);
    }
    @PostMapping(value="/create")
    public Result createPart(@RequestParam String type,
                             @RequestParam String name,
                             @RequestParam Long quantity){

        Part newPart = new Part();
        newPart.setName(name);
        newPart.setType(type);
        newPart.setRev(1L); // for new parts the rev starts from 1, in each update call, the rev increments by 1
        newPart.setQuantity(quantity);
        System.out.println("Part creation in progress12355544444");
        return this.partBOMService.createPart(newPart);


    }


}
