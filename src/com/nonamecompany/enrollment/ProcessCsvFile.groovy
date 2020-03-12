package com.nonamecompany.enrollment

/**
 * This function will generate new files that represent each companies' enrollees. Within each of the company files,
 * the enrollees will be sorted by last and first name in ascending order. Also for any duplicate ids, we will take
 * the entry that has the highest version.
 *
 * @param csvFile Name of the file to be processed
 * @return
 */
def processCsvFile(csvFile){
    def enMap = Enrollee.insurancesCompanies.collectEntries{
        [(it): [:]]
    }
    File file = new File(csvFile)
    file.readLines().each{ line ->
        fields = line.split(',')
        Enrollee enrollee = new Enrollee(userId:fields[0].toInteger(), firstName:fields[1], lastName:fields[2], company:fields[3], versionNumber:fields[4].toInteger())

        // This check will determine if the current user id is already in the map
        if(enMap[enrollee.company].containsKey(enrollee.userId)){
            // Determine if we are going to overwrite the current enrollee data by comparing the version numbers
            if(enrollee.versionNumber > enMap[enrollee.company][enrollee.userId].versionNumber) {
                enMap[enrollee.company][enrollee.userId] = enrollee
            }
        }
        else
        {
            enMap[enrollee.company][enrollee.userId] = enrollee
        }
    }
    enMap.each{ key, value ->
        println "Generating ${key}_enrollees.csv file..."
        File companyFile = new File("${key}_enrollees.csv")
        // This is to ensure we start with an empty file
        companyFile.write("")
        List<Enrollee> companyList = value.collect{ k, v -> v }
        companyList.sort { a, b -> a.lastName + a.firstName <=> b.lastName + b.firstName }
        companyList.each { Enrollee enrollee ->
            companyFile.append("${enrollee.userId},${enrollee.firstName} ${enrollee.lastName},${enrollee.company},${enrollee.versionNumber}\n")
        }
    }
}



