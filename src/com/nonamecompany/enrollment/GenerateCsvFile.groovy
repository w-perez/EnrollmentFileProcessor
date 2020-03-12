package com.nonamecompany.enrollment

/**
 * This script will generate a csv files populated with enrollment data generated from the following arrays
 * for each property. This file can then be used to test the ProcessCSVFile script.
 *
 * @param csvFile Name of the file to be processed
 * @return
 */
def generateCsvFile(csvFile) {
    int entriesToGenerate = 60
    def insurancesCompanies = Enrollee.insurancesCompanies
    def firstNames = ["Jennifer", "Han", "Peter", "Monica", "Bernadette", "Marty",
                      "Kim", "Raven", "Billy", "Kelly", "Luke", "Ferris"]
    def lastName = ["Smith", "Hale", "Skywalker", "Symone", "Perez", "Solo",
                    "Parker", "Madison", "Bueller", "McFly", "Geller", "Rostenkowski"]
    def versions = (1..10).collect()
    File file = new File(csvFile)
    // This is to ensure we start with an empty file
    file.write("")

    int id = 0;
    Random rnd = new Random()

    (1..entriesToGenerate).each {

        int f = rnd.nextInt(11)
        int l = rnd.nextInt(11)
        int c = rnd.nextInt(5)
        int v = rnd.nextInt(9)

        file.append("${id},${firstNames[f]},${lastName[l]},${insurancesCompanies[c]},${versions[v]}\n")
        // This helps up test duplicate ids in the ProcessCSVFile script
        id = (id == 5) ? 0 : id + 1
    }
}
