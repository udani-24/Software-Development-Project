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

import java.util.List;

import org.junit.Test;
import org.openmrs.module.webservices.rest.SimpleObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockOrderableControllerTest {

    private final MockOrderableController controller = new MockOrderableController();

    @Test
    public void shouldReturnMatchingOrderablesIgnoringCase() {
        SimpleObject response = controller.search("AMO");

        assertEquals("AMO", response.get("searchTerm"));
        List<?> results = (List<?>) response.get("results");
        assertEquals(1, results.size());

        SimpleObject result = (SimpleObject) results.get(0);
        assertEquals("mock-drug-001", result.get("uuid"));
        assertEquals("Amoxicillin 500 mg", result.get("name"));
        assertEquals("500 mg", result.get("strength"));
        assertEquals("Tablet", result.get("form"));
    }

    @Test
    public void shouldReturnNoOrderablesWhenThereIsNoMatch() {
        SimpleObject response = controller.search("not-a-drug");

        List<?> results = (List<?>) response.get("results");
        assertTrue(results.isEmpty());
    }
}