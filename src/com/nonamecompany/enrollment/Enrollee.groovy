package com.nonamecompany.enrollment

/**
 * Class used to hold enrollee data
 */
@groovy.transform.Sortable
@groovy.transform.ToString(includeNames = true)
class Enrollee {

    def static insurancesCompanies = ["UnitedHealth", "Kaiser Foundation", "Cigna Health", "BCBS", "Metropolitan"]

    String firstName, lastName, company
    int userId, versionNumber
}

