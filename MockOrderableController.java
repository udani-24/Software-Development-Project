/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.emrapi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.module.webservices.rest.SimpleObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //tells Spring this class is a web controller
@RequestMapping(
    method = RequestMethod.GET, //defines the URL pattern and says this endpoint accepts GET.
    value = "/rest/**/emrapi/mock-orderable" //defines the URL pattern and says this endpoint accepts GET 
) // ** for rest version
public class MockOrderableController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SimpleObject search(@RequestParam("term") String term) { // @requestparam - takes the search text from: ?term=amoxicillin
        List<SimpleObject> results = new ArrayList<>();

        String searchTerm = term.toLowerCase();

        if ("amoxicillin".contains(searchTerm)) {
            SimpleObject drug = new SimpleObject();

            drug.add("uuid", "mock-drug-001");
            drug.add("name", "Amoxicillin 500 mg");
            drug.add("strength", "500 mg");
            drug.add("form", "Tablet");

            results.add(drug);
        }

        if ("amlodipine".contains(searchTerm)) {
            SimpleObject drug = new SimpleObject();

            drug.add("uuid", "mock-drug-002");
            drug.add("name", "Amlodipine 5 mg");
            drug.add("strength", "5 mg");
            drug.add("form", "Tablet");

            results.add(drug);
        }

        SimpleObject response = new SimpleObject();

        response.add("searchTerm", term);
        response.add("results", results);

        return response;
    }
}
