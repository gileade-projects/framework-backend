package gumga.framework.presentation.api;

import gumga.framework.application.GumgaService;
import gumga.framework.application.tag.GumgaTagDefinitionService;
import gumga.framework.domain.tag.GumgaTagDefinition;
import gumga.framework.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gumgatagdefinition")
public class GumgaTagDefinitionAPI extends GumgaAPI<GumgaTagDefinition, Long> {

    @Autowired
    public GumgaTagDefinitionAPI(GumgaService<GumgaTagDefinition, Long> service) {
        super(service);
    }

    @Override
    public GumgaTagDefinition load(@PathVariable Long id) {
        return ((GumgaTagDefinitionService) service).loadGumgaTagDefinitionFat(id);
    }

}
